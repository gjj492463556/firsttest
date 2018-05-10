package com.example.gjj.myapplication.http.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by 8 on 2018/5/9.
 */

public class RxJavaUtils7 {
    private final static String TAG = "GJJ";
    public void rxjava7(){
        Flowable<Integer> upsteam = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d("GJJ", "emit 1");
                emitter.onNext(1);
                Log.d("GJJ", "emit 2");
                emitter.onNext(2);
                Log.d("GJJ", "emit 3");
                emitter.onNext(3);
                Log.d("GJJ", "emit complete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downStream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);  //注意这句代码
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        upsteam.subscribe(downStream);
    }
}
