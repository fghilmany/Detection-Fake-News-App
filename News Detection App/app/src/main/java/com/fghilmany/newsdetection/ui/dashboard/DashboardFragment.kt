package com.fghilmany.newsdetection.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.fghilmany.movieapp.core.data.Resource
import com.fghilmany.newsdetection.databinding.FragmentDashboardBinding
import com.fghilmany.newsdetection.helper.EspressoIdlingResource
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModel()

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnConfirm.setOnClickListener {

            if (binding.edtNews.text == null || binding.edtNews.text.toString() == ""){
                binding.edtNews.error = "Must be Filled"
                binding.edtNews.requestFocus()
                return@setOnClickListener
            }

            viewModel.setNews(binding.edtNews.text.toString())

            EspressoIdlingResource.increment()
            viewModel.postNews().observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {
                        binding.loadingView.root.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.loadingView.root.visibility = View.GONE
                        val toResult = DashboardFragmentDirections.actionDashboardFragmentToResultFragment()
                        toResult.result = it.data?.value
                        findNavController().navigate(toResult)
                        EspressoIdlingResource.decrement()
                    }
                    is Resource.Error -> {
                        binding.loadingView.root.visibility = View.GONE
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}