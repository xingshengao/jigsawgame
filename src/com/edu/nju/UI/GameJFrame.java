package com.edu.nju.UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // 继承了JFrame的界面
    int[][] data = new int[4][4];
    // 记录0的位置
    int x = 0, y = 0;

    // 用来记录选的哪个路径
    String path = "image\\girl\\girl3\\";
    // 二维数组, 储存正确的数据
    int[][] win = new int[4][4];
    int step = 0; // 记录走了多少步
    // 创建选项上的条目对象
    JMenuItem rePlayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    // 更换图片JMenu里面的对象
    JMenuItem girl= new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {
        // 初始化界面
        initJFrame();
        // 初始话菜单
        initJMenu();

        // 打乱数据
        initData();

        // 初始化图片
        initImage();


        // 界面可视化, 一般放在最后
        this.setVisible(true);

        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    private void initData() {
        int[] arr = new int[16];
        for (int i = 0; i < 16; i++) {
            arr[i] = i;
        }
        // 打乱arr的顺序
        Random r = new Random();
        for (int i = 0; i < 16; ++i) {
            int idx = r.nextInt(16);
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
        for (int i = 0; i < 16; ++i) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }

    // 路径是相对路径, 从这个项目开始找
    private void initImage() {
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        // 判断胜利
        if (victory()) {
            JLabel jb = new JLabel(new ImageIcon("image/win.png"));
            jb.setBounds(203, 283, 197, 73);
            this.getContentPane().add(jb);
        }
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                // 创建一个imageIcon对象
                ImageIcon icon = new ImageIcon(path + data[i][j] + ".jpg");
                // 创建管理容器JLabel对象
                JLabel jLabel = new JLabel(icon);
                // 设置位置
                // 设置偏移量
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                jLabel.setBorder(new BevelBorder(1));
                // 把管理容器添加到界面
                this.getContentPane().add(jLabel);
            }
        }
        // 添加背景图片
        ImageIcon bg = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        // 把背景图片添加到界面
        this.getContentPane().add(background);
        // 细节, 先加载的在上面, 后加载的在下边

        // 刷新一下界面
        this.getContentPane().repaint();
    }

    private void initJMenu() {
        // 初始化菜单
        // 菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        // 创建菜单上两个选项的功能
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutUsJMenu = new JMenu("关于我们");

        // 更换美女图片
        JMenu changeImage = new JMenu("更换图片");
        // 美女运动动物添加到更换图片JMenu中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        // 选项的条目添加到选项中, 新增JMenu可以添加JMenu
        functionJMenu.add(rePlayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(changeImage);

        aboutUsJMenu.add(accountItem);
        // 将两个选项添加到菜单'
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutUsJMenu);
        // 给条目绑定事件
        rePlayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置界面的宽高
        this.setSize(603, 680);

        // 界面标题
        this.setTitle("拼图游戏v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);

        // 界面居中
        this.setLocationRelativeTo(null);

        // 设置关闭模式, 关闭所有窗口后可以停止程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 取消默认的居中放置, 只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                win[i][j] = i * 4 + j + 1;
            }
        }
        win[3][3] = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 按下不松开时, 会调用这个方法
        // 实现按下A显示完整图片的功能
        int code = e.getKeyCode();
        if (code == 65) {
            // 界面中的图片全部删除
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon(path + "all.jpg"));
            jLabel.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLabel);
            // 添加背景图片
            ImageIcon bg = new ImageIcon("image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508, 560);
            // 把背景图片添加到界面
            this.getContentPane().add(background);
            // 细节, 先加载的在上面, 后加载的在下边

            // 刷新一下界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否胜利, 如果胜利直接return 不能再移动
        if (victory()) {
            return;
        }

        // 对上下左右进行判断
        // 左37 上38 右39 下40
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 3) { // 不能向右走了
                return;
            }
            System.out.println("向左移动");
            // 逻辑把空白方块右边的数字向左移动
            // 空白x,y
            // x, y + 1
            if (y + 1 < 4) {
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                this.y++;
                step++;
                initImage();
            }
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 3) {
                return;
            }
            // 逻辑把空白方块下方的数字向上移动
            // 空白x,y
            // x + 1, y
            if (x + 1 < 4) {
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                this.x++;
                step++;
                initImage();
            }


        } else if (code == 39) {
            System.out.println("向右移动");
            // 逻辑把空白方块左边的数字向右移动
            // 空白x,y
            // x, y - 1
            if (y == 0) {
                return;
            }
            if (y - 1 >= 0) {
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                this.y--;
                step++;
                initImage();
            }
        } else if (code == 40) {
            System.out.println("向下移动");
            // 逻辑把空白方块下方的数字向上移动
            // 空白x,y
            // x - 1, y
            if (x == 0) {
                return;
            }
            if (x - 1 >= 0) {
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                this.x--;
                step++;
                initImage();
            }
        } else if (code == 65) {
            // 松开时, 不显示all图片
            initImage();
        } else if (code == 87) { // 作弊码W
            int[][] newArr = new int[4][4];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    newArr[i][j] = i * 4 + j + 1;
                }
            }
            newArr[3][3] = 0;
            data = newArr;
            x = 3;
            y = 3; // 作弊之后, 空白图片的位置
            initImage();
        }

    }

    // 判断是否胜利
    public boolean victory() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (data[i][j] != win[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        if (obj == rePlayItem) {
            System.out.println("重新游戏");
            // 打乱数据
            initData();
            // 计数器清0
            step = 0;
            // 重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            // 关闭当前界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            // 直接关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            // 创建一个弹窗对象
            JDialog jd = new JDialog();
            JLabel jb = new JLabel(new ImageIcon("image\\about1.jpg"));
            jb.setBounds(0, 0, 678, 678);
            jd.getContentPane().add(jb);
            // 弹窗设置大小
            jd.setSize(720, 720);
            // 弹窗置顶
            jd.setAlwaysOnTop(true);
            // 弹框居中
            jd.setLocation(0, 0);
//            jd.setLocationRelativeTo(null);
            // 弹框不关闭则无法操作
            jd.setModal(true);
            // 弹框可以显示
            jd.setVisible(true);
        } else if (obj == girl) {
            // gril 有1~13个
            Random rd = new Random();
            int aNum = rd.nextInt(13) + 1; // [0:12] + 1
            path = "image\\girl\\girl" + aNum + "\\";
            // 改变路径后, 写重新游戏的逻辑
            // 打乱数据
            initData();
            // 计数器清0
            step = 0;
            // 重新加载图片
            initImage();
        } else if (obj == animal) {
            // 1~8个
            Random rd = new Random();
            int aNum = rd.nextInt(8) + 1; // [0:7] + 1
            path = "image\\animal\\animal" + aNum + "\\";
            // 改变路径后, 写重新游戏的逻辑
            // 打乱数据
            initData();
            // 计数器清0
            step = 0;
            // 重新加载图片
            initImage();
        } else if (obj == sport) {
            //1~10
            Random rd = new Random();
            int aNum = rd.nextInt(10) + 1; // [0:9] + 1
            path = "image\\sport\\sport" + aNum + "\\";
            // 改变路径后, 写重新游戏的逻辑
            // 打乱数据
            initData();
            // 计数器清0
            step = 0;
            // 重新加载图片
            initImage();
        }
    }

    public static class User {
        private String username;
        private String password;


        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        /**
         * 获取
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * 设置
         * @param username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * 获取
         * @return password
         */
        public String getPassword() {
            return password;
        }

        /**
         * 设置
         * @param password
         */
        public void setPassword(String password) {
            this.password = password;
        }


    }
}
