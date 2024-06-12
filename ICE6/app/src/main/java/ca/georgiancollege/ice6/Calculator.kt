package ca.georgiancollege.ice6

import android.util.Log
import ca.georgiancollege.ice6.databinding.ActivityMainBinding

class Calculator(dataBinding: ActivityMainBinding)
{
    // Simple Computation: 1 + 2 = 3
    // operand1 [operator] operand2 [equalsOperator] result
    // LHS [operator] RHS [equalsOperator] result


    private val binding: ActivityMainBinding = dataBinding
    private var result: String
    private var currentOperand: String
    private var currentOperator: String

    init {
        result = ""
        currentOperand = ""
        currentOperator = ""
        createButtonReferences()
    }

    private fun createButtonReferences(): Unit
    {
        val operandButtons = arrayOf(
            binding.oneButton, binding.twoButton, binding.threeButton, binding.fourButton,
            binding.fiveButton, binding.sixButton, binding.sevenButton, binding.eightButton,
            binding.nineButton, binding.zeroButton, binding.decimalButton, binding.deleteButton,
            binding.plusMinusButton
        )

        val operatorButtons = arrayOf(
            binding.mulitplyButton, binding.plusButton, binding.percentButton, binding.minusButton,
            binding.equalsButton, binding.divideButton, binding.clearButton
        )

        operandButtons.forEach { it.setOnClickListener { operandHandler(it.tag as String) } }
        operatorButtons.forEach { it.setOnClickListener { operatorHandler(it.tag as String) } }
    }

    private fun operandHandler(tag: String): Unit
    {
        when(tag)
        {
            "." -> {
                if(!binding.resultTextView.text.contains("."))
                {
                    result += if(result.isEmpty()) "0." else "."

                    binding.resultTextView.text = result
                }
            }
            "Delete" -> {
                result = result.dropLast(1)

                binding.resultTextView.text = if(result.isEmpty() || result=="-") "0" else result
            }
            "Plus_Minus" -> {
                if(result.startsWith("-"))
                {
                    result = result.substring(1)
                    binding.resultTextView.text = result
                }
                else
                {
                    if(result.isNotEmpty())
                    {
                        result = "-".plus(result)
                        binding.resultTextView.text = result
                    }
                }
            }
            else -> {
                if(binding.resultTextView.text == "0")
                {
                    result = tag
                }
                else
                {
                    result += tag
                }
                binding.resultTextView.text = result
            }
        }
    }

    private fun operatorHandler(tag: String): Unit
    {
        if(tag != "Clear")
        {
            if(currentOperand.isNotEmpty())
            {
                when(currentOperator)
                {
                    "Plus" -> add()
                }
            }
            else
            {
                currentOperand = binding.resultTextView.text.toString()
                result = ""
                binding.resultTextView.text = ""
            }
            currentOperator = tag
        }
        else
        {
            clear()
        }

    }

    private fun clear(): Unit
    {
        result = ""
        currentOperand = ""
        currentOperator = ""
        binding.resultTextView.text = "0"
    }

    private fun add(): Unit
    {
        if(currentOperand.contains(".") || result.contains("."))
        {
            result = (currentOperand.toFloat() + result.toFloat()).toString()
        }
        else
        {
            result = (currentOperand.toInt() + result.toInt()).toString()
        }
        // TODO: Remove .0 if the result should be an Int
        binding.resultTextView.text = result
    }
}