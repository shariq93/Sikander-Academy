package app.com.sikanderacademy.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import app.com.sikanderacademy.R
import kotlinx.android.synthetic.main.activity_web_view.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar!!.setHomeButtonEnabled(true)
        webview.settings.javaScriptEnabled = true

        val pdf = intent.getStringExtra("pdf_url")
        val title = intent.getStringExtra("title")
        val fulUrl = "http://drive.google.com/viewerng/viewer?embedded=true&url=$pdf"
        Log.i("=========",fulUrl)
        webview.loadUrl(fulUrl)

        this.supportActionBar!!.title = title
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            this.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
