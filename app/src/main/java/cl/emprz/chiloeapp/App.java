package cl.emprz.chiloeapp;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by elias on 04-07-16.
 */
public class App extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }
}
