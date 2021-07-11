package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import vista.ConsCliente;

public class darkConsClient implements ActionListener {

	private vista.ConsCliente vistaCliente;
	
	public darkConsClient(ConsCliente vistaCliente) {
		this.vistaCliente = vistaCliente;
		
		vistaCliente.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vistaCliente.btnDarkMode) {
			
			vistaCliente.txtCodigo.setForeground(Color.LIGHT_GRAY);
			vistaCliente.txtCodigo.setBackground(new Color(20, 0, 50));
			
			//labels
			vistaCliente.lblid.setForeground(Color.LIGHT_GRAY);
			
			vistaCliente.lblApellido.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblApellidoR.setForeground(Color.LIGHT_GRAY);
			
			vistaCliente.lblDireccion.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblDireccionR.setForeground(Color.LIGHT_GRAY);
			
			vistaCliente.lblDni.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblDniR.setForeground(Color.LIGHT_GRAY);
			
			vistaCliente.lblNombre.setForeground(Color.LIGHT_GRAY);
			vistaCliente.lblNombreR.setForeground(Color.LIGHT_GRAY);
			
			//background
			vistaCliente.contentPanel.setBackground(new Color(30, 30, 60));
			
			//table
			vistaCliente.tblCliente.setBackground(new Color(20, 20, 50));
			vistaCliente.tblCliente.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaCliente.tblCliente.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaCliente.tblCliente.setForeground(Color.LIGHT_GRAY);
			vistaCliente.tblCliente.setSelectionBackground(new Color(225, 225, 225));
			vistaCliente.tblCliente.setSelectionForeground(Color.DARK_GRAY);
			
			//button
			vistaCliente.btnConsultar.setBackground(new Color(30, 30, 30));
			vistaCliente.btnConsultar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			vistaCliente.btnConsultar.setForeground(Color.LIGHT_GRAY);
			
			vistaCliente.btnListar.setBackground(new Color(20, 20, 50));
			vistaCliente.btnListar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));			

			//eventos mouse
			/* btnDarkMode */
			vistaCliente.btnDarkMode.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnDarkMode.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnDarkMode.setBackground(Color.DARK_GRAY);
				}
			});
			
			/* btnConsultar */
			vistaCliente.btnConsultar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnConsultar.setBackground(new Color(15, 15, 15));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnConsultar.setBackground(new Color(30, 30, 30));
				}
			});
			
			/* btnListar */
			vistaCliente.btnListar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaCliente.btnListar.setBackground(new Color(0, 0, 0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaCliente.btnListar.setBackground(new Color(20, 20, 50));
				}
			});
		}
	}
}
