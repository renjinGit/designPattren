package com.rj.design.study.bridge.eg2;

/**
 * 定义抽象部分接口
 * @author renjin
 * @date 2020/1/14
 */
public abstract class Abstraction {

    /**
     * 持有一个实现部分的对象
     */
    protected Implementor impl;

    public Abstraction(Implementor impl) {
        this.impl = impl;
    }

    /**
     * 实现一定的功能，可能需要转调实现部分的具体实现方法
     */
    public void operation() {
        impl.operationImpl();;
    }
}
