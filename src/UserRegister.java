/**
 * Created by apple on 16/12/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UserRegister extends JFrame  {

    //定义登录界面的组件
    JButton jb1, jb2, jb3 = null;
    JRadioButton jrb1, jrb2 = null;
    JPanel jp1, jp2, jp3 = null;
    JTextField jtf = null;
    JLabel jlb1, jlb2 = null;
    JPasswordField jpf = null;

    private MyClient mc;
    private UserRegister self;

    public UserRegister(MyClient x) {
        //创建组件
        //创建组件

        jb1 = new JButton("登录");
        jb2 = new JButton("注册");
        jb3 = new JButton("退出");

        jlb1 = new JLabel("用户名：");
        jlb2 = new JLabel("密    码：");

        jtf = new JTextField(10);
        jpf = new JPasswordField(10);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);

        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("注册登录界面");
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
                if (jtf.getText().isEmpty() || jpf.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "请输入用户名和密码", "提示信息", JOptionPane.WARNING_MESSAGE);
                else {
                    String xcommand = "21"+jtf.getText()+"\t"+jpf.getText();
                    String feedback = mc.Transfer_Command(xcommand);
                    System.out.print(feedback);
                    if(feedback.equals("true")){
                        JOptionPane.showMessageDialog(null, "登录成功！", "提示信息", JOptionPane.WARNING_MESSAGE);
                        self.dispose();

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "登录失败！", "提示信息", JOptionPane.WARNING_MESSAGE);
                        jpf.setText(" ");
                    }
                    //System.out.print(feedback);

                }
            }
        });

        jb2.addActionListener(new ActionListener() {//按钮注册
            @Override
            public void actionPerformed(ActionEvent e) {
                UI ui = new UI(mc);

            }
        });

        jb3.addActionListener(new ActionListener() {//按钮退出
            @Override
            public void actionPerformed(ActionEvent e) {
                self.dispose();

            }
        });

    }

}
