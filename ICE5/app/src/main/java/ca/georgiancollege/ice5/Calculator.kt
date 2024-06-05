package ca.georgiancollege.ice5

import ca.georgiancollege.ice5.databinding.ActivityMainBinding

class Calculator(dataBinding: ActivityMainBinding)
{
    private val binding: ActivityMainBinding = dataBinding

    init {
        createBindings()
    }

    private fun createBindings(): Unit
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

    private fun operandHandler(tag: String)
    {
        binding.resultTextView.text = tag
    }

    private fun operatorHandler(tag: String)
    {
        binding.resultTextView.text = tag
    }
}