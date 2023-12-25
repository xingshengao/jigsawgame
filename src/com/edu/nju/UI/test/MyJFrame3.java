package com.edu.nju.UI.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3 extends JFrame implements KeyListener {
    public MyJFrame3() {
        // 一些设置写在构造方法里
        this.setSize(488, 430);
        // 界面标题
        this.setTitle("拼图游戏v1.0 -- 登录");
        // 设置界面置顶
        this.setAlwaysOnTop(true);

        // 界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式, 关闭所有窗口后可以停止程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        // 界面添加键盘监听
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // 按下一个按键不松开, 重复调用
        System.out.println(" 按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) { // 送开始调用一次
        System.out.println("松开按键");
        int code = e.getKeyCode();
        System.out.println(code);
    }
}
