package za.ac.iie

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    private val question = arrayOf("The Orange River is the longest river in South Africa",
        "The Blue Crane is the South African National Bird",
        "Pretoria is the South African legislative capital",
        "There are 100 cents in a South African Rand",
        "Nelson Mandela wasn't the first black president in South Africa",
    )
    private val feedback = arrayOf(true, true, false, true, false)
    private var score = 0
    private var currentQuestion = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val score = intent.getIntExtra("Score", 0)


        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtResults = findViewById<TextView>(R.id.txtResults)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnStop = findViewById<Button>(R.id.btnStop)

        txtScore.text = "Your quiz score is:$score/5"
        txtResults.text = if (score >= 3) "Execellent Work!" else "Keep trying you got this!"

        btnReview.setOnClickListener{
            val reviewText = StringBuilder()
            for (i in question.indices){
                reviewText.append("${i + 1}.${question[i]} - Answer:} ${feedback[i]}\n\n")
            }
            txtResults.text = reviewText.toString()
        }
        btnStop.setOnClickListener{
            finishAffinity()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}