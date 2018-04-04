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
	
	public int salvaPrestazione(Prestazione prestazione) throws SQLException {
		
		String sql = "INSERT INTO prestazioni (ID_fattura,Sezione,Descrizione,UM,Qta,Importo,Aliquota_IVA) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1,prestazione.getId_fattura());
		ps.setString(2, prestazione.getSezione());
		ps.setString(3, prestazione.getDescrizione());
		ps.setInt(4, prestazione.getUnita_misura());
		ps.setDouble(5, prestazione.getQuantita());
		ps.setDouble(6, prestazione.getImporto());
		ps.setInt(7, prestazione.getIva());
		
		int idPrestazione = ps.executeUpdate();
		
		return idPrestazione;
	}
	
	
//	public Prestazione prestazione(Integer id) throws SQLException{
//		
//		String sql = "SELECT * FROM prestazioni WHERE ID=?";
//		MyDBConnector connector = MyDBConnector.getConnector();
//		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
////		ps.setInt(1, idFattura.intValue());
//		ResultSet rs = ps.executeQuery();
//		
//		List<Prestazione> listaPrestazioni = new ArrayList<Prestazione>();
//		while(rs.next()) {
//			
//			Prestazione p = new Prestazione();
//			p.setId(rs.getInt(1));
//			p.setId_fattura(rs.getInt(2));
//			p.setSezione(rs.getString(3));
//			p.setDescrizione(rs.getString(4));
//			p.setUnita_misura(rs.getInt(5));
//			p.setQuantita(rs.getDouble(6));
//			p.setImporto(rs.getDouble(7));
//			p.setIva(rs.getInt(8));
//			listaPrestazioni.add(p);
//		}
////		return listaPrestazioni;
//	}

}
