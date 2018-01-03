package efood.com.food.Adapter.Expand;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mylibrary.ViewHolder.ChildViewHolder;

import efood.com.food.Model.Reciper;
import efood.com.food.R;
import efood.com.food.Untils.adapter.ItemOnclick;


public class ItemViewHolder extends ChildViewHolder {
    private TextView txtitle, txtTimeTotal;

    public ItemViewHolder(final View itemView) {
        super(itemView);
        txtitle = (TextView) itemView.findViewById(R.id.txtitle);
        txtTimeTotal = (TextView) itemView.findViewById(R.id.txtTimeTotal);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnclick.onclick(v, getPosition(), false);
            }
        });
    }

    ItemOnclick itemOnclick;

    public void Onclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;

    }


    public void bind(Reciper reciper) {
        txtitle.setText(reciper.getTitle());
        txtTimeTotal.setText("Thời gian " + reciper.getCook_time() + " phút");
///        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
//        mTimelineView.initLine(viewType);
    }

    public void setDragShow() {
        txtitle.setTextColor(Color.BLUE);
        txtitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtitle.setTextColor(Color.BLACK);
            }
        }, 3000);
    }


}
