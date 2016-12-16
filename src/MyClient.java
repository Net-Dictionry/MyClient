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

        private DataOutputStream toServer;
        private DataInputStream fromServer;

        public static void main(String[] args) {
            MyClient x = new MyClient();
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
            Jinput.addActionListener(new TextAreaListener());
            jbtsort.addActionListener(new ButtonListener());
            Jinput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    //super.keyPressed(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            String input = "10" + Jinput.getText().trim();
                            //input="10"+input;
                            toServer.writeUTF(input);
                            toServer.flush();

                            String result = fromServer.readUTF();
                            String[] TransRes = SegmentResult(result);
                            text_1.setText(TransRes[0]);
                            text_2.setText(TransRes[1]);
                            text_3.setText(TransRes[2]);

                        } catch (IOException ex) {
                            System.err.println(ex);
                        }

                    }
                }
            });


            try {
                Socket socket = new Socket("192.168.1.100", 8088);
                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());

            } catch (IOException ex) {

                text_1.append(ex.toString() + '\n');
                text_2.append(ex.toString() + '\n');
                text_3.append(ex.toString() + '\n');
            }
        }


        private class TextAreaListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {

                    String input = "10" + Jinput.getText().trim();

                    toServer.writeUTF(input);
                    toServer.flush();

                    String result = fromServer.readUTF();
                    String[] TransRes = SegmentResult(result);

                    text_1.setText(TransRes[0]);
                    text_2.setText(TransRes[1]);
                    text_3.setText(TransRes[2]);

                    //text_1.setText(result);
                    //text_2.setText(result);
                    //text_3.setText(result);


                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }

        private class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                /*try {
                	String input="10"+Jinput.getText().trim();
                	//input="10"+input;
                    toServer.writeUTF(input);
                    toServer.flush();

                    String result=fromServer.readUTF();
                    String[] TransRes=SegmentResult(result);
                    text_1.setText(TransRes[0]);
                    text_2.setText(TransRes[1]);
                    text_3.setText(TransRes[2]);

                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }*/
                UserRegister ur = new UserRegister();

            }
        }

        public String[] SegmentResult(String str) {
            String[] res = new String[3];
            int index1 = str.indexOf("@");
            res[0] = str.substring(0, index1);
            int index2 = str.indexOf("@", index1 + 1);
            res[1] = str.substring(index1 + 1, index2);
            res[2] = str.substring(index2 + 1, str.length());
            return res;
        }
    }


