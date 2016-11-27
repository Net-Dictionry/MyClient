/**
 * Created by apple on 16/11/27.
 */

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class MyClient extends JFrame{

        private JTextField Jinput=new JTextField(35);//�����
        private JButton jbtsort=new JButton("search");//��ѯ��ť
        private JTextArea youdao=new JTextArea(6,47);//�е���ѯ���
        private JTextArea baidu=new JTextArea(6,47);//�ٶȲ�ѯ���
        private JTextArea biying=new JTextArea(6,47);//��Ӧ��ѯ���

        private DataOutputStream toServer;
        private DataInputStream fromServer;

	public static void main(String[] args) {
		new MyClient();
	}

        public MyClient() {
            JFrame frame=new JFrame();

            //��ѯ��
            frame.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
            frame.add(new JLabel("Input :"));
            frame.add(Jinput);

            //jbtsort.setForeground(new Color(0,0,149));
            //frame.add(jbtsort);

            //��ʾ��  �е�
            frame.add(new JLabel("youdao:"));
            youdao.setLineWrap(true);
            frame.add(youdao);

            //��ʾ��  �ٶ�
            frame.add(new JLabel("baidu:"));
            baidu.setLineWrap(true);
            frame.add(baidu);

            //��ʾ��  ��Ӧ
            frame.add(new JLabel("bing"));
            biying.setLineWrap(true);
            frame.add(biying);

            //
            Jinput.addActionListener(new TextAreaListener());
            //jbtsort.addActionListener(new ButtonListener());

            frame.setTitle("Net Dictionary");
            frame.setSize(600,500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            try {
                Socket socket=new Socket("202.119.44.57",8088);
                fromServer=new DataInputStream(socket.getInputStream());
                toServer=new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException ex) {
                System.err.println(ex);

            }
        }

        private class TextAreaListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try {
                    String input=Jinput.getText().trim();
                    System.out.println(input);//??????????
                    toServer.writeChars(input);
                    toServer.flush();

                    String result=fromServer.readUTF();
                    youdao.setText(result);
                    baidu.setText(result);
                    biying.setText(result);
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }

        /*private class ButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try {
                    String input=Jinput.getText().trim();
                    toServer.writeChars(input);
                    toServer.flush();

                    String result=fromServer.readUTF();
                    youdao.setText(result);
                    baidu.setText(result);
                    biying.setText(result);
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }*/
    }
//>>>>>>> origin/master

