package interfaces;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import controlador.consultasCliente;
import modelo.cliente;
import vista.ConsCliente;

public class intConsCliente implements ActionListener {

	private modelo.cliente cli;
	private controlador.consultasCliente conCli;
	private vista.ConsCliente vistaCliente;
	
	public intConsCliente(cliente cli, consultasCliente conCli, ConsCliente vistaCliente) {
		
		this.cli = cli;
		this.conCli = conCli;
		this.vistaCliente = vistaCliente;
		
		this.vistaCliente.btnConsultar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaCliente.btnConsultar) {
			
			if(vistaCliente.txtCodigo.getText().trim().length() == 0) {
				vistaCliente.lblError.setText("No hay cliente a consultar");
				return;
			}
			
			cli.setId(Integer.parseInt(vistaCliente.txtCodigo.getText().trim()));
			
			if(conCli.buscar(cli)) {
				vistaCliente.lblNombreR.setText(cli.getNombre());
				vistaCliente.lblApellidoR.setText(cli.getApellido());
				vistaCliente.lblDireccionR.setText(cli.getDireccion());
				vistaCliente.lblDniR.setText(String.valueOf(cli.getDni()));
			}
			
			else {
				mensaje("Registro no encontrado");
			}
		}
	}
	
	public void mensaje (String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
