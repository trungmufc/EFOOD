//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package efood.com.food.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import efood.com.food.Adapter.Adapter_SpinnerCategory;
import efood.com.food.Adapter.Adapter_ingredients;
import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Data.Dao.Dao_Ingredients;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.MainActivity;
import efood.com.food.Model.Ingredient;
import efood.com.food.Model.Reciper;
import efood.com.food.Model.mCategory;
import efood.com.food.R;


public class Activity_AddReciper extends AppCompatActivity implements OnClickListener {
    Toolbar toolbar;
    MaterialEditText edt_title;
    MaterialEditText edt_preTime;
    MaterialEditText edt_timecook;
    MaterialEditText edt_serving;
    int itemCategory = 0;
    TextView body;

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    MaterialEditText edt_link;
    //    MaterialEditText edt_huongdan;Log.e("SÂS", "sâssasa");
    Spinner soSpinner;
    ImageButton btn_add_tp, btn_add_content;
    Dao_Reciper reciperDao;
    Dao_Category categoryDao;
    Dao_Ingredients ingredientDao;
    ArrayList<mCategory> list;
    ArrayList<Ingredient> ingredients;
    Adapter_ingredients adapterIngredients;
    String[] Untils = new String[]{"đơn vị", "grams", "oz", "tsp", "tbls", "cups", "lb", "gal", "kg", "l", "ml", "cm"};
    private LinearLayout mAutoLabel;

    public Activity_AddReciper() {
    }

    public void InsertAll() {
        if (st(edt_title).trim().equals("")) edt_title.setError("chưa có gía trị");
        else if (st(edt_preTime).trim().equals("")) edt_preTime.setError("chưa có gía trị");
        else if (st(edt_timecook).trim().equals("")) edt_timecook.setError("chưa có gía trị");
        else if (st(edt_serving).trim().equals("")) edt_serving.setError("chưa có gía trị");
        else if (mAutoLabel.getChildCount() <= 0)
            Snackbar.make(body, "chưa có thành phần", Snackbar.LENGTH_SHORT).show();
        else if (!Check()) ;
        else if ((body.getText() + "").trim().equals(""))
            Snackbar.make(body, "chưa có Hướng dẩn", Snackbar.LENGTH_SHORT).show();
        else {
            Reciper r = new Reciper();
            r.setPrep_time(Integer.parseInt(st(edt_preTime)));
            r.setTitle(st(edt_title));
            r.setLink((st(edt_link).trim().length() > 0 ? st(edt_link) : ""));
            r.setCook_time(Integer.valueOf(st(edt_timecook)));
            r.setContent(body.getText() + "");
            r.setNumber(Integer.parseInt(st(edt_serving)));
            r.setId_cate(getItemCategory() > 0 ? getItemCategory() : list.get(0).getId());
            reciperDao.Insert(r);
            Insert();
            finish();
        }
    }

    public String st(MaterialEditText edt) {
        return edt.getText().toString();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.reciperDao = new Dao_Reciper(this);
        this.categoryDao = new Dao_Category(this);
        this.ingredientDao = new Dao_Ingredients(this);
        this.setContentView(R.layout.activity_add_recipe);
        this.Actionbar();
        this.init();
    }

    private void Actionbar() {
        this.toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Thêm công thức");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        this.ingredients = new ArrayList();
        this.adapterIngredients = new Adapter_ingredients(this.ingredients);
        this.edt_title = (MaterialEditText) this.findViewById(R.id.edttitle);
        this.soSpinner = (Spinner) this.findViewById(R.id.spCategory);
        btn_add_content = (ImageButton) this.findViewById(R.id.btn_add_content);
        this.mAutoLabel = (LinearLayout) this.findViewById(R.id.label_view);
        this.edt_preTime = (MaterialEditText) this.findViewById(R.id.edt_preTime);
        this.edt_timecook = (MaterialEditText) this.findViewById(R.id.edt_timecook);
        this.edt_serving = (MaterialEditText) this.findViewById(R.id.edt_serving);
        body = (TextView) findViewById(R.id.body);
        this.edt_link = (MaterialEditText) this.findViewById(R.id.edt_link);
        this.btn_add_tp = (ImageButton) this.findViewById(R.id.btn_add_tp);

        btn_add_tp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Addinc();
            }
        });
        this.list = this.categoryDao.getlist();
        Adapter_SpinnerCategory adapter_spinnerCategory = new Adapter_SpinnerCategory(this.categoryDao.getlist());
        this.soSpinner.setAdapter(adapter_spinnerCategory);

        soSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setItemCategory(list.get(position).getId());
                Log.e("SÂSAAS", getItemCategory() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_add_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_Note.class);
                intent.putExtra("content", (body.getText().toString()).trim().equals("") ? "" : body.getText().toString());
                startActivityForResult(intent, 122);
            }
        });
    }

    public String St(View v) {
        return v instanceof MaterialEditText ? ((MaterialEditText) v).getText() + "" : null;
    }

    public boolean isEmty(MaterialEditText edt) {
        return !TextUtils.isEmpty(this.St(edt));
    }

    private View id(int id) {
        return this.findViewById(id);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menusave, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
                return true;
            case R.id.item_save:
                InsertAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    MaterialEditText edttitle, edtQuantity;
    Spinner spUnit;
    //
    ImageView imageButton;

    public void Addinc() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.item_in, (ViewGroup) findViewById(R.id.container), false);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        layout.setId(mAutoLabel.getChildCount());
        edttitle = (MaterialEditText) layout.findViewById(R.id.edttitle);
        edttitle.requestFocus();
        imageButton = (ImageButton) layout.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mAutoLabel.getChildCount(); i++) {
                    if (mAutoLabel.getChildAt(i).getId() == ((View) ((View) v.getParent()).getParent()).getId()) {
                        mAutoLabel.removeView(mAutoLabel.getChildAt(i));
                        return;
                    }
                }
            }
        });
        edtQuantity = (MaterialEditText) layout.findViewById(R.id.edt_quantity);
        spUnit = (Spinner) layout.findViewById(R.id.spUnit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Untils);
        spUnit.setAdapter(adapter);
        mAutoLabel.addView(layout);
    }

    public void Insert() {
        for (int i = 0; i < mAutoLabel.getChildCount(); i++) {
            Ingredient ingredient = new Ingredient();
            ViewGroup v = (ViewGroup) mAutoLabel.getChildAt(i);
            /// get  two editext
            LinearLayout v1 = (LinearLayout) v.getChildAt(0);
            // get spinner
            LinearLayout v2 = (LinearLayout) v.getChildAt(1);
            edttitle = (MaterialEditText) (v1.getChildAt(0));
            Log.e("SẤDASDASD", edttitle.getText().toString() + "sâs");
            edtQuantity = (MaterialEditText) (v1.getChildAt(1));
            spUnit = (Spinner) (v2.getChildAt(0));
            ingredient.setTitle(edttitle.getText() + "");

            ingredient.setQuantity(Double.parseDouble(edtQuantity.getText() + ""));
            ingredient.setUnit_Type(spUnit.getSelectedItem() + "");
            /// get size chắc đúng
            ingredient.setRe_id(reciperDao.getlist().get(reciperDao.getlist().size() - 1).getId());
            ingredientDao.Insert(ingredient);
        }
        Log.e("resas", "âssa" + reciperDao.getlist().get(reciperDao.getlist().size() - 1).getId());
    }
    public boolean Check() {
        for (int i = 0; i < mAutoLabel.getChildCount(); i++) {
            ViewGroup v = (ViewGroup) mAutoLabel.getChildAt(i);
            LinearLayout v1 = (LinearLayout) v.getChildAt(0);
            LinearLayout v2 = (LinearLayout) v.getChildAt(1);

            edttitle = (MaterialEditText) (v1.getChildAt(0));
            edtQuantity = (MaterialEditText) (v1.getChildAt(1));
            spUnit = (Spinner) (v2.getChildAt(0));
            if (TextUtils.isEmpty(edttitle.getText() + "")) {
                edttitle.setError("Giá trị rổng");
                return false;
            } else if (TextUtils.isEmpty(edtQuantity.getText() + "")) {
                edtQuantity.setError("Giá trị rổng");
                return false;
            } else if ((spUnit.getSelectedItem().toString()).trim().equals("đơn vị")) {
                Snackbar.make(spUnit, "Bạn chưa chọn đơn vị", Snackbar.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 122 && data != null) {
            if (data.getStringExtra("content") != null || data.getStringExtra("content").trim().equals("")) {
                body.setText(data.getStringExtra("content"));
            }
        }
    }

    public static class LineEditText extends TextView {
        public LineEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.parseColor("#cccccc"));
        }

        private Rect mRect;
        private Paint mPaint;

        @Override

        protected void onDraw(Canvas canvas) {
            int height = getHeight();
            int line_height = getLineHeight();
            int count = height / line_height;
            if (getLineCount() > count)
                count = getLineCount();
            Rect r = mRect;
            Paint paint = mPaint;
            int baseline = getLineBounds(0, r);
            for (int i = 0; i < count; i++) {
                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
                baseline += getLineHeight();
                super.onDraw(canvas);
            }
        }
    }

}
