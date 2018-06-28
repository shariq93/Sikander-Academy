package app.com.sikanderacademy.activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.com.sikanderacademy.ModelClass.Book;
import app.com.sikanderacademy.R;
import app.com.sikanderacademy.adapters.CustomBookAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowBookActivity extends AppCompatActivity {

    ImageButton btn_showbooks;
    ListView lv_show_books;
    List<Book> booksArry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_showbooks = findViewById(R.id.btn_showbooks);
        lv_show_books = findViewById(R.id.lv_show_books);
        btn_showbooks.setVisibility(View.GONE);
        booksArry = new ArrayList<>();

        booksArry = Book.listAll(Book.class);
        CustomBookAdapter customBookAdapter = new CustomBookAdapter(this,booksArry);
        lv_show_books.setAdapter(customBookAdapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}