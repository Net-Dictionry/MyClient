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

        private JCheckBox jcb1 = new JCheckBox("有道");//词典复选框
        private JCheckBox jcb2 = new JCheckBox("金山");
        private JCheckBox jcb3 = new JCheckBox("bing");

        private JButton jbtspot1 = new JButton("赞");//
        private JButton jbtspot2 = new JButton("赞");//
        private JButton jbtspot3 = new JButton("赞");//
        private JTextArea text_1 = new JTextArea(6, 38);//
        private JTextArea text_2 = new JTextArea(6, 38);//
        private JTextArea text_3 = new JTextArea(6, 38);//

        private boolean bool_youdao = false;//判断复选框是否选中
        private boolean bool_jinshan = false;
        private boolean bool_bing = false;

        private boolean bool_zan_youdao = false;//判断是否已经点赞
        private boolean bool_zan_jinshan = false;
        private boolean bool_zan_bing = false;

        private int zan_youdao = 0;//计算各个词典中单词的点赞数
        private int zan_jinshan = 0;
        private int zan_bing = 0;

        private String text_one = "";//标记编辑框中显示想得到词典类型
        private String text_two = "";
        private String text_there = "";

        private String word = "";//纪录每次搜索的单词
        private String UserName = "";

        private boolean bool_login = false;//标记用户登录状态

        private boolean search_over = false;//判断搜索是否完成

        private DataOutputStream toServer;//发送给服务器的流
        private DataInputStream fromServer;//接受自服务起的流


        public static void main(String[] args) {
            MyClient x = new MyClient();
        }


        public String get_UserName() {

           return this.UserName;

        }

        public void add_UserName(String y){
            this.UserName = y;
        }

        public boolean get_login() {

            return this.bool_login;

        }

        public void add_login(boolean x) {
            this.bool_login = x;
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
                    /*if(search_over==true){
                        if (jCheckBox.isSelected()) {

                        }
                    }
                    */


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
                    boolean _bool = false;
                    if(bool_login==false && _bool==false) {
                        System.out.print("注册登录按钮");
                        UserRegister user = new UserRegister(x);
                        System.out.print("dengluzhuangtai"+bool_login);
                        _bool = true;
                    }

                    if(bool_login==true&&_bool==false){
                        System.out.print("UserShow");
                        UserShow us = new UserShow(x);
                    }

                }
            });

            jbtspot1.addActionListener(new ActionListener() {//点赞
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(text_one.equals("youdao")&&bool_youdao==true&&search_over==true&&bool_zan_youdao==false){

                        String input = "11"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_youdao = true;
                        }
                    }
                    if(text_one.equals("jinshan")&&bool_jinshan==true&&search_over==true&&bool_zan_jinshan==false){
                        String input = "12"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_jinshan = true;
                        }
                    }
                    if(text_one.equals("bing") && bool_bing==true&&search_over==true&&bool_zan_bing==false){
                        String input = "13"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_bing = true;
                        }

                    }

                }
            });

            jbtspot2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(text_two.equals("youdao") && bool_youdao==true&&search_over==true&&bool_zan_youdao==false){

                        String input = "11"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_youdao = true;
                        }
                    }
                    if(text_two.equals("jinshan") && bool_jinshan==true&&search_over==true&&bool_zan_jinshan==false){
                        String input = "12"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_jinshan = true;
                        }
                    }
                    if(text_two.equals("bing") && bool_bing==true&&search_over==true&&bool_zan_bing==false){
                        String input = "13"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_bing = true;
                        }
                    }
                }
            });

            jbtspot3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(text_there.equals("youdao") && bool_youdao==true&&search_over==true&&bool_zan_youdao==false){

                        String input = "11"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_youdao = true;
                        }
                    }
                    if(text_there.equals("jinshan") && bool_jinshan==true&&search_over==true&&bool_zan_jinshan==false){
                        String input = "12"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_jinshan = true;
                        }
                    }
                    if(text_there.equals("bing") && bool_bing==true&&search_over==true&&bool_zan_bing==false){
                        String input = "13"+word;
                        String result = Transfer_Command(input);
                        if(result.equals("true")){
                            bool_zan_bing = true;
                        }
                    }
                }

            });


            Jinput.addActionListener(new ActionListener() {//文本框监听
                @Override
                public void actionPerformed(ActionEvent e) {

                    search_over = false;
                    String input = "10" + Jinput.getText().trim();
                    word = Jinput.getText().trim();

                    String result = Transfer_Command(input);
                    System.out.print(result);
                    String[] TransRes = SegmentResult(result);

                    bool_show(TransRes);//依据复选框判断是否输出
                    sort_search(TransRes);

                    boolean bool_if = false;

                    if(zan_youdao >= zan_bing && zan_youdao >= zan_jinshan && bool_if==false){
                        if(zan_jinshan >= zan_bing){
                            text_1.setText("有道翻译："+"\n"+TransRes[0]);
                            text_2.setText("金山翻译："+"\n"+TransRes[2]);
                            text_3.setText("bing翻译："+"\n"+TransRes[4]);
                            text_one ="youdao";
                            text_two = "jinshan";
                            text_there = "bing";}
                        else{
                            text_1.setText("有道翻译："+"\n"+TransRes[0]);
                            text_3.setText("金山翻译："+"\n"+TransRes[2]);
                            text_2.setText("bing翻译："+"\n"+TransRes[4]);
                            text_one ="youdao";
                            text_two = "bing";
                            text_there = "jinshan";
                        }
                        bool_if = true;

                    }

                    if(zan_bing >= zan_youdao && zan_bing >= zan_jinshan && bool_if==false){
                        if(zan_youdao >=zan_jinshan){
                        text_2.setText("有道翻译："+"\n"+TransRes[0]);
                        text_3.setText("金山翻译："+"\n"+TransRes[2]);
                        text_1.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="bing";
                        text_two = "youdao";
                        text_there = "jinshan";}
                        else{
                            text_3.setText("有道翻译："+"\n"+TransRes[0]);
                            text_2.setText("金山翻译："+"\n"+TransRes[2]);
                            text_1.setText("bing翻译："+"\n"+TransRes[4]);
                            text_one ="bing";
                            text_two = "jinshan";
                            text_there = "youdao";
                        }
                        bool_if = true;
                    }

                    if(zan_jinshan >= zan_youdao && zan_jinshan >= zan_bing && bool_if==false){
                        if(zan_youdao >= zan_bing){
                        text_2.setText("有道翻译："+"\n"+TransRes[0]);
                        text_1.setText("金山翻译："+"\n"+TransRes[2]);
                        text_3.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="jinshan";
                        text_two = "youdao";
                        text_there = "bing";}
                        else{
                            text_3.setText("有道翻译："+"\n"+TransRes[0]);
                            text_1.setText("金山翻译："+"\n"+TransRes[2]);
                            text_2.setText("bing翻译："+"\n"+TransRes[4]);
                            text_one ="jinshan";
                            text_two = "bing";
                            text_there = "youdao";
                        }
                        bool_if = true;
                    }

                    /*
                    if(zan_youdao > zan_bing && zan_youdao > zan_jinshan && zan_jinshan > zan_bing){
                        text_1.setText("有道翻译："+"\n"+TransRes[0]);
                        text_2.setText("金山翻译："+"\n"+TransRes[2]);
                        text_3.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="youdao";
                        text_two = "jinshan";
                        text_there = "bing";
                    }
                    else if(zan_youdao > zan_bing && zan_youdao > zan_jinshan && zan_jinshan < zan_bing){
                        text_1.setText("有道翻译："+"\n"+TransRes[0]);
                        text_3.setText("金山翻译："+"\n"+TransRes[2]);
                        text_2.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="youdao";
                        text_two = "bing";
                        text_there = "jinshan";
                    }
                    else if(zan_bing > zan_youdao && zan_bing > zan_jinshan && zan_jinshan < zan_youdao){
                        text_2.setText("有道翻译："+"\n"+TransRes[0]);
                        text_3.setText("金山翻译："+"\n"+TransRes[2]);
                        text_1.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="bing";
                        text_two = "youdao";
                        text_there = "jinshan";
                    }
                    else if(zan_bing > zan_youdao && zan_bing > zan_jinshan && zan_jinshan > zan_youdao){
                        text_3.setText("有道翻译："+"\n"+TransRes[0]);
                        text_2.setText("金山翻译："+"\n"+TransRes[2]);
                        text_1.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="bing";
                        text_two = "jinshan";
                        text_there = "youdao";
                    }
                    else if(zan_jinshan > zan_youdao && zan_jinshan > zan_bing && zan_youdao > zan_bing){
                        text_2.setText("有道翻译："+"\n"+TransRes[0]);
                        text_1.setText("金山翻译："+"\n"+TransRes[2]);
                        text_3.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="jinshan";
                        text_two = "youdao";
                        text_there = "bing";
                    }
                    else {
                        text_3.setText("有道翻译："+"\n"+TransRes[0]);
                        text_1.setText("金山翻译："+"\n"+TransRes[2]);
                        text_2.setText("bing翻译："+"\n"+TransRes[4]);
                        text_one ="jinshan";
                        text_two = "bing";
                        text_there = "youdao";
                    }*/


                    bool_zan_youdao = false;
                    bool_zan_jinshan = false;
                    bool_zan_bing = false;
                    search_over = true;
                    Jinput.setText("");

                }
            });



        }


        /*public void text_show(String[] TransRes){

            if(zan_youdao > zan_bing && zan_youdao > zan_jinshan && zan_jinshan > zan_bing){
                text_1.setText("有道翻译："+"\n"+TransRes[0]);
                text_2.setText("金山翻译："+"\n"+TransRes[2]);
                text_3.setText("bing翻译："+"\n"+TransRes[4]);
            }
            else if(zan_youdao > zan_bing && zan_youdao > zan_jinshan && zan_jinshan < zan_bing){
                text_1.setText("有道翻译："+"\n"+TransRes[0]);
                text_3.setText("金山翻译："+"\n"+TransRes[2]);
                text_2.setText("bing翻译："+"\n"+TransRes[4]);
            }
            else if(zan_bing > zan_youdao && zan_bing > zan_jinshan && zan_jinshan < zan_youdao){
                text_2.setText("有道翻译："+"\n"+TransRes[0]);
                text_3.setText("金山翻译："+"\n"+TransRes[2]);
                text_1.setText("bing翻译："+"\n"+TransRes[4]);
            }
            else if(zan_bing > zan_youdao && zan_bing > zan_jinshan && zan_jinshan > zan_youdao){
                text_3.setText("有道翻译："+"\n"+TransRes[0]);
                text_2.setText("金山翻译："+"\n"+TransRes[2]);
                text_1.setText("bing翻译："+"\n"+TransRes[4]);
            }
            else if(zan_jinshan > zan_youdao && zan_jinshan > zan_bing && zan_youdao > zan_bing){
                text_2.setText("有道翻译："+"\n"+TransRes[0]);
                text_1.setText("金山翻译："+"\n"+TransRes[2]);
                text_3.setText("bing翻译："+"\n"+TransRes[4]);
            }
            else {
                text_3.setText("有道翻译："+"\n"+TransRes[0]);
                text_1.setText("金山翻译："+"\n"+TransRes[2]);
                text_2.setText("bing翻译："+"\n"+TransRes[4]);
            }


        }*/




        public void bool_show(String[] TransRes){

            if(bool_youdao==false){
                TransRes[0] = "";
            }
            if(bool_jinshan==false){
                TransRes[2] = "";
            }
            if(bool_bing==false){
                TransRes[4] = "";
            }
        }

        public void sort_search(String[] TransRes){

            zan_youdao = Integer.parseInt(TransRes[1]);
            zan_jinshan = Integer.parseInt(TransRes[3]);
            zan_bing = Integer.parseInt(TransRes[5]);


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

