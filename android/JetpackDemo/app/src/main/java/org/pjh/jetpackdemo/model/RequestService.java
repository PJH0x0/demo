package org.pjh.jetpackdemo.model;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {
    private static final String TAG = "RequestService";
    private static final String BASE_URL = "https://fans.jstinno.com/CloudServer/api/";
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(30, TimeUnit.SECONDS).
            readTimeout(30, TimeUnit.SECONDS).
            writeTimeout(30, TimeUnit.SECONDS)
            .build();
    private APIService mServices;
    private RequestService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServices = retrofit.create(APIService.class);

    }
    public static RequestService getInstance() {
        return RequestServiceHolder.sService;
    }
    private static class RequestServiceHolder {
        private static final RequestService sService = new RequestService();
    }

    public void loginEmail(String imei, String pkgName, String email, String password) throws IOException {
        if (null == mServices) {
            Log.w(TAG, "loginEmail: null mServices");
            return;
        }
        Call<LoginResponseBody> call = mServices.loginEmail(imei, pkgName, email, password);
        if (null == call) {
            Log.w(TAG, "loginEmail: null call");
            return;
        }
        Log.d(TAG, "loginEmail: start");
        Response<LoginResponseBody> response = call.execute();
        if (null != response){
            Log.d(TAG, "result = " + response.body());
        } else {
            Log.d(TAG, "log failed");
        }

    }

}
