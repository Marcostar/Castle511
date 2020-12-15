package com.sagycorp.castle511.ui.home

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sagycorp.castle511.R
import com.sagycorp.castle511.adapter.TrafficDataAdapter
import com.sagycorp.castle511.data.PagesItem
import com.sagycorp.castle511.data.TrafficData
import com.sagycorp.castle511.databinding.HomeFragmentBinding
import kotlin.text.StringBuilder

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater, container, false)
        loadingMessageVisible()
        setHasOptionsMenu(true)
        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getTrafficDataList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                loadingMessageVisible()
            }
            else loadingMessageDisable()
            it?.let {
                binding.recyclerView.apply {
                    adapter = TrafficDataAdapter(it, HomeFragment())
                }
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(!it) binding.loadingText.text = getString(R.string.fetching_error)
                else binding.loadingText.text = getString(R.string.fetching_data);
            }
        })

    }

    fun homeToDetailsScreen(v: View?, trafficData: TrafficData){
        if (v != null) {
            val message:String = convertToString(trafficData.display?.pages)
            val bundle = bundleOf("Message" to message)
            Navigation.findNavController(v).navigate(R.id.detailsFragment,bundle)
        }
    }

    private fun convertToString(pages: List<PagesItem?>?):String{

        val value = StringBuilder()
        if (pages != null) {
            for (page in pages)
                value.append(page?.lines?.joinToString(" ","\n","\n", -1,"..."))
        }
        return value.toString()
    }

    private fun loadingMessageVisible(){
        binding.loadingText.visibility = View.VISIBLE
    }

    private fun loadingMessageDisable(){
        binding.loadingText.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.refresh -> {
                loadingMessageVisible()
                binding.loadingText.text = getString(R.string.fetching_data);
                viewModel.downLoadTrafficData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}