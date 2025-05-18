package com.wyq.UI;

import javax.swing.*;

//注册界面
public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        //设置宽高
        this.setSize(488, 500);
        //设置标题
        this.setTitle("注册界面");
        //界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);

        //显示界面
        this.setVisible(true);
    }
}
