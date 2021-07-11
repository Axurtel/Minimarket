package mod;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.AcercaTienda;

public class darkTienda implements ActionListener {

	private vista.AcercaTienda vistaTienda;
	
	public darkTienda(AcercaTienda vistaTienda) {
		this.vistaTienda = vistaTienda;
		
		vistaTienda.btnDarkMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vistaTienda.btnDarkMode) {
			
			vistaTienda.contentPanel.setBackground(new Color(30, 30, 60));
			
			vistaTienda.lblAutores.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblIntegrante1.setForeground(Color.LIGHT_GRAY);
			
			vistaTienda.lblSection.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblSectionR.setForeground(Color.LIGHT_GRAY);
			
			vistaTienda.lblCurso.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblCursoR.setForeground(Color.LIGHT_GRAY);
			
			vistaTienda.lblCodigoEstudiante.setForeground(Color.CYAN);
			vistaTienda.lblCodigoEstudianteR.setForeground(Color.CYAN);
			
			vistaTienda.lblBasadoEn.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblJava.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblMysql.setForeground(Color.LIGHT_GRAY);
			
			vistaTienda.lblImplementacion.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblImplemento1.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblImplemento2.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblImplemento3.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblImplemento4.setForeground(Color.LIGHT_GRAY);
			vistaTienda.lblImplemento5.setForeground(Color.LIGHT_GRAY);
			
			vistaTienda.separator.setForeground(Color.GRAY);
		}
	}
}
