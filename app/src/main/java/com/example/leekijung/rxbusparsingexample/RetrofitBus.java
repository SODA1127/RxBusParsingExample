package com.example.leekijung.rxbusparsingexample;

import com.example.leekijung.rxbusparsingexample.model.Bus;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitBus {
    @GET("json/arriveAppInfo")
    Observable<Bus> getBusData(@Query("BUSSTOP_ID") String busStopId);
}
