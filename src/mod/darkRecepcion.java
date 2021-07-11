package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import vista.Recepcion;

public class darkRecepcion implements ActionListener{

	private vista.Recepcion vistaRecepcion;
	
	public darkRecepcion(Recepcion vistaRecepcion) {
		this.vistaRecepcion = vistaRecepcion;
		
		vistaRecepcion.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaRecepcion.btnDarkMode) {
			
			//background
			vistaRecepcion.contentPanel.setBackground(new Color(30, 30, 60));
			
			//labels
			vistaRecepcion.lblSentencia.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.lblCargo.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.lblFecha.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.lblEmisor.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.lblMotivo.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.lblAccion.setForeground(Color.LIGHT_GRAY);
			
			//comboBox
			vistaRecepcion.cboAccion.setBackground(new Color(40, 40, 70));
			vistaRecepcion.cboAccion.setForeground(Color.LIGHT_GRAY);
			
			//button
			vistaRecepcion.btnPdf.setBackground(Color.DARK_GRAY);
			vistaRecepcion.btnPdf.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaRecepcion.btnDeleteSupreme.setBackground(Color.DARK_GRAY);
			vistaRecepcion.btnDeleteSupreme.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaRecepcion.btnSaveFile.setBackground(Color.DARK_GRAY);
			vistaRecepcion.btnSaveFile.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaRecepcion.btnAsistencia.setBackground(Color.DARK_GRAY);
			vistaRecepcion.btnAsistencia.setBorder(new LineBorder(Color.GRAY, 1));
			
			vistaRecepcion.btnConfiguracion.setBackground(Color.DARK_GRAY);
			vistaRecepcion.btnConfiguracion.setBorder(new LineBorder(Color.GRAY, 1));
			
			//table
			vistaRecepcion.tblRecepcion.setBackground(new Color(20, 20, 50));
			vistaRecepcion.tblRecepcion.getTableHeader().setBackground(new Color(0, 100, 170));
			vistaRecepcion.tblRecepcion.getTableHeader().setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.tblRecepcion.setForeground(Color.LIGHT_GRAY);
			vistaRecepcion.tblRecepcion.setSelectionBackground(new Color(225, 225, 225));
			vistaRecepcion.tblRecepcion.setSelectionForeground(Color.DARK_GRAY);
			
			//text
			vistaRecepcion.txtEmisor.setBorder(new LineBorder(Color.LIGHT_GRAY));
			vistaRecepcion.txtEmisor.setBackground(new Color(30, 0, 80));
			vistaRecepcion.txtEmisor.setForeground(Color.white);
			
			vistaRecepcion.cboSentencia.setBorder(new LineBorder(Color.LIGHT_GRAY));
			vistaRecepcion.cboSentencia.setBackground(new Color(30, 0, 80));
			vistaRecepcion.cboSentencia.setForeground(Color.white);
			
			vistaRecepcion.txtCargo.setBorder(new LineBorder(Color.LIGHT_GRAY));
			vistaRecepcion.txtCargo.setBackground(new Color(30, 0, 80));
			vistaRecepcion.txtCargo.setForeground(Color.white);
			
			vistaRecepcion.txtMotivo.setBorder(new LineBorder(Color.LIGHT_GRAY));
			vistaRecepcion.txtMotivo.setBackground(new Color(20, 0, 50));
			vistaRecepcion.txtMotivo.setForeground(Color.white);
		}
	}
}
