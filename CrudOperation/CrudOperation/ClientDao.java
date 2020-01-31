import java.util.*;
import java.sql.*;

public class ClientDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jay","root","jaisen");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Client e){
		int status=0;
		try{
			Connection con=ClientDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into Client(name,password,email,country) values (?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Client e){
		int status=0;
		try{
			Connection con=ClientDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update Client set name=?,password=?,email=?,country=? where id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=ClientDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from Client where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Client getClientById(int id){
		Client e=new Client();
		
		try{
			Connection con=ClientDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from Client where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Client> getAllClient(){
		List<Client> list=new ArrayList<Client>();
		
		try{
			Connection con=ClientDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from Cliet");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Client e=new Client();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
