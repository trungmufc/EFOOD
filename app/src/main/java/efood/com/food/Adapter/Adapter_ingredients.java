package efood.com.food.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import efood.com.food.Model.Ingredient;
import efood.com.food.R;

public class Adapter_ingredients extends BaseAdapter {
    ArrayList<Ingredient> list;

    public Adapter_ingredients(ArrayList<Ingredient> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    View v;
    ViewHoler viewHoler;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ingre, parent, false);
            viewHoler = new ViewHoler();
            viewHoler.item = (TextView) v.findViewById(R.id.item);
            v.setTag(viewHoler);
        }
        viewHoler = (ViewHoler) v.getTag();
        String title = list.get(position).getTitle();
        String Quantity = +list.get(position).getQuantity() + " " + list.get(position).getUnit_Type();

        return v;
    }

    private static class ViewHoler {
        TextView item;

    }
}
