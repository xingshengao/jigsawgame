package com.edu.nju.UI;

import javax.swing.*;

/**
 * @author XsaDeleteMemory
 * @date 2023/12/22 16:01
 * 注册有关的界面
 */
public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setSize(488, 500);
        // 界面标题
        this.setTitle("拼图游戏v1.0 --注册");
        // 设置界面置顶
        this.setAlwaysOnTop(true);

        // 界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式, 关闭所有窗口后可以停止程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
