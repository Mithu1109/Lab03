package com.example.lab03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab03.databinding.FragmentCalculatorInputBinding

class CalculatorInputFragment : Fragment() {

    private var _binding: FragmentCalculatorInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener { calculate('+') }
        binding.btnSubtract.setOnClickListener { calculate('-') }
        binding.btnMultiply.setOnClickListener { calculate('*') }
        binding.btnDivide.setOnClickListener { calculate('/') }
    }

    private fun calculate(operator: Char) {
        val num1Str = binding.etNumber1.text.toString()
        val num2Str = binding.etNumber2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = num1Str.toDouble()
        val num2 = num2Str.toDouble()

        if (operator == '/' && num2 == 0.0) {
            Toast.makeText(requireContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> 0.0
        }

        openResultFragment(result)
    }

    private fun openResultFragment(result: Double) {
        val fragment = ResultFragment.newInstance(result)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}