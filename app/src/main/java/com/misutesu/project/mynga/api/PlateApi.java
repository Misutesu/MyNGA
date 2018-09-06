package com.misutesu.project.mynga.api;

import com.misutesu.project.mynga.entity.AllPlate;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlateApi {

    @GET("?__lib=home&__act=category")
    Observable<AllPlate> getAllPlate();
}
