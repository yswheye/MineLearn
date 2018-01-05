package com.example.garry.designpatterns.decorator;


import com.example.garry.designpatterns.PrintUtils;

/**
 * Created by garry on 2018/1/5.
 */

public class ConcreteDecorator_B extends Decorator {


    public ConcreteDecorator_B(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        this.method_B();
    }

    private void method_B() {
        PrintUtils.println("do method_B");
    }
}
