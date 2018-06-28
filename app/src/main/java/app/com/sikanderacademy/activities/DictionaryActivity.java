package app.com.sikanderacademy.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.com.sikanderacademy.ModelClass.Book;
import app.com.sikanderacademy.ModelClass.Chapter;
import app.com.sikanderacademy.ModelClass.Dictionary;
import app.com.sikanderacademy.R;
import app.com.sikanderacademy.adapters.CustomDictionaryAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DictionaryActivity extends AppCompatActivity {

    ListView lv_search;
    EditText et_search;
    List<Dictionary> dictionaryArrayList;
    List<Dictionary> searchResults;
    String TAG = "((((((((";

    private void bringSearchResults(String temp) {
        searchResults = Dictionary.find(Dictionary.class,"word like '"+temp+"%'");
        Log.i(TAG, "searchResults : size "+searchResults.size());
        for (int i = 0 ; i < searchResults.size() ; i ++) {
//            Log.i(TAG, "Word : "+searchResults.get(i).getWord()+
//                    "Meaning : "+searchResults.get(i).getMeaning());
            searchResults.get(i).setDictionaryId(""+(i+1));
        }
        CustomDictionaryAdapter customDictionaryAdapter = new CustomDictionaryAdapter(this,searchResults);
        lv_search.setAdapter(customDictionaryAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv_search = findViewById(R.id.lv_search);
        et_search = findViewById(R.id.et_search);

        dictionaryArrayList = new ArrayList<>();
        dictionaryArrayList = Dictionary.listAll(Dictionary.class);

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
