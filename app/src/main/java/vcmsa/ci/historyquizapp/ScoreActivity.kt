package vcmsa.ci.historyquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout fot the score screen
        setContentView(R.layout.activity_score)

        // Get the score passed from the QuizActivity
        val score = intent.getIntExtra("score", 0)
// Get the arrays passed from the QuizActivity
        val answers = intent.getStringArrayExtra("answers")
        val questions = intent.getStringArrayExtra("questions")
// Get views by their IDs
        val resultText = findViewById<TextView>(R.id.scoreTextView)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
// Create feedback message based on how well the user did
        val message = when (score) {
            5 -> "Excellent!"
            in 3..4 -> "Well done!"
            else -> "Keep practicing!"
        }
// Show the user's final score and feedback message
        resultText.text = "You scored $score out of 5\n$message"
        reviewButton.setOnClickListener {
            val reviewText = StringBuilder()
            if (questions != null && answers != null) {
// Loop through questions and answers
                for (i in questions.indices) {
                    reviewText.append("Q${i + 1}: ${questions[i]}\n")
                    reviewText.append("Answer: ${answers[i]}\n\n")
                }
// Display in an AlertDialog
                AlertDialog.Builder(this)
                    .setTitle("Review Flashcards")
                    .setMessage(reviewText.toString())
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                Toast.makeText(this, "No review data available.", Toast.LENGTH_SHORT).show()
            }
        }

        // Exit the app completely when Exit button is clicked
        exitButton.setOnClickListener {
            finishAffinity() // Closes all app activities
        }
    }
}