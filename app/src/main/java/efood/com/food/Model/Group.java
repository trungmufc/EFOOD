package efood.com.food.Model;

import com.mylibrary.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import efood.com.food.Adapter.Expand.itemGroup;

/**
 * Created by loc on 09/03/2016.
 */
public class Group extends ParentListItem {

    private String mName;
    //    private FoodType food;
    private ArrayList<Reciper> mIngredients;
    int i;
    itemGroup itemG;

    public Group(itemGroup itemGroup, ArrayList<Reciper> list, int i) {
        this.mIngredients = list;
        this.itemG = itemGroup;
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public itemGroup getItemG() {
        return itemG;
    }

    public void setItemG(itemGroup itemG) {
        this.itemG = itemG;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<Reciper> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(ArrayList<Reciper> mIngredients) {
        this.mIngredients = mIngredients;
    }

    @Override
    public List<Reciper> getChildItemList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;

    }

    @Override
    public boolean isLoadMore() {
        return true;

    }
}
