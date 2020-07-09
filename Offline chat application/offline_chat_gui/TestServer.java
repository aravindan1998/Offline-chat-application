/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline_chat_gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestServer {
   /*  DataInputStream d1;
DataOutputStream d2;
clients[] cthread;
Socket csocket;
TestServer(Socket s1,clients[] c)
{
    this.cthread=c;
    csocket=s1;
    
}
public void run()
{
try{
d1 = new DataInputStream(csocket.getInputStream());    
d2 = new DataOutputStream(csocket.getOutputStream());
String chat;
while(true)
{
    chat=d1.readUTF();
    for(int i=0;i<10;i++)
    {
        if(cthread[i]!=null && cthread[i]!=this)
        {
            String u=null;
            cthread[i].d2.writeUTF(u);
        }
    }
}

}catch(Exception e)
{
    System.out.println("Error");
}
}
}
  
public class Server_1 {
    ServerSocket MyServer;
    Socket socket;
    clients cthread[]=new clients[10];
    
    public void closeCon(){
        try {
            MyServer.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Server_side(){
        try {
        MyServer=new ServerSocket(8090);   
        socket=MyServer.accept();
        for(int i=0;i<10;i++)
        {
            if(cthread[i]!=null)
            {
                (cthread[i]=new clients(socket,cthread)).start();
                break;
            }
            if(i==10)
            {
                System.out.println("Server Busy");
            }
        }
     final DataOutputStream out=new DataOutputStream(socket.getOutputStream());
        final DataInputStream in=new DataInputStream(socket.getInputStream());
        Thread t1=new Thread(() -> {
            while(true){
                try{
                    String msg=in.readUTF();
                    System.out.println(msg);
                }catch(Exception e){                    
                }
            }
            });
        Thread t2=new Thread(() -> {
            while(true){
                Scanner ss=new Scanner(System.in);
                
                String send="server: "+ss.nextLine();
                try {
                    out.writeUTF(send);
                } catch (IOException ex) {
                    Logger.getLogger(Server_1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   });
        t1.start();
        t2.start();
        
        } catch (IOException ex) {            
            System.out.println("server not started...");
        }
       
    }*/
}
