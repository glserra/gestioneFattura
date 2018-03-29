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
	

}
