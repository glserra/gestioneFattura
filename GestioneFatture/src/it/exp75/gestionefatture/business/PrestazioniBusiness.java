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
import it.exp75.gestionefatture.model.Prestazione;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class PrestazioniBusiness {
	
	private static PrestazioniBusiness pb;
	
	public static PrestazioniBusiness getInstance() {
		if(pb == null) {
			pb = new PrestazioniBusiness();
		}
		return pb;
	}
	
	public List<Prestazione> listaPrestazioni(Integer idFattura) throws SQLException{
		
		String sql = "SELECT * FROM prestazioni WHERE ID_fattura=?";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ps.setInt(1, idFattura.intValue());
		ResultSet rs = ps.executeQuery();
		
		List<Prestazione> listaPrestazioni = new ArrayList<Prestazione>();
		while(rs.next()) {
			
			Prestazione p = new Prestazione();
			p.setId(rs.getInt(1));
			p.setId_fattura(rs.getInt(2));
			p.setSezione(rs.getString(3));
			p.setDescrizione(rs.getString(4));
			p.setUnita_misura(rs.getInt(5));
			p.setQuantita(rs.getDouble(6));
			p.setImporto(rs.getDouble(7));
			p.setIva(rs.getInt(8));
			listaPrestazioni.add(p);
		}
		return listaPrestazioni;
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
