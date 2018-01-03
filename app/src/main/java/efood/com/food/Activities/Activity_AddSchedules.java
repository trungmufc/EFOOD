package efood.com.food.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import java.util.ArrayList;

import efood.com.food.Adapter.Adapter_SpinnerCategory;
import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Model.mCategory;
import efood.com.food.R;

public class Activity_AddSchedules extends AppCompatActivity {
    Toolbar toobar;
    Intent intent;
    int typefood = 1;
    Spinner spinner;
    ArrayList<mCategory> categories;
    Dao_Category categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryDao = new Dao_Category(this);
        categories = categoryDao.getlist();
        setContentView(R.layout.activity_addfoodcaculator);
        intent = getIntent();
        init();
        Adapter_SpinnerCategory adapter_category = new Adapter_SpinnerCategory(categories);
        spinner.setAdapter(adapter_category);
        ActionBar();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void ActionBar() {
        toobar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toobar);
        if (typefood == 1) {
            getSupportActionBar().setTitle("Bửa Sáng " + intent.getStringExtra("date"));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddfood, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }
}
