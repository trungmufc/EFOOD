package efood.com.food.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylibrary.Adapter.ExpandableRecyclerAdapter;
import com.mylibrary.Model.ParentListItem;

import java.util.List;

import efood.com.food.Adapter.Expand.GroupViewHolder;
import efood.com.food.Adapter.Expand.ItemViewHolder;
import efood.com.food.Model.Group;
import efood.com.food.Model.Reciper;
import efood.com.food.R;
import efood.com.food.Untils.adapter.ItemOnclick;

public class Adapter_GroupMeal extends ExpandableRecyclerAdapter<GroupViewHolder, ItemViewHolder> {

    private LayoutInflater mInflator;
    ItemOnclick itemOnclick;

    public Adapter_GroupMeal(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    public void Onclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    @Override
    public GroupViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.item_group, parentViewGroup, false);
        return new GroupViewHolder(recipeView);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View ingredientView = mInflator.inflate(R.layout.item_childfood, childViewGroup, false);
        return new ItemViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(GroupViewHolder groupViewHolder, int position, ParentListItem parentListItem) {
        Group group = (Group) parentListItem;
        groupViewHolder.bind(group);
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder itemViewHolder, int position, Object childListItem) {
        Reciper redReciper = (Reciper) childListItem;
        itemViewHolder.bind(redReciper);
        itemViewHolder.Onclick(itemOnclick);
    }
}
