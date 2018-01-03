package efood.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import efood.com.food.Data.Dao.Dao_Ingredients;
import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.Reciper;
import efood.com.food.R;
import efood.com.food.Untils.SelectableAdapter;

public class Adapter_Reciper extends SelectableAdapter<Adapter_Reciper.ViewHolder> {
    ArrayList<Reciper> list;
    ViewHolder.ClickListener ClickListener;
    Dao_Ingredients ingredientDao;

    public Adapter_Reciper(Context context, ArrayList<Reciper> list, ViewHolder.ClickListener ClickListener) {
        this.list = list;
        this.ClickListener = ClickListener;
        ingredientDao = new Dao_Ingredients(context);
        reciperDao = new Dao_Reciper(context);
    }

    Dao_Reciper reciperDao;

    public void removeItem(int position) {


        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItems(List<Integer> positions) {
        // Reverse-sort the list
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });
        // Split the list in ranges
        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
                Log.e("SÂS", "SÂSASA");
            } else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)) {
                    ++count;
                }
                if (count == 1) {
                    removeItem(positions.get(0));
                    Log.e("SÂS", "SÂSASA");
                } else {
                    removeRange(positions.get(count - 1), count);
                    Log.e("SÂS", "SÂSASA1");
                }
                for (int i = 0; i < count; ++i) {
                    positions.remove(0);
    //                    reciperDao.DeleteByReId(list.get(0).getId());
                }


            }
        }
    }

    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            list.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reciper, parent, false);
        return new ViewHolder(v, ClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtitle.setText(list.get(position).getTitle());
        holder.txtTime.setText("Thời gian " + list.get(position).getCook_time() + " phút");
        ;
        holder.overplay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {
        TextView txtitle, txtTime;
        View overplay;
        private ClickListener listener;

        public ViewHolder(View itemView, ClickListener listener) {
            super(itemView);
            txtitle = (TextView) itemView.findViewById(R.id.txtitle);
            txtTime = (TextView) itemView.findViewById(R.id.txtTimeTotal);
            overplay = itemView.findViewById(R.id.selected_overlay);
            this.listener = listener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                return listener.onItemLongClicked(getPosition());
            }
            return false;
        }

        public interface ClickListener {
            public void onItemClicked(int position);

            public boolean onItemLongClicked(int position);
        }
    }
}
