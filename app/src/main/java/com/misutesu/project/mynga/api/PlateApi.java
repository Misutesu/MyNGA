package com.misutesu.project.mynga.api;

import com.misutesu.project.mynga.data.AllPlate;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PlateApi {

    @GET("?__lib=home&__act=category")
    Observable<AllPlate> getAllPlate();
}
