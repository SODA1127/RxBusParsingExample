package com.example.leekijung.rxbusparsingexample;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leekijung.rxbusparsingexample.databinding.ActivityMainBinding;
import com.example.leekijung.rxbusparsingexample.model.Bus;
import com.example.leekijung.rxbusparsingexample.viewmodel.BusViewModel;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {
    public ActivityMainBinding binding;
    private BusViewModel busViewModel;
    private final String SUCCESS = "SUCCESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        busViewModel = ViewModelProviders.of(this).get(BusViewModel.class);

        RxView.clicks(binding.busSearchBtn)
                .subscribe(bus -> getBus());
    }

    @SuppressLint("CheckResult")
    public void getBus() {
        String busStopId = binding.busNoInput.getText().toString();
        Observable<Bus> busData = busViewModel.getBusInfo(busStopId);
        Observable.interval(5, TimeUnit.SECONDS)
                .flatMap(bus -> busData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(bus -> {
                            if (bus.getRESULT().getRESULTCODE().equals(SUCCESS)) {
                                makeToast("버스 도착정보 있음");
                                StringBuffer buffer = new StringBuffer();
                                Observable.fromIterable(bus.getBUSSTOPLIST())
                                        .take(1)
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
                        }, Throwable::printStackTrace
                        , () -> makeToast("성공적으로 버스정보 바인딩됨"));
    }

    public void makeToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
