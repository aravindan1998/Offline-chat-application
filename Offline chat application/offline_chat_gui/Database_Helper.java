/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline_chat_gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import javax.swing.JOptionPane;


public class Database_Helper {
    private static Connection connection = null;
    static String ip;
	public static Connection getDatabaseConnection(String DBip){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
		}

		System.out.println("MySQL JDBC Driver Registered!");

		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://"+DBip+":3306","root", "1196");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		return connection;
	}
        public static boolean SignUp(Sign_up_Form suf) {
		//Sign_up_Form suf=new Sign_up_Form();
		boolean result = true;
		
		try{
			
			Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("INSERT INTO CLIENT (NAME, AGE, SEX, MOB_NUM, EMAIL, USERNAME, PASSWORD) "
						+ "VALUES ('"+suf.getName()+"','"+suf.getAge()+"','"+suf.getSex()+"','"+suf.getMob()+"','"+suf.getEmail()+"',"
								+ "'"+suf.getUsername()+"','"+suf.getPassword()+"')");
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("rows not inserted");
			return false ;
			
		}
		
		return result;
	}
        public void IP(String ip){
            this.ip=ip;
    }
        public static boolean signIn(String username, String password) {
		
		boolean result = false;
		try{
                    
		Statement stmt=getDatabaseConnection(DBreg.regi()).createStatement(); 
                stmt.execute("USE `CHAT_GUI`");
                ResultSet rs=stmt.executeQuery("SELECT * FROM CLIENT");  
                while(rs.next()){
                if((rs.getString(6).equals(username))&&(rs.getString(7).equals(password))){
                String user=rs.getString(1);
                result = true; System.out.println("logged in..");
                Chat_Form.enablee(result, username);//Client_Login_Form.dis();
                //Client_Login_Form.setVisible(false);
                //new Chat_Form(ip).setVisible(true);
                
                break;
                }
                else{
                //JOptionPane.showMessageDialog(null,"invalid credentials"."Access Denied",JOptionPane.ERROR_MESSAGE);
                }
                }
                   
		
		}catch(Exception e){
			System.out.println("invalid input");
			return result ;
			
		}
		
		return result;
	}
        public static boolean online(String name) {
		//Sign_up_Form suf=new Sign_up_Form();
		boolean result = true;
		
		try{
			
			Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("INSERT INTO ONLINE_FRNDS (NAME) "
						+ "VALUES ('"+name+"')");System.out.println("name :"+name);
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("rows not inserted");
			return false ;
			
		}
		
		return result;
	}
        public static TreeSet<String> fetchAllClients() {

		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		TreeSet<String> treeSet = new TreeSet<String>();
		
		try{
                    
                        Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
                        resultSet=stmt.executeQuery("SELECT * FROM ONLINE_FRNDS");	                        
			
			while (resultSet.next()){
				String messagedata = resultSet.getString(2);
				if(messagedata != null){
					treeSet.add(messagedata);
				}
			}
		
		}catch(Exception e){
			System.out.println("clients not fetched..");
		}
		
		return treeSet;
	}

        public static boolean delFromOnline(String id) {
		
		boolean result = false;
		
		try{
                    
                        Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("DELETE FROM ONLINE_FRNDS WHERE ID='"+id+"'");System.out.println("deleted database row id :"+id);
			
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("rows not deleted");
			return false ;
			
		}
		
		return result;
	}
        public static boolean truncateOnlineFrns() {
		//Sign_up_Form suf=new Sign_up_Form();
		boolean result = true;
		
		try{
			
			Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("TRUNCATE ONLINE_FRNDS");
                        System.out.println("online frnds table truncated...");
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("online frnds table not truncated...");
			return false ;
			
		}
		
		return result;
	}
        
        public static boolean serverList(String name,String serverIP) {
		boolean result = true;
		
		try{
			
			Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("INSERT INTO SERVER_LIST (NAME,SERVER_IP) "
						+ "VALUES ('"+name+"','"+serverIP+"')");System.out.println("inserted-----name :"+name+"server ip: "+serverIP);
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("server list rows not inserted");
			return false ;
			
		}
		
		return result;
	}
        
        public static boolean delFromServerList(String serverIP) {
		
		boolean result = false;
		
		try{
                    
                        Statement stmt = getDatabaseConnection(DBreg.regi()).createStatement();
                        stmt.execute("USE `CHAT_GUI`");
			stmt.execute("DELETE FROM SERVER_LIST WHERE SERVER_IP='"+serverIP+"'");System.out.println("deleted database row IP :"+serverIP);
			
		}catch(Exception e){
                        System.out.println(e);
			System.out.println("rows not deleted");
			return false ;
			
		}
		
		return result;
	}
        
        public static String fetchClient(String name) {
		String ID = null;
		try{
                    
		Statement stmt=getDatabaseConnection(DBreg.regi()).createStatement(); 
                stmt.execute("USE `CHAT_GUI`");
                ResultSet rs=stmt.executeQuery("SELECT * FROM ONLINE_FRNDS");  
                while(rs.next()){
                if((rs.getString(2).equals(name))){
                ID=rs.getString(1);
                System.out.println("ID is "+ID+" name is "+rs.getString(2));                
                break;
                }
                }                 
		
		}catch(Exception e){
			System.out.println("Client not found...");
			
			
		}
		
		return ID;
	}
        
        public static void main(String args[]){
        //Database_Helper dh=new Database_Helper();
        //Database_Helper.getDatabaseConnection();
        }
}
