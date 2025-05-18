package com.wyq.UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

//游戏的主界面
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] arr = new int[4][4];    //图片索引数组
    int[][] win_arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    int index_0_x, index_0_y;   //0在二维数组中的位置索引
    int step_count = 0;

    String type = "girl";
    int num = 1;

    String filepath = "image\\" + type + "\\" + type + num + "\\";

    //创建功能菜单选项下的条目对象
    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    //创建更换图片的子Item
    JMenuItem girlItem = new JMenuItem("女孩");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱图片)
        initImageIndex();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    //初始化打乱图片索引
    private void initImageIndex() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int index = r.nextInt(16);
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }

        for (int i = 0; i < 16; i++) {
            if (a[i] == 0) {
                index_0_x = i / 4;
                index_0_y = i % 4;
            }
            arr[i / 4][i % 4] = a[i];
        }
    }

    //初始化图片
    private void initImage() {
        //清空所有图片
        this.getContentPane().removeAll();

        //判断胜利
        if (isWin()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        //显示步数
        JLabel countJLabel = new JLabel("步数:" + step_count);
        countJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(countJLabel);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
//                if (i == 3 && j == 3)
//                    break;
                //创建JLabel对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(filepath + arr[i][j] + ".jpg"));
                jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                //给每个图片添加边框
                jLabel.setBorder(new BevelBorder(1));   // 0:凸起来 1:凹下去
                //将JLabel对象添加到界面的隐藏容器中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片(先加载的图片在上方,所以背景图最后添加)
        JLabel backgroundJLabel = new JLabel(new ImageIcon("image\\background.png"));
        backgroundJLabel.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backgroundJLabel);
        this.getContentPane().repaint();
    }

    //初始化菜单
    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar JB = new JMenuBar();

        //创建菜单选项
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");
        JMenu changeImageJmenu = new JMenu("更换图片");

        //将条目对象添加到菜单选项中
        changeImageJmenu.add(girlItem);
        changeImageJmenu.add(animalItem);
        changeImageJmenu.add(sportItem);

        functionJmenu.add(changeImageJmenu);
        functionJmenu.add(replayItem);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(accountItem);

        //给条目绑定事件
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单选项添加到菜单
        JB.add(functionJmenu);
        JB.add(aboutJmenu);

        this.setJMenuBar(JB);
    }

    //初始化界面
    private void initJFrame() {
        //设置宽高
        this.setSize(603, 680);
        //设置标题
        this.setTitle("拼图小游戏1.0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认居中放置
        this.setLayout(null);
        //给界面添加键盘事件监听
        this.addKeyListener(this);
    }

    //判断胜利
    public boolean isWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != win_arr[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //如果胜利直接返回
        if (isWin())
            return;

        int code = e.getKeyCode();
//        System.out.println(code);
        if (code == 65) {  //按下a不松时展示完整图片
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel allJLabel = new JLabel(new ImageIcon(filepath + "all.jpg"));
            allJLabel.setBounds(83, 134, 420, 420);
            this.getContentPane().add(allJLabel);
            //加载背景图片
            JLabel backgroundJLabel = new JLabel(new ImageIcon("image\\background.png"));
            backgroundJLabel.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backgroundJLabel);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //如果胜利直接返回
        if (isWin())
            return;

        // 左:37 上:38 右:39 下:40
        int code = e.getKeyCode();
//        System.out.println(code);
        if (code == 37) {
            if (index_0_y == 0)
                return;
            System.out.println("向左移动");
            arr[index_0_x][index_0_y] = arr[index_0_x][index_0_y - 1];
            arr[index_0_x][index_0_y - 1] = 0;
            index_0_y--;
            step_count++;
            this.initImage();
        } else if (code == 38) {
            if (index_0_x == 0)
                return;
            System.out.println("向上移动");
            arr[index_0_x][index_0_y] = arr[index_0_x - 1][index_0_y];
            arr[index_0_x - 1][index_0_y] = 0;
            index_0_x--;
            step_count++;
            this.initImage();
        } else if (code == 39) {
            if (index_0_y == 3)
                return;
            System.out.println("向右移动");
            arr[index_0_x][index_0_y] = arr[index_0_x][index_0_y + 1];
            arr[index_0_x][index_0_y + 1] = 0;
            index_0_y++;
            step_count++;
            this.initImage();
        } else if (code == 40) {
            if (index_0_x == 3)
                return;
            System.out.println("向下移动");
            arr[index_0_x][index_0_y] = arr[index_0_x + 1][index_0_y];
            arr[index_0_x + 1][index_0_y] = 0;
            index_0_x++;
            step_count++;
            this.initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) { //按w直接拼成
            arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
            initImage();
//            index_0_x=3;
//            index_0_y=3;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            //计数器清零
            step_count = 0;
            //打乱arr数组
            initImageIndex();
            //重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.exit(0);
        } else if (obj == accountItem) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();

            JLabel jLabel = new JLabel(new ImageIcon("image\\me.jpg"));
            jLabel.setBounds(0, 0, 254, 347);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(550, 550);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);    //居中
            jDialog.setModal(true); //弹框不关闭则无法操作下面的界面
            jDialog.setVisible(true);
        } else if (obj == girlItem) {
            Random r = new Random();
            int i = r.nextInt(13);
            filepath = "image\\girl\\girl" + (i + 1) + "\\";
            //打乱arr数组
            initImageIndex();
            //重新加载图片
            initImage();
            //更改type\num值
            type = "girl";
            num = i + 1;
        } else if (obj == animalItem) {
            Random r = new Random();
            int i = r.nextInt(8);
            filepath = "image\\animal\\animal" + (i + 1) + "\\";
            //打乱arr数组
            initImageIndex();
            //重新加载图片
            initImage();
            //更改type\num值
            type = "animal";
            num = i + 1;
        } else if (obj == sportItem) {
            Random r = new Random();
            int i = r.nextInt(10);
            filepath = "image\\sport\\sport" + (i + 1) + "\\";
            //打乱arr数组
            initImageIndex();
            //重新加载图片
            initImage();
            //更改type\num值
            type = "sport";
            num = i + 1;
        }
    }
}