package superAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class SystemConsole extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scp;
	public static JTextArea txtSConsole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemConsole frame = new SystemConsole();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SystemConsole() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SystemConsole.class.getResource("/imagenes/conexion_sql.png")));
		setResizable(false);
		setTitle("SystemConsole | | SuperUsuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scp = new JScrollPane();
		scp.setBounds(0, 0, 736, 313);
		contentPane.add(scp);
		
		txtSConsole = new JTextArea();
		txtSConsole.setEditable(false);
		txtSConsole.setForeground(Color.LIGHT_GRAY);
		txtSConsole.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtSConsole.setLineWrap(true);
		txtSConsole.setBackground(Color.DARK_GRAY);
		scp.setViewportView(txtSConsole);
	}
}
