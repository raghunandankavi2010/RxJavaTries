/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings.TestZip;

import com.mycompany.rxjava2testings.TestZip.TestZip.Data;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Raghunandan
 */
public class TestZip  {

    public TestZip() {
        Observable<Data> s1 = Observable.fromArray(new Data[]{new Data(3), new Data(1), new Data(2), new Data(4)});
        Observable<String> s2 = Observable.fromArray(new String[]{"A", "B", "C", "D"});

//        s1.toSortedList((Data a,Data b)->  a.number.equals(b.number) ? 0 : (a.number < b.number ? -1 : 1))
//                .flatMapObservable(t->Observable.fromIterable(t));
//        Single<List<Data>> listData = s1.toSortedList(new Comparator<Data>() {
//            @Override
//            public int compare(Data a, Data b) {
//                return a.number.equals(b.number) ? 0 : (a.number < b.number ? -1 : 1);
//            }
//        });
//        
//        Observable<Data> flatMapObservable = listData.flatMapObservable(new Function<List<Data>,Observable<Data>>()
//        {
//
//            @Override
//            public Observable<Data> apply(List<Data> t) throws Exception {
//                return Observable.fromIterable(t);
//            }
//            
//        });
        Observable.zip(
                s1.toSortedList((Data a,Data b)->  a.number.equals(b.number) ? 0 : (a.number < b.number ? -1 : 1))
                .flatMapObservable(t->Observable.fromIterable(t)),
                s2,
                (d, s) -> s + d.number
        ).subscribe(s -> {
            System.out.println("Hello"+s);
        });
    }

    public static void main(String args[]) {

        new TestZip();
    }

    static class Data {

        private final Integer number;

        Data(Integer number) {
            this.number = number;
        }
    }
}
