package vcmsa.ci.historyquizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this screen
        setContentView(R.layout.activity_welcome)

        // Get the Start button using its ID
        val startButton =
            findViewById<Button>(R.id.startButton)

        // Set what happens when the Start button is clicked
        startButton.setOnClickListener {
            // Create a new screen (intent) to move to the QuizActivity
            val intent = Intent (this, QuizActivity::class.java)
            // Start the QuizActivity screen
            startActivity(intent)
        }
    }
}