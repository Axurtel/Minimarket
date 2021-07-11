package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblConexion;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContraseña;
	private JPasswordField pfContraseña;
	private JButton btnConectar;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setDefaultCloseOperation(dialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setUndecorated(true);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 400, 400);
		setTitle("Login | | Conectar a Base de datos");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblConexion = new JLabel("");
		lblConexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConexion.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/conexion_sql.png")));
		lblConexion.setBounds(120, 20, 140, 140);
		contentPanel.add(lblConexion);
		
		separator = new JSeparator();
		separator.setBounds(20, 180, 350, 1);
		contentPanel.add(separator);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Candara Light", Font.PLAIN, 15));
		lblUsuario.setBounds(90, 210, 100, 30);
		contentPanel.add(lblUsuario);
		contentPanel.setBorder(new LineBorder(Color.BLACK, 5));
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		txtUsuario.setBounds(210, 210, 100, 30);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblContraseña = new JLabel("Contrase\u00F1a :");
		lblContraseña.setFont(new Font("Candara Light", Font.PLAIN, 15));
		lblContraseña.setBounds(90, 273, 100, 30);
		contentPanel.add(lblContraseña);
		
		pfContraseña = new JPasswordField();
		pfContraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char[] contraseña = pfContraseña.getPassword();
				
				if (contraseña.length >= 8) {
					e.consume();
					getToolkit().beep();
					informacion("No lleva más de 8 carácteres.");
				}

			}
		});
		pfContraseña.setBounds(210, 273, 100, 30);
		contentPanel.add(pfContraseña);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setMnemonic(KeyEvent.VK_ENTER);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String usuario = txtUsuario.getText().trim();
				char[] contraseña = pfContraseña.getPassword();
				String contra = String.valueOf(contraseña);
				
				if (usuario.length() == 0 && contraseña.length == 0) {
					alerta("No has completado los datos");
					return;
				}
				
				if(usuario.equals("root") && contra.equals("root")) {
					informacion("Conexión completa");
					dispose();
				}
				
				if(!usuario.equals("root") && !contra.equals("root")) {
					informacion("Datos Incorrectos");
				}
				
				else if(usuario.length() == 0) {
					informacion("No has llenado el usuario");
					return;
				}
				
				else if(contraseña.length == 0) {
					informacion("No has colocado la contraseña");
					return;
				}
			}
		});
		btnConectar.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		btnConectar.setForeground(Color.BLACK);
		btnConectar.setBackground(Color.WHITE);
		btnConectar.setBounds(140, 340, 100, 30);
		contentPanel.add(btnConectar);
	}
	
	void soloLetras(KeyEvent e) {
		char letra = e.getKeyChar();
		
		if(Character.isLetter(letra)) {
			return;
		}
		
		else {
			e.consume();
			getToolkit().beep();
		}
	}
	
	private void alerta(String mens) {
		JOptionPane.showMessageDialog(this, mens,"Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	private void informacion(String mens) {
		JOptionPane.showMessageDialog(this, mens,"Advertencia",JOptionPane.INFORMATION_MESSAGE);
	}
}
