package app.com.sikanderacademy.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import app.com.sikanderacademy.App
import app.com.sikanderacademy.ModelClass.Chapter
import app.com.sikanderacademy.ModelClass.Topic
import app.com.sikanderacademy.R
import app.com.sikanderacademy.activities.SubTopicActivity
import app.com.sikanderacademy.activities.TopicsActivity
import kotlinx.android.synthetic.main.item_list_title.view.*


class TopicsListAdapter(var context: Context?,var list:List<Topic>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater =context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.item_list_title,null)

        val tv = view.findViewById(R.id.tv_title) as TextView
        view.tv_count.text = "${(position+1)}."
        tv.text = list[position].title
        view.setOnClickListener {
            val intent = Intent(App().getAppContext(), SubTopicActivity::class.java)
            intent.putExtra("topic_id",list[position].topicId)
            intent.putExtra("title",list[position].title)
            context!!.startActivity(intent)
        }
        return view
    }

    override fun getItem(position: Int): Any {
return list[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
      return  list.size

    }

}