package com.example.garry.designpatterns.decorator;

/**
 * Created by garry on 2018/1/5.
 */

public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        //第一次修饰
        component = new ConcreteDecorator_A(component);
        //第二次修饰
        component = new ConcreteDecorator_B(component);
        component.operate();
    }
}
