package tech.rishit.makecode_android

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.mahfa.dnswitch.DayNightSwitch
import org.w3c.dom.Text
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    lateinit var dayNightSwitch: DayNightSwitch
    lateinit var background_view: View
    lateinit var textView: TextView
    lateinit var myWebView: WebView
    lateinit var github: FloatingActionButton
    lateinit var project_name: TextView
    lateinit var footer: TextView


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dayNightSwitch = findViewById<View>(R.id.dayNight) as DayNightSwitch
        background_view = findViewById(R.id.background_view)
        myWebView = findViewById(R.id.webview)
        github = findViewById(R.id.github)
        project_name = findViewById(R.id.project_name)
        this.footer = findViewById(R.id.footer)

        myWebView.settings.javaScriptEnabled = true

        val projectUrl: String = getString(R.string.makecode_project)
        myWebView.loadUrl(projectUrl)

        dayNightSwitch.setDuration(450)

        dayNightSwitch.setListener { is_night ->
            if (is_night) {
                Toast.makeText(this@MainActivity, "Night Mode!", Toast.LENGTH_SHORT).show()
                project_name.setTextColor(Color.WHITE)
                footer.setTextColor(Color.WHITE)
                this.background_view.alpha = 1f
            } else {
                Toast.makeText(this@MainActivity, "Day Mode!", Toast.LENGTH_SHORT).show()
                project_name.setTextColor(Color.DKGRAY)
                footer.setTextColor(Color.DKGRAY)
                this.background_view.alpha = 0f
            }
        }

        github.setOnClickListener{
            val github_project = getString(R.string.githubproject_url)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(github_project)
            startActivity(i)
        }

    }
}
