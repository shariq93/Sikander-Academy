package app.com.sikanderacademy.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import app.com.sikanderacademy.ModelClass.Chapter
import app.com.sikanderacademy.ModelClass.Topic
import app.com.sikanderacademy.R
import app.com.sikanderacademy.adapters.ChaptersListAdapter
import app.com.sikanderacademy.adapters.TopicsListAdapter
import kotlinx.android.synthetic.main.activity_topics.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class TopicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)
        val id = intent.getStringExtra("chapter_id")
        val list = Topic.find(Topic::class.java,"chapter_id=$id")
        val adapter = TopicsListAdapter(this,list)
        lv_titles.adapter = adapter
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar!!.setHomeButtonEnabled(true)
        val ttl = intent.getStringExtra("title")
        this.supportActionBar!!.title = ttl
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            this.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
