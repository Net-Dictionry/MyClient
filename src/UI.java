/**
 * Created by apple on 16/12/16.
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


class UI extends JFrame  {

    //定义登录界面的组件
    JButton jb1, jb2 = null;
    JRadioButton jrb1, jrb2 = null;
    JPanel jp1, jp2, jp3,jp4 = null;
    JTextField jtf = null;
    JLabel jlb1, jlb2,jlb3= null;
    JPasswordField jpf1 = null;
    JPasswordField jpf2 = null;

    private MyClient mc;
    private UI self;

    public UI(MyClient x) {
        //创建组件
        //创建组件


        jb1 = new JButton("注册");
        jb2 = new JButton("退出");

        jlb1 = new JLabel("用户名：");
        jlb2 = new JLabel("密   码：");
        jlb3 = new JLabel("再次输入");

        jtf = new JTextField(15);
        jpf1 = new JPasswordField(15);
        jpf2 = new JPasswordField(15);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jp1.add(jlb1);
        jp1.add(jtf);

        jp1.add(jlb2);
        jp1.add(jpf1);

        jp2.add(jlb3);
        jp2.add(jpf2);

        jp3.add(jb1);
        jp3.add(jb2);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("注册界面");
        this.setLayout(new GridLayout(3, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 300, 230);
        mc =x;
        this.listener();
        self=this;
        //System.out.print(x.getcommand());

    }

    public void listener( ) {
        jb1.addActionListener(new ActionListener() {//按钮登录
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("注册登录按钮");
                //UserRegister user = new UserRegister(x);
                String password_a  = new String(jpf1.getPassword());
                String password_b  = new String(jpf2.getPassword());

                if (jtf.getText().isEmpty() || password_a.isEmpty()||password_b.isEmpty()){
                    JOptionPane.showMessageDialog(null, "请完整输入信息", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
                else if(!password_a.equals(password_b)){
                    JOptionPane.showMessageDialog(null, "两次密码不同", "提示信息", JOptionPane.WARNING_MESSAGE);
                    jpf1.setText("");
                    jpf2.setText("");
                    System.out.print(password_a);
                    System.out.print(password_b);
                }
                else {
                    String xcommand = "20"+jtf.getText()+"\t" + password_a;
                    String feedback = mc.Transfer_Command(xcommand);
                    System.out.print(feedback);
                    if(feedback.equals("true")){
                        JOptionPane.showMessageDialog(null, "注册成功！", "提示信息", JOptionPane.WARNING_MESSAGE);
                        self.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "注册失败,已存在该用户名！", "提示信息", JOptionPane.WARNING_MESSAGE);
                        jpf1.setText("");
                        jpf2.setText("");
                    }
                    //System.out.print(feedback);

                }
            }
        });

        jb2.addActionListener(new ActionListener() {//按钮退出
            @Override
            public void actionPerformed(ActionEvent e) {
                self.dispose();

            }
        });

    }

}


/*
 * 注册界面。
 */

/*
class UI extends JFrame implements ActionListener {


    //定义组件
    JFrame jf;
    JPanel jp;
    JLabel jl1, jl2;
    static JTextField jtf1, jtf2, jtf3, jtf4;
    JButton jb1, jb2;

    public UI(MyClient x) {
        //初始化组件
        jf = new JFrame();
        jp = new JPanel();
        jl1 = new JLabel("请输入用户名：");
        jtf1 = new JTextField(10);
        jtf1.setToolTipText("用户名必须为3-6位字母_或者数字");
        jl2 = new JLabel("请输入密码：");
        jtf2 = new JTextField(10);
        jtf2.setToolTipText("密码必须为6位字母_或者数字");
        jb1 = new JButton("返回");
        jb1.setToolTipText("点我返回登录界面哦");
        jb2 = new JButton("注册");
        //jb1.addActionListener(this);
        //jb2.addActionListener(this);

        jp.setLayout(new GridLayout(5,2));

        jp.add(jl1);
        jp.add(jtf1);

        jp.add(jl2);
        jp.add(jtf2);

        jp.add(jb1);
        jp.add(jb2);

        this.add(jp);
        this.setTitle("注册界面");
        this.setBounds(200, 100, 250, 150);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "返回") {
            this.dispose();


        } else if (e.getActionCommand() == "注册") {
            //调用注册方法
            this.zhuce();

        }

    }

    public void zhuce() {
        String regex1 = "\\w{3,6}"; //用户名必须是3-6位
        boolean flag1 = jtf1.getText().matches(regex1);

        String regex2 = "\\w{6}"; //密码必须是6位
        boolean flag2 = jtf2.getText().matches(regex2);

        String regex3 = "[\\u4e00-\\u9fa5]{2,4}"; //姓名必须是汉字2-4个字
        boolean flag3 = jtf3.getText().matches(regex3);

        String regex4 = "\\d{3,6}";  //学号必须是3-6位
        boolean flag4 = jtf4.getText().matches(regex4);

//		if(jtf1.getText()==null||jtf2.getText()==null||jtf3.getText()==null||jtf4.getText()==null)
        if (flag1 == false) {
            JOptionPane.showMessageDialog(null, "用户名填写错误,必须为3-6位字母_或者数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf1.setText("");
        } else if (flag2 == false) {
            JOptionPane.showMessageDialog(null, "密码填写错误,必须为6位字母_或者数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf2.setText("");
        } else if (flag3 == false) {
            JOptionPane.showMessageDialog(null, "姓名填写错误,必须汉字2-4位", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf3.setText("");
        } else if (flag4 == false) {
            JOptionPane.showMessageDialog(null, "学号填写错误,必须为3-6位数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf4.setText("");
        } else {

            this.jtf1.setText("");
            this.jtf2.setText("");
            this.jtf3.setText("");
            this.jtf4.setText("");

        }
    }

}*/


/*
class UI extends JFrame implements ActionListener {


    //门面类对象
    //Facade fcd=new Facade();

    //定义组件
    JFrame jf;
    JPanel jp;
    JLabel jl1, jl2, jl3, jl4;
    static JTextField jtf1, jtf2, jtf3, jtf4;
    JButton jb1, jb2;

    public UI() {
        //初始化组件
        jf = new JFrame();
        jp = new JPanel();
        jl1 = new JLabel("请输入用户名：");
        jtf1 = new JTextField(10);
        jtf1.setToolTipText("用户名必须为3-6位字母_或者数字");
        jl2 = new JLabel("请输入密码：");
        jtf2 = new JTextField(10);
        jtf2.setToolTipText("密码必须为6位字母_或者数字");
        jl3 = new JLabel("请输入姓名：");
        jtf3 = new JTextField(10);
        jtf3.setToolTipText("姓名必须汉字2-4位");
        jl4 = new JLabel("请输入学号：");
        jtf4 = new JTextField(10);
        jtf4.setToolTipText("学号必须为3-6位数字");

        jb1 = new JButton("返回");
        jb1.setToolTipText("点我返回登录界面哦");
        jb2 = new JButton("注册");
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jp.setLayout(new GridLayout(5,2));

        jp.add(jl1);
        jp.add(jtf1);

        jp.add(jl2);
        jp.add(jtf2);

        jp.add(jl3);
        jp.add(jtf3);

        jp.add(jl4);
        jp.add(jtf4);

        jp.add(jb1);
        jp.add(jb2);

        this.add(jp);
        this.setTitle("注册界面");
        this.setBounds(200, 100, 250, 150);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);


    }


    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "返回") {
            this.dispose();
            new UserRegister();
//			System.out.println("-------");

        } else if (e.getActionCommand() == "注册") {
            //调用注册方法
            this.zhuce();

        }

    }

    public void zhuce() {
        String regex1 = "\\w{3,6}"; //用户名必须是3-6位
        boolean flag1 = jtf1.getText().matches(regex1);

        String regex2 = "\\w{6}"; //密码必须是6位
        boolean flag2 = jtf2.getText().matches(regex2);

        String regex3 = "[\\u4e00-\\u9fa5]{2,4}"; //姓名必须是汉字2-4个字
        boolean flag3 = jtf3.getText().matches(regex3);

        String regex4 = "\\d{3,6}";  //学号必须是3-6位
        boolean flag4 = jtf4.getText().matches(regex4);

//		if(jtf1.getText()==null||jtf2.getText()==null||jtf3.getText()==null||jtf4.getText()==null)
        if (flag1 == false) {
            JOptionPane.showMessageDialog(null, "用户名填写错误,必须为3-6位字母_或者数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf1.setText("");
        } else if (flag2 == false) {
            JOptionPane.showMessageDialog(null, "密码填写错误,必须为6位字母_或者数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf2.setText("");
        } else if (flag3 == false) {
            JOptionPane.showMessageDialog(null, "姓名填写错误,必须汉字2-4位", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf3.setText("");
        } else if (flag4 == false) {
            JOptionPane.showMessageDialog(null, "学号填写错误,必须为3-6位数字", "提示信息", JOptionPane.WARNING_MESSAGE);
            jtf4.setText("");
        } else {
            //调用注册方法/先检查要注册的用户名是否存在
//			 SQLserver ss=new SQLserver();
//	    	 ss.ConnectSQL();
//	    	 ss.ZhuceVerify(jtf1.getText());

//			SQLserver ss=SQLserver.getInstance();
//	    	 ss.ConnectSQL();
//	    	 ss.ZhuceVerify(jtf1.getText());

            //使用门面模式
            //fcd.ConnectSQL();
            //fcd.zhuceverify(jtf1.getText());

//			ss.UserRegis(jtf1.getText(),jtf2.getText(),jtf3.getText(), jtf4.getText());
            this.jtf1.setText("");
            this.jtf2.setText("");
            this.jtf3.setText("");
            this.jtf4.setText("");

        }
    }

}*/