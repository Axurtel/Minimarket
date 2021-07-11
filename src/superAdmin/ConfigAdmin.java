package superAdmin;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.Menu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ConfigAdmin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblClave;
	private JTextField txtClave1;
	private JTextField txtClave2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNuevaClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigAdmin dialog = new ConfigAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfigAdmin() {
		setModal(true);
		setTitle("Configuraci\u00F3n | | SuperUsuario ~ Recepci\u00F3n");
		setResizable(false);
		setBounds(100, 100, 370, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblClave = new JLabel("Clave :");
		lblClave.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblClave.setBounds(15, 15, 100, 20);
		contentPanel.add(lblClave);
		
		txtClave1 = new JTextField();
		txtClave1.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtClave1.setBounds(40, 45, 150, 25);
		contentPanel.add(txtClave1);
		txtClave1.setColumns(10);
		
		lblNuevaClave = new JLabel("Nueva Clave :");
		lblNuevaClave.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblNuevaClave.setBounds(15, 100, 100, 20);
		contentPanel.add(lblNuevaClave);
		
		txtClave2 = new JTextField();
		txtClave2.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtClave2.setColumns(10);
		txtClave2.setBounds(40, 130, 150, 25);
		contentPanel.add(txtClave2);
		
		btnAceptar = new JButton("");
		btnAceptar.setBackground(SystemColor.controlHighlight);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtClave1.getText().trim().length() == 0 && txtClave2.getText().trim().length() == 0) {
						mensaje("Ningún casillero está completo");
						return;
					}
					
					else if (txtClave1.getText().trim().length() == 0 || txtClave2.getText().trim().length() == 0) {
						informacion("Faltan casilleros");
						return;
					}
					
					else {
						if(txtClave1.getText().trim().equals(Menu.key)) {
							Menu.key = txtClave2.getText().trim();
							informacion("Sentencia cambiada");
							SystemConsole.txtSConsole.append("Cambio exitoso, nueva clave: "+ Menu.key +"\n");
							dispose();
						}
						
						else
							mensaje("La sentencia no es correcta.");
					}
				} 
				
				catch(NullPointerException ex) {
		        	System.out.println("Ejecute el programa desde el Menú, es necesario el uso de consola");
		        }
			}
		});
		btnAceptar.setIcon(new ImageIcon(ConfigAdmin.class.getResource("/imagenes/procesar.png")));
		btnAceptar.setBounds(250, 45, 55, 40);
		contentPanel.add(btnAceptar);
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(SystemColor.controlHighlight);
		btnCancelar.setIcon(new ImageIcon(ConfigAdmin.class.getResource("/imagenes/eliminar.png")));
		btnCancelar.setBounds(250, 100, 55, 40);
		contentPanel.add(btnCancelar);
	}
	
    void mensaje(String s) {
    	getToolkit().beep();
    	JOptionPane.showMessageDialog(this, s, "Advertencia", 0);
    }
    
	void informacion(String s) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, s,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
