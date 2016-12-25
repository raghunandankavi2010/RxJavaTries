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
public class ColdObservables {

    public static void main(String[] args) throws InterruptedException {
        testColdObservable();

    }

    public static void testColdObservable() throws InterruptedException {

        Observable<Long> cold = Observable.interval(2, TimeUnit.SECONDS);

        cold.subscribe(new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {
                
            }

            @Override
            public void onNext(Long t) {
                System.out.println("First"+t);
            }

            @Override
            public void onError(Throwable thrwbl) {
                
            }

            @Override
            public void onComplete() {
                
            }
        });
        Thread.sleep(500);
        cold.subscribe(new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {
                
            }

            @Override
            public void onNext(Long t) {
                System.out.println("Second :"+t);
            }

            @Override
            public void onError(Throwable thrwbl) {
                
            }

            @Override
            public void onComplete() {
                
            }
        });
//        //Cold Observables emit items as soon as observable Subscribes to an Observer.
//        Observable coldObservable = Observable.just(new Date().getTime());
//
//        coldObservable.subscribe(new Observer<Long>() {
//
//            @Override
//            public void onSubscribe(Disposable dspsbl) {
//
//            }
//
//            @Override
//            public void onNext(Long t) {
//                System.out.println("Raw Date1: " + t);
//            }
//
//            @Override
//            public void onError(Throwable thrwbl) {
//                thrwbl.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//
//        Thread.sleep(5000);
//        coldObservable.subscribe(new Observer<Long>() {
//
//            @Override
//            public void onSubscribe(Disposable dspsbl) {
//
//            }
//
//            @Override
//            public void onNext(Long t) {
//                System.out.println("Raw Date2: " + t);
//            }
//
//            @Override
//            public void onError(Throwable thrwbl) {
//                thrwbl.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

}
