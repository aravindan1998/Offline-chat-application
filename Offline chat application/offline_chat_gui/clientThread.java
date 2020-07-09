/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline_chat_gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class clientThread extends Thread  {
     private DataInputStream is = null;
  private DataOutputStream os = null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int maxClientsCount;
  private String DBip=null;
  

  public clientThread(Socket clientSocket, clientThread[] threads, String DBip) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    this.DBip=DBip;System.out.println("client thread: "+DBip);
    maxClientsCount = threads.length;
  }

  public void run() {
    int maxClientsCount = this.maxClientsCount;
    clientThread[] threads = this.threads;

    try {
      /*
       * Create input and output streams for this client.
       */
      is = new DataInputStream(clientSocket.getInputStream());
      os = new DataOutputStream(clientSocket.getOutputStream());
      os.writeUTF("DBip:");
      
      os.writeUTF(DBip);
      //wait to read echo..
      //System.out.println("client thread running...");
      //is.readUTF();
      os.writeUTF("Enter your name.");
      String name = is.readUTF().trim();
      
      
      os.writeUTF("Hello " + name
          + " to our chat room.\nTo leave enter /quit in a new line");
      Database_Helper.online(name);
      for (int i = 1; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].os.writeUTF("*** A new user " + name
              + " entered the chat room !!! ***");
          
         // threads[i].os.writeUTF("onlineList"+name);
        }
      }
      while (true) {
        String line = is.readUTF();
        //for quiting the chat
        if (line.startsWith("/quit")) {
          break;
        }
        
        //for individual chat
        if (line.startsWith(":::idComing:::")) {
          int ithread=Integer.parseInt(is.readUTF());System.out.println("i thread::"+ithread);
          String r=is.readUTF();
          for (int i = 1; i < maxClientsCount; i++) {        
          if (threads[i] == this) {
          threads[i].os.writeUTF(name+": "+r);
          threads[ithread].os.writeUTF(name+": "+r);
        }
      }
        }

        //for group
       if(line.startsWith(":::idComingGroup:::")){
        for (int i = 1; i < maxClientsCount; i++) {
          if (threads[i] != null) {
            threads[i].os.writeUTF(name + " " + line);
          }
        }
       }
      }
      
      
      for (int i = 1; i < maxClientsCount; i++) {        
        if (threads[i] != null && threads[i] != this) {          
          threads[i].os.writeUTF("*** The user " + name
              + " is leaving the chat room !!! ***");
        }
      }
      
      os.writeUTF("*** Bye " + name + " ***");

      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
      for (int i = 1; i < maxClientsCount; i++) {
        if (threads[i] == this) {
          String j=Integer.toString(i);System.out.println(" client thread id :"+j);
          Database_Helper.delFromOnline(j);
          threads[i] = null;
        }
      }

      /*
       * Close the output stream, close the input stream, close the socket.
       */
      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }
 
}
