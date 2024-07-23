package com.itheima;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PictureFrame extends JFrame {
    //定义一个二维数组 存储图片编号
    private int[][] datas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //定义移动成功后的数组
    private int[][] winDatas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //记录白色图片的位置
    private int  x0;
    private int  y0;

    //定义按钮
    private JButton shangButton;
    private JButton xiaButton;
    private JButton zuoButton;
    private JButton youButton;
    private JButton qiuZhuButton;
    private JButton chongZhiButton;

    //定义面板
    private JPanel imagePanel;

    //判断游戏是否结束
    public boolean isSuccess(){
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != winDatas[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // 无参构造方法
    public PictureFrame() {
        //初始化窗口
        initFrame();
        //打乱二维数组
        randomDatas();
        //绘制窗口
        paintView();
        //给按钮添加事件
        addButtonEvent();
        this.setVisible(true);
    }

    //求助按钮 直接成功
    public void success(){
        datas = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        shangButton.setEnabled(false);
        xiaButton.setEnabled(false);
        zuoButton.setEnabled(false);
        youButton.setEnabled(false);
    }

    //重绘方法
    public void rePaintView(){
        //移除面板上所有的组件
        imagePanel.removeAll();
        //遍历二维数组 把图片装到面板上
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel imageLabel = new JLabel(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\" + datas[i][j] + ".png"));
                imageLabel.setBounds(j * 90, i * 90, 90, 90);
                //把图片放在Panel面板上
                imagePanel.add(imageLabel);
            }
        }
        //把Panel面板放回JFrame
        this.add(imagePanel);
        //重新绘制窗体
        imagePanel.repaint();
    }

    //给按钮添加事件
    public void addButtonEvent(){
        shangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("上");
                //边界处理
                if (x0 == 3){
                    return;
                }
                //确认x0≠3后开始交换
                datas[x0][y0] = datas[x0+1][y0];
                datas[x0+1][y0] = 0;
                x0 = x0+1;

                //调用重绘的方法
                rePaintView();
                //判断是否成功
                if (isSuccess()){
                    success();
                    rePaintView();
                    JOptionPane.showMessageDialog(PictureFrame.this, "恭喜获胜!");
                }
            }
        });
        xiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("下");
                if (x0 == 0){
                    return;
                }
                datas[x0][y0] = datas[x0 - 1][y0];
                datas[x0 - 1][y0] = 0;
                x0 = x0 - 1;
                rePaintView();
                //判断是否成功
                if (isSuccess()){
                    success();
                    rePaintView();
                    JOptionPane.showMessageDialog(PictureFrame.this, "恭喜获胜!");
                }
            }
        });
        zuoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("左");
                if (y0 == 3){
                    System.out.println("撞墙了");
                    return;
                }
                datas[x0][y0] = datas[x0][y0+1];
                datas[x0][y0+1] = 0;
                y0 = y0 + 1;
                rePaintView();
                //判断是否成功
                if (isSuccess()){
                    success();
                    rePaintView();
                    JOptionPane.showMessageDialog(PictureFrame.this, "恭喜获胜!");
                }
            }
        });
        youButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("右");
                if (y0 == 0){
                    return;
                }
                datas[x0][y0] = datas[x0][y0-1];
                datas[x0][y0-1] = 0;
                y0 = y0 - 1;
                rePaintView();
                //判断是否成功
                if (isSuccess()){
                    success();
                    rePaintView();
                    JOptionPane.showMessageDialog(PictureFrame.this, "恭喜获胜!");
                }
            }
        });
        qiuZhuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("求助");
                success();
                rePaintView();
                JOptionPane.showMessageDialog(PictureFrame.this, "恭喜获胜!");
            }
        });
        chongZhiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重置");
                datas = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                randomDatas();
                rePaintView();
                shangButton.setEnabled(true);
                xiaButton.setEnabled(true);
                zuoButton.setEnabled(true);
                youButton.setEnabled(true);
            }
        });
    }

    //二维数组元素打乱
    public void randomDatas(){
        Random r = new Random();
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                int x = r.nextInt(datas.length);
                int y = r.nextInt(datas[i].length);
                int temp;
                temp = datas[i][j];
                datas[i][j] = datas[x][y];
                datas[x][y] = temp;
            }
        }
        
        //寻找0号图片的位置
        wc:for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] == 0)
                {
                    x0 = i;
                    y0 = j;

                    break wc;
                }
            }
        }
        System.out.println(x0 + "," + y0);

    }

    //初始化窗口
    public void initFrame() {
        this.setSize(960, 565);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setTitle("动漫拼图");
    }

    //窗体上组件的绘制
    public void paintView() {
        //标题图片
        JLabel titleLabel = new JLabel(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\title.png"));
        titleLabel.setBounds(354, 27, 232, 57);
        this.add(titleLabel);

//        //定义一个二维数组 存储图片编号
//        int[][] datas = {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
//        };
        //建立一个面板 安装到JFrame上
        imagePanel = new JPanel();
        imagePanel.setBounds(150, 114, 360, 360);
        //取消默认布局
        imagePanel.setLayout(null);
        //遍历二维数组 把图片装到面板上
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel imageLabel = new JLabel(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\" + datas[i][j] + ".png"));
                imageLabel.setBounds(j * 90, i * 90, 90, 90);
                //把图片放在Panel面板上
                imagePanel.add(imageLabel);
            }
        }
        //把Panel面板放回JFrame
        this.add(imagePanel);

        //动漫参照图
        JLabel canZhaoTuLabel = new JLabel(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\canzhaotu.png"));
        canZhaoTuLabel.setBounds(574, 114, 122, 121);
        this.add(canZhaoTuLabel);

        //上下左右 求助 重置
        shangButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\shang.png"));
        shangButton.setBounds(732, 265, 57, 57);
        this.add(shangButton);

        xiaButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\xia.png"));
        xiaButton.setBounds(732, 347, 57, 57);
        this.add(xiaButton);

        zuoButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\zuo.png"));
        zuoButton.setBounds(650, 347, 57, 57);
        this.add(zuoButton);

        youButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\you.png"));
        youButton.setBounds(813, 347, 57, 57);
        this.add(youButton);

        qiuZhuButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\qiuzhu.png"));
        qiuZhuButton.setBounds(626, 444, 108, 45);
        this.add(qiuZhuButton);

        chongZhiButton = new JButton(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\chongzhi.png"));
        chongZhiButton.setBounds(786, 444, 108, 45);
        this.add(chongZhiButton);

        //背景图片
        JLabel backgroundLable = new JLabel(new ImageIcon("D:\\JavaWorkSpace\\literals\\beautiful-lady\\images\\background.png"));
        backgroundLable.setBounds(0, 0, 960, 530);
        this.add(backgroundLable);

    }
}
