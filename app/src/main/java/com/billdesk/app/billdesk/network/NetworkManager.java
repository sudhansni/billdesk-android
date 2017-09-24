package com.billdesk.app.billdesk.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by sudha on 23-09-2017.
 */

public class NetworkManager {
    private static NetworkManager instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private NetworkManager(Context applicationContext) {
        requestQueue = Volley.newRequestQueue(applicationContext);

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(10);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static void initialize (Context applicationContext) {
        if (instance == null) {
            instance = new NetworkManager(applicationContext);
        }
    }

    public static RequestQueue getRequestQueue() {
        return instance.requestQueue;
    }

    public static <T> void addToRequestQueue(Request<T> req) {
        instance.requestQueue.add(req);
    }

    public static ImageLoader getImageLoader() {
        return instance.imageLoader;
    }
}
