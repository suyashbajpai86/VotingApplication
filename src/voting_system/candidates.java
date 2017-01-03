
package voting_system;
import java.sql.*;
import java.util.Scanner;
public class candidates {
    public static String sql_insert = "insert into candidates(first_name, last_name, date_of_birth, email, party_name,department,userid, password,votes) values (?,?,?,?,?,?,?,?,0)";
    public static String url = "jdbc:mysql://localhost:3306/voting_system?autoReconnect=true&useSSL=false";
    public static String sql_check = "select * from candidates where userid = ? and password = ?";
    public static String sql_votecheck="select * from candidates where userid = ?";
    public candidates(){
        Scanner obj = new Scanner(System.in);
        try{
                    Connection con = DriverManager.getConnection(url,"root","root");
                    PreparedStatement mys = con.prepareStatement(sql_insert);
                    System.out.println("Enter name,surname,DOB,email,department in order");
                    mys.setString(1, obj.next());
                    mys.setString(2, obj.next());
                    mys.setString(3, obj.next());
                    mys.setString(4, obj.next());
                    mys.setString(6, obj.next());
                    System.out.println("ENTER YOUR PARTY NAME");
                    mys.setString(5, obj.nextLine());
                    System.out.println("Enter a username and password");
                    String userid = obj.next();
                    PreparedStatement check = con.prepareStatement("select * from candidates where userid = ?");
                    check.setString(1, userid);
                    ResultSet checkr = check.executeQuery();
                    while(checkr.next()){
                       System.out.println("Username already taken. Try another");
                       System.out.println("Enter a username and password");
                       userid = obj.next();
                       check.setString(1, userid);
                       checkr = check.executeQuery();
                    }
                    mys.setString(7, userid);
                    
                    mys.setString(8, obj.next());
                    
                    mys.executeUpdate(); 
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
    }
    public candidates(int t){
        Scanner obj = new Scanner(System.in);
        try{
                    Connection con = DriverManager.getConnection(url,"root","root");
                    PreparedStatement mys = con.prepareStatement(sql_check);
                    System.out.println("Enter your userid and password");
                    String user = obj.next();
                    mys.setString(1, user);
                    mys.setString(2, obj.next());
                    ResultSet myr = mys.executeQuery();
                    if(myr.next()){
                        System.out.println("logged in");
                        PreparedStatement mystate = con.prepareStatement(sql_votecheck);
                        mystate.setString(1, user);
                        ResultSet myrs = mystate.executeQuery();
                        System.out.print("your votes are:");
                        myrs.next();
                        System.out.println(myrs.getInt("votes"));
                        
                    }
                    else{
                        System.out.println("wrong userid or password");
                    } 
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
    }
}
