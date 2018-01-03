package efood.com.food.Model;

/**
 * Created by loc on 18/03/2016.
 */
public class Time {
    private int hour;
    private int minutes;
    private int seccon;
    public Time() {

    }

    public Time(int hour, int minutes, int seccon) {
        this.hour = hour;
        this.minutes = minutes;
        this.seccon = seccon;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeccon() {
        return seccon;
    }

    public void setSeccon(int seccon) {
        this.seccon = seccon;
    }
}
