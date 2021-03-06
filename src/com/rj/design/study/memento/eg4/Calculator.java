package com.rj.design.study.memento.eg4;

import com.rj.design.study.memento.eg.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算器类，计算器上有加法按钮，减法按钮，还有撤销和恢复的按钮
 *
 * @author renjin
 * @date 2020/1/9
 */
public class Calculator {

    /**
     * 命令的操作历史记录，在撤销时用
     */
    private List<Command> undoCmds = new ArrayList<>();

    /**
     * 命令的操作历史记录，在恢复时用
     */
    private List<Command> redoCmds = new ArrayList<>();

    /**
     * 命令操作对应的备忘录对象的历史记录，在撤销时用
     * 数组有两个元素，第一个是命令执行前的状态，第二个是命令执行后的状态
     */
    private List<Memento[]> undoMementos = new ArrayList<>();

    /**
     * 命令操作对应的备忘录对象的历史记录，在恢复时用
     * 数组有两个元素，第一个是命令执行前的状态，第二个是命令执行后的状态
     */
    private List<Memento[]> redoMementos = new ArrayList<>();

    private Command addCmd;

    private Command substractCmd;

    public void setAddCmd(Command addCmd) {
        this.addCmd = addCmd;
    }

    public void setSubstractCmd(Command substractCmd) {
        this.substractCmd = substractCmd;
    }

    public void addPressed() {
        //获取对应的备忘录对象，并保存在相应的历史记录中
        Memento m1 = this.addCmd.createMemento();

        //执行命令
        this.addCmd.execute();
        //把操作记录到历史记录中
        undoCmds.add(this.addCmd);

        //获取执行命令后的备忘录对象
        Memento m2 = this.addCmd.createMemento();
        //设置到撤销的历史记录中
        this.undoMementos.add(new Memento[]{m1, m2});
    }

    public void substractPressed() {
        //获取对应的备忘录对象，并保存在相应的历史记录中
        Memento m1 = this.substractCmd.createMemento();

        //执行命令
        this.substractCmd.execute();
        //把操作记录到历史记录中
        undoCmds.add(this.substractCmd);

        //获取执行命令后的备忘录对象
        Memento m2 = this.substractCmd.createMemento();
        //设置到撤销的历史记录中
        this.undoMementos.add(new Memento[]{m1, m2});
    }

    public void undoPressed() {
        if (undoCmds.size() > 0) {
            //取出最后一个命令来撤销
            Command cmd = undoCmds.get(undoCmds.size() - 1);
            //获取对应的备忘录对象
            Memento[] ms = undoMementos.get(undoCmds.size() - 1);
            //撤销
            cmd.undo(ms[0]);

            //如果还有恢复的功能，那就把这个命令记录到恢复的历史记录中
            redoCmds.add(cmd);
            //把相应的备忘录对象也添加过去
            redoMementos.add(ms);

            //然后把最后一个命令删除
            undoCmds.remove(cmd);
            //把相应的备忘录对象也删除
            undoMementos.remove(ms);
        } else {
            System.out.println("很抱歉， 没有可撤销的命令");
        }
    }


    public void redoPressed() {
        if (redoCmds.size() > 0) {
            //取出最后一个命令来重做
            Command cmd = redoCmds.get(redoCmds.size() - 1);
            //获取对应的备忘录对象
            Memento[] ms = redoMementos.get(redoCmds.size() - 1);
            //重做
            cmd.redo(ms[1]);

            //如果还有撤销的功能，那就把这个命令记录到撤销的历史记录中
            undoCmds.add(cmd);
            //把相应的备忘录对象也添加过去
            undoMementos.add(ms);

            //然后把最后一个命令删除
            redoCmds.remove(cmd);
            //把相应的备忘录对象也删除
            redoMementos.remove(ms);
        } else {
            System.out.println("很抱歉， 没有可恢复的命令");
        }
    }
}
