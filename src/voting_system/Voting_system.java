
package voting_system;

import java.sql.*;
import java.util.Scanner;
public class Voting_system {
    public static String url = "jdbc:mysql://localhost:3306/voting_system?autoReconnect=true&useSSL=false";
    public static String sql_insert = "insert into signed_up(first_name, last_name, designation, email, mobile, userid, password) values (?,?,?,?,?,?,?)";
    public static String sql_check = "select * from signed_up where userid = ? and password = ?";
    public static void main(String[] args) {
        System.out.println("WELCOME TO NIT RAIPUR ONLINE VOTING SYSTEM");
        System.out.println("     VOTER                                    CANDIDATE");
        System.out.println(" TO LOGIN PRESS L                          TO LOGIN PRESS l ");
        System.out.println("TO SIGN UP PRESS S                        TO SIGN UP PRESS s");
        Scanner obj = new Scanner(System.in);
        String in = obj.nextLine();
        switch(in){
            case "L":
                try{
                    Connection con = DriverManager.getConnection(url,"root","root");
                    PreparedStatement mys = con.prepareStatement(sql_check);
                    System.out.println("Enter your userid and password");
                    
                    mys.setString(1, obj.next());
                    mys.setString(2, obj.next());
                    ResultSet myr = mys.executeQuery();
                    if(myr.next()){
                        System.out.println("logged in");
                        Statement s = con.createStatement();
                        ResultSet r = s.executeQuery("select * from candidates");
                        System.out.println("Choose and enter the id of the candidate and party you want to vote");
                        System.out.println("id  name   party name");
                        while(r.next()){
                            System.out.println(r.getInt("id")+ "   " +r.getString("first_name") + "   " + r.getString("party_name"));
                        }
                        int id = obj.nextInt();
                        PreparedStatement ps =  con.prepareStatement("update candidates set votes = votes+1 where id = ?");
                        ps.setInt(1, id);
                        ps.executeUpdate();
                        System.out.println("Vote given succesfully");
                    }
                    else{
                        System.out.println("wrong userid or password");
                    }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            break;
            case "S":
                try{
                    Connection con = DriverManager.getConnection(url,"root","root");
                    PreparedStatement mys = con.prepareStatement(sql_insert);
                    System.out.println("Enter name,surname,designation,email,mobile no. in order");
                    mys.setString(1, obj.next());
                    mys.setString(2, obj.next());
                    mys.setString(3, obj.next());
                    mys.setString(4, obj.next());
                    mys.setLong(5, obj.nextLong());
                    System.out.println("Enter a username and password");
                    String userid = obj.next();
                    PreparedStatement check = con.prepareStatement("select * from signed_up where userid = ?");
                    check.setString(1, userid);
                    ResultSet checkr = check.executeQuery();
                    while(checkr.next()){
                       System.out.println("Username already taken. Try another");
                       System.out.println("Enter a username and password");
                       userid = obj.next();
                       check.setString(1, userid);
                       checkr = check.executeQuery();
                    }
                    mys.setString(6, userid);
                    
                    mys.setString(7, obj.next());
                    
                     mys.executeUpdate(); 
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "s":
                candidates can = new candidates();
                
                break;
            case "l":
                candidates cann = new candidates(0);
        }
        
    }
    
}
