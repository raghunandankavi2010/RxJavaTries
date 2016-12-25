/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;

/**
 *
 * @author Raghunandan
 */
public class MultipleObservable {

    public static void main(String[] args) throws InterruptedException {
        
        
        Observable<Integer> intObservable = Observable.just(100);
        intObservable
                .map(new Function<Integer, Integer>() {

                    public Integer apply(Integer integer) {
                        int sum = 0;
                        for (int i = 0; i < integer.intValue(); i++) {
                            sum = sum + i;
                        }
                        return (Integer) sum;
                    }
                }).observeOn(Schedulers.newThread())
                .map(new Function<Integer, String>() {

                    @Override
                    public String apply(Integer t) throws Exception {
                         System.out.println("Integer value" + t
                               + " on thread " + Thread.currentThread().getName());
                        return String.valueOf(t);
                    }

                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(Disposable dspsbl) {

                    }

                    @Override
                    public void onNext(String t) {

                        System.out.println("String value" + t
                               + " on thread " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable thrwbl) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
        
        // sleep main thread as you don't want mainto finish before executing other threads
        Thread.sleep(4000);

//        Observable<Integer> source = Observable.range(1, 10);
//
//        source.map(new Function<Integer, Integer>() {
//
//            public Integer apply(Integer i) {
//                return i * 100;
//            }
//        }).observeOn(Schedulers.newThread())
//                .doOnNext(new Consumer<Integer>() {
//
//                    public void accept(Integer i) {
//                        System.out.println("Emitting " + i
//                                + " on thread " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.computation())
//                .map(i -> i * 10)
//                .subscribe(i -> System.out.println("Received " + i + " on thread "
//                                + Thread.currentThread().getName()));
//        Thread.sleep(4000);
    }

    public MultipleObservable() {
    }

}
