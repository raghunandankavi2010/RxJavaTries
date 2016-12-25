/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raghunandan
 */
public class Response {
    
    List<HotelAvailability> mList =  new ArrayList<>();

    public List<HotelAvailability> getmList() {
        return mList;
    }

    public void setmList(List<HotelAvailability> mList) {
        this.mList = mList;
    }
    
    
}
