package efood.com.food.Data;

public interface database {
    public static String User = "User",
            Id = "Id", Username = "Username",
            Email = "Email", reciper = "reciper", title = "title", type_meal = "type_meal", ingredients = "ingredients",
            introdustion = "introdustion", servings = "servings", status = "status", date_meal = "date_meal",
            password = "password", ingredients_id = "ingredients_id", Cook_time = "Cook_time", prep_time = "prep_time", Re_Id = "Re_Id",
            category_id = "category_id", Category = "mCategory", quantity = "quantity", unit_type = "unit_type", link = "link",
            reciper_id = "reciper_id", schedule = "Schedule";
    public static String TABLE_USER = "CREATE TABLE \"User\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
            "\"Username\" VARCHAR(255), " +
            "\"Email\" VARCHAR(255), " +
            "\"password\" VARCHAR(255))";
    public static String TABLE_RECIPER = "CREATE TABLE \"reciper\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT ," +
            " \"title\" VARCHAR(255), " +
            "\"introdustion\" TEXT," +
            "\"link\" TEXT," +
            " \"servings\" INTEGER, " +
            "\"Cook_time\" INTEGER, " +
            "\"prep_time\" INTEGER, " +
            "\"category_id\" INTEGER)";
    public static String TABLE_CATEGORY = "CREATE TABLE \"mCategory\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
            "\"title\" VARCHAR(255))";
    public static String TABLE_INGREDIENS = "CREATE TABLE \"ingredients\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
            "\"title\" VARCHAR(255), " +
            "\"Re_Id\" INTEGER, " +
            "\"quantity\" DOUBLE, " +
            "\"unit_type\" VARCHAR(8))";

    public static String TABLE_SCHEDULE = "CREATE TABLE \"Schedule\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
            "\"title\" VARCHAR(255), " +
            "\"type_meal\" INTEGER, \"date_meal\" DATE, " +
            "\"status\" INTEGER,\"reciper_id\" INTEGER)";

}

