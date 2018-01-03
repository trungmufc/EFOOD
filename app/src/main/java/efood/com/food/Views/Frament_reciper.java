package efood.com.food.Views;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cocosw.bottomsheet.BottomSheet;
import com.github.clans.fab.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import efood.com.food.Activities.Activity_CategoryDetails;
import efood.com.food.Activities.Activity_AddReciper;
import efood.com.food.Adapter.Adapter_Category;
import efood.com.food.Data.Dao.Dao_Category;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.mCategory;
import efood.com.food.R;
import efood.com.food.Untils.DividerItemDecoration;
import efood.com.food.Untils.adapter.ItemOnclick;


public class Frament_reciper extends Fragment {
    View v;
    FloatingActionButton btnAddReciper;
    Adapter_Category adapter_category;
    Dao_Reciper reciperDao;
    Dao_Category categoryDao;
    ArrayList<mCategory> list;
    private int mScrollOffset = 4;
    public static int CATEGRYID = 0;
    public static String TITLE = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frament_reciper, container, false);
        setHasOptionsMenu(true);
        init();
        recycleCateGory.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        btnAddReciper.hide(true);
                    } else {
                        btnAddReciper.show(true);
                    }
                }
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ReCategory();
    }

    RecyclerView recycleCateGory;

    private void ReCategory() {
        categoryDao = new Dao_Category(getContext());
        list = categoryDao.getlist();
        recycleCateGory.setHasFixedSize(true);
        recycleCateGory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_category = new Adapter_Category(getActivity(), list);
        recycleCateGory.setAdapter(adapter_category);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recycleCateGory.addItemDecoration(itemDecoration);
        // onclick
        adapter_category.SetOnclickListener(new ItemOnclick() {
            @Override
            public void onclick(View v, int postion, boolean isLongclick) {
                if (!isLongclick) {
                    Intent intent = new Intent(getActivity(), Activity_CategoryDetails.class);
//                    intent.putExtra("Id", list.get(postion).getId());
//                    intent.putExtra("Title", list.get(postion).getTitle());
                    Frament_reciper.CATEGRYID = list.get(postion).getId();
                    Frament_reciper.TITLE = list.get(postion).getTitle();

                    startActivity(intent);
                } else {
                    botomsheet(postion);
                }

            }
        });
    }

    private void init() {
        recycleCateGory = (RecyclerView) v.findViewById(R.id.Recategory);
        btnAddReciper = (FloatingActionButton) v.findViewById(R.id.fab);
        btnAddReciper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Activity_AddReciper.class
                ));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_resiper, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_save:
                return true;
            case R.id.Category:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                CategoryDialog categoryDialog = new CategoryDialog().newInstance("mCategory");
                categoryDialog.show(fm, "fragment_edit_name");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("ValidFragment")
    class CategoryDialog extends DialogFragment {
        MaterialEditText materialEditText;
        Button btnAddcategory, btnCancel;

        public CategoryDialog() {

        }

        CategoryDialog newInstance(String title) {
            CategoryDialog frag = new CategoryDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return inflater.inflate(R.layout.dialog_category, container);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            // Get field from view
            materialEditText = (MaterialEditText) view.findViewById(R.id.edt_Category);
            btnAddcategory = (Button) view.findViewById(R.id.btnAddcate);
            btnCancel = (Button) view.findViewById(R.id.btnCancel);
            String title = getArguments().getString("title", "Enter Name");
            getDialog().setTitle(title);
            materialEditText.requestFocus();
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            btnAddcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryDao = new Dao_Category(getContext());
                    if (TextUtils.isEmpty(materialEditText.getText() + ""))
                        materialEditText.setError("Giá trị rổng");
                    else {
                        if (!categoryDao.getbyname(materialEditText.getText().toString()))
                            materialEditText.setError("Nhóm thực phẩm đã tồn tại");
                        else {
                            mCategory c = new mCategory();
                            c.setTitle(materialEditText.getText() + "");
                            categoryDao.insert(c);
                            categoryDao.getlist();
                            list.add(c);
                            dismiss();
                        }
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }


    class Capnhat extends DialogFragment {
        MaterialEditText materialEditText;
        Button btnAddcategory, btnCancel;
        int postion;


        public Capnhat() {
        }

        Capnhat newInstance(String title, int position) {
            Capnhat frag = new Capnhat();
            this.postion = position;
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return inflater.inflate(R.layout.dialog_category, container);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final mCategory c = list.get(postion);
            materialEditText = (MaterialEditText) view.findViewById(R.id.edt_Category);
            btnAddcategory = (Button) view.findViewById(R.id.btnAddcate);
            btnCancel = (Button) view.findViewById(R.id.btnCancel);
            materialEditText.setText(c.getTitle());
            String title = getArguments().getString("title", "Enter Name");
            getDialog().setTitle(title);
            materialEditText.requestFocus();
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            btnAddcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryDao = new Dao_Category(getContext());
                    if (TextUtils.isEmpty(materialEditText.getText() + ""))
                        materialEditText.setError("Giá trị rổng");
                    else {
                        if (!categoryDao.getbyname(materialEditText.getText().toString()))
                            materialEditText.setError("Nhóm thực phẩm đã tồn tại");
                        else {
                            c.setTitle(materialEditText.getText() + "");
                            categoryDao.update(c);
                            categoryDao = new Dao_Category(getContext());
                            adapter_category.notifyDataSetChanged();
                            recycleCateGory.setAdapter(adapter_category);
                            dismiss();
                        }
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    private void botomsheet(final int posion) {
        new BottomSheet.Builder(getActivity()).title("Lựa chọn").sheet(R.menu.menu_item_sheet).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.Delete:
                        reciperDao = new Dao_Reciper(getContext());
                        if (reciperDao.getlistById(list.get(posion).getId()).size() > 0) {
                            DialogCancel();
                        } else DialogDelte(posion);

                        break;
                    case R.id.Update:
                        Capnhat c = new Capnhat().newInstance("Cập nhật", posion);
                        btnAddReciper.show(true);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        c.show(fm, "fragment_edit_name");
                        break;
                    case R.id.Cancel:
                        btnAddReciper.show(true);
                        break;
                }
            }
        }).show();
    }

    public void DialogDelte(final int posion) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        String Message = "Bạn chắc muốn xóa một nhóm thưc phẩm", Dele = "Xóa";
        boolean isDelete = true;
        reciperDao = new Dao_Reciper(getActivity());
        alertDialogBuilder.setTitle("XÁC NHẬN");
        final boolean finalIsDelete = isDelete;
        alertDialogBuilder
                .setMessage(Message)
                .setCancelable(false)
                .setPositiveButton(Dele, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        categoryDao.delete(list.get(posion).getId());
                        adapter_category.notifyDataSetChanged();
                        recycleCateGory.setAdapter(adapter_category);
                        btnAddReciper.show(true);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void DialogCancel() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        String Message = "Nhóm thực phẩm này không thể xóa";
        alertDialogBuilder.setTitle("XÁC NHẬN");
        alertDialogBuilder
                .setMessage(Message)
                .setCancelable(false)
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}