package tech.rishit.makecode_android

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.mahfa.dnswitch.DayNightSwitch
import com.mahfa.dnswitch.DayNightSwitchListener

class MainActivity : AppCompatActivity() {
    lateinit var dayNightSwitch: DayNightSwitch
    lateinit var background_view: View
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dayNightSwitch = findViewById<View>(R.id.dayNight) as DayNightSwitch
        background_view = findViewById(R.id.background_view)
        textView = findViewById(R.id.textView2)

        dayNightSwitch.setDuration(450)
        dayNightSwitch.setListener { is_night ->
            if (is_night) {
                Toast.makeText(this@MainActivity, "Night Mode!", Toast.LENGTH_SHORT).show()
                this.background_view.alpha = 1f
            } else {
                Toast.makeText(this@MainActivity, "Day Mode!", Toast.LENGTH_SHORT).show()
                this.background_view.alpha = 0f
            }
        }
    }
}
