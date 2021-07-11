package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.Menu;

public class darkMenu implements ActionListener {
	
	private vista.Menu vistaMenu;
	
	public darkMenu(Menu vistaMenu) {
		this.vistaMenu = vistaMenu;
		
		this.vistaMenu.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaMenu.btnDarkMode) {
			
			//Barra de menu
			vistaMenu.menuBar.setBackground(Color.DARK_GRAY);
			
			//Items menu
			vistaMenu.mnArchivo.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mnConsultar.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mnMantenimiento.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mnTransaccion.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mnReporte.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mnAcercaTienda.setForeground(Color.LIGHT_GRAY);
			
			/* items menu interno */
			vistaMenu.mntmCaja.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmCaja.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmCaja.setBorder(null);
			
			vistaMenu.mntmCliente.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmCliente.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmCliente.setBorder(null);
			
			vistaMenu.mntmProducto.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmProducto.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmProducto.setBorder(null);
			
			vistaMenu.mntmCliente1.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmCliente1.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmCliente1.setBorder(null);
			
			vistaMenu.mntmProducto1.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmProducto1.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmProducto1.setBorder(null);
			
			vistaMenu.mntmSalir.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmSalir.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmSalir.setBorder(null);
			
			vistaMenu.mntmVentas.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmVentas.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmVentas.setBorder(null);
			
			vistaMenu.mntmAcercaTienda.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmAcercaTienda.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmAcercaTienda.setBorder(null);
			
			vistaMenu.mntmRecepcion.setBackground(Color.DARK_GRAY);
			vistaMenu.mntmRecepcion.setForeground(Color.LIGHT_GRAY);
			vistaMenu.mntmRecepcion.setBorder(null);
			
			//eventos mouse
			
				/* btnDarkMode */
			vistaMenu.btnDarkMode.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.btnDarkMode.setBackground(new Color(30, 30, 30));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.btnDarkMode.setBackground(Color.DARK_GRAY);
				}
			});
			
				/* barra menu */
			//archivo
			vistaMenu.mnArchivo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnArchivo.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnArchivo.setForeground(Color.LIGHT_GRAY);
				}
			});
			
			//consultar
			vistaMenu.mnConsultar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnConsultar.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnConsultar.setForeground(Color.LIGHT_GRAY);
				}
			});
			
			//mantenimiento
			vistaMenu.mnMantenimiento.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnMantenimiento.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnMantenimiento.setForeground(Color.LIGHT_GRAY);
				}
			});
			
			//transact
			vistaMenu.mnTransaccion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnTransaccion.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnTransaccion.setForeground(Color.LIGHT_GRAY);
				}
			});
			
			//report
			vistaMenu.mnReporte.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnReporte.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnReporte.setForeground(Color.LIGHT_GRAY);
				}
			});
			
			//Acerca de Tienda
			vistaMenu.mnAcercaTienda.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					vistaMenu.mnAcercaTienda.setForeground(new Color(255, 255, 255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					vistaMenu.mnAcercaTienda.setForeground(Color.LIGHT_GRAY);
				}
			});			
		}
	}
}
