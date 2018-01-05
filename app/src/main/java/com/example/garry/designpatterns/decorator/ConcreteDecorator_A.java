package com.example.garry.designpatterns.decorator;


import com.example.garry.designpatterns.PrintUtils;

/**
 * 具体装饰器
 *
 * 实现抽象装饰器，同时有自己的装饰方法。
 * 在重写的方法里调用父类的方法super.xxx
 */
public class ConcreteDecorator_A extends Decorator {


    public ConcreteDecorator_A(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        this.method_A();
    }

    private void method_A() {
        PrintUtils.println("do method_A");
    }
}
