package cl.emprz.chiloeapp.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import cl.emprz.chiloeapp.clases.LruBitmapCache;

/**
 * Created by elias on 27-06-16.
 */
public class ECSingleton {

    private static ECSingleton singleton;
    private RequestQueue requestQueue;
    private static Context context;
    private ImageLoader mImageLoader;

    private ECSingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(requestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(this.context)));





    }

    public static synchronized ECSingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new ECSingleton(context);
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
