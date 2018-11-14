package info.rayrojas.bichito.frutapp;

import android.app.Application;
import android.os.SystemClock;

public class MyFrutApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
