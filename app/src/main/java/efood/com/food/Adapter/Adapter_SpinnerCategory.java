package efood.com.food.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import efood.com.food.Model.mCategory;
import efood.com.food.R;

/**
 * Created by loc on 03/03/2016.
 */
public class Adapter_SpinnerCategory extends BaseAdapter {
    ArrayList<mCategory> list;
    View v;

    public Adapter_SpinnerCategory(ArrayList<mCategory> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        return list;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    ViewHole holer;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinnercategory, parent, false);
            ViewHole viewHole = new ViewHole();
            viewHole.txtTitle = (TextView) v.findViewById(R.id.txt_dinner);
            v.setTag(viewHole);
        }
        ViewHole vh = (ViewHole) v.getTag();
        vh.txtTitle.setText(list.get(position).getTitle());
        return v;
    }

    class ViewHole {
        TextView txtTitle;

    }
}
