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
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Raghunandan
 */
public class RxJava3Test {

    /**
     * @param args the command line arguments
     */
    private static final Function<Observable<List<Integer>>, Observable<List<Integer>>> func
            = new Function<Observable<List<Integer>>, Observable<List<Integer>>>() {

                @Override
                public Observable<List<Integer>> apply(Observable<List<Integer>> number) throws Exception {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };

    private static int count;

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        count = 0;
        //System.out.println("HelloWorld");
        //test();
        //testColdObservable();
        testHotObservables();
        //ArrayList<Integer> mList =
        List<Integer> asList = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable<List<Integer>> just = Observable.just(asList);

        Observable<Integer> flatMap = just.flatMap(new Function<List<Integer>, Observable<Integer>>() {
            
            @Override
            public Observable<Integer> apply(List<Integer> t) throws Exception {
                return Observable.fromIterable(t);
            }

        });
        just.flatMapIterable(list -> list)
                .flatMap(convert)
                .subscribeWith(new DisposableObserver<Integer>() {

                    @Override
                    public void onNext(Integer t) {

                        System.out.println("Later" + t);
                    }

                    @Override
                    public void onError(Throwable thrwbl) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
        //just.flatMap(SQUARE_OF_NUMBER)
        //   .retry(3)
        // .subscribe(getObserver());

//        just.retryWhen(errors -> errors.flatMap(error -> {
//            // For IOExceptions, we  retry
//            if (error instanceof IOException) {
//                return Observable.just(null);
//            }
//
//            // For anything else, don't retry
//            return Observable.error(error);
//        }));
    }

    private static final Function<Integer, Observable<Integer>> convert
            = (Integer t) -> {
                if (t % 2 == 0) {
                    System.out.println(t);
                    return Observable.just(t);
                    
                } else {
                    return Observable.empty();
                }
    };

    private static final Function<Observable<? extends Throwable>, Observable<String>> Error
            = new Function<Observable<? extends Throwable>, Observable<String>>() {

        public Observable<String> apply(Observable<? extends Throwable> t) {
            return Observable.just("Hello");
        }
    };
    private static final Function<Integer, Observable<Integer>> SQUARE_OF_NUMBER
            = (Integer number) -> {
                if (count != 2) {
                    count++;
                    System.out.println("IOException");
                    throw new IOException();
                } else {
                    return Observable.just(number * number);
                }
            };

    private static Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable");
            }

            @Override
            public void onNext(Integer number) {
                System.out.println("Number: " + number);

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                System.out.println("Error");
                //e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Complete");
            }
        };
    }

    private static void test() throws InterruptedException {

        Observable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS);

        cold.subscribe(getLongObserver());
        Thread.sleep(500);
        cold.subscribe(getLongObserver2());

    }

    private static Observer<Long> getLongObserver2() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long number) {
                System.out.println("Second: " + number);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private static Observer<Long> getLongObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long number) {
                System.out.println("First: " + number);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public static void testColdObservable() {
        //Cold Observables emit items as soon as oBservable Subscribes to an Observer.
        Observable coldObservable = Observable.just(new Date());

        coldObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date: " + t);
            }

            @Override
            public void onError(Throwable thrwbl) {
                thrwbl.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
        coldObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date: " + t);
            }

            @Override
            public void onError(Throwable thrwbl) {
                thrwbl.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void testHotObservables() {
        Observable hotObservable = Observable.just(new Date());
        ConnectableObservable<Date> connectableObservable = hotObservable.publish();
        connectableObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date: " + t);
            }

            @Override
            public void onError(Throwable thrwbl) {

            }

            @Override
            public void onComplete() {

            }
        });

        connectableObservable.subscribe(new Observer<Date>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {

            }

            @Override
            public void onNext(Date t) {
                System.out.println("Raw Date: " + t);
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
