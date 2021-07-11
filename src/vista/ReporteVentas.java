package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import controlador.consultasVenta;

import mod.darkReporte;
import modelo.venta;
import sql.conexion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReporteVentas extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public DefaultTableModel modelo = new DefaultTableModel();
	public final JPanel contentPanel = new JPanel();
	DecimalFormat df = new DecimalFormat("#.00");
	public JTextField txtCodigo;
	public JLabel lblCodigo;
	public JLabel lblError;
	public JButton btnListar;
	public JButton btnDarkMode;
	public JScrollPane scrollPane;
	public JTable tblVenta;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			venta vent = new venta();
			consultasVenta conVent = new consultasVenta();
			ReporteVentas dialog = new ReporteVentas();
	
			darkReporte dr = new darkReporte(dialog);
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
	public ReporteVentas() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 800, 400);
		setTitle("Reporte - Ventas");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblCodigo = new JLabel("Codigo :");
		lblCodigo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblCodigo.setBounds(15, 15, 100, 30);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		txtCodigo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtCodigo.setBounds(127, 15, 130, 30);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(267, 15, 223, 30);
		contentPanel.add(lblError);
		
		btnListar = new JButton("Listado");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				String codigo = txtCodigo.getText().trim();
				
				if(codigo.isEmpty()) {
					reCrear("call selectVenta()");
					lblError.setText("Sin registro de venta a listar");
				}
								
				else {
					reCrear("select * from venta where codigo=" + codigo);					
					lblError.setText("Venta Encontrada");
				}
				
				if(modelo.getRowCount() == 0) {
					lblError.setText("Este registro, no Existe");
				}
			}
		});
		btnListar.setForeground(Color.WHITE);
		btnListar.setBackground(new Color(102, 153, 255));
		btnListar.setFont(new Font("Candara Light", Font.BOLD, 13));
		btnListar.setBounds(613, 15, 100, 30);
		contentPanel.add(btnListar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 58, 750, 280);
		contentPanel.add(scrollPane);
		
		tblVenta = new JTable();
		tblVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				reVent();
			}
		});
		tblVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reVent();
			}
		});
		tblVenta.setGridColor(SystemColor.controlShadow);
		tblVenta.setFillsViewportHeight(true);
		tblVenta.setSelectionForeground(new Color(0, 0, 0));
		tblVenta.setSelectionBackground(new Color(204, 255, 204));
		tblVenta.getTableHeader().setBackground(new Color(32, 155, 253));
		tblVenta.getTableHeader().setForeground(new Color(255, 255, 255));
		tblVenta.setRowHeight(20);
		tblVenta.setForeground(SystemColor.controlDkShadow);
		tblVenta.setBackground(new Color(255, 255, 204));
		tblVenta.setFont(new Font("Candara Light", Font.PLAIN, 13));
		tblVenta.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblVenta);
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		modelo.addColumn("Total a Pagar");
		tblVenta.setModel(modelo);
		
		reCrear("call selectVenta()");
		
		btnDarkMode = new JButton("");
		btnDarkMode.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnDarkMode.setBackground(Color.LIGHT_GRAY);
		btnDarkMode.setBounds(725, 15, 40, 30);
		contentPanel.add(btnDarkMode);
	}
	
	void seeVent(int fila) {
		txtCodigo.setText(String.valueOf(modelo.getValueAt(fila, 0)));
	}
	
	void reVent() {
		int fila;
		fila = tblVenta.getSelectedRow();
		
		if(fila == -1) {
			return;
		}
		
		else {
			seeVent(fila);
		}
	}
	
	@SuppressWarnings("static-access")
	void reCrear(String s ) {
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
}
