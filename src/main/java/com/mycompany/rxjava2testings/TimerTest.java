/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Raghunandan
 */
public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable dspsbl) {

                    }

                    @Override
                    public void onNext(Long t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onError(Throwable thrwbl) {

                        thrwbl.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Complete");
                    }

                });
        
        Thread.sleep(1200);

    }
    
      private static Observable<? extends Long> getObservable() {
        return Observable.interval(0, 2, TimeUnit.SECONDS);
    }
}
