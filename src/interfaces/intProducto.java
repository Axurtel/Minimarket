package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

import controlador.consultasProducto;
import modelo.producto;
import sql.conexion;
import vista.MantProducto;

public class intProducto implements ActionListener {

	private modelo.producto prod;
	private controlador.consultasProducto conProd;
	private vista.MantProducto vistaProducto;
	
	public intProducto(producto prod, consultasProducto conProd, MantProducto vistaProducto) {
		
		this.prod = prod;
		this.conProd = conProd;
		this.vistaProducto = vistaProducto;
		
		this.vistaProducto.btnAgregar.addActionListener(this);
		this.vistaProducto.btnEliminar.addActionListener(this);
		this.vistaProducto.btnModificar.addActionListener(this);
		this.vistaProducto.btnBuscar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == vistaProducto.btnAgregar) {
			
			String precio = vistaProducto.txtPrecio.getText().trim();
			String lote = vistaProducto.txtLote.getText().trim();
			String marca = vistaProducto.txtMarca.getText().trim();
			String producto = vistaProducto.txtProducto.getText().trim();
			
			if(precio.length() == 0 && lote.length() == 0 && marca.length() == 0 && producto.length() == 0
				&& vistaProducto.txtPrecio.getText().trim().length() == 0) {
				vistaProducto.alerta("No has llenado casilleros");
				return;
			}
			
			else if(precio.length() == 0 || lote.length() == 0 || marca.length() == 0 || producto.length() == 0
				|| vistaProducto.txtPrecio.getText().trim().length() == 0) {
				vistaProducto.informacion("Te faltan casilleros");
				return;
			}
			
			prod.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText().trim()));
			prod.setProducto(producto);
			prod.setMarca(marca);
			prod.setLote(Integer.parseInt(lote));
			prod.setPrecio(Double.parseDouble(precio));
			
			if(conProd.registrar(prod)) {
				mensaje("Registro añadido");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al registrar");
			}
		}
		
		if(e.getSource() == vistaProducto.btnModificar) {
			
			String codigo = vistaProducto.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaProducto.lblError.setText("No hay producto a modificar");
				return;
			}
			
			prod.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText().trim()));
			prod.setProducto(vistaProducto.txtProducto.getText().trim());
			prod.setMarca(vistaProducto.txtMarca.getText().trim());
			prod.setLote(Integer.parseInt(vistaProducto.txtLote.getText().trim()));
			prod.setPrecio(Double.parseDouble(vistaProducto.txtPrecio.getText().trim()));
			
			if(conProd.modificar(prod)) {
				mensaje("Modificado completo");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al modificar");
			}
		}
		
		if(e.getSource() == vistaProducto.btnEliminar) {
			
			String codigo = vistaProducto.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaProducto.lblError.setText("No hay elemento a eliminar");
				return;
			}
			
			prod.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText().trim()));
			
			if(conProd.eliminar(prod)) {
				mensaje("Registro eliminado");
				clear();
				
				reCrear();
			}
			
			else {
				mensaje("Error al eliminar");
				clear();
			}
		}
		
		if(e.getSource() == vistaProducto.btnBuscar) {
			
			String codigo = vistaProducto.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaProducto.lblError.setText("No hay elemento a buscar");
				return;
			}
			
			prod.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText().trim()));
			
			if(conProd.buscar(prod)) {
				vistaProducto.txtCodigo.setText(String.valueOf(prod.getCodigo()));
				vistaProducto.txtProducto.setText(prod.getProducto());
				vistaProducto.txtMarca.setText(prod.getMarca());
				vistaProducto.txtLote.setText(String.valueOf(prod.getLote()));
				vistaProducto.txtPrecio.setText(String.valueOf(prod.getPrecio()));
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
			vistaProducto.modelo.setRowCount(0);
			conexion con = new conexion();
			Connection conexion = con.getConnection();
			
			String sql = "call selectProducto()";
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
				vistaProducto.modelo.addRow(dato);			
			}
		}
		
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void clear() {
		vistaProducto.txtCodigo.setText(null);
		vistaProducto.txtProducto.setText(null);
		vistaProducto.txtMarca.setText(null);
		vistaProducto.txtLote.setText(null);
		vistaProducto.txtPrecio.setText(null);
	}
	
	public void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}