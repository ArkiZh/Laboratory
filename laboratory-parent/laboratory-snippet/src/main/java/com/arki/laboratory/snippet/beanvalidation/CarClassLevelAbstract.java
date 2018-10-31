package com.arki.laboratory.snippet.beanvalidation;

import com.arki.laboratory.snippet.beanvalidation.annotation.ValidCarBeanAnnotation;

@ValidCarBeanAnnotation(message = "CarBean校验失败")
public class CarClassLevelAbstract {

    private int seatCount;
    private String driverName;

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
