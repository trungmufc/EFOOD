package efood.com.food.Model;

public class Ingredient {
    private int id;
    private String title;
    private double quantity;
    private String unit_Type;
    private int re_id;

    public Ingredient(int id, String title, double quantity, String unit_Type, int re_id) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.unit_Type = unit_Type;
        this.re_id = re_id;
    }

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit_Type() {
        return unit_Type;
    }

    public void setUnit_Type(String unit_Type) {
        this.unit_Type = unit_Type;
    }

    public int getRe_id() {
        return re_id;
    }

    public void setRe_id(int re_id) {
        this.re_id = re_id;
    }
}
