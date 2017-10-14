package com.billdesk.app.billdesk.services;

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

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceUtil {
    private static final int TIMEOUT_SECONDS = 15;
    private static final String BASE_URL = "http://www.billdesk.com.au/api/";
    private static final String MAX_AGE_DEFAULT = "public, max-age=1";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String DEVICE_TYPE_VALUE = "android";
    private static final String UNIQUE_ID = "125946";
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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().
                readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).
                connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).
                sslSocketFactory(getDirtySocketFactory(), trustManager).
                hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).
                addInterceptor(new BasicAuthInterceptor("admin", "1234")).
                addInterceptor(interceptor).
                build();
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
            Log.e("ServiceUtil","Exception while creating Socket Factory", e);
        }
        return null;
    }

    private static class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        private BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials)
                    .header("source-system", "android")
                    .header("deviceId", "125946")
                    .removeHeader("Content-Type")
            .build();
            return chain.proceed(authenticatedRequest);
        }

    }


    public static BillDeskClient getService() {
        return retrofit.create(BillDeskClient.class);
    }
}
