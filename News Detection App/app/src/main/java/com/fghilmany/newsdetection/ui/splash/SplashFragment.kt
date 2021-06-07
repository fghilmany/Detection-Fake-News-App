package com.fghilmany.newsdetection.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.fghilmany.newsdetection.R
import com.fghilmany.newsdetection.databinding.FragmentSplashBinding
import com.fghilmany.newsdetection.helper.EspressoIdlingResource
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()

    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btt = AnimationUtils.loadAnimation(context, R.anim.btt)

        EspressoIdlingResource.increment()
        viewModel.liveData.observe(viewLifecycleOwner){
            when(it){
                is SplashState.MainActivity -> {
                    binding.tvTitle.visibility = View.VISIBLE
                    binding.tvTitle.startAnimation(btt)
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }

                is SplashState.TextStart -> {
                    findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
                }
            }
        }

    }

}