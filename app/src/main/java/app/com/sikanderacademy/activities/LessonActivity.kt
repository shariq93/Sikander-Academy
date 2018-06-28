package app.com.sikanderacademy.activities

import android.content.Context
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import app.com.sikanderacademy.ModelClass.Lesson
import app.com.sikanderacademy.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lesson.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_subject.view.*
import kotlin.math.log


class LessonActivity : YouTubeBaseActivity() {
    var intitializer : YouTubePlayer.OnInitializedListener? = null
    var header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <title>Bootstrap Example</title>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "  <link rel=\"stylesheet\" href=\"'file:///android_asset/bootstrap.min.css\">\n" +
            "  <script src=\"file:///android_asset/jquery.min.js\"></script>\n" +
            "  <script src=\"file:///android_asset/popper.min.js\"></script>\n" +
            "  <script src=\"file:///android_asset/bootstrap.min.js\"></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div class=\"container\">"
    var footer = "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>"

    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-1109439098014642/2617663941"
        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("B40037E56E469CB1C0885A1422537769").build())
        val adRequest = AdRequest.Builder().addTestDevice("B40037E56E469CB1C0885A1422537769").build()
        adView.loadAd(adRequest)
        val id = intent.getStringExtra("subTopicId")
        val ttl = intent.getStringExtra("title")
//        toolbar)
            toolbar!!.title = ttl

        iv_cover.visibility = View.GONE
        youtube_player.visibility = View.GONE
        val lesson = Lesson.find(Lesson::class.java,"sub_topic_id=$id").firstOrNull() ?: return
        Log.i("====",Gson().toJson(lesson))
        if (lesson.videoUrl.toLowerCase().endsWith(".jpeg") || lesson.videoUrl.toLowerCase().endsWith(".jpg")
        || lesson.videoUrl.toLowerCase().endsWith(".png")){
            Picasso.get().load(lesson.videoUrl.trim()).into(iv_cover)

            Picasso.get()
                    .load(lesson.videoUrl.trim())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_error)
                    .into(iv_cover)
            iv_cover.visibility = View.VISIBLE
        }else{
            iv_cover.visibility = View.GONE
        }

        if (lesson.videoUrl.toLowerCase().trim().startsWith("yt:")){
            youtube_player.visibility = View.VISIBLE
            intitializer = object:YouTubePlayer.OnInitializedListener{
                override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                    val ytId = lesson.videoUrl.replace("yt:","").trim()
                    p1!!.loadVideo(ytId)
                }

                override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

                }

            }
            youtube_player.initialize("AIzaSyB37avG451FcoxFBlaPWUoDy5uFnXZtDMU",intitializer)
        }else{
            youtube_player.visibility = View.GONE
        }

        webView.settings.javaScriptEnabled= true

        var body =decodeUnicode(lesson.description).replace("/admin/uploads/","http://sikanderacademy.com/admin/uploads/")
        Log.i("==========", body)
        webView.loadData(header+body+footer, "text/html", "UTF-8")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(viewx: WebView, urlx: String): Boolean {
                viewx.loadUrl(urlx)
                return false
            }
        }

//        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        this.supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            this.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun decodeUnicode(theString: String): String {
        var aChar: Char
        val len = theString.length
        val outBuffer = StringBuffer(len)
        var x = 0
        while (x < len) {
            aChar = theString[x++]
            if (aChar == '\\') {
                aChar = theString[x++]
                if (aChar == 'u') {
                    // Read the xxxx
                    var value = 0
                    for (i in 0..3) {
                        aChar = theString[x++]
                        when (aChar) {
                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> value = (value shl 4) + aChar.toInt() - '0'.toInt()
                            'a', 'b', 'c', 'd', 'e', 'f' -> value = (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                            'A', 'B', 'C', 'D', 'E', 'F' -> value = (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                            else -> throw IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.")
                        }

                    }
                    outBuffer.append(value.toChar())
                } else {
                    if (aChar == 't')
                        aChar = '\t'
                    else if (aChar == 'r')
                        aChar = '\r'
                    else if (aChar == 'n')
                        aChar = '\n'
//                    else if (aChar == 'f')
//                       // aChar = '\f'
                    outBuffer.append(aChar)
                }
            } else
                outBuffer.append(aChar)
        }
        return outBuffer.toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }
}