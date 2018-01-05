package com.example.garry.designpatterns.decorator;

import com.example.garry.designpatterns.PrintUtils;

/**
 * 具体构件
 *
 * 最基本的接口或是抽象类的实现，即抽象构件的实现。
 * 被装饰的对象。
 */
public class ConcreteComponent implements Component {

    @Override
    public void operate() {
        PrintUtils.println("ConcreteComponent operate");
    }
}
