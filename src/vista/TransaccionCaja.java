package vista;

import java.awt.BorderLayout;

import mod.darkTransact;
import modelo.venta;
import sql.conexion;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controlador.consultasVenta;
import interfaces.intVenta;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class TransaccionCaja extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public DefaultTableModel modelo = new DefaultTableModel();
	public final JPanel contentPanel = new JPanel();
	DecimalFormat df = new DecimalFormat("#.00");
	public JLabel lblCodigo;
	public JLabel lblTitulo;
	public JLabel lblCantidad;
	public JLabel lblProducto;
	public JTable tblProducto;
	public JLabel lblPrecio;
	public JLabel lblError;
	public JLabel lblInfo;
	public JTextField txtTotal;
	public JScrollPane scrollPane;
	public static JTextField txtCodigo;
	public static JTextField txtPrecio;
	public static JTextField txtProducto;
	public JTextField txtCantidad;
	public JButton btnImprimir;
	public JButton btnAgregar;
	public JButton btnBuscar;
	public JButton btnEliminar;
	public JButton btnDarkMode;
	public JSeparator separator;
	public JSeparator separator_1;
	public JButton btnProducto;
	public JPanel panel;
	public JButton btnClear;
	public JButton btnTruncate;
	public JButton btnSaveFile;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			venta vent = new venta();
			consultasVenta conVen = new consultasVenta();
			TransaccionCaja dialog = new TransaccionCaja();
			
			intVenta ctrl = new intVenta(vent, conVen, dialog);
			darkTransact dtt = new darkTransact(dialog);
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

	public TransaccionCaja() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 800, 530);
		setTitle("Transacci\u00F3n - Caja");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblCodigo = new JLabel("Codigo :");
		lblCodigo.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblCodigo.setBounds(15, 15, 100, 30);
		contentPanel.add(lblCodigo);
		
		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblCantidad.setBounds(15, 60, 100, 30);
		contentPanel.add(lblCantidad);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
				
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
		txtCodigo.setBounds(125, 14, 100, 30);
		contentPanel.add(txtCodigo);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
				
				if(txtCantidad.getText().trim().length() >= 3) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(125, 59, 100, 30);
		contentPanel.add(txtCantidad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 210, 590, 255);
		contentPanel.add(scrollPane);		
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		
		tblProducto = new JTable(modelo);
		tblProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				reVent();
			}
		});
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reVent();
			}
		});
		tblProducto.setFont(new Font("Candara Light", Font.PLAIN, 13));
		tblProducto.setRowHeight(20);
		tblProducto.getTableHeader().setBackground(new Color(32, 155, 253));
		tblProducto.getTableHeader().setForeground(new Color(255, 255, 255));
		tblProducto.getTableHeader().setOpaque(false);
		tblProducto.setForeground(SystemColor.controlDkShadow);
		tblProducto.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProducto);
		
		reCrear("select * from venta");
		
		lblProducto = new JLabel("Producto :");
		lblProducto.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblProducto.setBounds(352, 16, 100, 30);
		contentPanel.add(lblProducto);
		
		lblPrecio = new JLabel("Precio :");
		lblPrecio.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblPrecio.setBounds(352, 61, 100, 30);
		contentPanel.add(lblPrecio);
		
		txtProducto = new JTextField();
		txtProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		txtProducto.setColumns(10);
		txtProducto.setBounds(462, 15, 100, 30);
		contentPanel.add(txtProducto);
				
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(462, 60, 100, 30);
		contentPanel.add(txtPrecio);
		
		txtProducto.setEditable(false);
		txtPrecio.setEditable(false);
		
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		separator.setBounds(615, 15, 2, 450);
		contentPanel.add(separator);
		
		btnAgregar = new JButton("");
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnAgregar.setBackground(SystemColor.inactiveCaption);
		btnAgregar.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/agregar.png")));
		btnAgregar.setBounds(640, 15, 50, 30);
		contentPanel.add(btnAgregar);
		
		btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(Color.LIGHT_GRAY);
				lblInfo.setText("Buscar");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(SystemColor.inactiveCaption);
				lblInfo.setText("");
			}
		});
		btnBuscar.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/buscar.png")));
		btnBuscar.setBackground(SystemColor.inactiveCaption);
		btnBuscar.setBounds(710, 15, 50, 30);
		contentPanel.add(btnBuscar);
		
		btnEliminar = new JButton("");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(Color.LIGHT_GRAY);
				lblInfo.setText("Eliminar");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(SystemColor.inactiveCaption);
				lblInfo.setText("");
			}
		});
		btnEliminar.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setBackground(SystemColor.inactiveCaption);
		btnEliminar.setBounds(640, 60, 50, 30);
		contentPanel.add(btnEliminar);
		
		lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblInfo.setForeground(Color.RED);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(640, 110, 120, 30);
		contentPanel.add(lblInfo);
		
		lblTitulo = new JLabel("Total a Pagar : (S/.)");
		lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(0, 0, 153));
		lblTitulo.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblTitulo.setBounds(640, 150, 120, 30);
		contentPanel.add(lblTitulo);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(640, 191, 120, 2);
		contentPanel.add(separator_1);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setColumns(10);
		txtTotal.setBounds(650, 204, 100, 30);
		contentPanel.add(txtTotal);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Corbel Light", Font.PLAIN, 13));
		lblError.setBounds(15, 100, 210, 30);
		contentPanel.add(lblError);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setBounds(676, 260, 40, 30);
		contentPanel.add(btnDarkMode);
		
		btnProducto = new JButton("");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchProduct obj = new SearchProduct();
				obj.setLocationRelativeTo(null);
				obj.setVisible(true);
			}
		});
		btnProducto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProducto.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/search.png")));
		btnProducto.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnProducto.setBackground(SystemColor.controlHighlight);
		btnProducto.setBounds(241, 15, 50, 30);
		contentPanel.add(btnProducto);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(255, 255, 255), 
				new Color(160, 160, 160)), "Buttons", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel.setBounds(15, 150, 590, 50);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		btnClear = new JButton("");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCantidad.setText(null);
				txtCodigo.setText(null);
				txtPrecio.setText(null);
				txtProducto.setText(null);
				txtTotal.setText(null);
			}
		});
		btnClear.setBackground(SystemColor.controlHighlight);
		btnClear.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/eraser.png")));
		btnClear.setBounds(218, 12, 40, 30);
		panel.add(btnClear);
		
		btnTruncate = new JButton("");
		btnTruncate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultasVenta ccv = new consultasVenta();
				ccv.spDelete();
				reCrear("select * from venta");
			}
		});
		btnTruncate.setBackground(SystemColor.controlHighlight);
		btnTruncate.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/pluma.png")));
		btnTruncate.setBounds(268, 12, 40, 30);
		panel.add(btnTruncate);
		
		btnSaveFile = new JButton("");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {			        
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("Guardar Archivo : Especifica la ruta y nombre con su extensión");   
			         
			        int userSelection = fileChooser.showSaveDialog(fileChooser);
			         
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToSave = fileChooser.getSelectedFile();
			            
			            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));

			            for (int i = 0 ; i < tblProducto.getRowCount(); i++) { //row
			            	
			                for(int j = 0 ; j < tblProducto.getColumnCount(); j++) { //column
			                	
			                    bfw.write((String)(tblProducto.getValueAt(i,j)));
			                    
			                    if (j < tblProducto.getColumnCount() -1) {
			                        bfw.write(",");
			                    }
			                }
			                bfw.newLine();
			            }
			            bfw.close();
			            Desktop.getDesktop().open(new File(fileToSave.getAbsolutePath()));
			        }
		        } 
		        
		        catch (IOException ex) {
		        	ex.getMessage();
		        } 
			}
		});
		btnSaveFile.setBackground(SystemColor.controlHighlight);
		btnSaveFile.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/document.png")));
		btnSaveFile.setBounds(318, 12, 40, 30);
		panel.add(btnSaveFile);
		
		btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(TransaccionCaja.class.getResource("/imagenes/pdf.png")));
		btnImprimir.setBounds(368, 12, 40, 30);
		panel.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("reports/reporteCaja.pdf"));
					doc.open();

					Paragraph titulo = new Paragraph();
					titulo.setAlignment(Paragraph.ALIGN_CENTER);
					titulo.setFont(FontFactory.getFont("Candara", 30, Font.PLAIN,BaseColor.CYAN));
					titulo.add("Reporte de Ventas\n\n");
					
					doc.add(titulo);
										
					PdfPTable tabla = new PdfPTable(4);
					tabla.setWidthPercentage(90);
					
					tabla.addCell("Código");
					tabla.addCell("Nombre");
					tabla.addCell("Cantidad");
					tabla.addCell("Precio");

					for (int i = 0; i < tblProducto.getRowCount(); i++) {
						tabla.addCell(String.valueOf(tblProducto.getValueAt(i, 0)));
						tabla.addCell(String.valueOf(tblProducto.getValueAt(i, 1)));
						tabla.addCell(String.valueOf(tblProducto.getValueAt(i, 2)));
						tabla.addCell(String.valueOf(tblProducto.getValueAt(i, 3)));
					}					
					
					doc.add(tabla);
					doc.close();
					
					Desktop.getDesktop().open(new File("D:/reporteCaja.pdf"));
					System.out.println("pdf generado");
				}
				
				catch(Exception e1) {
					alerta(e1.getMessage() + "");
				}
				
			}
		});
		btnImprimir.setBackground(SystemColor.controlHighlight);
		btnImprimir.setFont(new Font("Candara Light", Font.PLAIN, 13));
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
				modelo.addRow(dato);			
			}
		} 

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void seeVent(int fila) {		
		txtCodigo.setText(String.valueOf(modelo.getValueAt(fila, 0)));
		txtProducto.setText(String.valueOf(modelo.getValueAt(fila, 1)));
		txtCantidad.setText(String.valueOf(modelo.getValueAt(fila, 2)));
		txtPrecio.setText(String.valueOf(modelo.getValueAt(fila, 3)));
		
		int cantidad = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 2)));
		double precio = Double.parseDouble(String.valueOf(modelo.getValueAt(fila, 3)));
		
		double mult = cantidad * precio;
		
		txtTotal.setText(String.valueOf(df.format(mult)));
	}
	
	void reVent() {
		int fila;
		fila = tblProducto.getSelectedRow();
		
		if(fila == -1) {
			return;
		}
		
		else {
			seeVent(fila);
		}
	}
	
	public void alerta(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	public void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
