package efood.com.food.Application;

import android.app.Application;

import efood.com.food.Data.dbhelper;


/**
 * Created by Hoang on 03/03/2016.
 */
public class App extends Application {
    //  đây là class kế thừa application và thực hiện trong manifest
    // thực hiện tạo các dữ liệu khi lần đầu tiên khởi tạo ứng dụng
    // tránh lag khi vào các frament
    @Override
    public void onCreate() {
        super.onCreate();
        dbhelper hDbhelper = new dbhelper(this);
    }
}
