package controlador;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

import modelo.recepcion;
import sql.conexion;
import superAdmin.SystemConsole;

public class consultasRecepcion extends conexion {

	public boolean registrar(recepcion rcp) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call insertarRecepcion(?,?,?,?,?)}");
			cs.setString(1, rcp.getSentencia());
			cs.setString(2, rcp.getEmisor());
			cs.setString(3, rcp.getCargo());
			cs.setString(4, rcp.getRazon());
			cs.setString(5, rcp.getFecha());
			return true;
		}
		
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
		
		finally {
			try {
				con.close();
			}
			
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public boolean eliminar(recepcion rcp) {
		Connection con = getConnection();
	
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call eliminarRecepcion(?,?,?,?,?)}");
			cs.setString(1, rcp.getSentencia());
			cs.setString(2, rcp.getEmisor());
			cs.setString(3, rcp.getCargo());
			cs.setString(4, rcp.getRazon());
			cs.setString(5, rcp.getFecha());
			cs.execute();
			return true;
		}
		
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
		
		finally {
			try {
				con.close();
			}
			
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public boolean spDelete() {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call spDeleteRecepcion()}");
			cs.execute();
			SystemConsole.txtSConsole.append("You truncate the table recepcion.");
			return true;
		}
		
		catch(SQLException e) {
			SystemConsole.txtSConsole.append("No contienes el procedure");
			return false;
		}
		
		finally {
			try {
				con.close();
			}
			
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}
}
