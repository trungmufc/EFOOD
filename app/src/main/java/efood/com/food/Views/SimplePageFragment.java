package efood.com.food.Views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import efood.com.food.Adapter.Adapter_SpinnerCategory;
import efood.com.food.Adapter.Adapter_spinner_reciper;
import efood.com.food.Adapter.Expand.itemGroup;
import efood.com.food.Adapter.Adapter_GroupMeal;
import efood.com.food.Alam.AlamReserver;
import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Data.Dao.Dao_Schedule;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.Group;
import efood.com.food.Model.Reciper;
import efood.com.food.Model.Schedule;
import efood.com.food.R;
import efood.com.food.Untils.adapter.ItemOnclick;

//import efood.loc.com.viewpagerdate.R;
//import pl.rspective.pagerdatepicker.sample.R;
public class SimplePageFragment extends Fragment implements View.OnClickListener {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final String DATE_PICKER_DATE_KEY = "date_picker_date_key";
    private static final String DATE_PICKER_POSITION_KEY = "date_picker_position_key";
    private TextView tvDate;
    FloatingActionButton Float;
    private TextView tvPosition;
    private int position;
    private long date;
    ListView listView;
    ArrayList<Reciper> breakfasts;
    ArrayList<Reciper> lunchs;
    ArrayList<Reciper> snacks;
    ArrayList<Reciper> dinners;
    Dao_Schedule scheduleDao;
    Dao_Reciper resReciperDao;
    private DatePickerDialog dialogDate = null;

    public static SimplePageFragment newInstance(int position, long date) {
        Bundle bundle = new Bundle();
        bundle.putInt(DATE_PICKER_POSITION_KEY, position);
        bundle.putLong(DATE_PICKER_DATE_KEY, date);
        Log.e("sáasadsda", date + "");
        SimplePageFragment simplePageFragment = new SimplePageFragment();
        simplePageFragment.setArguments(bundle);
        return simplePageFragment;
    }

    private void initArrayList(String Date) {
        try {
            breakfasts = scheduleDao.getItemReciper(1, Date);
            lunchs = scheduleDao.getItemReciper(2, Date);
            dinners = scheduleDao.getItemReciper(3, Date);
            snacks = scheduleDao.getItemReciper(4, Date);
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        Log.e("ChexLength", breakfasts.size() + "HAHA");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(DATE_PICKER_POSITION_KEY, -1);
        date = getArguments().getLong(DATE_PICKER_DATE_KEY, -1);

    }

    ArrayList<String> list = new ArrayList<>();
    List<Group> groups;
    Dao_Reciper reciperDao;
    RecyclerView recyclerView;
    Adapter_GroupMeal mAdapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scheduleDao = new Dao_Schedule(getActivity());
        reciperDao = new Dao_Reciper(getContext());
        view = inflater.inflate(R.layout.fragment_page_simple, container, false);
        Float = (FloatingActionButton) view.findViewById(R.id.fab);
        Float.setOnClickListener(this);
        setVal();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mAdapter = new Adapter_GroupMeal(getActivity(), groups);
        recyclerView.setAdapter(mAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Onclick();
        return view;
    }

    //----
    public void Onclick() {
        mAdapter.Onclick(new ItemOnclick() {
            @Override
            public void onclick(View v, int postion, boolean isLongclick) {
                if (!isLongclick) {

                }
            }
        });
    }
    // Đổ dữ liệu đến ItemGoup
    public void setVal() {
        groups = new ArrayList<>();
        String st = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        initArrayList(st);
        itemGroup temGroup = new itemGroup("BUỔI SÁNG", "8BC34A", breakfasts.size());
        Group groupBreakfast = new Group(temGroup, breakfasts, 1);
        itemGroup itemGroup1 = new itemGroup("BUỔI TRƯA", "FF9800", lunchs.size());
        Group lunch = new Group(itemGroup1, lunchs, 2);
        itemGroup itemGroup2 = new itemGroup("BUỔI TỐI", "9E9E9E", dinners.size());
        Group dinner = new Group(itemGroup2, dinners, 3);
        itemGroup itemGroup3 = new itemGroup("ĂN VẶT", "E91E63", snacks.size());
        Group snack = new Group(itemGroup3, snacks, 4);
        groups.add(groupBreakfast);
        groups.add(lunch);
        groups.add(dinner);
        groups.add(snack);
    }

    String MeatType[] = {"Buổi Sáng", "Buổi Trưa", "Buổi Tối", "Ăn Vặt"};
    Spinner sp_type, spcategory, spreciper;

    int type = 1;
    int Id = 0;
    Button btnAddcate, btnCancel;

    ArrayList<Reciper> recipers;
    AlertDialog dialog;
    int RecID = 0;

    public int getRecID() {
        return RecID;
    }

    public void setRecID(int recID) {
        RecID = recID;
    }

    //    ArrayList
    public void DialogAddfood(Context context) {

        final Dao_Category categoryDao = new Dao_Category(context);
        scheduleDao = new Dao_Schedule(context);
        final Dao_Reciper reciperDao = new Dao_Reciper(context);
        View vs = LayoutInflater.from(context).inflate(R.layout.dialog_addfood, null);
        dialog = new AlertDialog.Builder(context)
                .setView(vs)
                .show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        TextView txtDate;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        sp_type = (Spinner) vs.findViewById(R.id.sp_type);
        spreciper = (Spinner) vs.findViewById(R.id.spreciper);
        txtDate = (TextView) vs.findViewById(R.id.txtDate);
        spcategory = (Spinner) vs.findViewById(R.id.spcateGory);
        btnAddcate = (Button) vs.findViewById(R.id.btnAddcate);
        btnCancel = (Button) vs.findViewById(R.id.btncanel);
        Adapter_SpinnerCategory adapter_spinnerCategory = new Adapter_SpinnerCategory(categoryDao.getlist());
        final ArrayAdapter<String> typeAdapte = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, MeatType);
        spcategory.setAdapter(adapter_spinnerCategory);
        txtDate.setText((new SimpleDateFormat("yyyy-MM-dd")).format(date));
        sp_type.setAdapter(typeAdapte);
        categoryDao.getlist().get(0).getId();
        if (categoryDao.getlist().size() > 0 && reciperDao.getlistByCateId(categoryDao.getlist().get(0).getId()).size() > 0) {
            setRecID(reciperDao.getlistByCateId(categoryDao.getlist().get(0).getId()).get(0).getId());
            Log.e("SÂSA", "âssaas");
        }
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /*---*/
        spreciper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RecID = recipers.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /*---*/
        spcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RecID = categoryDao.getlist().get(position).getId();
                recipers = reciperDao.getlistByCateId(categoryDao.getlist().get(position).getId());
                Adapter_spinner_reciper adapterItemReciper = new Adapter_spinner_reciper(recipers);
                spreciper.setAdapter(adapterItemReciper);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnAddcate.setOnClickListener(AddSchedule);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public View.OnClickListener AddSchedule = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("GETreciper", getRecID() + "");
            if (getRecID() > 0) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    if ((scheduleDao.getlistByDateAndReciper(type, (format.format(date)), getRecID())).size() == 0) {
                        Schedule schedule = new Schedule();
                        schedule.setDate_mead((format.format(date)));
                        schedule.setType_meal(type);
                        schedule.setReciper_id(getRecID());
                        schedule.setStatus(0);
                        Log.e("JAJAA", type + "");

                        if (getRecID() > 0) {
                            scheduleDao.insert(schedule);
                            setVal();
                            mAdapter = new Adapter_GroupMeal(getActivity(), groups);
                            recyclerView.setAdapter(mAdapter);
                            AlamReserver reserver = new AlamReserver();
                            reserver.setAlam(getActivity());
                        }

                    } else {
                        Snackbar.make(recyclerView, "Bản đã có món này", Snackbar.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();
            Onclick();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                DialogAddfood(getActivity());
                break;
        }
    }

//    //    ArrayList
//    public void DialogItem(final int position) {
//        scheduleDao = new Dao_Schedule(getActivity());
//        View vs = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete, null);
//        dialog = new AlertDialog.Builder(getActivity())
//                .setView(vs)
//                .show();
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        Window window = dialog.getWindow();
//        lp.copyFrom(window.getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//        (vs.findViewById(R.id.btndetails)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                dialog.dismiss();
//                startActivity(new Intent(getActivity(), Activity_ReciperDetails.class));
//                dialog.dismiss();
//
//            }
//        });
//        (vs.findViewById(R.id.btnDelete)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
//        Onclick();
//    }

}

