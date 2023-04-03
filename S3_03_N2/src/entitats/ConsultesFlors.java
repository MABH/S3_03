package entitats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexio.Connexio;

public class ConsultesFlors extends Connexio{
	public boolean insert (String nom, String color, float preu, int id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "insert into flors (nom, color, preu, floristeria_id) values (?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, nom);
			ps.setString(2, color);
			ps.setFloat(3, preu);
			ps.setFloat(4, id);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public boolean update (int id, int ticket_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "update flors set ticket_id=? where id_flors=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, ticket_id);
			ps.setInt(2, id);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public boolean delete (String nom, int floristeria_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "delete from flors where nom =? and floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setString(1, nom);
			ps.setInt(2, floristeria_id);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public Flor select (String nom, int floristeria_id) {
		Flor flor = new Flor();
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from flors where nom=? and floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setString(1, nom);
			ps.setInt(2,floristeria_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				flor.setId(rs.getInt("id_flors"));
				flor.setNom(rs.getString("nom"));
				flor.setColor(rs.getString("color"));
				flor.setPreu(Float.parseFloat(rs.getString("preu")));				
				return flor;
			}
			return flor;
		}
		catch(SQLException e) {
			System.err.println(e);
			return flor;
		}
	}
	
	public boolean selectAll (int floristeria_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from flors where floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setInt(1, floristeria_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Nom: "+rs.getString("nom"));
				System.out.println("Color: "+rs.getString("color"));
				System.out.println("Preu: "+rs.getFloat("preu")+"\n");
				//return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public boolean selectCount (int floristeria_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select count(*) from flors where floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setInt(1, floristeria_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				System.out.println("Quantitats de flors: "+rs.getInt(1)+"\n");
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public float selectTotal (int floristeria_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		float total=0;
		String sql = "select sum(preu) from flors where floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setFloat(1, floristeria_id);
			rs = ps.executeQuery();
			if (rs.next()) {			
				total = rs.getFloat(1);
				System.out.println("Valor total del stock de flors: "+total);
				return total;
			}
			return total;
		}
		catch(SQLException e) {
			System.err.println(e);
			return total;
		}
	}
	
	public int selectId (String nom, int floristeria_id) {
		int id =0;
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from flors where nom=? and floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setString(1, nom);
			ps.setInt(2, floristeria_id);
			rs = ps.executeQuery();
			if (rs.next()) {				
				return rs.getInt("id");
			}
			return id;
		}
		catch(SQLException e) {
			System.err.println(e);
			return id;
		}
	}
	
	public boolean selectVendes (int ticket_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from flors where ticket_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setInt(1, ticket_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Nom: "+rs.getString("nom"));
				System.out.println("Color: "+rs.getString("color"));
				System.out.println("Preu: "+rs.getFloat("preu")+"\n");
				//return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public float selectFacturacio (int ticket_id) {
		float totalFac=0;
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select preu from flors where ticket_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setInt(1, ticket_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalFac = totalFac+rs.getFloat("preu");
				//return true;
			}
			return totalFac;
		}
		catch(SQLException e) {
			System.err.println(e);
			return totalFac;
		}
	}
}
