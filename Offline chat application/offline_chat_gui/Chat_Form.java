/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline_chat_gui;

//import networks.Client;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class Chat_Form extends javax.swing.JFrame {
String Ip,aread,awrite,x;
static String c;
static String name=null;
String DBip;
static boolean b=false;
    /**
     * Creates new form Chat_Form
     * @param Ip
     */
Socket socket=null;
DataInputStream in=null;
static DataOutputStream out=null;
static Thread t1;


    public Chat_Form(String Ip){
    try {
        list=new JList();
        JScrollPane sp= new JScrollPane(list);
        Container contentPane = getContentPane();
        contentPane.add(sp, BorderLayout.CENTER);
        initComponents();
        
        this.Ip=Ip;
        ip.setText(Ip);        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        socket=new Socket(Ip,2222);
        out=new DataOutputStream(socket.getOutputStream());
        in=new DataInputStream(socket.getInputStream());
        //DBip=in.readUTF();
        combo.addItem("group");
        String r1=in.readUTF();
        if("DBip:".equals(r1)){
                                list.setModel(listModel);
                                listModel.addElement(r1);
                                DBip=in.readUTF();
                                listModel.addElement(DBip);                                
                                DBreg.reg(DBip);
                                ta1.setEnabled(b);  send.setEnabled(b);
                                //new Client_Login_Form().setVisible(true);
                            }
       
        
        
        
        t1=new Thread(new Runnable() {

            public void run() {
                try{
                    while(true){
                        try{
                            if(b=true){
                                
                            ta1.setEnabled(b);  send.setEnabled(b);
                            aread=in.readUTF();
                            
                            list.setModel(listModel);
                            listModel.addElement(aread);
                            
                             
                            if("Enter your name.".equals(aread)){                                
                                list.setModel(listModel);                                
                                listModel.addElement(name);
                                out.writeUTF(name);                                
                            }   
                            
                            }
                        }catch(Exception e){
                            break;
                        }
                    }
                }catch(Exception ex){try {
                    out.close();in.close();socket.close();
                    } catch (IOException ex1) {
                        System.out.println("client socket not closed...");
                    }
}
            }
        });
            
       
    } catch (IOException ex) {
        try {
                    out.close();in.close();socket.close();
                    } catch (IOException ex1) {
                        System.out.println("client socket not closed...");
                    }
    }
    }
public static boolean enablee(boolean b, String name){
    Chat_Form.b=b;
    Chat_Form.name=name;
    System.out.println("login ok..");//Client_Login_Form.setVisible(false);
    t1.start();   
return b;
}
public static void individual(String x){
    try {
        String id=Database_Helper.fetchClient(x);
        System.out.println("individual::::combo selected item is "+x+" id is"+id);
        out.writeUTF(":::idComing:::");
        out.writeUTF(id);
        //out.writeUTF(send.getText());
    } catch (IOException ex) {
        System.out.println("individual write not worked...");
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        send = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        ip = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox();
        refresh = new javax.swing.JButton();
        DBipLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        signIn = new javax.swing.JButton();
        signUp = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(list);

        ta1.setColumns(20);
        ta1.setRows(5);
        jScrollPane2.setViewportView(ta1);

        send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/offline_chat_gui/pics/send3.png"))); // NOI18N
        send.setText("SEND");
        send.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        send.setIconTextGap(15);
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/offline_chat_gui/pics/logout2.png"))); // NOI18N
        logout.setText("Logout");
        logout.setToolTipText("press to loggout from the chat platform");
        logout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logout.setIconTextGap(15);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/offline_chat_gui/pics/refresh.png"))); // NOI18N
        refresh.setToolTipText("press to refresh the list");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel1.setText("USERNAME");

        jLabel2.setText("PASSWORD");

        signIn.setText("Sign-In");
        signIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInActionPerformed(evt);
            }
        });

        signUp.setText("Sign-Up");
        signUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(DBipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo, 0, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(username)
                    .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(signIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signUp, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DBipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logout)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(combo, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(signIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(signUp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
    
        awrite=ta1.getText();
        //list.setModel(listModel);
        //listModel.addElement(awrite);
        ta1.setText(null);
        try {
            if(combo.getSelectedItem().equals("group")){ 
                //list.removeAll();
                            
                            System.out.println(":::group chat selected::: ");
                            out.writeUTF(":::idComingGroup:::");
                            
                            }
                            else{
                //list.removeAll();
                            x=(String) combo.getSelectedItem();System.out.println("client name selected: "+x);        
                            //Chat_Form.individual(x);
                            String id=Database_Helper.fetchClient(x);
                            System.out.println("individual::::combo selected item is "+x+" id is"+id);
                            out.writeUTF(":::idComing:::");
                            out.writeUTF(id);
                            }
            
        out.writeUTF(awrite);      
            } 
        catch (IOException ex) {        
        System.out.println("err in write it...");
    }        
    }//GEN-LAST:event_sendActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
    try {        
        out.writeUTF("/quit");
        in.close();
        out.close();
        socket.close();
        setVisible(false);        
        new Logout_Success().setVisible(true);socket.close();
    } catch (IOException ex) {
        System.out.println("logout not success...");
    }            
    }//GEN-LAST:event_logoutActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
     int itemCount = combo.getItemCount();
     for(int i=0;i<itemCount;i++){
        combo.removeItemAt(0);
     }combo.addItem("group");
        TreeSet<String> Set = Database_Helper.fetchAllClients();
        Set.stream().forEach((client) -> {
            combo.addItem(client);
    });
    }//GEN-LAST:event_refreshActionPerformed

    private void signInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInActionPerformed
        // TODO add your handling code here:
        boolean result=Database_Helper.signIn(username.getText(),password.getText());
    }//GEN-LAST:event_signInActionPerformed

    private void signUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpActionPerformed
        // TODO add your handling code here:
        new Sign_up_Form().setVisible(true);
    }//GEN-LAST:event_signUpActionPerformed
DefaultListModel<String> listModel = new DefaultListModel<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {  
                    new Chat_Form(null).setVisible(true);          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DBipLabel;
    private javax.swing.JComboBox combo;
    private javax.swing.JLabel ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList list;
    private javax.swing.JButton logout;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton refresh;
    private javax.swing.JButton send;
    private javax.swing.JButton signIn;
    private javax.swing.JButton signUp;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
