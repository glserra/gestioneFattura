package it.exp75.gestionefatture.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import it.exp75.gestionefatture.model.Cliente;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ClientiBusiness {
	
	private static ClientiBusiness cb;
	
	public static ClientiBusiness getInstance() {
		if(cb == null) {
			cb = new ClientiBusiness();
		}
		return cb;
	}
	
	public List<Cliente> listaClienti() throws SQLException{
		
		String sql = "SELECT * FROM clienti ORDER BY Ragione_sociale";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Cliente> listaClienti = new ArrayList<Cliente>();
		while(rs.next()) {
			
			Cliente c = new Cliente();
			c.setId(rs.getInt(1));
			c.setPartitaIva(rs.getString(2));
			c.setCodiceFiscale(rs.getString(3));
			c.setRagioneSociale(rs.getString(4));
			c.setIndirizzo(rs.getString(5));
			c.setCap(rs.getString(6));
			c.setCitta(rs.getString(7));
			c.setProvincia(rs.getString(8));
			c.setNote(rs.getString(9));
			listaClienti.add(c);
		}
		return listaClienti;
	}
	
	public Cliente cliente(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM clienti WHERE ID=?";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ps.setInt(1, id.intValue());
		ResultSet rs = ps.executeQuery();
		
		Cliente c = new Cliente();
		while(rs.next()) {
			c.setId(rs.getInt(1));
			c.setPartitaIva(rs.getString(2));
			c.setCodiceFiscale(rs.getString(3));
			c.setRagioneSociale(rs.getString(4));
			c.setIndirizzo(rs.getString(5));
			c.setCap(rs.getString(6));
			c.setCitta(rs.getString(7));
			c.setProvincia(rs.getString(8));
			c.setNote(rs.getString(9));
		}
		
		return c;
	}
	
	public int salvaCliente(Cliente cliente) throws SQLException {
		
		String sql = "INSERT INTO clienti (PIVA,CF,Ragione_sociale,Indirizzo,CAP,Citta,Provincia,Note) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1,cliente.getPartitaIva());
		ps.setString(2, cliente.getCodiceFiscale());
		ps.setString(3, cliente.getRagioneSociale());
		ps.setString(4, cliente.getIndirizzo());
		ps.setString(5, cliente.getCap());
		ps.setString(6, cliente.getCitta());
		ps.setString(7, cliente.getProvincia());
		ps.setString(8, cliente.getNote());
		int idCliente = ps.executeUpdate();
		
		return idCliente;
	}
	
	public int updateCliente(Cliente cl) throws SQLException {
		String sql = "UPDATE clienti set PIVA=?,CF=?,Ragione_sociale=?,Indirizzo=?,CAP=?,Citta=?,Provincia=?,Note=? WHERE ID=?";
		PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
		ps.setString(1, cl.getPartitaIva());
		ps.setString(2, cl.getCodiceFiscale());
		ps.setString(3, cl.getRagioneSociale());
		ps.setString(4, cl.getIndirizzo());
		ps.setString(5, cl.getCap());
		ps.setString(6, cl.getCitta());
		ps.setString(7, cl.getProvincia());
		ps.setString(8, cl.getNote());
		ps.setInt(9, cl.getId());
		
		int idCliente = ps.executeUpdate();
		return idCliente;
		
	}
}
