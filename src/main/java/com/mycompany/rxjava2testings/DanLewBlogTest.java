/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 * @author Raghunandan
 */
public class DanLewBlogTest {

    public static void main(String[] args) {
       /* Observable.just("Hello!")
                .map(new Function<String, Object>() {

            public Exception apply(String input) {
                throw new RuntimeException();
            }
        })
                .subscribe(System.out::println, new Consumer<Throwable>() {

            public void accept(Throwable error) {
                System.out.println("Error!");
            }
        });*/
        
        //////
         Observable.just("some name")
        .map(name -> mightThrowException(name))
        .subscribe(new Observer<Observable<String>>() {

             @Override
             public void onSubscribe(Disposable dspsbl) {
                
             }

             @Override
             public void onNext(Observable<String> t) {
             
             }

             @Override
             public void onError(Throwable thrwbl) {
                 
                 System.out.println("Error");
                 //thrwbl.printStackTrace();
                 
             }

             @Override
             public void onComplete() {
               
             }

         
          
        });

    
    }
    
    private static Observable<String> mightThrowException(String name) {
        throw new NullPointerException();
    }
}
