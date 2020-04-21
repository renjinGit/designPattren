package com.rj.design.study.observer.eg1;


import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，作为被观察者，使用推模型
 */
public class Subject {

    /**
     * 用来保存注册的观察者对象，也就是报纸的订阅者
     */
    private List<Observer> readers = new ArrayList<>();

    /**
     * 报纸的读者需要向报社订阅，先要注册
     * @param reader
     */
    public void attach(Observer reader) {
        readers.add(reader);
    }

    /**
     * 报纸的读者可以取消订阅
     * @param reader
     */
    public void detach(Observer reader) {
        readers.remove(reader);
    }

    /**
     * 当每期报纸印刷出来后，就要迅速主动地被送到读者的手中，相当于通知读者，让他们知道
     */
    protected void notifyAllObservers(String content) {
        for (Observer reader : readers) {
            reader.update(content);
        }
    }
}