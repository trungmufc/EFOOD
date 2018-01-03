package efood.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import efood.com.food.Data.Dao.Dao_Reciper;
import efood.com.food.Model.mCategory;
import efood.com.food.R;
import efood.com.food.Untils.adapter.ItemOnclick;


public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.Viewholer> {
    ArrayList<mCategory> categories;
    Dao_Reciper reciperDao;
    ItemOnclick itemOnclick;

    public Adapter_Category(Context context, ArrayList<mCategory> list) {
        this.categories = list;
        reciperDao = new Dao_Reciper(context);
    }

    public void SetOnclickListener(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    @Override
    public Viewholer onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new Viewholer(v);
    }

    @Override
    public void onBindViewHolder(Viewholer h, int position) {
        h.txtTitle.setText(categories.get(position).getTitle());
        h.txtContent.setText("Có  : " + reciperDao.getlistByCateId(categories.get(position).getId()).size() + " món");
        h.SetOnclick(itemOnclick);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView imageView;
        TextView txtTitle, txtContent;
        ItemOnclick itemOnclick;

        public Viewholer(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_dinner);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_dinner);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void SetOnclick(ItemOnclick itemOnclick) {
            this.itemOnclick = itemOnclick;
        }

        @Override
        public void onClick(View v) {
            itemOnclick.onclick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemOnclick.onclick(v, getPosition(), true);
            return false;
        }
    }

}
