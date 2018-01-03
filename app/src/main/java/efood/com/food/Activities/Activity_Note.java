package efood.com.food.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import efood.com.food.R;

public class Activity_Note extends AppCompatActivity {
    EditText edt;
    Intent intent, intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ActionBar();
        intent2 = getIntent();
        intent = new Intent();
        init();

    }


    private void ActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Nội dung hướng dẩn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        edt = (EditText) findViewById(R.id.body);
        edt.setText(intent2.getStringExtra("content"));
    }

    public static class LineEditText extends EditText {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                intent.putExtra("content", "");
//                setResult(122, intent);
//                finish();
                break;
            case R.id.item_success:
                intent.putExtra("content", edt.getText() + "");
                setResult(122, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemsuccess, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
//        intent.putExtra("content", "");
//        setResult(122, intent);
        super.onBackPressed();
    }
}
