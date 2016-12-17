/**
 * Created by apple on 16/11/27.
 */

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class MyClient extends JFrame {

        private JTextField Jinput = new JTextField(33);//
        private JButton jbtsort = new JButton("登录");//

        private JCheckBox jcb1 = new JCheckBox("有道");
        private JCheckBox jcb2 = new JCheckBox("金山");
        private JCheckBox jcb3 = new JCheckBox("bing");

        private JButton jbtspot1 = new JButton("赞");//
        private JButton jbtspot2 = new JButton("赞");//
        private JButton jbtspot3 = new JButton("赞");//
        private JTextArea text_1 = new JTextArea(6, 38);//
        private JTextArea text_2 = new JTextArea(6, 38);//
        private JTextArea text_3 = new JTextArea(6, 38);//

        private boolean bool_youdao = false;
        private boolean bool_jinshan = false;
        private boolean bool_bing = false;


        private DataOutputStream toServer;
        private DataInputStream fromServer;

        private String comand = "孙喜哲";

        public String getcommand(){
            return comand;
        }

        public static void main(String[] args) {
            MyClient x = new MyClient();
        }

        public void listener(MyClient x){


            jcb1.addItemListener(new ItemListener() {//单选框监听
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JCheckBox jCheckBox;
                    jCheckBox = (JCheckBox) e.getSource();
                    if (jCheckBox.isSelected()) {
                        bool_youdao = true;
                        System.out.print("youdao");
                        System.out.println(bool_youdao);
                    } else {
                        bool_youdao = false;
                        System.out.print("youdao");
                        System.out.println(bool_youdao);
                    }


                }
            });

            jcb2.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JCheckBox jCheckBox;
                    jCheckBox = (JCheckBox) e.getSource();
                    if (jCheckBox.isSelected()) {
                        bool_jinshan = true;
                        System.out.print("jinshan");
                        System.out.println(bool_jinshan);
                    } else {
                        bool_jinshan = false;
                        System.out.print("jinshan");
                        System.out.println(bool_jinshan);
                    }

                }
            });

            jcb3.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JCheckBox jCheckBox;
                    jCheckBox = (JCheckBox) e.getSource();
                    if (jCheckBox.isSelected()) {
                        bool_bing = true;
                        System.out.print("bing");
                        System.out.println(bool_bing);
                    } else {
                        bool_bing = false;
                        System.out.print("bing");
                        System.out.println(bool_bing);
                    }

                }
            });

            jbtsort.addActionListener(new ActionListener() {//按钮监听
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.print("注册登录按钮");
                    UserRegister user = new UserRegister(x);


                }
            });

            Jinput.addActionListener(new ActionListener() {//文本框监听
                @Override
                public void actionPerformed(ActionEvent e) {

                        String input = "10" + Jinput.getText().trim();
                        String result = Transfer_Command(input);
                        System.out.print(result);
                        String[] TransRes = SegmentResult(result);

                        text_1.setText(TransRes[0]);
                        text_2.setText(TransRes[2]);
                        text_3.setText(TransRes[4]);



                }
            });

        }

        public MyClient() {

            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
            frame.add(new JLabel("Input :"));
            frame.add(Jinput);
            jbtsort.setForeground(new Color(0, 0, 149));
            frame.add(jbtsort);

            frame.add(jcb1);
            frame.add(jcb2);
            frame.add(jcb3);

            text_1.setLineWrap(true);
            frame.add(text_1);
            frame.add(jbtspot1);//
            //

            text_2.setLineWrap(true);
            frame.add(text_2);
            frame.add(jbtspot2);//
            //

            text_3.setLineWrap(true);
            frame.add(text_3);
            frame.add(jbtspot3);//
            //

            frame.setTitle("Net Dictionary");
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            listener(this);

            try {
                //Socket socket = new Socket("192.168.1.100", 8088);
                Socket socket = new Socket("192.168.1.101", 8088);
                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());
                System.out.print("sunxizhe");

            } catch (IOException ex) {

                text_1.append(ex.toString() + '\n');
                text_2.append(ex.toString() + '\n');
                text_3.append(ex.toString() + '\n');
            }
        }



        public String[] SegmentResult(String str) {

            String[] res = new String[6];
            int index1 = str.indexOf("@");
            res[0] = str.substring(0, index1);//有道解释

            int index2 = str.indexOf("@", index1 + 1);
            res[1] = str.substring(index1 + 1, index2);//有道赞数

            int index3 = str.indexOf("@", index2 + 1);
            res[2] = str.substring(index2 + 1, index3);//金山解释

            int index4 = str.indexOf("@", index3 + 1);
            res[3] = str.substring(index3 + 1, index4);//金山赞数

            int index5 = str.indexOf("@", index4 + 1);
            res[4] = str.substring(index4 + 1, index5);//bing解释

            res[5] = str.substring(index5 + 1, str.length());//bing赞数

            return res;
        }

        public String Transfer_Command(String command)  {

            try {
                this.toServer.writeUTF(command);
                this.toServer.flush();
                return fromServer.readUTF();
            }
            catch(IOException e){
                e.printStackTrace();
                return "NULL";
            }


        }
    }

