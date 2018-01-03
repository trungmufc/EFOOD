package efood.com.food.Model;

/**
 * Created by loc on 10/03/2016.
 */
public class Schedule {

    private int Id;
    private int type_meal;
    private String date_mead;
    private int status;
    private int reciper_id;

    public Schedule(int id, int type_meal, String date_mead, int status, int reciper_id) {
        Id = id;
        this.type_meal = type_meal;
        this.date_mead = date_mead;
        this.status = status;
        this.reciper_id = reciper_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType_meal() {
        return type_meal;
    }

    public void setType_meal(int type_meal) {
        this.type_meal = type_meal;
    }

    public String getDate_mead() {
        return date_mead;
    }

    public void setDate_mead(String date_mead) {
        this.date_mead = date_mead;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReciper_id() {
        return reciper_id;
    }

    public void setReciper_id(int reciper_id) {
        this.reciper_id = reciper_id;
    }

    public Schedule() {

    }

}
