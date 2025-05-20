package vcmsa.ci.historyquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    // Create an array of history questions
    val questions = arrayOf(
        "The main objective of Gandhi's non-cooperation movement was to protest British rule in india and attain self-governance.",
        "World war 2 ended in 1980.",
        "The first moon landing occurred in 1969.",
        "Thabo Mbeki is South Africa's first black president.",
        "The Great Wall of China was built to defend the empire from nomadic invaders."
    )

    // Create a parallel array of answers (True or False) for the questions
    val answers = arrayOf("True","False","True","False","True")

    // This will track which question we're on
    var currentIndex = 0

    // Track the user's score
    var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the quiz screen
        setContentView(R.layout.activity_quiz)

        // Find all the views by their IDs
        val questionText = findViewById<TextView>(R.id.questionTextView)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val feedbackText = findViewById<TextView>(R.id.feedbackTextView)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Display the first question
        questionText.text = questions[currentIndex]

        // What happens when the user clicks "True"
        trueButton.setOnClickListener {
            if (answers[currentIndex] == "True") {
                // Correct answer
                feedbackText.text = "Correct!"
                score++ // Add 1 to score
            } else {
                // Wrong answer
                feedbackText.text = "Incorrect"
            }

        }

        // What happens when the user clicks "False"
        falseButton.setOnClickListener {
            if (answers[currentIndex] == "False") {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect"
            }

        }

        // Move to the next question when "Next" is clicked
        nextButton.setOnClickListener {
            currentIndex++ // Move to the next index in the array

            if (currentIndex < questions.size) {
                // If there are more questions, display the next one
                questionText.text = questions[currentIndex]
                feedbackText.text = "" // Clear feedback for next question
            } else {
                // All questions are done – go to the Score screen
                val intent = Intent(this, ScoreActivity::class.java)
                // Pass the final score to the next screen
                intent.putExtra("score", score)
                // Pass the arrays to the next screen

                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                startActivity(intent)
                finish() // Close this screen
            }
        }
    }
}

