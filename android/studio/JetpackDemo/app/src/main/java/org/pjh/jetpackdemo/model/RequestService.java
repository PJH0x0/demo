package org.pjh.jetpackdemo.model;

import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {
    private static final String TAG = "RequestService";
    private static final String BASE_URL = "https://fans.jstinno.com/CloudServer/api/";
    static final String APP_ID = "wx148860f1960a9505";
    static final String APP_SECRET = "79bd828daefedab053f11cf58a552286";
    static final String CODE = "071wPP821BOpdO15eU521VoP821wPP8l";
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .eventListener(new LogEventListener())
            .build();

    private APIService mServices;
    //Retrofit.Builder builder;
    private RequestService() {
//       builder  = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client);
                //.addConverterFactory(GsonConverterFactory.create());
        //Retrofit retrofit = builder.build();
        //mServices = retrofit.create(APIService.class);

    }
    public static RequestService getInstance() {
        return RequestServiceHolder.sService;
    }
    private static class RequestServiceHolder {
        private static final RequestService sService = new RequestService();
    }

    public void testGetWechatAccessToken() throws IOException {
        StringBuffer url = new StringBuffer();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(APP_ID)
                .append("&secret=")
                .append(APP_SECRET)
                .append("&code=")
                .append(CODE)
                .append("&grant_type=authorization_code");
        Request request = new Request.Builder()
                .url(url.toString())
                .build();


        okhttp3.Call call = client.newCall(request);
        okhttp3.Response response = call.execute();
        Log.d(TAG, "testGetWechatAccessToken: code = " + response.code());
    }

    public void loginEmail(String imei, String pkgName, String email, String password) throws IOException {
//        if (null == mServices) {
//            Log.w(TAG, "loginEmail: null mServices");
//            return;
//        }
//        Call<ResponseBody> call = mServices.loginEmail(imei, pkgName, email, password);
//        if (null == call) {
//            Log.w(TAG, "loginEmail: null call");
//            return;
//        }
//        Log.d(TAG, "CALL adapter size = " + builder.callAdapterFactories());
//        Log.d(TAG, "loginEmail: start");
//
//        Response<ResponseBody> response = call.execute();
//        try {
//            Method method = mServices.getClass().getMethod("loginEmail", String.class, String.class, String.class, String.class);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                Log.d(TAG, method.getGenericReturnType().getTypeName());
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        Log.d(TAG, "CALL adapter size2 = " + builder.callAdapterFactories());
//        if (null != response){
//            Log.d(TAG, "result = " + response.body());
//        } else {
//            Log.d(TAG, "log failed");
//        }

    }

}
