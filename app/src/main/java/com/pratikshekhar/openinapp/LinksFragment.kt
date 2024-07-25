package com.pratikshekhar.openinapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.pratikshekhar.openinapp.adapter.InfoListAdapter
import com.pratikshekhar.openinapp.adapter.linkListAdapter
import com.pratikshekhar.openinapp.database.AuthorizationToken
import com.pratikshekhar.openinapp.databinding.FragmentLinksBinding
import com.pratikshekhar.openinapp.di.SparkLineChart
import com.pratikshekhar.openinapp.model.Api_Response
import com.pratikshekhar.openinapp.model.Link
import com.pratikshekhar.openinapp.uimodel.InfoListData
import com.pratikshekhar.openinapp.uimodel.LinkListItemData
import com.pratikshekhar.openinapp.viewmodels.LinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class LinksFragment : Fragment() {
    private var _binding: FragmentLinksBinding? = null
    lateinit var chartStyle: SparkLineChart
    private val linkViewModel: LinkViewModel by viewModels()
    private val apiResponse by lazy{
            MutableLiveData<Api_Response>(Api_Response())

    }
    private val selectedTab: MutableLiveData<tab> by lazy {
        MutableLiveData<tab>(tab.TOP_LINKS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLinksBinding.inflate(inflater, container, false)

        val view = _binding!!.root
        val token =AuthorizationToken(requireActivity()).getToken()
        linkViewModel.getData("Bearer ${token}")
        chartStyle = SparkLineChart(requireContext())
        val CHART_LABEL = "DAY_CHART"
        val dayData = mutableListOf<Entry>()
        val _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))

        _binding!!.greetingText.text = getGreeting()

        val lineDataSet: LiveData<LineDataSet> = _lineDataSet
        dayData.add(Entry(0f, 5f))
        dayData.add(Entry(10f, 40f))
        dayData.add(Entry(20f, 70f))
        dayData.add(Entry(30f, 80f))
        dayData.add(Entry(40f, 100f))
        dayData.add(Entry(50f, 70f))
        dayData.add(Entry(600f, 30f))
        dayData.add(Entry(70f, 60f))
        dayData.add(Entry(80f, 50f))
        dayData.add(Entry(90f, 80f))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)
        lineDataSet.observe(viewLifecycleOwner) {
            chartStyle.styleLineDataSet(lineDataSet.value!!)
            _binding!!.lineChart.data = LineData(lineDataSet.value)
            _binding!!.lineChart.invalidate()
            chartStyle.styleChart(_binding!!.lineChart)


        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                linkViewModel.data.collect { state ->
                    apiResponse.value = state
                    infoRecyclerViewSetUp(state)

                    linkListViewSetup(state.data.top_links)
                }
            }
        }

        val observer = Observer<tab>{
            newValue->
            if(newValue==tab.TOP_LINKS)   linkListViewSetup(apiResponse.value!!.data.top_links)
            else linkListViewSetup(apiResponse.value!!.data.recent_links)

        }
selectedTab.observe(viewLifecycleOwner,observer)

        tabGroupSetup()

        return view
    }

    private fun linkListViewSetup(dataList: List<Link>) {
        val uiDataList = mutableListOf<LinkListItemData>()
        for (dataItem in dataList) {

            val uiDataListItem = LinkListItemData(
                imageUrl = dataItem.original_image,
                title = dataItem.title,
                clicks = dataItem.total_clicks.toString(),
                link = dataItem.smart_link.toString(),
                time = dataItem.created_at
            )
            Log.d("ans", uiDataListItem.toString())
            uiDataList.add(uiDataListItem)
        }
        val linkListViewAdapter = linkListAdapter(data = uiDataList, context = requireContext())
        _binding!!.linkList.layoutManager = LinearLayoutManager(requireContext())
        _binding!!.linkList.adapter = linkListViewAdapter
    }

    private fun infoRecyclerViewSetUp(state: Api_Response) {

        val list = listOf<InfoListData>(
            InfoListData(
                R.drawable.illustration_click,
                state.today_clicks.toString(),
                "Today’s clicks"
            ),

            InfoListData(
                R.drawable.illustration_location,
                if (state.top_location == "") "_" else state.top_location,
                "Top Location"
            ),
            InfoListData(
                R.drawable.illustration_globe,
                if (state.top_source == "") "_" else state.top_source,
                "Top source"
            ),

            )
        val infoCardAdapter = InfoListAdapter(list)
        _binding!!.infoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        _binding!!.infoList.adapter = infoCardAdapter

    }

    private fun tabGroupSetup() {
        _binding!!.tabGroup.setOnCheckedChangeListener { _, selectedButton ->
            _binding!!.topLinks.setTextColor(requireActivity().getColor(if (selectedButton == R.id.topLinks) R.color.white else R.color.black))
            _binding!!.recentLinks.setTextColor(requireActivity().getColor(if (selectedButton == R.id.recentLinks) R.color.white else R.color.black))
if(selectedButton == R.id.topLinks) selectedTab.value = tab.TOP_LINKS
            else selectedTab.value = tab.RECENT_LINKS

        }
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

    enum class tab {
        TOP_LINKS,
        RECENT_LINKS
    }
    private fun getGreeting(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}
