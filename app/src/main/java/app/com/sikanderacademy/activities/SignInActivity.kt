//package app.com.sikanderacademy.activities
//
//import android.app.ProgressDialog
//import android.content.Intent
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//
//import com.google.gson.Gson
//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.JsonHttpResponseHandler
//import com.loopj.android.http.RequestParams
//import cz.msebera.android.httpclient.Header
//import kotlinx.android.synthetic.main.activity_sign_in.*
//
//import org.json.JSONObject
//
//class SignInActivity : AppCompatActivity() {
//    var app = this
//    var pd : ProgressDialog?  = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_lesson)
//
//
//
//        pd = ProgressDialog(app)
//        pd!!.setMessage("Please wait!")
//
//        btn_signIn.setOnClickListener {
//
//            var email = et_email.text.toString()
//            var password = et_password.text.toString()
//
//            if (email.isNotEmpty() && password.isNotEmpty()){
//                login(email,password)
//            }else{
//                Toast.makeText(this@SignInActivity,"Invalid email or password",Toast.LENGTH_SHORT).show()
//            }
//
//
//        }
//
//    }
//
//
//
//    fun login(email:String, password:String){
//        val client = AsyncHttpClient()
//        val url = getString(R.string.api_url)
//        val param = RequestParams()
//        param.put("email",email)
//        param.put("password",password)
//        param.put("tab","login")
//        pd!!.show()
//        client.post(url,param,object : JsonHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
//                super.onSuccess(statusCode, headers, response)
//                try{
//                    val status = response!!.getInt("status")
//                    val msg = response!!.getString("msg")
//                    if(status == 0) {
//
//                        val user = Gson().fromJson(response!!.getJSONObject("user").toString(), User::class.java)
//                        UserPrefs(this@SignInActivity).saveUser(user)
//
//                    if(user.paperKey.isNullOrEmpty()){
//                        pd!!.dismiss()
//                        //PostAuth.getInstance().onPhraseCheckAuth(this@SignInActivity, false)
//                        finish()
//                        startActivity(Intent(this@SignInActivity,SetPinActivity::class.java))
//                    }else{
//                        val cleanPhrase = SmartValidator.cleanPaperKey(app, user.paperKey)
//
//                        val m = WalletsMaster.getInstance(app)
//                        m.wipeWalletButKeystore(app)
//                        m.wipeKeyStore(app)
//                        PostAuth.getInstance().setPhraseForKeyStore(cleanPhrase)
//                        BRSharedPrefs.putAllowSpend(app, BRSharedPrefs.getCurrentWalletIso(app), false)
//                        //if this screen is shown then we did not upgrade to the new app, we installed it
//                        BRSharedPrefs.putGreetingsShown(app, true)
//                        PostAuth.getInstance().onRecoverWalletAuthWithCallback(app, false) {
//
//                            pd!!.dismiss()
//                            finish()
//                        }
//                    }
//
//                    }
//                    Toast.makeText(this@SignInActivity,msg,Toast.LENGTH_SHORT).show()
//
//                }catch (e:Exception){
//                    e.printStackTrace()
//                    Toast.makeText(this@SignInActivity,"Something went wrong please try again",
//                            Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
//                super.onFailure(statusCode, headers, responseString, throwable)
//                Toast.makeText(this@SignInActivity,"Internet connection error",Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    override fun onStop() {
//        super.onStop()
//    }
//}
