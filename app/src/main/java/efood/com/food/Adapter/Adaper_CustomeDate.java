package efood.com.food.Adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import efood.com.food.R;
import efood.com.food.Untils.PagerDatePickerDateFormat;
import efood.com.food.Untils.adapter.AbsDateAdapter;
import efood.com.food.Untils.adapter.AbsDateItemHolder;
import efood.com.food.Untils.model.DateItem;

//import efood.loc.com.efood.R;
//import efood.loc.com.efood.Untils.PagerDatePickerDateFormat;
//import efood.loc.com.efood.Untils.adapter.AbsDateAdapter;
//import efood.loc.com.efood.Untils.adapter.AbsDateItemHolder;
//import efood.loc.com.efood.Untils.model.DateItem;
//
///**
// * Created by loc on 26/02/2016.
// */
public class Adaper_CustomeDate extends AbsDateAdapter<Adaper_CustomeDate.CustomDateViewHolder> {

    public Adaper_CustomeDate(Date start, Date end) {
        this(start, end, null);
    }

    public Adaper_CustomeDate(Date start, Date end, Date defaultSelectedDate) {
        super(start, end, defaultSelectedDate);
    }

    @Override
    protected void onDateItemHolderClick(CustomDateViewHolder itemHolder) {
        if (onDateItemListener != null) {
            onDateItemListener.onDateItemClick(getItem(itemHolder.getPosition()), itemHolder.getPosition());
        }

        if (selectedDate != -1 && selectedDateView != null) {
            selectedDateView.changeDateIndicatorColor(false);
            selectedDateView.changeTextColor(false);
        }

        selectedDateView = itemHolder;
        selectedDate = dateItems.get(itemHolder.getPosition()).getDate().getTime();

        selectedDateView.changeDateIndicatorColor(true);
        selectedDateView.changeTextColor(true);
    }

    @Override
    public CustomDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_view_customedate, parent, false);

        return new CustomDateViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(CustomDateViewHolder dateItemHolder, int position) {
        DateItem dateItem = dateItems.get(position);

        dateItemHolder.setDay(dateItem.getDate());
        dateItemHolder.setMonthName(dateItem.getDate());
        dateItemHolder.setDayName(dateItem.getDate());

        dateItemHolder.itemView.setSelected(true);

        if (isDateSelected(dateItem)) {
            dateItemHolder.updateDateItemView(true);
            selectedDateView = dateItemHolder;
        } else {
            dateItemHolder.updateDateItemView(false);
        }
    }

    static class CustomDateViewHolder extends AbsDateItemHolder {

        TextView tvDay;
        TextView tvMonth;
        TextView tvDayName;
        RelativeLayout viewDateIndicator;

        Resources resources;

        public CustomDateViewHolder(View itemView, AbsDateAdapter dateAdapter) {
            super(itemView, dateAdapter);

            this.resources = itemView.getResources();

            tvDay = (TextView) itemView.findViewById(R.id.tv_date_picker_day);
            tvMonth = (TextView) itemView.findViewById(R.id.tv_date_picker_month_name);
            tvDayName = (TextView) itemView.findViewById(R.id.tv_date_picker_day_name);
            viewDateIndicator = (RelativeLayout) itemView.findViewById(R.id.view_date_indicator);
        }

        @Override
        public void changeTextColor(boolean isSelected) {
            if (isSelected) {
                tvDay.setTextColor(resources.getColor(R.color.date_custom_item_selected_indicator));
                tvDayName.setTextColor(resources.getColor(R.color.date_custom_item_day_name));
                tvMonth.setTextColor(resources.getColor(R.color.date_custom_item_month_name));
            } else {
                tvDay.setTextColor(resources.getColor(R.color.date_item_unselected_indicator));
                tvDayName.setTextColor(resources.getColor(R.color.date_item_unselected_indicator));
                tvMonth.setTextColor(resources.getColor(R.color.date_item_unselected_indicator));
            }
        }

        @Override
        public void setDay(Date date) {
            tvDay.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_FORMAT.format(date));
        }

        @Override
        public void setMonthName(Date date) {
            tvMonth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
        }

        @Override
        public void setDayName(Date date) {
            tvDayName.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_NAME_FORMAT.format(date));
        }

        @Override
        public void changeDateIndicatorColor(boolean isSelected) {
            if (isSelected) {
                viewDateIndicator.setBackgroundResource(R.color.date_custom_item_selected_indicator);
            } else {
                viewDateIndicator.setBackgroundResource(R.color.date_custom_item_unselected_indicator);
            }
        }

        @Override
        protected View getCurrentViewToAnimate() {
            return tvDay;
        }
    }

}

