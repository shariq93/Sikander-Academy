package app.com.sikanderacademy.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.com.sikanderacademy.ModelClass.Chapter

import app.com.sikanderacademy.R
import app.com.sikanderacademy.adapters.ChaptersListAdapter
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_subject.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SubjectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_PARAM1)

        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subject, container, false)

        val list = Chapter.find(Chapter::class.java,"subject_id=$id")
        val adapter = ChaptersListAdapter(context,list)
        view.lv_titles.adapter = adapter
        val adRequest = AdRequest.Builder().addTestDevice("B40037E56E469CB1C0885A1422537769").build()
        view.adView.loadAd(adRequest)
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubjectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                SubjectFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)

                    }
                }
    }
}
