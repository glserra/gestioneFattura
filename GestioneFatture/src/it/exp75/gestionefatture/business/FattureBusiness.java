package it.exp75.gestionefatture.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.DatiStampaFattura;
import it.exp75.gestionefatture.model.Fattura;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class FattureBusiness {
	
	private static FattureBusiness fb;
	
	public static FattureBusiness getInstance() {
		if(fb == null) {
			fb = new FattureBusiness();
		}
		return fb;
	}
	
	public List<Fattura> listaFatture() throws SQLException{
		
		
		String sql = "SELECT * FROM fatture ORDER BY Data_fattura DESC, Num_fattura DESC";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Fattura> listaFatture = new ArrayList<Fattura>();
		while(rs.next()) {
			
			Fattura f = new Fattura();
			f.setId_fattura(rs.getInt(1));
			f.setId_cliente(rs.getInt(2));
			f.setNum_fattura(rs.getInt(3));
			f.setData_fattura(rs.getDate(4));
			f.setPagamento(rs.getInt(5));
			f.setPagata(rs.getBoolean(6));
			f.setNote(rs.getString(7));
			listaFatture.add(f);
		}
		return listaFatture;
	}
	
	public Fattura fattura(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM fatture WHERE ID=?";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ps.setInt(1, id.intValue());
		ResultSet rs = ps.executeQuery();
		
		Fattura f = new Fattura();
		while(rs.next()) {
			f.setId_fattura(rs.getInt(1));
			f.setId_cliente(rs.getInt(2));
			f.setNum_fattura(rs.getInt(3));
			f.setData_fattura(rs.getDate(4));
			f.setPagamento(rs.getInt(5));
			f.setPagata(rs.getBoolean(6));
			f.setNote(rs.getString(7));
		}
		
		return f;
	}
	
	public int salvaFattura(Fattura fattura) throws SQLException {
		
		String sql = "INSERT INTO fatture (ID_cliente,Num_fattura,Data_fattura,Pagamento,Pagata,Note) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1,fattura.getId_cliente());
		ps.setInt(2, fattura.getNum_fattura());
		ps.setDate(3, Utility.convertJavaDateToSqlDate(fattura.getData_fattura()));
		ps.setInt(4, 1);//fattura.getPagamento());
		ps.setBoolean(5, true);//fattura.isPagata());
		ps.setString(6, fattura.getNote());
		int idFattura = ps.executeUpdate();
		
		return idFattura;
	}
	
	public Integer numNextFattura() throws SQLException{
		
		String sql = "SELECT MAX(Num_fattura) FROM fatture WHERE YEAR(Data_fattura) BETWEEN YEAR(DATE_ADD(NOW(), INTERVAL -1 YEAR)) AND YEAR(Now())";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		return rs.getInt(1);
	}
	
}
