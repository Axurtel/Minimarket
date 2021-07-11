package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import controlador.consultasVenta;
import modelo.venta;
import sql.conexion;
import vista.TransaccionCaja;

@SuppressWarnings("static-access")
public class intVenta implements ActionListener {

	private modelo.venta vent;
	private controlador.consultasVenta conVent;
	private vista.TransaccionCaja vistaVenta;
	
	public intVenta(venta vent, consultasVenta conVent, TransaccionCaja vistaVenta) {
		
		this.vent = vent;
		this.conVent = conVent;
		this.vistaVenta = vistaVenta;
		
		this.vistaVenta.btnAgregar.addActionListener(this);
		this.vistaVenta.btnBuscar.addActionListener(this);
		this.vistaVenta.btnEliminar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vistaVenta.btnAgregar) {
			String producto = vistaVenta.txtProducto.getText().trim();
			
			if(producto.equals("") && vistaVenta.txtCantidad.getText().trim().equals("") &&
				vistaVenta.txtCodigo.getText().trim().equals("") && vistaVenta.txtPrecio.getText().trim().equals("")) {
				vistaVenta.alerta("No has llenado ningún casillero!");
				return;
			}
			
			else if (producto.equals("") || vistaVenta.txtCantidad.getText().trim().equals("") ||
				vistaVenta.txtCodigo.getText().trim().equals("") || vistaVenta.txtPrecio.getText().trim().equals("")) {
				vistaVenta.informacion("Te faltan casilleros");
				return;
			}
			
			vent.setCodigo(Integer.parseInt(vistaVenta.txtCodigo.getText().trim()));
			vent.setProducto(producto);
			vent.setCantidad(Integer.parseInt(vistaVenta.txtCantidad.getText().trim()));
			vent.setPrecio(Double.parseDouble(vistaVenta.txtPrecio.getText().trim()));
			
			if(conVent.registrar(vent)) {
				mensaje("Registro añadido");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al registrar");
			}
		}
		
		if(e.getSource() == vistaVenta.btnBuscar) {
			String codigo = vistaVenta.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaVenta.lblError.setText("No hay registro a buscar");
				return;
			}
			
			vent.setCodigo(Integer.parseInt(codigo));
			
			if(conVent.buscar(vent)) {
				vistaVenta.txtCodigo.setText(String.valueOf(vent.getCodigo()));
				vistaVenta.txtProducto.setText(vent.getProducto());
				vistaVenta.txtCantidad.setText(String.valueOf(vent.getCantidad()));
				vistaVenta.txtPrecio.setText(String.valueOf(vent.getPrecio()));
			}
			
			else {
				mensaje("Registro no encontrado");
				clear();
			}
		}
		
		if(e.getSource() == vistaVenta.btnEliminar) {
			String codigo = vistaVenta.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaVenta.lblError.setText("No hay registro a eliminar");
				return;
			}
			
			vent.setCodigo(Integer.parseInt(codigo));
			
			if(conVent.eliminar(vent)) {
				mensaje("Registro elminado");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al eliminar");
				clear();
			}
		}
	}

	public void clear() {
		vistaVenta.txtCantidad.setText(null);
		vistaVenta.txtCodigo.setText(null);
		vistaVenta.txtPrecio.setText(null);
		vistaVenta.txtProducto.setText(null);
	}
	
	void reCrear() {
		try {
			vistaVenta.modelo.setRowCount(0);
			conexion con = new conexion();
			Connection conexion = con.getConnection();
			
			String sql = "select * from venta";
			Statement st;
			
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			String[] dato = new String[4];
			
			while(result.next()) {
				dato[0] = result.getString(1);
				dato[1] = result.getString(2); 
				dato[2] = result.getString(3); 
				dato[3] = result.getString(4); 
				vistaVenta.modelo.addRow(dato);			
			}
		}
		
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
