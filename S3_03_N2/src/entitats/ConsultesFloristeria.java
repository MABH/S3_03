package entitats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexio.Connexio;

public class ConsultesFloristeria extends Connexio{
	public boolean insert (String flo) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "insert into floristeries (nom) values (?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, flo);
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public boolean select (Floristeria flori) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from floristeries where nom=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setString(1, flori.getNom());
			rs = ps.executeQuery();
			if (rs.next()) {
				flori.setNom(rs.getString("nom"));	
				flori.setId(rs.getInt("id_floristeria"));
				System.out.println("Floristeria: "+flori.getNom());
				System.out.println("Id: "+flori.getId());
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
}
