package cl.emprz.chiloeapp.clases;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by elias on 16-06-16.
 */
public final class AppController {

    private static AppController singleton;
    private RequestQueue requestQueue;
    private static Context context;
    private ImageLoader mImageLoader;

    private AppController(Context context){
        this.context = context;
        requestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(requestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(this.context)));





    }

    public static synchronized AppController getInstance(Context context) {
        if (singleton == null) {
            singleton = new AppController(context);
        }
        return singleton;
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public  void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}