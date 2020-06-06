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

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.mahfa.dnswitch.DayNightSwitch


class credits : AppCompatActivity() {
    private lateinit var dayNightSwitch: DayNightSwitch
    private lateinit var backgroundView: View
    private lateinit var header: TextView
    private lateinit var bio: TextView

    private lateinit var twitter: ImageButton
    private lateinit var website: ImageButton
    private lateinit var insta: ImageButton
    private lateinit var lnkdn: ImageButton
    private lateinit var github: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        dayNightSwitch = findViewById<View>(R.id.dayNight) as DayNightSwitch
        backgroundView = findViewById(R.id.background_view)
        header = findViewById(R.id.Header)
        bio = findViewById(R.id.bio)

        twitter = findViewById(R.id.twitterbutton)
        website = findViewById(R.id.website)
        insta = findViewById(R.id.insta)
        lnkdn = findViewById(R.id.lnkdn)
        github = findViewById(R.id.github)

        DownloadImageTask(findViewById(R.id.profile_photo))
            .execute(getString(R.string.profile_picture_url))

        dayNightSwitch.setDuration(450)

        dayNightSwitch.setListener { is_night ->
            if (is_night) {
                Toast.makeText(this@credits, "Night Mode!", Toast.LENGTH_SHORT).show()
                header.setTextColor(Color.WHITE)
                bio.setTextColor(Color.WHITE)

                twitter.setBackgroundColor(Color.BLACK)
                website.setBackgroundColor(Color.BLACK)
                insta.setBackgroundColor(Color.BLACK)
                lnkdn.setBackgroundColor(Color.BLACK)
                github.setBackgroundColor(Color.BLACK)

                website.setImageResource(R.drawable.ic_link_white_24dp)
                github.setColorFilter(
                    ContextCompat.getColor(this, R.color.white_github),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

                this.backgroundView.alpha = 1f
            } else {
                Toast.makeText(this@credits, "Day Mode!", Toast.LENGTH_SHORT).show()
                header.setTextColor(Color.DKGRAY)
                bio.setTextColor(Color.DKGRAY)

                twitter.setBackgroundColor(Color.WHITE)
                website.setBackgroundColor(Color.WHITE)
                insta.setBackgroundColor(Color.WHITE)
                lnkdn.setBackgroundColor(Color.WHITE)
                github.setBackgroundColor(Color.WHITE)

                website.setImageResource(R.drawable.ic_link_black_24dp)
                github.setColorFilter(
                    ContextCompat.getColor(this, R.color.black_github),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

                this.backgroundView.alpha = 0f
            }
        }

        fun openUrl(url: String): Boolean {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            return true
        }

        github.setOnClickListener {
            openUrl(getString(R.string.github_profile))
        }
        twitter.setOnClickListener {
            openUrl(getString(R.string.twitter_url))
        }
        website.setOnClickListener {
            openUrl(getString(R.string.website))
        }
        lnkdn.setOnClickListener {
            openUrl(getString(R.string.linkedin_profile))
        }
        insta.setOnClickListener {
            openUrl(getString(R.string.insta_profile))
        }

    }
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
private class DownloadImageTask(internal var bmImage: ImageView) :
    AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val urldisplay = urls[0]
        var mIcon11: Bitmap? = null
        try {
            val `in` = java.net.URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        return mIcon11
    }

    override fun onPostExecute(result: Bitmap) {
        bmImage.setImageBitmap(result)
    }
}
