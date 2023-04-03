package entitats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexio.Connexio;

public class ConsultesTickets extends Connexio{
	public boolean insert (String data, int floristeria_id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "insert into tickets (data, floristeria_id) values (?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, data);
			ps.setInt(2, floristeria_id);
			ps.execute();
			return true;
			
		}
		catch(SQLException e) {
			System.out.println("Error al insertar el ticket: ");
			System.err.println(e);
			return false;
		}
	}
	
	public boolean update (Ticket tic) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "update tickets set (id=?, codi=?) where id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, tic.getId());
			ps.setString(2, tic.getCodi());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public boolean delete (Ticket tic) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		String sql = "delete from tickets where id=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setInt(1, tic.getId());
			ps.execute();
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public String select (int id) {
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from tickets where id_ticket=?";
		String codi = null;
		try {
			ps=con.prepareStatement(sql);			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				codi=rs.getString("codi");		
				return codi;
			}
			return codi;
		}
		catch(SQLException e) {
			System.err.println(e);
			return codi;
		}
	}
	
	public int selectId (int floristeria_id, String data) {
		int id = 0;
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from tickets where floristeria_id=? and data=?";
		try {
			ps=con.prepareStatement(sql);			
			ps.setInt(1, floristeria_id);
			ps.setString(2, data);
			rs = ps.executeQuery();			
			if (rs.next()) {
				id = rs.getInt("id_ticket");
				return id;
			}
			return id;
		}
		catch(SQLException e) {
			System.err.println(e);
			return id;
		}
	}
	
	public ArrayList <Ticket> selectAll (int floristeria_id)
	{
		ArrayList <Ticket> ticketList = new ArrayList<Ticket>();
		Ticket ticket = null;
		PreparedStatement ps = null;
		Connection con = getConnexio();
		ResultSet rs = null;
		String sql = "select * from tickets where floristeria_id=?";
		try {
			ps=con.prepareStatement(sql);	
			ps.setInt(1, floristeria_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ticket=new Ticket();
				ticket.setId(rs.getInt("id_ticket"));
				ticket.setCodi(rs.getString("data"));
				ticketList.add(ticket);
			}
			return ticketList;
		}
		catch(SQLException e) {
			System.err.println(e);
			return ticketList;
		}	
	}
}
