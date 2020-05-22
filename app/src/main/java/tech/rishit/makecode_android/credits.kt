package tech.rishit.makecode_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
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
    lateinit var dayNightSwitch: DayNightSwitch
    lateinit var background_view: View
    lateinit var header: TextView
    lateinit var bio:TextView

    lateinit var twitter: ImageButton
    lateinit var website: ImageButton
    lateinit var insta: ImageButton
    lateinit var lnkdn: ImageButton
    lateinit var github: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        dayNightSwitch = findViewById<View>(R.id.dayNight) as DayNightSwitch
        background_view = findViewById(R.id.background_view)
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
                github.setColorFilter(ContextCompat.getColor(this, R.color.white_github),
                    android.graphics.PorterDuff.Mode.SRC_IN)

                this.background_view.alpha = 1f
            }
            else {
                Toast.makeText(this@credits, "Day Mode!", Toast.LENGTH_SHORT).show()
                header.setTextColor(Color.DKGRAY)
                bio.setTextColor(Color.DKGRAY)

                twitter.setBackgroundColor(Color.WHITE)
                website.setBackgroundColor(Color.WHITE)
                insta.setBackgroundColor(Color.WHITE)
                lnkdn.setBackgroundColor(Color.WHITE)
                github.setBackgroundColor(Color.WHITE)

                website.setImageResource(R.drawable.ic_link_black_24dp)
                github.setImageResource(R.drawable.ic_github_black)

                this.background_view.alpha = 0f
            }
        }
    }
}

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
