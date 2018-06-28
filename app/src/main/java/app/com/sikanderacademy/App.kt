package app.com.sikanderacademy

import android.app.Application
import android.content.Context

import com.orm.SugarContext
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

//import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SugarContext.init(this)
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("Prototype.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
        App.context = applicationContext
    }


    companion object {
        var context: Context? = null

    }

    fun getAppContext(): Context {
        return App.context!!
    }
}
