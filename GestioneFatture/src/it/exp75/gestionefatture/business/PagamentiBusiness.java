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
import it.exp75.gestionefatture.model.Pagamento;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class PagamentiBusiness {
	
	private static PagamentiBusiness pb;
	
	public static PagamentiBusiness getInstance() {
		if(pb == null) {
			pb = new PagamentiBusiness();
		}
		return pb;
	}
	
	public Pagamento listaPagamenti() throws SQLException{
		
		String sql = "SELECT * FROM pagamenti";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		Pagamento listapagamenti = new Pagamento();
		while(rs.next()) {
			
			listapagamenti.setId(rs.getInt(1));
			listapagamenti.setTipo(rs.getString(2));
		}
		
		return listapagamenti;
	}
	
	public String pagamento(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM pagamenti WHERE ID=?";
		MyDBConnector connector = MyDBConnector.getConnector();
		PreparedStatement ps = connector.getConnention().prepareStatement(sql);
		ps.setInt(1, id.intValue());
		ResultSet rs = ps.executeQuery();
		
		String pagamento = null;
		
		while(rs.next()) {
			pagamento = rs.getString(2);
		}
		
		return pagamento;
	}
	
}
