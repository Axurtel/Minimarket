package mod;

import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import vista.ReporteVentas;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class darkReporte implements ActionListener {
	
	private vista.ReporteVentas vistaReporte;

	public darkReporte(ReporteVentas vistaReporte) {
		this.vistaReporte = vistaReporte;
		
		vistaReporte.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaReporte.btnDarkMode) {
			
			vistaReporte.contentPanel.setBackground(new Color(30, 30, 60));
			
			vistaReporte.txtCodigo.setForeground(Color.LIGHT_GRAY);
			vistaReporte.txtCodigo.setBackground(new Color(20, 0, 50));
			vistaReporte.txtCodigo.setBorder(new LineBorder(Color.LIGHT_GRAY));
			
			//labels
			vistaReporte.lblError.setForeground(Color.CYAN);			
			vistaReporte.lblCodigo.setForeground(Color.LIGHT_GRAY);
			
			//table
			vistaReporte.tblVenta.setBackground(new Color(20, 20, 50));
			vistaReporte.tblVenta.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaReporte.tblVenta.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaReporte.tblVenta.setForeground(Color.LIGHT_GRAY);
			vistaReporte.tblVenta.setSelectionBackground(new Color(225, 225, 225));
			vistaReporte.tblVenta.setSelectionForeground(Color.DARK_GRAY);
			
			//button			
			vistaReporte.btnListar.setBackground(new Color(20, 20, 50));
			vistaReporte.btnListar.setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
	}
}
