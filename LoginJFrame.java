package com.wyq.UI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

//登录界面
public class LoginJFrame extends JFrame implements MouseListener {
    //创建一个存储用户信息的集合
    static ArrayList<User> list = new ArrayList<>();

    static {
        list.add(new User("wyq", "tttt"));
    }


    //创建登录和注册两个按钮对象
    JButton login = new JButton();
    JButton register = new JButton();
    //创建用户名 密码 验证码JText对象
    JTextField usernameJText = new JTextField();
    JPasswordField jPasswordField = new JPasswordField();
    JTextField codeJText = new JTextField();

    JLabel rightCode = new JLabel();//验证码JLabel
    JLabel showPasswordJLabel = new JLabel(new ImageIcon("image\\login\\显示密码.png"));//显示密码小眼睛JLabel


    String code = verificationCode();//生成验证码


    public LoginJFrame() {
        //初始化登录界面
        initJFrame();

        //添加界面元素
        addElement();

        //显示界面
        this.setVisible(true);
    }

    //初始化界面
    private void initJFrame() {
        //设置宽高
        this.setSize(488, 430);
        //设置标题
        this.setTitle("登录界面");
        //界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认居中放置
        this.setLayout(null);
    }

    //添加元素
    private void addElement() {
        //添加用户名
        JLabel usernameJLabel = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameJLabel.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameJLabel);

        usernameJText.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameJText);

        //添加密码
        JLabel passwordJLabel = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordJLabel.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordJLabel);

        //添加显示密码小眼睛
        showPasswordJLabel.setBounds(395, 195, 21, 32);
//        showPasswordJLabel.setOpaque(false);
        this.getContentPane().add(showPasswordJLabel);
        showPasswordJLabel.addMouseListener(this);

        jPasswordField.setEchoChar('*');
        jPasswordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(jPasswordField);

        //添加验证码
        JLabel codeJLabel = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeJLabel.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeJLabel);

        codeJText.setBounds(195, 256, 100, 30);
        this.getContentPane().add(codeJText);

        rightCode.setText(code);
        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);

        //加载登录和注册按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //添加事件监听
        login.addMouseListener(this);
        register.addMouseListener(this);

        //加载登录界面背景图
        JLabel backgroundJLabel = new JLabel(new ImageIcon("image\\login\\background.png"));
        backgroundJLabel.setBounds(0, 0, 470, 390);
        this.getContentPane().add(backgroundJLabel);
        this.getContentPane().repaint();
    }

    //生成验证码
    public static String verificationCode() {
        Random r = new Random();
        char[] code = new char[5];
        for (int i = 0; i < code.length - 1; i++) {
            code[i] = (char) ('A' + r.nextInt(26));
            if (r.nextInt(2) == 1)
                code[i] = (char) (code[i] + 32);
        }
        code[code.length - 1] = (char) (r.nextInt(10) + '0');
        //形如ScVn2,打乱
        for (int i = 0; i < code.length; i++) {
            int j = r.nextInt(5);
            char c = code[i];
            code[i] = code[j];
            code[j] = c;
        }
        String s = new String(code);
//        System.out.println("验证码:"+s);
        return s;
    }

    //用户名或密码错误显示弹窗
    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);    //居中显示
        jDialog.setModal(true); //弹窗不关闭无法操作下面界面

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        } else if (obj == showPasswordJLabel) {
            jPasswordField.setEchoChar((char) 0);//显示密码
            showPasswordJLabel.setIcon(new ImageIcon("image\\login\\显示密码按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
            String usernameInput = usernameJText.getText();
            String passwordInput = new String(jPasswordField.getPassword());
            String vcodeInput = codeJText.getText();

            //判断输入是否为空
            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                showJDialog("用户名和密码不能为空!");
                clear();
                updateCode();
                return;
            } else if (vcodeInput.length() == 0) {
                showJDialog("验证码不能为空!");
                clear();
                updateCode();
                return;
            }

            int index = inList(usernameInput);
            if (index == -1) {//用户名不存在
                showJDialog("用户名或密码错误!");
                clear();
                updateCode();
            } else {//用户名存在
                if (list.get(index).getPassword().equals(passwordInput)) {//密码正确
                    if (code.equalsIgnoreCase(vcodeInput)) {//验证码正确
                        this.setVisible(false);
                        new GameJFrame();
                    } else {
                        showJDialog("验证码错误!");
                        clear();
                        updateCode();
                    }
                } else {
                    showJDialog("用户名或密码错误!");
                    clear();
                    updateCode();
                }
            }
        }
        else if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
            String usernameInput = usernameJText.getText();
            String passwordInput = new String(jPasswordField.getPassword());
            String vcodeInput = codeJText.getText();

            //判断用户名和密码是否为空
            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                showJDialog("用户名和密码不能为空!");
                clear();
                updateCode();
            } else {
                if (vcodeInput.equalsIgnoreCase(code)) {//验证码正确
                    if (inList(usernameInput) >= 0) {//用户已存在
                        showJDialog("该用户已存在!");
                        clear();
                        updateCode();
                    } else {//注册
                        list.add(new User(usernameInput, passwordInput));
                        showJDialog("注册成功!");
                        clear();
                        updateCode();
                    }
                } else {
                    if (vcodeInput.length() == 0) {
                        showJDialog("验证码不能为空！");
                        clear();
                        updateCode();
                    } else {
                        showJDialog("验证码错误，请重新输入！");
                        clear();
                        updateCode();
                    }
                }
            }

        }
        else if (obj == showPasswordJLabel) {
            jPasswordField.setEchoChar('*');
            showPasswordJLabel.setIcon(new ImageIcon("image\\login\\显示密码.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //清除用户名 密码 验证码文本框的数据
    public void clear() {
        usernameJText.setText("");
        jPasswordField.setText("");
        codeJText.setText("");
    }

    //更新验证码
    public void updateCode() {
        code = verificationCode();
        rightCode.setText(code);
    }

    //判断用户名是否在用户集合中 返回索引
    public int inList(String username) {
        if (list.size() == 0)
            return -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }
}
