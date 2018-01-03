package efood.com.food.Adapter.Expand;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mylibrary.ViewHolder.ParentViewHolder;

import efood.com.food.Model.Group;
import efood.com.food.R;


public class
GroupViewHolder extends ParentViewHolder {
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private TextView txtTitle, txtCount;
    RelativeLayout view;

    public GroupViewHolder(View itemView) {
        super(itemView);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_dinner);
        view = (RelativeLayout) itemView.findViewById(R.id.view);
        txtCount = (TextView) itemView.findViewById(R.id.txtCount);
    }

    public void bind(Group group) {
        txtTitle.setText(group.getItemG().getTitle());
        view.setBackgroundColor(Color.parseColor("#60" + group.getItemG().getImage()));
        txtCount.setText(group.getItemG().getSize() + " Món");
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
//                mArrowExpandImageView.setRotation(ROTATED_POSITION);
            } else {
//                mArrowExpandImageView.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation =
                        new RotateAnimation(ROTATED_POSITION, INITIAL_POSITION, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation =
                        new RotateAnimation(-1 * ROTATED_POSITION, INITIAL_POSITION, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
//            mArrowExpandImageView.startAnimation(rotateAnimation);
        }
    }
}
