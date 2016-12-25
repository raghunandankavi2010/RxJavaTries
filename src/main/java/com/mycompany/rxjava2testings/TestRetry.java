/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Raghunandan
 */
public class TestRetry {
    
    private static int count;
    public static void main(String[] args)
    {
       
          List<Integer> asList = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable<List<Integer>> just = Observable.just(asList);
     
        just.flatMapIterable(list -> list)
         .flatMap(SQUARE_OF_NUMBER).retry(2).subscribeWith(new DisposableObserver<Integer>() {

                    @Override
                    public void onNext(Integer t) {

                        System.out.println("Squared Number: " + t);
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
    
       private static final Function<Integer, Observable<Integer>> SQUARE_OF_NUMBER
            = new Function<Integer, Observable<Integer>>() {

        public Observable<Integer> apply(Integer number) throws IOException {
            if (number == 4 && (count ==0 || count ==1)) {
                System.out.println("IOException");
                count++;
                System.out.println("Retry"+count);
                throw new IOException();
            } else {
                return Observable.just(number * number);
            }
           
        }
    };
}
