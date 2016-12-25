/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Raghunandan
 */
public class HotObservables {

    public static void main(String[] args) throws InterruptedException {
        
        testHotObservables();
    }
    
     public static void testHotObservables() throws InterruptedException {
        Observable hotObservable = Observable.just(new Date());
        ConnectableObservable<Date> connectableObservable = hotObservable.publish();
        connectableObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date1: " + t.getTime());
            }

            @Override
            public void onError(Throwable thrwbl) {

            }

            @Override
            public void onComplete() {

            }
        });
 
        Thread.sleep(1000);
        connectableObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date2: " + t.getTime());
            }

            @Override
            public void onError(Throwable thrwbl) {

            }

            @Override
            public void onComplete() {

            }
        });

        connectableObservable.connect();

    }
}
