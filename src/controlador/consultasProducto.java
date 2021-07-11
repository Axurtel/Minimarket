package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

import modelo.producto;
import sql.conexion;

public class consultasProducto extends conexion {

	public boolean registrar(producto pro) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call agregarProducto(?,?,?,?,?)}");
			cs.setInt(1, pro.getCodigo());
			cs.setString(2, pro.getProducto());
			cs.setString(3, pro.getMarca());
			cs.setInt(4, pro.getLote());
			cs.setDouble(5, pro.getPrecio());
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
	
	public boolean modificar(producto pro) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call modificarProducto(?,?,?,?,?)}");
			cs.setString(1, pro.getProducto());
			cs.setString(2, pro.getMarca());
			cs.setInt(3, pro.getLote());
			cs.setDouble(4, pro.getPrecio());
			cs.setInt(5, pro.getCodigo());
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
	
	public boolean eliminar(producto pro) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call moduleProd(1, ?)}");
			cs.setInt(1, pro.getCodigo());
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
	
	public boolean buscar(producto pro) {
		ResultSet rs = null;
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call moduleProd(2, ?)}");
			cs.setInt(1, pro.getCodigo());
			rs = cs.executeQuery();
			
			if(rs.next()) {
				pro.setProducto(rs.getString("producto"));
				pro.setMarca(rs.getString("marca"));
				pro.setLote(Integer.parseInt(rs.getString("lote")));
				pro.setPrecio(Double.parseDouble(rs.getString("precio")));
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
	
	//SuperUser
	public boolean spDelete() {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call spDeleteProd()}");
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
}