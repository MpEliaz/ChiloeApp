
package cl.emprz.chiloeapp;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class App extends Application {

    //private String FIREBASE_CHILD

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}

