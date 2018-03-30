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
import it.exp75.gestionefatture.model.Misure;
import it.exp75.gestionefatture.model.Prestazione;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MisureBusiness {
	
	private static MisureBusiness mb;
	
	public static MisureBusiness getInstance() {
		if(mb == null) {
			mb = new MisureBusiness();
		}
		return mb;
	}
	
	public List<Misure> listaMisure() throws SQLException{
		
		String sql = "SELECT * FROM misure";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Misure> listaMisure = new ArrayList<Misure>();
		while(rs.next()) {
			
			Misure m = new Misure();
			m.setId(rs.getInt(1));
			m.setTipo(rs.getString(2));
			listaMisure.add(m);
		}
		return listaMisure;
	}
	
	public String misura(Integer id) throws SQLException{
		
		String sql = "SELECT * FROM misure WHERE id=?";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		rs.next();
			
		return rs.getString(2);
	}
	
}
