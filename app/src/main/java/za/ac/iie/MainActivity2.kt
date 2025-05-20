package za.ac.iie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private val question = arrayOf("The Orange River is the longest river in South Africa",
        "The Blue Crane is the South African National Bird",
        "Pretoria is the South African legislative capital",
        "There are 100 cents in a South African Rand",
        "Nelson Mandela wasn't the first black president in South Africa",
    )
   private val feedback = arrayOf(true, true, false, true, false)
    private var score = 0
    private var currentQuestion = 0
    private lateinit var txtFeedback: TextView
    private lateinit var txtQuestion: TextView
    private lateinit var btnNext: Button
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        txtQuestion = findViewById(R.id.txtQuestion)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        // the main function that loads the questions
        loadQuestion()



        //if true button is clicked the answer is true
        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        //if false button is clicked the answer is false
        btnFalse.setOnClickListener {
            checkAnswer(false)
        }
        // is the program written to link the next button to the actions it needs to follow
        btnNext.setOnClickListener {
            currentQuestion++
            if (currentQuestion < question.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                // used to finish the action and move onto the next
                finish()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadQuestion() {
        txtQuestion.text = question[currentQuestion]
        txtFeedback.text = ""
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.visibility = View.INVISIBLE
    }

    private fun checkAnswer(personAnswer: Boolean) {
        val correctAnswer = feedback[currentQuestion]
        if (personAnswer == correctAnswer) {
            txtFeedback.text = "Correct Answer!"
            score++
        } else {
            txtFeedback.text = "Incorrect Answer!"
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.visibility = View.VISIBLE
    }
}