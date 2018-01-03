package efood.com.food.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Data.Dao.Dao_Ingredients;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.Ingredient;
import efood.com.food.Model.Reciper;
import efood.com.food.R;


public class Activity_ReciperDetails extends AppCompatActivity {
    Dao_Reciper reciperDao;
    Intent intent;
    Dao_Category categoryDao;
    Dao_Ingredients ingredientDao;
    Reciper reciper;
    TextView body;
    public static int RECIPERID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reciperDao = new Dao_Reciper(this);
        categoryDao = new Dao_Category(this);
        ingredientDao = new Dao_Ingredients(this);
        setContentView(R.layout.activity_details_reciper);
        intent = getIntent();
        ActionBar();
        reciper = reciperDao.getReciperById(intent.getIntExtra("ID", 0));
        setTitle(reciper.getTitle());
        init();
    }

    private void ActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    TextView txtCategry, txtRepTime, timecook;
    TextView serving, txttp, content, txtLink;

    private void init() {
        txtCategry = (TextView) findViewById(R.id.txtCategry);
        txtRepTime = (TextView) findViewById(R.id.txtRepTime);
        serving = (TextView) findViewById(R.id.serving);
        body = (TextView) findViewById(R.id.body);
        timecook = (TextView) findViewById(R.id.timecook);
        txttp = (TextView) findViewById(R.id.tp);
        txtLink = (TextView) findViewById(R.id.txtLink);
        content = (TextView) findViewById(R.id.content);
        Setvalue();
    }

    public void Setvalue() {
        Reciper reciper = reciperDao.getReciperById(intent.getIntExtra("ID", 0));
        txtCategry.setText(categoryDao.getItemById(reciper.getId_cate()).getTitle());
        txtRepTime.setText("Thời gian chuẩn bị: " + reciper.getPrep_time() + " Phút");
        timecook.setText("Thời gian  nấu: " + reciper.getCook_time() + " Phút");
        serving.setText("Cho: " + reciper.getNumber() + " Người");
        //
        txtLink.setText(!reciper.getLink().trim().equals("") ? reciper.getLink() : "");
        body.setText(reciper.getContent());
        String tp = "";
        for (int i = 0; i < ingredientDao.GetlistByReciperId(reciper.getId()).size(); i++) {
            Ingredient ingredient = ingredientDao.GetlistByReciperId(reciper.getId()).get(i);
            tp = tp + ingredient.getQuantity() + "" + ingredient.getUnit_Type() + " " + ingredient.getTitle() + "\n";
        }
        txttp.setText(tp);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudetails, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_update) {
            Intent intent = new Intent(getApplicationContext(), Activity_UpdateReciper.class);
            Activity_ReciperDetails.RECIPERID = reciper.getId();
            startActivity(intent);
        }
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), Activity_CategoryDetails.class);
            intent.putExtra("ID", reciper.getId());
            startActivity(intent);
        } else if (item.getItemId() == R.id.item_delete) {
            reciperDao.Delete(reciper.getId());
            Intent intent = new Intent(getApplicationContext(), Activity_CategoryDetails.class);
            intent.putExtra("ID", reciper.getId());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
