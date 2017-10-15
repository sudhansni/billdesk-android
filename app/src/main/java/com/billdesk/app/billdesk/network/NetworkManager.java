package com.billdesk.app.billdesk.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.billdesk.app.billdesk.models.BaseResponse;
import com.billdesk.app.billdesk.models.BillDeskRequest;
import com.billdesk.app.billdesk.models.RegisterMobileResponse;
import com.billdesk.app.billdesk.models.UserProfileResponse;


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


    public static void registerMobile(BillDeskRequest request, Response.Listener<RegisterMobileResponse> responseListener, Response.ErrorListener errorListener) {
        GsonRequest<RegisterMobileResponse> gsonRequest = new GsonRequest<>(NetworkUtil.REGISTRATION_URL, Request.Method.POST,request,
                RegisterMobileResponse.class, responseListener, errorListener);
        queueRequest(gsonRequest);
    }

    public static void verifyOtpCode(BillDeskRequest request, Response.Listener<BaseResponse> responseListener, Response.ErrorListener errorListener) {
        GsonRequest<BaseResponse> gsonRequest = new GsonRequest<>(NetworkUtil.VERIFY_OTP_URL, Request.Method.POST,request,
                BaseResponse.class, responseListener, errorListener);
        queueRequest(gsonRequest);
    }

    public static void updateProfileDetails(BillDeskRequest request, Response.Listener<UserProfileResponse> responseListener, Response.ErrorListener errorListener) {
        GsonRequest<UserProfileResponse> gsonRequest = new GsonRequest<>(NetworkUtil.UPDATE_PROFILE_URL, Request.Method.POST, request,
                UserProfileResponse.class, responseListener, errorListener);
        queueRequest(gsonRequest);
    }

    private static void queueRequest(GsonRequest<? extends BaseResponse> request) {
        Log.d("NetworkManager", "Volley Request :: " + request.toString());
        instance.requestQueue.add(request);
    }

}
