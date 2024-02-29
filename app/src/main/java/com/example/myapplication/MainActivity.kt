package com.example.myapplication

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clear: Button
    private lateinit var backSpace: Button
    private lateinit var percent: Button
    private lateinit var multiply: Button
    private lateinit var subtract: Button
    private lateinit var plus: Button
    private lateinit var divide: Button
    private lateinit var equal: Button
    private lateinit var dot: Button
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var thre: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var str: String

        fun math(num: String) {
            if (expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", num)
                expressionText(str)
                resultText()
            } else {
                str = expression.text.toString() + num
                expressionText(str)
                resultText()
            }
        }

        fun operator(op: String) {
            if (expression.text.toString().endsWith("%") ||
                expression.text.toString().endsWith("/") ||
                expression.text.toString().endsWith("*") ||
                expression.text.toString().endsWith("+") ||
                expression.text.toString().endsWith("-") ||
                expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                str = str.substring(0, str.length - 1) + op
                expressionText(str)
            } else{
                str = expression.text.toString() + op
                expressionText(str)
            }
        }

        expression = findViewById(R.id.solution_tv)
        result = findViewById(R.id.result_tv)
        clear = findViewById(R.id.button_c)
        backSpace = findViewById(R.id.button_backSpace)
        percent = findViewById(R.id.button_percent)
        multiply = findViewById(R.id.button_multiply)
        subtract = findViewById(R.id.button_minus)
        equal = findViewById(R.id.button_equals)
        plus = findViewById(R.id.button_plus)
        divide = findViewById(R.id.button_divide)
        dot = findViewById(R.id.button_dot)
        zero = findViewById(R.id.button_0)
        one = findViewById(R.id.button_1)
        two = findViewById(R.id.button_2)
        thre = findViewById(R.id.button_3)
        four = findViewById(R.id.button_4)
        five = findViewById(R.id.button_5)
        six = findViewById(R.id.button_6)
        seven = findViewById(R.id.button_7)
        eight = findViewById(R.id.button_8)
        nine = findViewById(R.id.button_9)

        expression.movementMethod = ScrollingMovementMethod()
        expression.isActivated = true
        expression.isPressed = true

        clear.setOnClickListener {
            expressionText("0")
            resultText()
        }

        backSpace.setOnClickListener {
            if (expression.text.toString().isNotEmpty()) {
                val lastIndex = expression.text.toString().lastIndex
                str = expression.text.toString().substring(0, lastIndex)
                expressionText(str)
                resultText()
            }
        }

        percent.setOnClickListener {
            operator("%")
        }

        multiply.setOnClickListener {
            operator("*")
        }

        subtract.setOnClickListener {
            operator("-")
        }

        equal.setOnClickListener {
            val x: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
            val res = x.eval(expression.text.toString())
            if (res.toString().endsWith(".0")) {
                result.text = res.toString().replace(".0", "")
            }else {
                result.text = "$res"
            }
            expressionText(result.text.toString())
            resultText()
        }

        plus.setOnClickListener {
            operator("+")
        }

        divide.setOnClickListener {
            operator("/")
        }

        dot.setOnClickListener {
            operator(".")
        }

        zero.setOnClickListener {
            math("0")
        }

        one.setOnClickListener {
            math("1")
        }

        two.setOnClickListener {
            math("2")
        }

        thre.setOnClickListener {
            math("3")
        }

        four.setOnClickListener {
            math("4")
        }

        five.setOnClickListener {
            math("5")
        }

        six.setOnClickListener {
            math("6")
        }

        seven.setOnClickListener {
            math("7")
        }

        eight.setOnClickListener {
            math("8")
        }

        nine.setOnClickListener {
            math("9")
        }
    }

    private fun expressionText(str: String) {
        expression.text = str
    }

    private fun resultText() {
        val exp = expression.text.toString()
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        try {
            val res = engine.eval(exp)
            if (res.toString().endsWith(".0")) {
                result.text = "=" + res.toString().replace(".0", "")
            }else{
                result.text = "=$res"
            }
        } catch (e: Exception) {
            expression.text = expression.text.toString()
            result.text = expression.text.toString()
        }
    }

}