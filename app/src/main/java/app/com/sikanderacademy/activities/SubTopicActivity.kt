package app.com.sikanderacademy.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import app.com.sikanderacademy.ModelClass.SubTopic
import app.com.sikanderacademy.ModelClass.Topic
import app.com.sikanderacademy.R
import app.com.sikanderacademy.adapters.SubTopicsListAdapter
import kotlinx.android.synthetic.main.activity_sub_topic.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class SubTopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_topic)
        val id = intent.getStringExtra("topic_id")
        //val title = intent.getStringExtra("title")
        val list = SubTopic.find(SubTopic::class.java,"topic_id=$id")
        val adapter = SubTopicsListAdapter(this,list)
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
