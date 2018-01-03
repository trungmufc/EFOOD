package efood.com.food.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import efood.com.food.Alam.AlamReserver;
import efood.com.food.Data.Dao.Dao_Schedule;
import efood.com.food.Model.Schedule;
import efood.com.food.R;
import efood.com.food.Untils.PagerDatePickerDateFormat;
import efood.com.food.Untils.adapter.DatePagerFragmentAdapter;
import efood.com.food.Untils.adapter.DefaultDateAdapter;
import efood.com.food.Untils.model.DateItem;
import efood.com.food.Untils.view.DateRecyclerView;
import efood.com.food.Untils.view.RecyclerViewInsetDecoration;

//someTextView.setPaintFlags(someTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
public class Frament_AddCalender extends Fragment implements View.OnClickListener {
    AlamReserver alarm = new AlamReserver();
    View v;
    private ViewPager pager;
    private DateRecyclerView dateList;
    //    FloatingActionButton fab;
    String getdate;
    int foodtype = 1;
    Schedule schedule;
    Dao_Schedule scheduleDao;
    Date start = null;
    Date end = null;
    Date defaultDate = null;

    public String getGetdate() {
        return getdate;
    }

    public void setGetdate(String getdate) {
        this.getdate = getdate;
    }

    public static Frament_AddCalender newInstance() {
        return new Frament_AddCalender();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frament_caculator, container, false);
        scheduleDao = new Dao_Schedule(getContext());
        return v;
    }

    //    fab
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatePager(view);
        init(view);
    }

    private void init(View v) {

    }

    SimpleDateFormat format;

    // set date time menu example
    private void DatePager(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        dateList = (DateRecyclerView) view.findViewById(R.id.date_list);
        dateList.addItemDecoration(new RecyclerViewInsetDecoration(getActivity(), R.dimen.date_card_insets));

        try {
            //
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("01-01-1990");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("01-01-2050");
            Date date = new Date();
            format = new SimpleDateFormat("dd-MM-yyyy");
            String DateDefault = format.format(date.getTime());
            setGetdate(DateDefault);
            defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(DateDefault);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateList.setAdapter(new DefaultDateAdapter(start, end, defaultDate));
        DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date) {
                setGetdate(format.format(date));
                getActivity().setTitle(format.format(date));
                return SimplePageFragment.newInstance(position, date);
            }
        };
        pager.setAdapter(fragmentAdapter);
        dateList.setPager(pager);
        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {
//                scheduleDao


            }

            @Override
            public void onDatePickerPageSelected(int position) {
            }

            @Override
            public void onDatePickerPageStateChanged(int state) {
            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.calender, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_today:
                dateList.setAdapter(new DefaultDateAdapter(start, end, defaultDate));
                dateList.setPager(pager);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
    }
}

