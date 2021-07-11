package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import controlador.consultasCliente;
import modelo.cliente;
import sql.conexion;
import vista.MantCliente;

public class intCliente implements ActionListener {

	private modelo.cliente cli;
	private controlador.consultasCliente conCli;
	private vista.MantCliente vistaCliente;
	
	public intCliente(cliente cli, consultasCliente conCli, MantCliente vistaCliente) {
		this.cli = cli;
		this.conCli = conCli;
		this.vistaCliente = vistaCliente;
		
		this.vistaCliente.btnAgregar.addActionListener(this);
		this.vistaCliente.btnEliminar.addActionListener(this);
		this.vistaCliente.btnBuscar.addActionListener(this);
		this.vistaCliente.btnModificar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaCliente.btnAgregar) {
			
			String direccion = vistaCliente.txtDireccion.getText().trim();
			String apellido = vistaCliente.txtApellido.getText().trim();
			String nombre = vistaCliente.txtNombre.getText().trim();
			String id = vistaCliente.txtid.getText().trim();
			
			if(direccion.equals("") && apellido.equals("") && nombre.equals("") && id.equals("")
				&& vistaCliente.txtDni.getText().trim().equals("")) {
				vistaCliente.alerta("No has llenado ningún casillero!");
				return;
			}
				
			else if (direccion.equals("") || apellido.equals("") || nombre.equals("") || id.equals("")
				|| vistaCliente.txtDni.getText().trim().equals("")) {
				vistaCliente.informacion("Te faltan casilleros");
				return;
			}
			
			else if(vistaCliente.txtDni.getText().trim().length() < 8) {
				vistaCliente.informacion("El dni contiene 8 digitos.");
				return;
			}
			
			cli.setId(Integer.parseInt(vistaCliente.txtid.getText().trim()));
			cli.setNombre(vistaCliente.txtNombre.getText().trim());
			cli.setApellido(vistaCliente.txtApellido.getText().trim());
			cli.setDireccion(vistaCliente.txtDireccion.getText().trim());
			cli.setDni(Integer.parseInt(vistaCliente.txtDni.getText().trim()));
			
			if(conCli.registrar(cli)) {
				mensaje("Registro añadido");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al registrar");
				clear();
			}
		}
		
		if(e.getSource() == vistaCliente.btnModificar) {
			
			String id = vistaCliente.txtid.getText().trim();
			
			if(id.length() == 0) {
				vistaCliente.lblError.setText("No hay cliente a modificar");
				return;
			}
			
			cli.setId(Integer.parseInt(vistaCliente.txtid.getText().trim()));
			cli.setNombre(vistaCliente.txtNombre.getText().trim());
			cli.setApellido(vistaCliente.txtApellido.getText().trim());
			cli.setDni(Integer.parseInt(vistaCliente.txtDni.getText().trim()));
			cli.setDireccion(vistaCliente.txtDireccion.getText().trim());
			
			if(conCli.modificar(cli)) {
				mensaje("Modificado completo");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al modificar");
				clear();
			}
		}
		
		if(e.getSource() == vistaCliente.btnEliminar) {
			
			String id = vistaCliente.txtid.getText().trim();
			
			if(id.length() == 0) {
				vistaCliente.lblError.setText("No hay cliente a eliminar");
				return;
			}
			
			cli.setId(Integer.parseInt(vistaCliente.txtid.getText().trim()));
			
			if(conCli.eliminar(cli)) {
				mensaje("Registro eliminado");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al eliminar");
				clear();
			}
		}
		
		if(e.getSource() == vistaCliente.btnBuscar) {
			
			String id = vistaCliente.txtid.getText().trim();
			
			if(id.length() == 0) {
				vistaCliente.lblError.setText("No hay cliente a buscar");
				return;
			}
			
			cli.setId(Integer.parseInt(vistaCliente.txtid.getText().trim()));
			
			if(conCli.buscar(cli)) {
				vistaCliente.txtNombre.setText(cli.getNombre());
				vistaCliente.txtApellido.setText(cli.getApellido());
				vistaCliente.txtDireccion.setText(cli.getDireccion());
				vistaCliente.txtDni.setText(String.valueOf(cli.getDni()));
			}
			
			else {
				mensaje("Registro no encontrado");
				clear();
			}
		}
	}
	
	@SuppressWarnings("static-access")
	void reCrear() {
		try {
			vistaCliente.modelo.setRowCount(0);
			conexion con = new conexion();
			Connection conexion = con.getConnection();
			
			String sql = "select * from cliente";
			Statement st;
			
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			String[] dato = new String[5];
			
			while(result.next()) {
				dato[0] = result.getString(1);
				dato[1] = result.getString(2); 
				dato[2] = result.getString(3); 
				dato[3] = result.getString(4); 
				dato[4] = result.getString(5);
				vistaCliente.modelo.addRow(dato);			
			}
		}
		
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void clear() {
		vistaCliente.txtid.setText(null);
		vistaCliente.txtDni.setText(null);
		vistaCliente.txtDireccion.setText(null);
		vistaCliente.txtNombre.setText(null);
		vistaCliente.txtApellido.setText(null);
	}
	
	public void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}