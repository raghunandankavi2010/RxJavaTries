/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raghunandan
 */
public class FilterList {

    public static void main(String args[]) {
        new FilterList().filter();
    }

    private void filter() {

        Response response = new Response();
        List<HotelAvailability> mList = new ArrayList<HotelAvailability>();
        for (int i = 1; i <50; i++) {
            HotelAvailability hotelAvailability = new HotelAvailability();
            if (i % 2 == 0) {

                System.out.print(" "+i);
                hotelAvailability.setHotelinfo("Available");
            } else {
                hotelAvailability.setHotelinfo(null);
            }

              mList.add(hotelAvailability);
        
        }
        response.setmList(mList);
        
        System.out.println("List Size"+mList.size());

        Observable<List<HotelAvailability>> observable = Observable.just(response.getmList());
       
        Observable<HotelAvailability> flatMapIterable = observable.flatMapIterable(list -> list);
        
    
       
        Single<List<HotelAvailability>> observa = flatMapIterable.flatMap((HotelAvailability hotel) -> {
            if (hotel.getHotelinfo() != null) {

                return Observable.just(hotel);

            } else {
                return Observable.empty();
            }
        }).toList();//
       


//           List<String> string = (List<String>) flatMapIterable.flatMap((HotelAvailability hotel) -> {
//            if (hotel.getHotelinfo() != null) {
//
//                return Observable.just(hotel);
//
//            } else {
//                return Observable.empty();
//            }}).toList();
//            
       observa.subscribeWith( new SingleObserver<List<HotelAvailability>>() {

            @Override
            public void onSubscribe(Disposable dspsbl) {
                
            }

            @Override
            public void onSuccess(List<HotelAvailability> t) {
                
                System.out.println(t.size());
            }

            @Override
            public void onError(Throwable thrwbl) {
                
            }
        });
    }
    
 

}
