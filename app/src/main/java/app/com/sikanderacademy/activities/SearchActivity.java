package app.com.sikanderacademy.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.sikanderacademy.ModelClass.Chapter;
import app.com.sikanderacademy.ModelClass.Search;
import app.com.sikanderacademy.ModelClass.SubTopic;
import app.com.sikanderacademy.ModelClass.Topic;
import app.com.sikanderacademy.R;
import app.com.sikanderacademy.adapters.CustomSearchAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity extends AppCompatActivity {

    ImageView btn_search;
    ListView lv_search;
    EditText et_search;
    List<Chapter> allChapter;
    List<Topic> allTopic;
    List<SubTopic> allSubTopic;
    ArrayList<Search> searchArrayList;
    String TAG = "===========";
    ArrayList showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchArrayList = new ArrayList<>();
        lv_search = findViewById(R.id.lv_search);
        et_search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);
        allChapter = new ArrayList<>();
        allTopic = new ArrayList<>();
        allSubTopic = new ArrayList<>();
        showList = new ArrayList();
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String temp = s.toString();
                bringSearchResults(temp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void bringSearchResults(String temp) {

        if (temp.equals("")) {
            searchArrayList = new ArrayList<>();
            CustomSearchAdapter customSearchAdapter = new CustomSearchAdapter(this,searchArrayList);
            lv_search.setAdapter(customSearchAdapter);
            return;
        }

        allChapter = Chapter.find(Chapter.class,"title like '"+temp+"%'");
        allTopic = Topic.find(Topic.class,"title like '"+temp+"%'");
        allSubTopic = SubTopic.find(SubTopic.class,"title like '"+temp+"%'");
        searchArrayList = new ArrayList<>();
        for (int i = 0; i<allChapter.size();i++) {
            Search obj = new Search();
            obj.setTypeName("Chapter");
            obj.setID(allChapter.get(i).getChapterId());
            obj.setTitle(allChapter.get(i).getTitle());
            obj.setType("1");
            searchArrayList.add(obj);
        }

        for (int i = 0; i<allTopic.size();i++) {
            Search obj = new Search();
            obj.setTypeName("Topic");
            obj.setID(allTopic.get(i).getTopicId());
            obj.setTitle(allTopic.get(i).getTitle());
            obj.setType("2");
            searchArrayList.add(obj);
        }

        for (int i = 0; i<allSubTopic.size();i++) {
            Search obj = new Search();
            obj.setTypeName("SubTopic");
            obj.setID(allSubTopic.get(i).getSubTopicId());
            obj.setTitle(allSubTopic.get(i).getTitle());
            obj.setType("3");
            searchArrayList.add(obj);
        }

        CustomSearchAdapter customSearchAdapter = new CustomSearchAdapter(this,searchArrayList);

        lv_search.setAdapter(customSearchAdapter);

        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: "+searchArrayList.get(position).getID());
                Log.i(TAG, "onItemClick: "+searchArrayList.get(position).getTitle());
                Log.i(TAG, "onItemClick: "+searchArrayList.get(position).getType());
                Log.i(TAG, "onItemClick: "+searchArrayList.get(position).getTypeName());
                Intent intent = null;
                switch (searchArrayList.get(position).getTypeName()) {
                    case "Chapter":
                        intent = new Intent(SearchActivity.this,TopicsActivity.class);
                        intent.putExtra("chapter_id",searchArrayList.get(position).getID());
                        break;
                    case "Topic":
                        intent = new Intent(SearchActivity.this,SubTopicActivity.class);
                        intent.putExtra("topic_id",searchArrayList.get(position).getID());
                        break;
                    case "SubTopic":
                        intent = new Intent(SearchActivity.this,LessonActivity.class);
                        intent.putExtra("subTopicId",searchArrayList.get(position).getID());
                        break;
                }
                if (intent == null) {
                    return;
                }
                intent.putExtra("title",searchArrayList.get(position).getTitle());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
