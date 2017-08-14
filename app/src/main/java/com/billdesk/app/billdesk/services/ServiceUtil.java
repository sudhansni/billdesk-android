package com.billdesk.app.billdesk.services;

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.util.Log;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rajesh on 7/27/2017.
 */

public class ServiceUtil {
    public static final int TIMEOUT_SECONDS = 15;
    private static final String BASE_URL = "http://www.billdesk.com.au/api/";
    private static final String PUBLIC_ONLY_IF_CACHED_MAX_STALE = "public, only-if-cached, max-stale=2419200";
    private static final String MAX_AGE_DEFAULT = "public, max-age=1";
    private static final String MAX_AGE_PARENT_PROFILE = "public, max-age=7200";
    private static final String APP_TYPE_VALUE = "BillDeskapp";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String DEVICE_TYPE_VALUE = "Android";
    private static final OkHttpClient httpClient = createCachedClient();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.client(httpClient).build();

    private static OkHttpClient createCachedClient() {

        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).
                connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).
                sslSocketFactory(getDirtySocketFactory(), trustManager).
                hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).
                addInterceptor(getCacheInterceptor()).
                addNetworkInterceptor(getCacheInterceptor()).
                authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credentials = Credentials.basic("admin", "1234");
                        return response.request().newBuilder().header("Authorization", credentials).build();
                    }
                }).
                build();

        return okHttpClient;
    }

    private static SSLSocketFactory getDirtySocketFactory() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = {new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
        }
        return null;
    }

    @NonNull
    private static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String cacheTime = originalRequest.url().toString().contains("parentprofile") ? MAX_AGE_PARENT_PROFILE : MAX_AGE_DEFAULT;
                String cacheHeaderValue = cacheTime;
                Request request = originalRequest.newBuilder().addHeader("device", DEVICE_TYPE_VALUE).
                        addHeader("appType", APP_TYPE_VALUE).
                        addHeader("Content-Type", CONTENT_TYPE_VALUE).
                        addHeader("Cache-Control", cacheHeaderValue).
                        build();

                Response response = chain.proceed(request);

                if (BuildConfig.DEBUG) {
                    Log.d("ServiceUtil", "Caching Time" + cacheTime);
                }
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", cacheHeaderValue).build();
            }
        };
    }

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }


    public static BillDeskClient getService() {
        return retrofit.create(BillDeskClient.class);
    }
}
