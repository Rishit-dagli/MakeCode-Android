package tech.rishit.makecode_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.mahfa.dnswitch.DayNightSwitch


class credits : AppCompatActivity() {
    lateinit var dayNightSwitch: DayNightSwitch
    lateinit var background_view: View
    lateinit var header: TextView
    lateinit var bio:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        dayNightSwitch = findViewById<View>(R.id.dayNight) as DayNightSwitch
        background_view = findViewById(R.id.background_view)
        header = findViewById(R.id.Header)
        bio = findViewById(R.id.bio)

        DownloadImageTask(findViewById(R.id.profile_photo))
            .execute(getString(R.string.profile_picture_url))

        dayNightSwitch.setDuration(450)

        dayNightSwitch.setListener { is_night ->
            if (is_night) {
                Toast.makeText(this@credits, "Night Mode!", Toast.LENGTH_SHORT).show()
                header.setTextColor(Color.WHITE)
                bio.setTextColor(Color.WHITE)
                this.background_view.alpha = 1f
            } else {
                Toast.makeText(this@credits, "Day Mode!", Toast.LENGTH_SHORT).show()
                header.setTextColor(Color.DKGRAY)
                bio.setTextColor(Color.DKGRAY)
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
