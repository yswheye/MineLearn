package com.example.garry.designpatterns.decorator;

/**
 * 抽象装饰器
 *
 * 一般也是一个抽象类，实现抽象构件。
 * 在它的属性里面必然有一个private变量指向Component抽象构件。
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}
