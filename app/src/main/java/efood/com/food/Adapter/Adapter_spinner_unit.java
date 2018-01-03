package efood.com.food.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import efood.com.food.R;


public class Adapter_spinner_unit extends BaseAdapter {
    String[] list;

    public Adapter_spinner_unit(String[] list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);
            ViewHole viewHole = new ViewHole();
            viewHole.txtTitle = (TextView) v.findViewById(R.id.txt_dinner);
            v.setTag(viewHole);
        }
        ViewHole vh = (ViewHole) v.getTag();
        vh.txtTitle.setText(list[position]);

        return v;
    }

    class ViewHole {
        TextView txtTitle;

    }
}
