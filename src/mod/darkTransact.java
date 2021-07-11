package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import vista.TransaccionCaja;

public class darkTransact implements ActionListener {
	
	private vista.TransaccionCaja vistaVenta;
	
	public darkTransact(TransaccionCaja vistaVenta) {
		this.vistaVenta = vistaVenta;
		
		vistaVenta.btnDarkMode.addActionListener(this);
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource() == vistaVenta.btnDarkMode) {
			
			vistaVenta.txtCantidad.setForeground(Color.LIGHT_GRAY);
			vistaVenta.txtCantidad.setBackground(new Color(20, 0, 50));
			vistaVenta.txtCantidad.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			vistaVenta.txtCodigo.setForeground(Color.LIGHT_GRAY);
			vistaVenta.txtCodigo.setBackground(new Color(20, 0, 50));
			vistaVenta.txtCodigo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			vistaVenta.txtPrecio.setForeground(Color.LIGHT_GRAY);
			vistaVenta.txtPrecio.setBackground(new Color(20, 0, 50));
			
			vistaVenta.txtProducto.setForeground(Color.LIGHT_GRAY);
			vistaVenta.txtProducto.setBackground(new Color(20, 0, 50));
			
			vistaVenta.txtTotal.setForeground(Color.LIGHT_GRAY);
			vistaVenta.txtTotal.setBackground(new Color(20, 0, 50));
			vistaVenta.txtTotal.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			vistaVenta.panel.setBackground(new Color(30, 30, 60));
			vistaVenta.panel.setBorder(new TitledBorder(new EtchedBorder(
					EtchedBorder.LOWERED, new Color(30, 30, 60), 
					new Color(160, 160, 160)), "Buttons", 
					TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
			
			//labels
			vistaVenta.lblCantidad.setForeground(Color.LIGHT_GRAY);
			vistaVenta.lblCodigo.setForeground(Color.LIGHT_GRAY);
			vistaVenta.lblPrecio.setForeground(Color.LIGHT_GRAY);
			vistaVenta.lblProducto.setForeground(Color.LIGHT_GRAY);
			vistaVenta.lblCantidad.setForeground(Color.LIGHT_GRAY);
			vistaVenta.lblTitulo.setForeground(Color.LIGHT_GRAY);
			
			//background
			vistaVenta.contentPanel.setBackground(new Color(30, 30, 60));
			
			//table
			vistaVenta.tblProducto.setBackground(new Color(20, 20, 50));
			vistaVenta.tblProducto.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaVenta.tblProducto.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaVenta.tblProducto.setForeground(Color.LIGHT_GRAY);
			vistaVenta.tblProducto.setSelectionBackground(new Color(225, 225, 225));
			vistaVenta.tblProducto.setSelectionForeground(Color.DARK_GRAY);
			
			//button
			vistaVenta.btnAgregar.setBackground(new Color(30, 30, 30));
			vistaVenta.btnAgregar.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaVenta.btnEliminar.setBackground(new Color(30, 30, 30));
			vistaVenta.btnEliminar.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaVenta.btnBuscar.setBackground(new Color(30, 30, 30));
			vistaVenta.btnBuscar.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaVenta.separator.setBackground(Color.LIGHT_GRAY);
			vistaVenta.separator_1.setBackground(Color.LIGHT_GRAY);
			
			//event mouse
			// -> btnAgregar
			vistaVenta.btnAgregar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaVenta.btnAgregar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaVenta.btnAgregar.setBackground(new Color(30, 30, 30));
				}
			});
			
			// -> btnEliminar
			vistaVenta.btnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaVenta.btnEliminar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaVenta.btnEliminar.setBackground(new Color(30, 30, 30));
				}
			});
			
			
			// -> btnBuscar
			vistaVenta.btnBuscar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaVenta.btnBuscar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaVenta.btnBuscar.setBackground(new Color(30, 30, 30));
				}
			});
		}
	}
}
