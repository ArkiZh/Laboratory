package com.arki.laboratory.snippet.beanvalidation;

import com.arki.laboratory.snippet.beanvalidation.annotation.ValidPassengerCount;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ValidPassengerCount
public class CarClassLevel {
    @Min(value = 2, message = "座位数最小需为{value}")
    private int seatCount;
    @NotEmpty // 先校验passengers不能为空。 再校验添加的乘客不能为null。 再进入到Person内部校验（cascaded validation）
    private List<@NotNull(message = "乘客列表不能添加null") @Valid Person> passengers = new ArrayList<>();

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "CarClassLevel{" +
                "seatCount=" + seatCount +
                ", passengers=" + passengers +
                '}';
    }

    public static class Person{

        @NotEmpty(message = "乘客名不能为空")
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
