package com.example.leekijung.rxbusparsingexample.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;

import com.example.leekijung.rxbusparsingexample.RetrofitBus;
import com.example.leekijung.rxbusparsingexample.model.Bus;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusViewModel extends ViewModel {
    private static final String BASE_BUS_URL = "http://bis.naju.go.kr:8080/";

    @SuppressLint("CheckResult")
    public Observable<Bus> getBusInfo(String busStopId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_BUS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitBus retrofitBus = retrofit.create(RetrofitBus.class);

        return retrofitBus.getBusData(busStopId);
    }
}
