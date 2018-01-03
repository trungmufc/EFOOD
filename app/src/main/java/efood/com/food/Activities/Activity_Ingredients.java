package efood.com.food.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.rengwuxian.materialedittext.MaterialEditText;

import efood.com.food.Adapter.Adapter_spinner_unit;
import efood.com.food.Data.Dao.Dao_Ingredients;
import efood.com.food.R;

public class Activity_Ingredients extends AppCompatActivity {
    Toolbar toolbar;
    //------
    String Untils[] = {"unit", "grams", "oz", "tsp", "tbls", "cups", "lb", "gal", "kg", "l", "ml", "cm"};
    //------
    Spinner spinner_UNIT;
    MaterialEditText edt_name, edt_quantity;
    Dao_Ingredients IngredientDao;
    String data = "";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("title", "");
        intent.putExtra("quantity", "");
        intent.putExtra("unit", "");
        setResult(2, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        IngredientDao = new Dao_Ingredients(this);
        Actionbar();
        init();
        Intent intent = new Intent();
        intent.putExtra("title", "");
        intent.putExtra("quantity", "");
        intent.putExtra("unit", "");
        setResult(2, intent);
    }

    private void Actionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nguyên liệu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    Adapter_spinner_unit adapter_spinner_unit;

    private void init() {
        spinner_UNIT = (Spinner) findViewById(R.id.sp_unit);
        adapter_spinner_unit = new Adapter_spinner_unit(Untils);
        edt_name = (MaterialEditText) findViewById(R.id.edt_name);
        edt_quantity = (MaterialEditText) findViewById(R.id.edt_quantity);
        spinner_UNIT.setAdapter(adapter_spinner_unit);
        spinner_UNIT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(Untils[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void InsertAll() {
//        if()

    }


    private boolean isEmty(String st) {
        if (st.trim().length() > 0) return true;
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusave, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Activity_Ingredients.this, Activity_AddReciper.class));
                break;
            case R.id.item_save:
                if ((edt_name.getText().toString().trim()).equals(""))
                    edt_name.setError("null");
                else if ((edt_quantity.getText().toString()).trim().equals(""))
                    edt_quantity.setError("null");
                else if (getData().trim().equals("unit"))
                    Snackbar.make(spinner_UNIT, "Bạn chưa chọn đơn vị", Snackbar.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent();
                    intent.putExtra("title", edt_name.getText().toString());
                    intent.putExtra("quantity", edt_quantity.getText() + "");
                    intent.putExtra("unit", getData());
                    setResult(2, intent);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
