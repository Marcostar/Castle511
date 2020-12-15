package com.sagycorp.castle511.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getTrafficDataList.observe(viewLifecycleOwner, Observer {
            it?.let {
                //adapter.submitList(it)
                Log.d("HomeFragment",it.size.toString())
                binding.recyclerView.apply {
                    adapter = TrafficDataAdapter(it, HomeFragment())
                }
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

}