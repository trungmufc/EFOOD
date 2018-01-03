package efood.com.food.Model;


public class Reciper {
    private int id;
    private String title;
    private int id_cate;
    private String link;
    private String content;
    private int prep_time;
    private int id_tp;
    private int cook_time;
    private int number;

    public Reciper(int id, String title, int id_cate, String link, String content, int prep_time, int id_tp, int cook_time, int number) {
        this.id = id;
        this.title = title;
        this.id_cate = id_cate;
        this.link = link;
        this.content = content;
        this.prep_time = prep_time;
        this.id_tp = id_tp;
        this.cook_time = cook_time;
        this.number = number;
    }

    public Reciper() {
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

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(int prep_time) {
        this.prep_time = prep_time;
    }

    public int getId_tp() {
        return id_tp;
    }

    public void setId_tp(int id_tp) {
        this.id_tp = id_tp;
    }

    public int getCook_time() {
        return cook_time;
    }

    public void setCook_time(int cook_time) {
        this.cook_time = cook_time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
