package controlador;

import java.sql.*;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.CallableStatement;

import modelo.cliente;
import sql.conexion;

public class consultasCliente extends conexion {

	public boolean registrar(cliente cli) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call agregarCliente(?,?,?,?,?)}");
			cs.setInt(1, cli.getId());
			cs.setString(2, cli.getNombre());
			cs.setString(3, cli.getApellido());
			cs.setString(4, cli.getDireccion());
			cs.setInt(5, cli.getDni());
			cs.executeUpdate();
			return true;
		}
		
		catch(SQLIntegrityConstraintViolationException e) {
			advertencia("Entrada de DNI duplicada.");
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
	
	public boolean modificar(cliente cli) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call modificarCliente (?,?,?,?,?)}");
			cs.setString(1, cli.getNombre());
			cs.setString(2, cli.getApellido());
			cs.setString(3, cli.getDireccion());
			cs.setInt(4, cli.getDni());
			cs.setInt(5, cli.getId());
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
	
	public boolean eliminar(cliente cli) {
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call moduleCli(1, ?)}");
			cs.setInt(1, cli.getId());
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
	
	public boolean buscar(cliente cli) {
		ResultSet rs = null;
		Connection con = getConnection();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call moduleCli(2, ?)}");
			cs.setInt(1, cli.getId());
			rs = cs.executeQuery();
			
			if(rs.next()) {
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setDni(Integer.parseInt(rs.getString("dni")));
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
		PreparedStatement ps = null;
		Connection con = getConnection();
		
		String sql = "call spDeleteClient()";
		
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
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
	
	void advertencia(String s) {
		JOptionPane.showMessageDialog(null, s, "Advertencia", 0);
	}
}
