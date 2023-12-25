package com.edu.nju.UI;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        // 创建游戏主界面
        // JavaBean描述界面的
        // 属性(宽, 高) 行为
        // 上下左右移动的代码逻辑
        // 统计步数的代码逻辑
        // 一键通关
        // 查看最终效果
        // 恶搞好基友
        JFrame gameJFrame = new JFrame();
        gameJFrame.setSize(603, 680);
        gameJFrame.setVisible(true);

        // 创建登录界面
        // 获取用户名 && 密码
        // 生成验证码
        // 获取用户输入的验证码
        // 比较用户名, 密码, 验证码

        JFrame loginJFrame = new JFrame();
        loginJFrame.setSize(488, 430);
        loginJFrame.setVisible(true);
        // 登录注册界面
        // 获取用户名
        // 获取输入的密码两次
        // 两次密码是否一致
        // 判断当前用户是否已经注册
        JFrame registerJFrame = new JFrame();
        registerJFrame.setSize(488, 500);
        registerJFrame.setVisible(true);

    }
}
