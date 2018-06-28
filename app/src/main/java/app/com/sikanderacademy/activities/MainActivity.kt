package app.com.sikanderacademy.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import app.com.sikanderacademy.ModelClass.Book
import app.com.sikanderacademy.ModelClass.Subject
import app.com.sikanderacademy.R
import app.com.sikanderacademy.fragments.SubjectFragment
import com.google.android.gms.ads.MobileAds

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    lateinit var subjects : List<Subject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        MobileAds.initialize(this, "ca-app-pub-1109439098014642~1475039482");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        subjects = Subject.listAll(Subject::class.java)
        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
//actionBar.home

        findViewById<DrawerLayout>(R.id.drawer_layout)
        findViewById<ImageView>(R.id.drawer_btn)

        drawer_btn.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE)

        for (sub in subjects){
            tabs.addTab(tabs.newTab().setText(sub.title))
        }

        //tabs.addTab(tabs.newTab().setText("Hello world again"))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

//        Log.i("----------","hello $id")

        if (item.itemId == android.R.id.home){//16908332) {
            drawer_layout.openDrawer(GravityCompat.START)

            return true
        }

        if (id == R.id.action_search) {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java));
            return true
        }


        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_dictionary -> startActivity(Intent(this@MainActivity, DictionaryActivity::class.java))

            R.id.nav_handouts -> Toast.makeText(this,"This Feature Is Coming Soon",Toast.LENGTH_SHORT).show()

            R.id.nav_pastpapers -> Toast.makeText(this,"This Feature Is Coming Soon",Toast.LENGTH_SHORT).show()

            R.id.nav_books -> startActivity(Intent(this@MainActivity, ShowBookActivity::class.java));

            R.id.nav_about_us -> startActivity(Intent(this@MainActivity, AboutUsActivity::class.java));

            R.id.nav_about_us -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName)))
            }

            R.id.nav_logout -> startActivity(Intent(this@MainActivity, MainActivity::class.java))

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true

    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return
        } else {
            finish()
        }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return SubjectFragment.newInstance(subjects[position].subjectId)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return subjects.size
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
