/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline_chat_gui;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


class Server_1
{
    static String inet=null;
   // The server socket.
  private static ServerSocket serverSocket = null;
  // The client socket.
  private static Socket clientSocket = null;
  static Thread t1;

  // This chat server can accept up to maxClientsCount clients' connections.
  private static final int maxClientsCount = 10;
  private static final clientThread[] threads = new clientThread[maxClientsCount];
  
  String DBip;
  
  public static void Server_side(String DBip) throws IOException {

   String ip=DBip;System.out.println("server1: "+ip);
    int portNumber = 2222;
    
      System.out
          .println("Usage: java MultiThreadChatServer <portNumber>\n"
              + "Now using port number=" + portNumber);
   

    /*
     * Open a server socket on the portNumber (default 2222). Note that we can
     * not choose a port less than 1023 if we are not privileged users (root).
     */
    try {
      serverSocket = new ServerSocket(portNumber);System.out.println("server ok..");
    } catch (IOException e) {
      System.out.println(e);
      System.out.println("port...");
    }

    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
    t1=new Thread(new Runnable() {

       @Override
       public void run() {
           while (true) {
               try {
                   clientSocket = serverSocket.accept();
                   int i = 1;
                   for (i = 1; i < maxClientsCount; i++) {
                       if (threads[i] == null) {//list[i]="thread"+i;System.out.println(list[i]);
                           (threads[i] = new clientThread(clientSocket, threads, ip)).start();
                           break;
                       }
                   }
                   if (i == maxClientsCount) {
                       PrintStream os = new PrintStream(clientSocket.getOutputStream());
                       os.println("Server too busy. Try later.");
                       os.close();
                       clientSocket.close();
                   }
               } catch (IOException e) {
                   System.out.println("socket closed...");
                   break;
               }
           }  }
   });
    t1.start();
  }
  public static void setInet(String inet){Server_1.inet=inet;}
  public void conClose(){
      try {
          Database_Helper.truncateOnlineFrns();
          Database_Helper.delFromServerList(inet);
          serverSocket.close();
          //clientSocket.close();
          t1.stop();
          System.out.println("server closed...");
      } catch (IOException ex) {
          System.out.println("server not closed...");
      }
  }
}

