package com.sagycorp.castle511.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sagycorp.castle511.R
import com.sagycorp.castle511.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {


    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)


        if(!arguments?.getString("Message").isNullOrEmpty())
        {
            binding.message.text = arguments?.getString("Message")
        }else
        {
            binding.message.text = getString(R.string.no_message)
        }
    }

}