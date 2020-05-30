/*
 * Created by Rishit Dagli on 5/23/20 3:45 AM
 * Copyright (c) 2020.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at-
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package tech.rishit.makecode_android

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    lateinit var dayNightSwitch: DayNightSwitch
    lateinit var background_view: View
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

        val projectUrl: String = "<div style=\"position:relative;height:0;padding-bottom:117.6%;" +
                "overflow:hidden;\"><iframe style=\"position:absolute;top:0;left:0;width:100%;" +
                "height:100%;\" src=\"https://arcade.makecode.com/---run?" +
                "id=" +
                getString(R.string.project_id) +
                " " +
                "allowfullscreen=\"allowfullscreen\" sandbox=\"allow-popups allow-forms allow-" +
                "scripts allow-same-origin\" frameborder=\"0\"></iframe></div"

        val mimeType = "text/html"
        val encoding = "utf-8"

        myWebView.loadDataWithBaseURL("", projectUrl, mimeType, encoding, "")

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

        fun openUrl(url: String): Boolean {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            return true
        }

        github.setOnClickListener {
            openUrl(getString(R.string.githubproject_url))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.author_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if (id == R.id.credits) {
            val intent = Intent(this, credits::class.java)
            startActivity(intent)
        }

        return true
    }
}
