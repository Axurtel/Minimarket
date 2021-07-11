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

import controlador.consultasProducto;
import interfaces.intConsProducto;
import mod.darkConsProd;
import modelo.producto;
import sql.conexion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ConsProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public DefaultTableModel modelo= new DefaultTableModel();
	public final JPanel contentPanel = new JPanel();
	public JTextField txtCodigo;
	public JScrollPane scrollPane;
	public JTable tblProducto;
	public JLabel lblError;
	public JLabel lblProducto;
	public JLabel lblMarca;
	public JLabel lblLote;
	public JLabel lblPrecio;
	public JLabel lblPrecioR;
	public JLabel lblLoteR;
	public JLabel lblMarcaR;
	public JLabel lblProductoR;
	public JLabel lblCodigo;
	public JButton btnListar;
	public JButton btnBuscar;
	public JButton btnDarkMode;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			producto prod = new producto();
			consultasProducto conProd = new consultasProducto();
			ConsProducto dialog = new ConsProducto();
			
			darkConsProd dcp = new darkConsProd(dialog);
			intConsProducto icp = new intConsProducto(prod, conProd, dialog);
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
	public ConsProducto() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 825, 370);
		setTitle("Consultar - Producto");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
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
				String codigo = txtCodigo.getText().trim();
				
				if(codigo.length() >= 6) {
					lblError.setText("Solo se permiten 6 caracteres.");
					e.consume();
				}
				
				else {
					lblError.setText("");
				}
			}
		});
		txtCodigo.setColumns(10);
		txtCodigo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtCodigo.setBounds(125, 15, 100, 30);
		contentPanel.add(txtCodigo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 15, 510, 300);
		contentPanel.add(scrollPane);
		
		tblProducto = new JTable();
		tblProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				reProd();
			}
		});
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reProd();
			}
		});
		tblProducto.setFillsViewportHeight(true);
		tblProducto.setGridColor(SystemColor.controlShadow);
		tblProducto.setForeground(SystemColor.controlDkShadow);
		tblProducto.getTableHeader().setBackground(new Color(32, 155, 253));
		tblProducto.getTableHeader().setForeground(new Color(255, 255, 255));
		tblProducto.setSelectionBackground(new Color(204, 255, 204));
		tblProducto.setSelectionForeground(new Color(0, 0, 0));
		tblProducto.setBackground(new Color(255, 255, 204));
		tblProducto.setFont(new Font("Candara Light", Font.PLAIN, 13));
		tblProducto.setRowHeight(20);
		scrollPane.setViewportView(tblProducto);

		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Marca");
		modelo.addColumn("Lote");
		modelo.addColumn("Precio");
		tblProducto.setModel(modelo);
		tblProducto.setVisible(true);
		
		reCrear("call selectProducto()");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(15, 56, 210, 30);
		contentPanel.add(lblError);
		
		lblProducto = new JLabel("Producto :");
		lblProducto.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblProducto.setBounds(15, 97, 100, 30);
		contentPanel.add(lblProducto);
		
		lblProductoR = new JLabel("");
		lblProductoR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblProductoR.setBounds(125, 97, 100, 30);
		contentPanel.add(lblProductoR);
		
		lblMarca = new JLabel("Marca :");
		lblMarca.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblMarca.setBounds(15, 138, 100, 30);
		contentPanel.add(lblMarca);
		
		lblMarcaR = new JLabel("");
		lblMarcaR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblMarcaR.setBounds(125, 138, 100, 30);
		contentPanel.add(lblMarcaR);
		
		lblLote = new JLabel("Lote :");
		lblLote.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblLote.setBounds(15, 179, 100, 30);
		contentPanel.add(lblLote);
		
		lblLoteR = new JLabel("");
		lblLoteR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblLoteR.setBounds(125, 179, 100, 30);
		contentPanel.add(lblLoteR);
		
		lblPrecio = new JLabel("Precio :");
		lblPrecio.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPrecio.setBounds(15, 220, 100, 30);
		contentPanel.add(lblPrecio);
		
		lblPrecioR = new JLabel("");
		lblPrecioR.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPrecioR.setBounds(125, 220, 100, 30);
		contentPanel.add(lblPrecioR);
		
		btnBuscar = new JButton("Consultar");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnBuscar.setBackground(new Color(204, 255, 255));
		btnBuscar.setBounds(10, 285, 100, 30);
		contentPanel.add(btnBuscar);
		
		btnListar = new JButton("Listar");
		btnListar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText().trim();
				
				if(txtCodigo.getText().isEmpty()) {
					reCrear("call selectProducto()");
					lblError.setText("Sin producto a listar");
				}
				
				else {
					reCrear("select * from producto where codigo = " + codigo);
					lblError.setText("Producto Encontrado");
				}
				
				if(modelo.getRowCount() == 0)
					lblError.setText("Este producto no Existe");
			}
		});
		btnListar.setForeground(Color.WHITE);
		btnListar.setFont(new Font("Candara Light", Font.BOLD, 13));
		btnListar.setBackground(new Color(102, 153, 255));
		btnListar.setBounds(125, 285, 100, 30);
		contentPanel.add(btnListar);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setIcon(new ImageIcon(ConsProducto.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setBounds(235, 285, 40, 30);
		contentPanel.add(btnDarkMode);
	}
	
	void soloLetras(KeyEvent e) {
		char letra = e.getKeyChar();
		
		if(Character.isLetter(letra)) {
			lblError.setText("");
		}
		
		else {
			lblError.setText("Escribe solo letras.");
			e.consume();
			getToolkit().beep();
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
	
	void seeProd(int fila) {
		txtCodigo.setText("" + modelo.getValueAt(fila, 0));
		lblProductoR.setText("" + modelo.getValueAt(fila, 1));
		lblMarcaR.setText("" + modelo.getValueAt(fila, 2));
		lblLoteR.setText("" + modelo.getValueAt(fila, 3));
		lblPrecioR.setText("" + modelo.getValueAt(fila, 4));
	}
	
	void reProd() {
		int fila;
		fila = tblProducto.getSelectedRow();
		
		if(fila == -1) {
			return;
		}
		
		else {
			seeProd(fila);
		}
	}
	
	@SuppressWarnings("static-access")
	void reCrear(String s) {
		modelo.setRowCount(0);
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
	

	public void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
