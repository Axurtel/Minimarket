package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import vista.ConsProducto;

public class darkConsProd implements ActionListener {
	
	private vista.ConsProducto vistaProducto;
	
	public darkConsProd(ConsProducto vistaProducto) {
		this.vistaProducto = vistaProducto;
		
		vistaProducto.btnDarkMode.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vistaProducto.btnDarkMode) {
			
			vistaProducto.txtCodigo.setForeground(Color.LIGHT_GRAY);
			vistaProducto.txtCodigo.setBackground(new Color(20, 0, 50));
			
			//labels
			vistaProducto.lblCodigo.setForeground(Color.LIGHT_GRAY);
			
			vistaProducto.lblLote.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblLoteR.setForeground(Color.LIGHT_GRAY);
			
			vistaProducto.lblMarca.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblMarcaR.setForeground(Color.LIGHT_GRAY);
			
			vistaProducto.lblPrecio.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblPrecioR.setForeground(Color.LIGHT_GRAY);
			
			vistaProducto.lblProducto.setForeground(Color.LIGHT_GRAY);
			vistaProducto.lblProductoR.setForeground(Color.LIGHT_GRAY);
			
			//background
			vistaProducto.contentPanel.setBackground(new Color(30, 30, 60));
			
			//table
			vistaProducto.tblProducto.setBackground(new Color(20, 20, 50));
			vistaProducto.tblProducto.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaProducto.tblProducto.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaProducto.tblProducto.setForeground(Color.LIGHT_GRAY);
			vistaProducto.tblProducto.setSelectionBackground(new Color(225, 225, 225));
			vistaProducto.tblProducto.setSelectionForeground(Color.DARK_GRAY);
			
			//button
			vistaProducto.btnBuscar.setBackground(new Color(30, 30, 30));
			vistaProducto.btnBuscar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnBuscar.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaProducto.btnListar.setBackground(new Color(20, 20, 20));
			vistaProducto.btnListar.setForeground(Color.LIGHT_GRAY);
			vistaProducto.btnListar.setBorder(new LineBorder(Color.GRAY, 1));
			
			//MouseEvent
			// -> btnListar
			vistaProducto.btnListar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnListar.setBackground(new Color(15, 15, 15));
					vistaProducto.btnListar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnListar.setBackground(new Color(20, 20, 20));
					vistaProducto.btnListar.setBorder(new LineBorder(Color.GRAY, 1));
				}
			});
			
			// -> btnBuscar
			vistaProducto.btnBuscar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaProducto.btnBuscar.setBackground(new Color(15, 15, 15));
					vistaProducto.btnBuscar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaProducto.btnBuscar.setBackground(new Color(30, 30, 30));
					vistaProducto.btnBuscar.setBorder(new LineBorder(Color.GRAY, 1));
				}
			});
		}
	}
}
