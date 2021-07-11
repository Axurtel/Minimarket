package superAdmin;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Asistencia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSolicitar;
	private JComboBox cboSolicitud;
	private JLabel lblMensaje;
	private JScrollPane scp;
	private JButton btnEnviar;
	private JTextArea txtS;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Asistencia dialog = new Asistencia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public Asistencia() {
		setTitle("Asisntecia | | SuperUsuario ~ Recepci\u00F3n");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(20, 20, 40));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblSolicitar = new JLabel("Solicitar :");
		lblSolicitar.setForeground(Color.LIGHT_GRAY);
		lblSolicitar.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblSolicitar.setBounds(15, 20, 100, 20);
		contentPanel.add(lblSolicitar);
		
		cboSolicitud = new JComboBox();
		cboSolicitud.setBackground(new Color(40, 40, 70));
		cboSolicitud.setForeground(Color.LIGHT_GRAY);
		cboSolicitud.setFont(new Font("Consolas", Font.PLAIN, 13));
		cboSolicitud.setModel(new DefaultComboBoxModel(new String[] {"Mantenimiento", "Pedido por falta de Stock", "Arreglar falla de Programa", "Acciones de Botones nuevas", "Otro"}));
		cboSolicitud.setBounds(125, 13, 200, 30);
		contentPanel.add(cboSolicitud);
		
		lblMensaje = new JLabel("Mensaje :");
		lblMensaje.setForeground(Color.LIGHT_GRAY);
		lblMensaje.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblMensaje.setBounds(15, 80, 100, 20);
		contentPanel.add(lblMensaje);
		
		scp = new JScrollPane();
		scp.setViewportBorder(null);
		scp.setBounds(95, 75, 230, 90);
		contentPanel.add(scp);
		
		txtS = new JTextArea();
		txtS.setSelectedTextColor(Color.LIGHT_GRAY);
		txtS.setSelectionColor(Color.BLACK);
		txtS.setForeground(Color.LIGHT_GRAY);
		txtS.setBorder(null);
		txtS.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtS.setBackground(new Color(50, 50, 90));
		txtS.setLineWrap(true);
		scp.setViewportView(txtS);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboSolicitud.getSelectedIndex() == 4 && txtS.getText().trim().length() == 0) 
					alerta("En caso de otro, por favor, llene el mensaje");
				
				else {
					informacion("Solicitud Enviada");
					dispose();
				}
			}
		});
		btnEnviar.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnEnviar.setBackground(new Color(40, 40, 70));
		btnEnviar.setForeground(Color.LIGHT_GRAY);
		btnEnviar.setBounds(95, 184, 100, 30);
		contentPanel.add(btnEnviar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(40, 40, 70));
		btnCancelar.setForeground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(225, 185, 100, 30);
		contentPanel.add(btnCancelar);
	}
	
	void alerta(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
