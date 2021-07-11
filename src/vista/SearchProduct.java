package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Cursor;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.conexion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SearchProduct extends JDialog {

	DefaultComboBoxModel caja = new DefaultComboBoxModel();
	DefaultTableModel modelo = new DefaultTableModel();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cboLote;
	private JLabel lblNewLabel;
	private JButton btnListar;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JTable tblLote;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchProduct dialog = new SearchProduct();
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
	public SearchProduct() {
		setTitle("Reporte Ventas | | Lista Productos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 660, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Lote :");
		lblNewLabel.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblNewLabel.setBounds(15, 19, 90, 20);
		contentPanel.add(lblNewLabel);
		
		caja.addElement("Todos los lotes");
		caja.addElement("Lote 1 :: Útiles Escolares");
		caja.addElement("Lote 2 :: Alimentos portadores de Calorías");
		caja.addElement("Lote 3 :: Elementos Sanitarios");
		caja.addElement("Lote 4 :: Derivados de Casa");
		caja.addElement("Lote 5 :: Sanidad");
		caja.addElement("Lote 6 :: Derivados Cualquiera");
		
		cboLote = new JComboBox(caja);
		cboLote.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboLote.setFont(new Font("Consolas", Font.PLAIN, 13));
		cboLote.setBounds(115, 15, 330, 25);
		contentPanel.add(cboLote);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valor =  cboLote.getSelectedIndex();
				
				if (valor == 0)
					reCrear("call selectProducto()");
				
				else 
					reCrear("select * from producto where lote = " + valor);
				
			}
		});
		btnListar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnListar.setBackground(SystemColor.controlHighlight);
		btnListar.setIcon(new ImageIcon(SearchProduct.class.getResource("/imagenes/search.png")));
		btnListar.setBounds(480, 12, 100, 30);
		contentPanel.add(btnListar);
		
		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(15, 54, 615, 2);
		contentPanel.add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 66, 615, 230);
		contentPanel.add(scrollPane);
		
		modelo.addColumn("Código");
		modelo.addColumn("Producto");
		modelo.addColumn("Marca");
		modelo.addColumn("Lote");
		modelo.addColumn("Precio (S/.)");
		
		tblLote = new JTable(modelo);
		tblLote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila;
				fila = tblLote.getSelectedRow();
				int lote = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 3)));
				cboLote.setSelectedIndex(lote);
			}
		});
		tblLote.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblLote);
		
		btnAceptar = new JButton("");
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setIcon(new ImageIcon(SearchProduct.class.getResource("/imagenes/procesar.png")));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reProd();
				}
				
				catch(Exception e1) {
					informacion("Ejecutar acción desde Transacción");
				}
			}
		});
		btnAceptar.setBackground(SystemColor.controlHighlight);
		btnAceptar.setBounds(590, 12, 40, 30);
		contentPanel.add(btnAceptar);
		
		reCrear("call selectProducto()");
	}
	
	void seeProd(int fila) {
		TransaccionCaja.txtCodigo.setText(String.valueOf(modelo.getValueAt(fila, 0)));
		TransaccionCaja.txtProducto.setText(String.valueOf(modelo.getValueAt(fila, 1)));
		TransaccionCaja.txtPrecio.setText(String.valueOf(modelo.getValueAt(fila, 4)));
	}
	
	void reProd() {
		int fila;
		fila = tblLote.getSelectedRow();
		
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
	
	void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
