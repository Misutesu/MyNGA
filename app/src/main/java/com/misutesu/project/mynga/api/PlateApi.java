package com.misutesu.project.mynga.api;

import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.data.PostList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PlateApi {

    String NGA_USER_AGENT = "X-User-AGENT:Nga_Official/73001";

    @GET("?__lib=home&__act=category")
    Observable<AllPlate> getAllPlate();

    @Headers({NGA_USER_AGENT})
    @POST("?__lib=subject&__act=list")
    @FormUrlEncoded
    Observable<PostList> getPostList(@Field("fid") int plateId,
                                     @Field("page") int page,
                                     @Field("t") long time,
                                     @Field("sign") String sign);
}
