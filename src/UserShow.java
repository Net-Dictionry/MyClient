/**
 * Created by apple on 16/12/18.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserShow extends JFrame {

    JButton jb1, jb2 = null;
    JPanel jp1, jp2,jp3 = null;
    JTextField jtf = null;
    JLabel jlb1,jlb2 = null;

    private MyClient mc;
    private UserShow self;

    public UserShow(MyClient x) {
        //创建组件
        //创建组件

        jb1 = new JButton("下线");
        jb2 = new JButton("退出");

        jlb1 = new JLabel("用户名：");
        jtf = new JTextField(15);

        //jlb2 = new JLabel("在线用户:");
        //JTextArea text = new JTextArea(15, 15);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jp1.setSize(100,100);

        jp1.add(jlb1);
        jp1.add(jtf);

        //jp1.add(jlb2);
        //jp2.add(text);

        jp2.add(jb1);
        jp2.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);



        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("用户状态");
        this.setLayout(new GridLayout(3, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 300, 230);
        mc =x;
        self=this;
        String _username = mc.get_UserName();
        System.out.print(_username);
        jtf.setText(_username);
        self.listener();

        //System.out.print(x.getcommand());

    }

    public void listener( ) {
        jb1.addActionListener(new ActionListener() {//按钮下线
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = "22"+mc.get_UserName();
                String result = mc.Transfer_Command(input);
                if(result.equals("true")){
                mc.add_login(false);//下线
                self.dispose();
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
