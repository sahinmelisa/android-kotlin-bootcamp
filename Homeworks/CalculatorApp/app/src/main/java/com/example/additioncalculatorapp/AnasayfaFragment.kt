package com.example.additioncalculatorapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.additioncalculatorapp.databinding.FragmentAnasayfaBinding

class AnasayfaFragment : Fragment() {

    private lateinit var binding: FragmentAnasayfaBinding
    private var expression = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)


        val digitButtons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9
        )

        digitButtons.forEach { button ->
            button.setOnClickListener {
                expression += button.text
                binding.textViewSonuc.text = expression
            }
        }

        binding.buttonArti.setOnClickListener { appendOperator("+") }
        binding.buttonEksi.setOnClickListener { appendOperator("-") }
        binding.buttonCarpi.setOnClickListener { appendOperator("*") }
        binding.buttonBolu.setOnClickListener { appendOperator("/") }

        // AC tuşu → her şeyi sıfırlar
        binding.buttonAC.setOnClickListener {
            expression = ""
            binding.textViewSonuc.text = "0"
        }

        // Delete tuşu → sadece son karakteri siler
        binding.buttonDelete.setOnClickListener {
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                binding.textViewSonuc.text = if (expression.isEmpty()) "0" else expression
            }
        }


        binding.buttonEsit.setOnClickListener {
            try {
                val result = evaluateExpression(expression)
                binding.textViewSonuc.text = result.toString()
                expression = result.toString()
            } catch (e: Exception) {
                binding.textViewSonuc.text = "Hata"
                expression = ""
            }
        }

        return binding.root
    }

    // Operatör ekleme kontrol edilir
    private fun appendOperator(op: String) {
        if (expression.isNotEmpty() && !isLastCharOperator()) {
            expression += op
            binding.textViewSonuc.text = expression
        }
    }

    // Son karakter operatör mü kontrolü yapar
    private fun isLastCharOperator(): Boolean {
        return expression.lastOrNull() in listOf('+', '-', '*', '/')
    }

    private fun evaluateExpression(expr: String): Double {
        val sanitized = expr.replace("×", "*").replace("÷", "/")
        val tokens = Regex("(?<=[-+*/])|(?=[-+*/])").split(sanitized)
        val values = mutableListOf<Double>()
        val ops = mutableListOf<String>()

        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]
            if (token.isBlank()) {
                i++
                continue
            }

            if (token.matches(Regex("[0-9.]+"))) {
                values.add(token.toDouble())
            } else if (token in listOf("+", "-", "*", "/")) {
                while (ops.isNotEmpty() && precedence(ops.last()) >= precedence(token)) {
                    val b = values.removeAt(values.lastIndex)
                    val a = values.removeAt(values.lastIndex)
                    val op = ops.removeAt(ops.lastIndex)
                    values.add(applyOperator(a, b, op))
                }
                ops.add(token)
            }
            i++
        }

        while (ops.isNotEmpty()) {
            val b = values.removeAt(values.lastIndex)
            val a = values.removeAt(values.lastIndex)
            val op = ops.removeAt(ops.lastIndex)
            values.add(applyOperator(a, b, op))
        }

        return values.first()
    }

    private fun precedence(op: String): Int {
        return when (op) {
            "+", "-" -> 1
            "*", "/" -> 2
            else -> 0
        }
    }

    private fun applyOperator(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> 0.0
        }
    }
}