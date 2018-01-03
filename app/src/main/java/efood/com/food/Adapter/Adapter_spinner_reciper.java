package efood.com.food.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import efood.com.food.Model.Reciper;
import efood.com.food.R;

/**
 * Created by loc on 10/03/2016.
 */
public class Adapter_spinner_reciper extends BaseAdapter {
    ArrayList<Reciper> recipers;

    public Adapter_spinner_reciper(ArrayList<Reciper> List) {
        this.recipers = List;

    }

    @Override
    public int getCount() {
        return recipers.size();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
//            LayoutInflater inflater = context.getLayoutInflater();
//            rowView = inflater.inflate(R.layout.rowlayout, null);
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinerreciper, parent, false);
            // configure view holder
            ViewHoler viewHolder = new ViewHoler();
            viewHolder.txtTitle = (TextView) rowView.findViewById(R.id.txtitle);
            viewHolder.txtTimeTotal = (TextView) rowView.findViewById(R.id.txtTimeTotal);
            rowView.setTag(viewHolder);
        }
        ViewHoler viewHoler = (ViewHoler) rowView.getTag();
        viewHoler.txtTitle.setText(recipers.get(position).getTitle());
        viewHoler.txtTimeTotal.setText("Thời gian " + recipers.get(position).getCook_time() + " m");
        return rowView;
    }


    class ViewHoler {
        TextView txtTitle;
        TextView txtTimeTotal;

    }
}
