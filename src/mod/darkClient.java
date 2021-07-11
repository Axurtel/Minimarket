package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import vista.MantCliente;

public class darkClient implements ActionListener {
	
	private vista.MantCliente vistaCliente;
	
	public darkClient(MantCliente vistaCliente) {
		this.vistaCliente = vistaCliente;
		
		vistaCliente.btnDarkMode.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaCliente.btnDarkMode) {
			
			//background
			vistaCliente.contentPanel.setBackground(new Color(30, 30, 60));
			
			//labels
			vistaCliente.lblDireccion.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblDni.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblid.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblNombre.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblApellido.setForeground(Color.LIGHT_GRAY);
			
			//button
			vistaCliente.btnAgregar.setBackground(new Color(30, 30, 30));
			vistaCliente.btnBuscar.setBackground(new Color(30, 30, 30));
			vistaCliente.btnEliminar.setBackground(new Color(30, 30, 30));
			vistaCliente.btnModificar.setBackground(new Color(30, 30, 30));
			
			
			/* btnAgregar */
			vistaCliente.btnAgregar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnAgregar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnAgregar.setBackground(new Color(30, 30, 30));
				}
			});
			
			/* btnBuscar */
			vistaCliente.btnBuscar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnBuscar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnBuscar.setBackground(new Color(30, 30, 30));
				}
			});
			
			/* btnModificar */
			vistaCliente.btnModificar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnModificar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnModificar.setBackground(new Color(30, 30, 30));
				}
			});
			
			/* btnEliminar */
			vistaCliente.btnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnEliminar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnEliminar.setBackground(new Color(30, 30, 30));
				}
			});
			
			/* foreground */
			vistaCliente.btnAgregar.setForeground(Color.LIGHT_GRAY);
			vistaCliente.btnBuscar.setForeground(Color.LIGHT_GRAY);
			vistaCliente.btnEliminar.setForeground(Color.LIGHT_GRAY);
			vistaCliente.btnModificar.setForeground(Color.LIGHT_GRAY);
			
			/* line border */
			vistaCliente.btnAgregar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.btnBuscar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.btnEliminar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.btnModificar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.btnDarkMode.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			//table
			vistaCliente.tblCliente.setBackground(new Color(20, 20, 50));
			vistaCliente.tblCliente.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaCliente.tblCliente.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaCliente.tblCliente.setForeground(Color.LIGHT_GRAY);
			vistaCliente.tblCliente.setSelectionBackground(new Color(225, 225, 225));
			vistaCliente.tblCliente.setSelectionForeground(Color.DARK_GRAY);
			
			//textField
			vistaCliente.txtid.setBackground(new Color(20, 0, 50));
			vistaCliente.txtNombre.setBackground(new Color(20, 0, 50));
			vistaCliente.txtDireccion.setBackground(new Color(20, 0, 50));
			vistaCliente.txtDni.setBackground(new Color(20, 0, 50));
			vistaCliente.txtApellido.setBackground(new Color(20, 0, 50));
			
			/* line border */
			vistaCliente.txtid.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.txtNombre.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.txtDireccion.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.txtDni.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.txtApellido.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			
			/* color */
			vistaCliente.txtApellido.setForeground(Color.white);
			vistaCliente.txtid.setForeground(Color.white);
			vistaCliente.txtNombre.setForeground(Color.white);
			vistaCliente.txtDireccion.setForeground(Color.white);
			vistaCliente.txtDni.setForeground(Color.white);
		}
	}
}
