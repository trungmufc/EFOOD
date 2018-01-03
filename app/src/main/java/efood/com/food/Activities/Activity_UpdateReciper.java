package efood.com.food.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Arrays;

import efood.com.food.Adapter.Adapter_SpinnerCategory;
import efood.com.food.Adapter.Adapter_ingredients;
import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Data.Dao.Dao_Ingredients;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.Ingredient;
import efood.com.food.Model.Reciper;
import efood.com.food.Model.mCategory;
import efood.com.food.R;

public class Activity_UpdateReciper extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    MaterialEditText edt_title;
    MaterialEditText edt_preTime;
    MaterialEditText edt_timecook;
    MaterialEditText edt_serving;
    int itemCategory = 0;
    TextView body;
    ArrayList<Integer> integers;

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    Adapter_SpinnerCategory adapter_spinnerCategory;
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
    Reciper reciper;

    public void UpdateAll() {
        if (st(edt_title).trim().equals("")) edt_title.setError("Chưa có gía trị");
        else if (st(edt_preTime).trim().equals("")) edt_preTime.setError("Chưa có gía trị");
        else if (st(edt_timecook).trim().equals("")) edt_timecook.setError("Chưa có gía trị");
        else if (st(edt_serving).trim().equals("")) edt_serving.setError("Chưa có gía trị");
        else if (mAutoLabel.getChildCount() <= 0)
            Snackbar.make(body, "Chưa có thành phần!", Snackbar.LENGTH_SHORT).show();
        else if (!Check()) ;
        else if ((body.getText() + "").trim().equals(""))
            Snackbar.make(body, "Chưa có hướng dẫn", Snackbar.LENGTH_SHORT).show();
        else {
            Reciper r = new Reciper();
            r.setPrep_time(Integer.parseInt(st(edt_preTime)));
            r.setTitle(st(edt_title));
            r.setLink((st(edt_link).trim().length() > 0 ? st(edt_link) : ""));
            r.setCook_time(Integer.valueOf(st(edt_timecook)));
            r.setContent(body.getText() + "");
            r.setNumber(Integer.parseInt(st(edt_serving)));
            r.setId(reciper.getId());
            r.setId_cate(getItemCategory() > 0 ? getItemCategory() : list.get(0).getId());
            reciperDao.Update(r);
            UpdateInc();
            Intent intent = new Intent(getApplicationContext(), Activity_ReciperDetails.class);
            intent.putExtra("ID", reciper.getId());
            startActivity(intent);
            finish();
        }
    }

    public void LoadReciper() {
        edt_link.setText(reciper.getLink());
        edt_preTime.setText(reciper.getPrep_time() + "");
        edt_serving.setText(reciper.getNumber() + "");
        edt_timecook.setText(reciper.getCook_time() + "");
        edt_title.setText(reciper.getTitle());
        body.setText(reciper.getContent());
        LoadInc();
        for (int i = 0; i < categoryDao.getlist().size(); i++) {
            if (categoryDao.getlist().get(i).getId() == reciper.getId_cate()) {
                soSpinner.setSelection(i);
                return;
            }
        }

    }


    public void LoadInc() {
        ArrayList<Ingredient> ingredients = ingredientDao.GetlistByReciperId(reciper.getId());
        for (int i = 0; i < ingredients.size(); i++) {
            Addinc(ingredients.get(i), true);
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
        integers = new ArrayList<>();
        this.setContentView(R.layout.activity_add_recipe);
        reciper = reciperDao.getReciperById(Activity_ReciperDetails.RECIPERID);
        this.Actionbar();
        this.init();
    }

    private void Actionbar() {
        this.toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Cập nhật");
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
//        Frament_reciper
        this.edt_serving = (MaterialEditText) this.findViewById(R.id.edt_serving);
        body = (TextView) findViewById(R.id.body);
        this.edt_link = (MaterialEditText) this.findViewById(R.id.edt_link);
        this.btn_add_tp = (ImageButton) this.findViewById(R.id.btn_add_tp);
        btn_add_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addinc(null, false);
            }
        });
        this.list = this.categoryDao.getlist();
        adapter_spinnerCategory = new Adapter_SpinnerCategory(this.categoryDao.getlist());
        this.soSpinner.setAdapter(adapter_spinnerCategory);
        LoadReciper();
        soSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setItemCategory(list.get(position).getId());
                Log.e("SÂSAAS", getItemCategory() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_add_content.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(this.getApplicationContext(), Activity_ReciperDetails.class);
                intent.putExtra("ID", reciper.getId());
                startActivity(intent);
                return true;
            case R.id.item_save:
                UpdateAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    MaterialEditText edttitle, edtQuantity;
    Spinner spUnit;
    //
    ImageView imageButton;


    public void InflaterInit(View layout) {
        edttitle = (MaterialEditText) layout.findViewById(R.id.edttitle);
        edtQuantity = (MaterialEditText) layout.findViewById(R.id.edt_quantity);
        spUnit = (Spinner) layout.findViewById(R.id.spUnit);
        imageButton = (ImageButton) layout.findViewById(R.id.imageButton);
        spUnit.setAdapter(Adapter_SpinnerUnit);
        edttitle.requestFocus();
    }

    ArrayAdapter<String> Adapter_SpinnerUnit;

    public void Addinc(Ingredient ingredient, boolean bo) {
        Adapter_SpinnerUnit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Untils);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.item_in, (ViewGroup) findViewById(R.id.container), false);
        InflaterInit(layout);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        layout.setTag(bo);

        if (ingredient != null) {
            edttitle.setText(ingredient.getTitle());
            edtQuantity.setText(ingredient.getQuantity() + "");
            Log.e("sâssas", Arrays.asList(Untils).indexOf(ingredient.getUnit_Type()) + "");
            spUnit.setSelection(Arrays.asList(Untils).indexOf(ingredient.getUnit_Type()));
            layout.setId(ingredient.getId());
        } else layout.setId(mAutoLabel.getChildCount());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mAutoLabel.getChildCount(); i++) {
                    if (mAutoLabel.getChildAt(i).getId() == ((View) ((View) v.getParent()).getParent()).getId()) {
                        int id = ((View) ((View) v.getParent()).getParent()).getId();
                        if (ingredientDao.getlistById(id).size() > 0 && (Boolean) ((View) ((View) v.getParent()).getParent()).getTag()) {
                            integers.add(id);
                        }
                        mAutoLabel.removeView(mAutoLabel.getChildAt(i));
                        return;
                    }
                }
            }
        });
        mAutoLabel.addView(layout);

    }

    public void UpdateInc() {
        /// thuc delete tat ca cac gia tri hien tai
        if (integers.size() > 0) {
            for (int id : integers) {
                ingredientDao.DeleteById(id);
            }
        }
        for (int i = 0; i < mAutoLabel.getChildCount(); i++) {
            Ingredient ingredient = new Ingredient();
            ViewGroup v = (ViewGroup) mAutoLabel.getChildAt(i);
            edttitle = (MaterialEditText) v.findViewById(R.id.edttitle);
            edtQuantity = (MaterialEditText) v.findViewById(R.id.edt_quantity);
            spUnit = (Spinner) v.findViewById(R.id.spUnit);

            if ((boolean)v.getTag() == true) {
                ingredient.setId(v.getId());
                ingredient.setTitle(edttitle.getText() + "");
                ingredient.setQuantity(Double.parseDouble(edtQuantity.getText() + ""));
                ingredient.setUnit_Type(spUnit.getSelectedItem() + "");
                ingredient.setRe_id(reciper.getId());
                ingredientDao.Update(ingredient);
            } else {
                ingredient.setTitle(edttitle.getText() + "");
                ingredient.setQuantity(Double.parseDouble(edtQuantity.getText() + ""));
                ingredient.setUnit_Type(spUnit.getSelectedItem() + "");
                ingredient.setRe_id(reciper.getId());
                ingredientDao.Insert(ingredient);
            }
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
                edttitle.setError("Giá trị rỗng");
                return false;
            } else if (TextUtils.isEmpty(edtQuantity.getText() + "")) {
                edtQuantity.setError("Giá trị rỗng");
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

//    public static class LineEditText extends TextView {
//        public LineEditText(Context context, AttributeSet attrs) {
//            super(context, attrs);
//            mRect = new Rect();
//            mPaint = new Paint();
//            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//            mPaint.setColor(Color.parseColor("#cccccc"));
//        }
//
//        private Rect mRect;
//        private Paint mPaint;
//
//        @Override
//
//        protected void onDraw(Canvas canvas) {
//            int height = getHeight();
//            int line_height = getLineHeight();
//            int count = height / line_height;
//            if (getLineCount() > count)
//                count = getLineCount();
//            Rect r = mRect;
//            Paint paint = mPaint;
//            int baseline = getLineBounds(0, r);
//            for (int i = 0; i < count; i++) {
//                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
//                baseline += getLineHeight();
//                super.onDraw(canvas);
//            }
//        }
//    }
}
