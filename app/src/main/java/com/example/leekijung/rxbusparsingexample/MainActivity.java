package com.example.leekijung.rxbusparsingexample;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leekijung.rxbusparsingexample.databinding.ActivityMainBinding;
import com.example.leekijung.rxbusparsingexample.model.Bus;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends RxAppCompatActivity {
    final String BASE_BUS_URL = "http://bis.naju.go.kr:8080/";
    final String SUCCESS = "SUCCESS";
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RxView.clicks(binding.busSearchBtn)
                .subscribe(e -> getBus());
    }

    @SuppressLint("CheckResult")
    public void getBus() {
        String busStopId = binding.busNoInput.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_BUS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitBus retrofitBus = retrofit.create(RetrofitBus.class);

        Observable<Bus> busData = retrofitBus.getBusData(busStopId);

        Observable.interval(0, 5, TimeUnit.SECONDS)
                .flatMap(n -> busData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(bus -> {
                            if (bus.getRESULT().getRESULTCODE().equals(SUCCESS)) {
                                makeToast("버스 도착정보 있음");
                                StringBuffer buffer = new StringBuffer();
                                Observable.fromIterable(bus.getBUSSTOPLIST())
                                        .subscribe(
                                                list -> {
                                                    buffer.append(list.getLINENAME() + "번 버스가\n");
                                                    buffer.append("도착까지" + list.getREMAINMIN() + "분 | " + list.getREMAINSTOP() + "개 정류장 남았습니다.\n");
                                                    buffer.append("현재 버스 위치는 " + list.getCURRSTOPID() + "입니다.");
                                                    binding.resultTv.setText(buffer);
                                                }
                                        );
                            } else {
                                binding.resultTv.setText("버스 도착정보가 없음");
                                makeToast("버스 도착정보가 없음");
                            }
                        }, e -> e.printStackTrace()
                        , () -> makeToast("성공적으로 버스정보 바인딩됨"));
    }

    public void makeToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
