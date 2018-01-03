package efood.com.food.Adapter.Expand;


public class itemGroup {
    private String title;
    private String image;
    private int size;

    public itemGroup(String title, String image, int size) {
        this.title = title;
        this.image = image;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
