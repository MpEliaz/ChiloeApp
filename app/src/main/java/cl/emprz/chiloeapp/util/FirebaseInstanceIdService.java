package cl.emprz.chiloeapp.util;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by elias on 30-01-17.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d("PUSH", "token: " + token);
    }
}
