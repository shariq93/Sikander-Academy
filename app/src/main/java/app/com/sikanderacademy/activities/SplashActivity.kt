package app.com.sikanderacademy.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.com.sikanderacademy.ModelClass.*
import app.com.sikanderacademy.R
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.orm.SugarRecord
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.net.NetworkInfo
import android.net.ConnectivityManager



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (isNetworkAvailable()) {
            getEveryThing()
        } else {
            faltuKaDelay(3000)
        }
    }

    fun faltuKaDelay(delay:Long){
        Thread{
            run {
                Thread.sleep(delay);
//                startActivity(Intent(this@SplashActivity, ShowBookActivity::class.java));
                startActivity(Intent(this@SplashActivity, MainActivity::class.java));
                finish()
            }
        }.start()
    }


    fun getEveryThing() {

        val client = AsyncHttpClient()
        client.get("http://sikanderacademy.com/admin/app_service.php?tab=get_everything", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                super.onSuccess(statusCode, headers, response)
                val everything = Gson().fromJson(response.toString(), Everything::class.java);

                SugarRecord.deleteAll(Subject::class.java)
                SugarRecord.deleteAll(Chapter::class.java)
                SugarRecord.deleteAll(Lesson::class.java)
                SugarRecord.deleteAll(Topic::class.java)
                SugarRecord.deleteAll(SubTopic::class.java)
                SugarRecord.deleteAll(Dictionary::class.java)
                SugarRecord.deleteAll(Book::class.java)



                SugarRecord.saveInTx(everything.subject)
                SugarRecord.saveInTx(everything.chapter)
                SugarRecord.saveInTx(everything.topic)
                SugarRecord.saveInTx(everything.subTopic)
                SugarRecord.saveInTx(everything.lesson)
                SugarRecord.saveInTx(everything.dictionary)
                SugarRecord.saveInTx(everything.books)


                faltuKaDelay(1500)
            }
        }
        )
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
