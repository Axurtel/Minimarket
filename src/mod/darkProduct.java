package mod;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;

import vista.MantProducto;

public class darkProduct implements ActionListener {

	private vista.MantProducto vistaProducto;
	
	public darkProduct(MantProducto vistaProducto) {
		this.vistaProducto = vistaProducto;
		
		vistaProducto.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaProducto.btnDarkMode) {
			
			//background
			vistaProducto.contentPanel.setBackground(new Color(30, 30, 60));
			
			//labels
			vistaProducto.lblCodigo.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblLote.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblMarca.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblPrecio.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblProducto.setForeground(Color.LIGHT_GRAY);
			
			//button
			vistaProducto.btnAgregar.setBackground(new Color(30, 30, 30));
			vistaProducto.btnAgregar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnAgregar.setBorder(new LineBorder(Color.GRAY));
			
			vistaProducto.btnEliminar.setBackground(new Color(30, 30, 30));
			vistaProducto.btnEliminar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnEliminar.setBorder(new LineBorder(Color.GRAY));
			
			vistaProducto.btnBuscar.setBackground(new Color(30, 30, 30));
			vistaProducto.btnBuscar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnBuscar.setBorder(new LineBorder(Color.GRAY));
			
			vistaProducto.btnModificar.setBackground(new Color(30, 30, 30));
			vistaProducto.btnModificar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnModificar.setBorder(new LineBorder(Color.GRAY));
			
			//event mouse
			// -> btnAgregar
			vistaProducto.btnAgregar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnAgregar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnAgregar.setBackground(new Color(30, 30, 30));
				}
			});
			
			// -> btnEliminar
			vistaProducto.btnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnEliminar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnEliminar.setBackground(new Color(30, 30, 30));
				}
			});
			
			// -> btnBuscar
			vistaProducto.btnBuscar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnBuscar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnBuscar.setBackground(new Color(30, 30, 30));
				}
			});
			
			// -> btnModificar
			vistaProducto.btnModificar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnModificar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnModificar.setBackground(new Color(30, 30, 30));
				}
			});
			
			//table
			vistaProducto.tblProducto.setBackground(new Color(20, 20, 50));
			vistaProducto.tblProducto.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaProducto.tblProducto.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaProducto.tblProducto.setForeground(Color.LIGHT_GRAY);
			vistaProducto.tblProducto.setSelectionBackground(new Color(225, 225, 225));
			vistaProducto.tblProducto.setSelectionForeground(Color.DARK_GRAY);
			
			//text
			vistaProducto.txtCodigo.setBackground(new Color(20, 0, 50));
			vistaProducto.txtCodigo.setForeground(Color.white);
			vistaProducto.txtCodigo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

			vistaProducto.txtLote.setBackground(new Color(20, 0, 50));
			vistaProducto.txtLote.setForeground(Color.white);
			vistaProducto.txtLote.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

			vistaProducto.txtMarca.setBackground(new Color(20, 0, 50));
			vistaProducto.txtMarca.setForeground(Color.white);
			vistaProducto.txtMarca.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			vistaProducto.txtPrecio.setBackground(new Color(20, 0, 50));
			vistaProducto.txtPrecio.setForeground(Color.white);
			vistaProducto.txtPrecio.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			vistaProducto.txtProducto.setBackground(new Color(20, 0, 50));
			vistaProducto.txtProducto.setForeground(Color.white);
			vistaProducto.txtProducto.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		}
	}
}
