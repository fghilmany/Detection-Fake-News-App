package com.fghilmany.newsdetection.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fghilmany.newsdetection.R
import com.fghilmany.newsdetection.databinding.FragmentResultBinding
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class ResultFragment : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return  binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val result = ResultFragmentArgs.fromBundle(arguments as Bundle).result
        if (result != null){
            var fake = result.predictValue[0][0]
            var fact = result.predictValue[0][1]

            if (fake > fact){
                binding.groupFake.visibility = View.VISIBLE
                binding.groupFact.visibility = View.GONE
            }else{
                binding.groupFake.visibility = View.GONE
                binding.groupFact.visibility = View.VISIBLE
            }

            val decimalFormat = DecimalFormat("#.##")
            fake = getSimpleDecimal(fake)
            fact = getSimpleDecimal(fact)

            binding.tvFactIndication.text = "${(fact*100).toInt()} %"
            binding.tvFakeIndication.text = "${(fake*100).toInt()} %"

            binding.btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_resultFragment_to_dashboardFragment)
            }
        }
    }

    fun getSimpleDecimal(value: Double): Double{
        val sinificantNumber = 2
        val temp = 10.0.pow(sinificantNumber.toDouble())
        val result = (value * temp).roundToInt().toDouble() / temp
        return result
    }

}