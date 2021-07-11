package interfaces;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import controlador.consultasProducto;
import modelo.producto;
import vista.ConsProducto;

public class intConsProducto implements ActionListener {

	private modelo.producto prod;
	private controlador.consultasProducto conProd;
	private vista.ConsProducto vistaProducto;
	
	public intConsProducto(producto prod, consultasProducto conProd, ConsProducto vistaProducto) {
		this.prod = prod;
		this.conProd = conProd;
		this.vistaProducto = vistaProducto;
		
		this.vistaProducto.btnBuscar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaProducto.btnBuscar) {
			
			String codigo = vistaProducto.txtCodigo.getText().trim();
			
			if(codigo.length() == 0) {
				vistaProducto.informacion("No hay elemento a buscar");
				return;
			}
			
			prod.setCodigo(Integer.parseInt(codigo));
			
			if(conProd.buscar(prod)) {
				vistaProducto.lblProductoR.setText(prod.getProducto());
				vistaProducto.lblMarcaR.setText(prod.getMarca());
				vistaProducto.lblLoteR.setText(String.valueOf(prod.getLote()));
				vistaProducto.lblPrecioR.setText(String.valueOf(prod.getPrecio()));
			}
			
			else {
				mensaje("Registro no encontrado");
				return;
			}
		}
	}
	
	public void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
