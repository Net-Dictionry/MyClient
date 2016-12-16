/**
 * Created by apple on 16/11/27.
 */

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class MyClient extends JFrame{

        private JTextField Jinput=new JTextField(36);//
        private JButton jbtsort=new JButton("search");//
        private JTextArea youdao=new JTextArea(6,45);//
        private JTextArea baidu=new JTextArea(6,45);//
        private JTextArea bing=new JTextArea(6,45);//

        private DataOutputStream toServer;
        private DataInputStream fromServer;

	public static void main(String[] args) {
		new MyClient();
	}

        public MyClient() {
            JFrame frame=new JFrame();

            //
            frame.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
            frame.add(new JLabel("Input :"));
            frame.add(Jinput);

            jbtsort.setForeground(new Color(0,0,149));
            frame.add(jbtsort);

            //
            frame.add(new JLabel("youdao:"));
            youdao.setLineWrap(true);
            frame.add(youdao);

            //
            frame.add(new JLabel("  baidu:  "));
            baidu.setLineWrap(true);
            frame.add(baidu);

            //
            frame.add(new JLabel("   bing:   "));
            bing.setLineWrap(true);
            frame.add(bing);

            //
            Jinput.addActionListener(new TextAreaListener());
            jbtsort.addActionListener(new ButtonListener());

            frame.setTitle("Net Dictionary");
            frame.setSize(600,500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            try {
                Socket socket=new Socket("192.168.1.101",8088);
                fromServer=new DataInputStream(socket.getInputStream());
                toServer=new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException ex) {
            	youdao.append(ex.toString()+'\n');
            }
        }

        private class TextAreaListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try {
                    String input=Jinput.getText().trim();
                    input='1'+'\t'+input;
                    toServer.writeUTF(input);
                    toServer.flush();

                    String result=fromServer.readUTF();
                    String[] TransRes=SegmentResult(result);
                    youdao.setText(TransRes[0]);
                    baidu.setText(TransRes[1]);
                    bing.setText(TransRes[2]);
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }

        private class ButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try {
                	String input=Jinput.getText().trim();
                	input='1'+'\t'+input;
                    toServer.writeUTF(input);
                    toServer.flush();

                    String result=fromServer.readUTF();
                    String[] TransRes=SegmentResult(result);
                    youdao.setText(TransRes[0]);
                    baidu.setText(TransRes[1]);
                    bing.setText(TransRes[2]);
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        
        public static String[] SegmentResult(String str){
        	String[] res=new String [3];
        	int index1=str.indexOf('\t');
        	res[0]=str.substring(0, index1);
        	int index2=str.indexOf('\t',index1+1);
        	res[1]=str.substring(index1+1, index2);
        	res[2]=str.substring(index2+1, str.length());
        	return res;
        }
    }
//>>>>>>> origin/master

