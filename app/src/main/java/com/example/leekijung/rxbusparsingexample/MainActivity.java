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
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {
    public ActivityMainBinding activityMainBinding;
    private BusViewModel busViewModel;
    private final String SUCCESS = "SUCCESS";
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
    }

    public void initViewBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        busViewModel = ViewModelProviders.of(this).get(BusViewModel.class);
        RxView.clicks(activityMainBinding.busSearchBtn)
                .subscribe(event -> applyBusData());
    }

    @SuppressLint("CheckResult")
    public void applyBusData() {
        if (disposable != null) {
            if (!disposable.isDisposed()) disposable.dispose();
        }
        String busStopId = activityMainBinding.busNoInput.getText().toString();
        Observable<Bus> busObservable = busViewModel.getBusInfo(busStopId);
        disposable = Observable.interval(5, TimeUnit.SECONDS)
                .flatMap(busData -> busObservable)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(busData -> {
                            if (busData.getRESULT().getRESULTCODE().equals(SUCCESS)) {
                                makeToast("버스 도착정보 있음");
                                StringBuffer buffer = new StringBuffer();
                                Observable.fromIterable(busData.getBUSSTOPLIST())
                                        .subscribe(bus -> {
                                            buffer.append(bus.getLINENAME() + "번 버스가\n");
                                            buffer.append("도착까지" + bus.getREMAINMIN() + "분 | " + bus.getREMAINSTOP() + "개 정류장 남았습니다.\n");
                                            buffer.append("현재 버스 위치는 " + bus.getCURRSTOPID() + "입니다.");
                                        });
                                activityMainBinding.resultTv.setText(buffer);
                            } else {
                                activityMainBinding.resultTv.setText("버스 도착정보가 없음");
                                makeToast("버스 도착정보가 없음");
                            }
                        }, Throwable::printStackTrace
                        , () -> makeToast("성공적으로 버스정보 바인딩됨")
                );
    }

    public void makeToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
