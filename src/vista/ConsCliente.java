package vista;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.consultasCliente;
import interfaces.intConsCliente;
import mod.darkConsClient;
import modelo.cliente;
import sql.conexion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public final JPanel contentPanel = new JPanel();
	public JLabel lblid;
	public JTextField txtCodigo;
	private JScrollPane scrollPane;
	public JTable tblCliente;
	public JLabel lblError;
	public JButton btnConsultar;
	public JButton btnListar;
	public JLabel lblNombre;
	public JLabel lblApellido;
	public JLabel lblDireccion;
	public JLabel lblDni;
	public JLabel lblNombreR;
	public JLabel lblApellidoR;
	public JLabel lblDireccionR;
	public JLabel lblDniR;
	public DefaultTableModel modelo = new DefaultTableModel();
	public JButton btnDarkMode;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			cliente cli = new cliente();
			consultasCliente consCli= new consultasCliente();
			ConsCliente dialog = new ConsCliente();
			darkConsClient dcc = new darkConsClient(dialog);
			
			intConsCliente icc = new intConsCliente(cli, consCli, dialog);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsCliente() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 830, 370);
		setTitle("Consultar - Cliente");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblid = new JLabel("Identification :");
		lblid.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblid.setBounds(15, 15, 100, 30);
		contentPanel.add(lblid);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				soloNumeros(e);
				
				String codigo = txtCodigo.getText().trim();
				
				if(codigo.length() >= 5) {
					informacion("No más de 5 dígitos");
					txtCodigo.setText(null);
					e.consume();
				}
			}
		});
		txtCodigo.setBounds(125, 14, 100, 30);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText().trim();
				
				if(codigo.equals("")) {
					informacion("No hay Cliente a buscar");
				}
			}
		});
		btnConsultar.setForeground(new Color(0, 0, 0));
		btnConsultar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnConsultar.setBackground(new Color(204, 255, 255));
		btnConsultar.setBounds(15, 285, 100, 30);
		contentPanel.add(btnConsultar);
		
		btnListar = new JButton("Listar");
		btnListar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				String id = txtCodigo.getText().trim();
				
				if(txtCodigo.getText().isEmpty()) {
					reCrear("select * from cliente");
					lblError.setText("Sin cliente a listar");
				}
				
				else {
					reCrear("select * from cliente where id = " + id);
					lblError.setText("Cliente Encontrado");
				}
				
				if(modelo.getRowCount() == 0)
					lblError.setText("Este cliente no Existe");
			}
		});
		btnListar.setForeground(Color.WHITE);
		btnListar.setFont(new Font("Candara Light", Font.BOLD, 13));
		btnListar.setBackground(new Color(102, 153, 255));
		btnListar.setBounds(125, 285, 100, 30);
		contentPanel.add(btnListar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 15, 510, 300);
		contentPanel.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				reClient();
			}
		});
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reClient();
			}
		});
		tblCliente.setGridColor(SystemColor.controlShadow);
		tblCliente.setSelectionForeground(new Color(0, 0, 0));
		tblCliente.setSelectionBackground(new Color(204, 255, 204));
		tblCliente.getTableHeader().setBackground(new Color(32, 155, 253));
		tblCliente.getTableHeader().setForeground(new Color(255, 255, 255));
		tblCliente.setRowHeight(20);
		tblCliente.getTableHeader().setOpaque(false);
		tblCliente.setForeground(SystemColor.controlDkShadow);
		tblCliente.setBackground(new Color(255, 255, 204));
		tblCliente.setFont(new Font("Candara Light", Font.PLAIN, 13));
		tblCliente.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCliente);
		
		modelo.addColumn("id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Dirección");
		modelo.addColumn("Dni");
		tblCliente.setModel(modelo);
		tblCliente.setVisible(true);
		
		reCrear("select * from cliente");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(15, 56, 210, 30);
		contentPanel.add(lblError);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNombre.setBounds(15, 97, 100, 30);
		contentPanel.add(lblNombre);
		
		lblApellido = new JLabel("Apellido :");
		lblApellido.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblApellido.setBounds(15, 138, 100, 30);
		contentPanel.add(lblApellido);
		
		lblDireccion = new JLabel("Direcci\u00F3n :");
		lblDireccion.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDireccion.setBounds(15, 179, 100, 30);
		contentPanel.add(lblDireccion);
		
		lblDni = new JLabel("Dni :");
		lblDni.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDni.setBounds(15, 220, 100, 30);
		contentPanel.add(lblDni);
		
		lblNombreR = new JLabel("");
		lblNombreR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNombreR.setBounds(125, 97, 100, 30);
		contentPanel.add(lblNombreR);
		
		lblApellidoR = new JLabel("");
		lblApellidoR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblApellidoR.setBounds(125, 138, 100, 30);
		contentPanel.add(lblApellidoR);
		
		lblDireccionR = new JLabel("");
		lblDireccionR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDireccionR.setBounds(125, 179, 100, 30);
		contentPanel.add(lblDireccionR);
		
		lblDniR = new JLabel("");
		lblDniR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDniR.setBounds(125, 220, 100, 30);
		contentPanel.add(lblDniR);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setBorder(new LineBorder(Color.ORANGE, 3));
		btnDarkMode.setForeground(Color.WHITE);
		btnDarkMode.setFont(new Font("Candara Light", Font.BOLD, 13));
		btnDarkMode.setBackground(Color.DARK_GRAY);
		btnDarkMode.setBounds(235, 285, 40, 30);
		contentPanel.add(btnDarkMode);
	}
	
	void soloNumeros(KeyEvent e) {
		char numero = e.getKeyChar();
		
		if(Character.isDigit(numero)) {
			lblError.setText("");
		}
		
		else {
			lblError.setText("Escribe solo números.");
			e.consume();
			getToolkit().beep();
		}
	}
	
	void seeClient(int fila) {
		txtCodigo.setText("" + modelo.getValueAt(fila, 0));
		lblNombreR.setText("" + modelo.getValueAt(fila, 1));
		lblApellidoR.setText("" + modelo.getValueAt(fila, 2));
		lblDireccionR.setText("" + modelo.getValueAt(fila, 3));
		lblDniR.setText("" + modelo.getValueAt(fila, 4));
	}
	
	void reClient() {
		int fila;
		fila = tblCliente.getSelectedRow();
		
		if(fila == -1) {
			return;
		}
		
		else {
			seeClient(fila);
		}
	}
	
	@SuppressWarnings("static-access")
	void reCrear(String s) {
		conexion con = new conexion();
		Connection conexion = con.getConnection();
		
		String sql = s;
		Statement st;
		
		String[] dato = new String[5];
		
		try {
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				dato[0] = result.getString(1);
				dato[1] = result.getString(2); 
				dato[2] = result.getString(3); 
				dato[3] = result.getString(4); 
				dato[4] = result.getString(5);
				modelo.addRow(dato);			
			}
		} 

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
