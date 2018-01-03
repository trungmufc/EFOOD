package efood.com.food.Model;

/**
 * Created by loc on 10/03/2016.
 */
public class FoodType {
    String title;
    String Image;

    public FoodType(String title, String image) {
        this.title = title;
        Image = image;
    }

    public FoodType() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
