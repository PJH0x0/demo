package org.pjh.jetpackdemo.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("user/loginEmail")
    Call<LoginResponseBody> loginEmail(@Field("imei") String imei, @Field("pkgName") String pkgName,
                            @Field("email") String email, @Field("password") String password);

}
