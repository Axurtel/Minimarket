package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import modelo.recepcion;
import sql.conexion;
import controlador.consultasRecepcion;
import vista.Recepcion;

public class intRecepcion implements ActionListener {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private modelo.recepcion rcp;
	private controlador.consultasRecepcion conRcp;
	private vista.Recepcion recepcion;
	
	public intRecepcion(recepcion rcp, consultasRecepcion conRcp, Recepcion recepcion) {
		
		this.rcp = rcp;
		this.conRcp = conRcp;
		this.recepcion = recepcion;
		
		this.recepcion.btnProcesar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == recepcion.btnProcesar) {
			
			String sentencia = (String) recepcion.cboSentencia.getSelectedItem();
			String cargo = recepcion.txtCargo.getText().trim();
			String emisor = recepcion.txtEmisor.getText().trim();
			String motivo = recepcion.txtMotivo.getText();
			
			
			if(sentencia.length() == 0 && cargo.length() == 0 && 
			emisor.length() == 0 && motivo.length() == 0 && recepcion.dtcRecepcion.getDate() == null) {
				
				recepcion.alerta("Los casilleros están vacíos.");
				return;
			}
			
			else if(sentencia.length() == 0 || cargo.length() == 0 ||
			emisor.length() == 0 || motivo.length() == 0 || recepcion.dtcRecepcion.getDate() == null) {
				
				recepcion.informacion("Faltan casilleros.");
				return;
			}
			
			switch(recepcion.cboAccion.getSelectedIndex()) {
				case 0: //insertar
					
					rcp.setSentencia(sentencia);
					rcp.setEmisor(emisor);
					rcp.setCargo(cargo);
					rcp.setRazon(motivo);
					rcp.setFecha(df.format(recepcion.dtcRecepcion.getDate()));
					
					if(conRcp.registrar(rcp)) {
						mensaje("Auditoría añadida");
						clear();
						
						reCrear();
					}
					
					else {
						mensaje("Error al registrar");
					}
					
					break;
					
				default: //eliminar
					
					rcp.setSentencia(sentencia);
					rcp.setCargo(cargo);
					rcp.setEmisor(emisor);
					rcp.setRazon(motivo);
					rcp.setFecha(df.format(recepcion.dtcRecepcion.getDate()));
					
					if(conRcp.eliminar(rcp)) {
						mensaje("Auditoría eliminada");
						clear();
						
						recepcion.reCrear("select * from recepcion");
					}
					
					else {
						mensaje("Error al eliminar");
					}
					
					break;			
			}
		}
	}
	
	public void clear() {
		recepcion.dtcRecepcion.setDate(null);
		recepcion.txtCargo.setText(null);
		recepcion.txtEmisor.setText(null);
		recepcion.txtMotivo.setText(null);
	}
	
	@SuppressWarnings("static-access")
	void reCrear() {
		try {
			recepcion.modelo.setRowCount(0);
			conexion con = new conexion();
			Connection conexion = con.getConnection();
			
			String sql = "select * from recepcion";
			Statement st;
			
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			String[] dato = new String[5];
			
			while(result.next()) {
				dato[0] = result.getString(1);
				dato[1] = result.getString(2); 
				dato[2] = result.getString(3); 
				dato[3] = result.getString(4); 
				dato[4] = result.getString(5);
				recepcion.modelo.addRow(dato);			
			}
		}
		
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
