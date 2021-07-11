package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

import modelo.venta;
import sql.conexion;

public class consultasVenta extends conexion {

	public boolean registrar(venta vent) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call insertarVenta(?,?,?,?)}");
			cs.setInt(1, vent.getCodigo());
			cs.setString(2, vent.getProducto());
			cs.setInt(3, vent.getCantidad());
			cs.setDouble(4, vent.getPrecio());
			cs.executeUpdate();
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
	
	public boolean buscar(venta vent) {
		ResultSet rs = null;
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call buscarVenta(?)}");
			cs.setInt(1, vent.getCodigo());
			rs = cs.executeQuery();
			
			if(rs.next()) {
				vent.setProducto(rs.getString("producto"));
				vent.setCantidad(Integer.parseInt(rs.getString("cantidad")));
				vent.setPrecio(Double.parseDouble(rs.getString("precio")));
				return true;
			}
			return false;
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
	
	public boolean eliminar(venta vent) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call borrarVenta(?)}");
			cs.setInt(1, vent.getCodigo());
			cs.executeUpdate();
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
			CallableStatement cs = (CallableStatement) con.prepareCall("{call spDeleteTransaccion()}");
			cs.execute();			
			return true;
		}
		
		catch(SQLException e) {
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
