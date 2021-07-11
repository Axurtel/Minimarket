package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.sql.*;

import controlador.consultasProducto;
import sql.conexion;
import superAdmin.SystemConsole;
import interfaces.intProducto;
import mod.darkProduct;
import modelo.producto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MantProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public final JPanel contentPanel = new JPanel();
	public JScrollPane scrollPane;
	public JTable tblProducto;
	
	public DefaultTableModel modelo= new DefaultTableModel();
	public JTextField txtProducto;
	public JTextField txtMarca;
	public JTextField txtLote;
	public JTextField txtPrecio;
	public JTextField txtCodigo;
	public JLabel lblLote;
	public JLabel lblMarca;
	public JLabel lblError;
	public JLabel lblPrecio;
	public JLabel lblCodigo;
	public JLabel lblProducto;
	public JButton btnAgregar;
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnDarkMode;
	public JButton btnClear;
	public JButton btnAdmin;
	public JButton btnDeleteSupreme;
	public JButton btnSaveFile;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			producto prod = new producto();
			consultasProducto conProd = new consultasProducto();			
			MantProducto dialog = new MantProducto();
			
			intProducto ctrl = new intProducto(prod, conProd, dialog);
			darkProduct dp = new darkProduct(dialog);
			ctrl.clear();
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
	public MantProducto() {		
		setModal(true);
		setResizable(false);
		setBackground(Color.WHITE);
		setBounds(100, 100, 900, 410);
		setTitle("Mantenimiento - Producto");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 14, 580, 340);
		contentPanel.add(scrollPane);
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Marca");
		modelo.addColumn("Lote");
		modelo.addColumn("Precio");
		
		tblProducto = new JTable(modelo);
		tblProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				reProd();
			}
		});
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reProd();
			}
		});
		tblProducto.setRowHeight(20);
		tblProducto.setGridColor(SystemColor.controlShadow);
		tblProducto.setSelectionForeground(new Color(0, 0, 0));
		tblProducto.setSelectionBackground(new Color(204, 255, 204));
		tblProducto.getTableHeader().setBackground(new Color(32, 155, 253));
		tblProducto.getTableHeader().setForeground(new Color(255, 255, 255));
		tblProducto.getTableHeader().setOpaque(false);
		tblProducto.setForeground(SystemColor.controlDkShadow);
		tblProducto.setBackground(new Color(255, 255, 204));
		tblProducto.setFont(new Font("Candara Light", Font.PLAIN, 13));
		tblProducto.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProducto);

		reCrear("call selectProducto()");
		
		lblProducto = new JLabel("Producto :");
		lblProducto.setForeground(SystemColor.controlDkShadow);
		lblProducto.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblProducto.setBounds(15, 57, 100, 25);
		contentPanel.add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.setSelectedTextColor(Color.WHITE);
		txtProducto.setSelectionColor(Color.GRAY);
		txtProducto.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
				
				if(txtProducto.getText().trim().length() >= 20)
					informacion("No coloques un producto muy largo!");
			}
		});
		txtProducto.setColumns(10);
		txtProducto.setBounds(125, 56, 100, 25);
		contentPanel.add(txtProducto);
		
		lblMarca = new JLabel("Marca :");
		lblMarca.setForeground(SystemColor.controlDkShadow);
		lblMarca.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblMarca.setBounds(15, 100, 100, 25);
		contentPanel.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setSelectedTextColor(Color.WHITE);
		txtMarca.setSelectionColor(Color.GRAY);
		txtMarca.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		txtMarca.setColumns(10);
		txtMarca.setBounds(125, 101, 100, 25);
		contentPanel.add(txtMarca);
		
		lblLote = new JLabel("Lote :");
		lblLote.setForeground(SystemColor.controlDkShadow);
		lblLote.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblLote.setBounds(15, 145, 100, 25);
		contentPanel.add(lblLote);
		
		txtLote = new JTextField();
		txtLote.setSelectedTextColor(Color.WHITE);
		txtLote.setSelectionColor(Color.GRAY);
		txtLote.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtLote.setColumns(10);
		txtLote.setBounds(125, 145, 100, 25);
		contentPanel.add(txtLote);
		
		lblPrecio = new JLabel("Precio :");
		lblPrecio.setForeground(SystemColor.controlDkShadow);
		lblPrecio.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblPrecio.setBounds(15, 189, 100, 25);
		contentPanel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setSelectedTextColor(Color.WHITE);
		txtPrecio.setSelectionColor(Color.GRAY);
		txtPrecio.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(125, 190, 100, 25);
		contentPanel.add(txtPrecio);
		
		lblCodigo = new JLabel("Codigo :");
		lblCodigo.setForeground(SystemColor.controlDkShadow);
		lblCodigo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblCodigo.setBounds(15, 14, 100, 25);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setSelectedTextColor(Color.WHITE);
		txtCodigo.setSelectionColor(Color.GRAY);
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
				
				String codigo = txtCodigo.getText().trim();
				
				if(codigo.length() >= 6) {
					informacion("No más de 6 dígitos");
					e.consume();	
				}
			}
		});
		txtCodigo.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(125, 14, 100, 25);
		contentPanel.add(txtCodigo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(0, 0, 0));
		btnAgregar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnAgregar.setBackground(new Color(204, 255, 255));
		btnAgregar.setBounds(15, 284, 100, 30);
		contentPanel.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(0, 0, 0));
		btnEliminar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(204, 255, 255));
		btnEliminar.setBounds(125, 284, 100, 30);
		contentPanel.add(btnEliminar);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(15, 225, 200, 30);
		contentPanel.add(lblError);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 0, 0));
		btnModificar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnModificar.setBackground(new Color(204, 255, 255));
		btnModificar.setBounds(15, 325, 100, 30);
		contentPanel.add(btnModificar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnBuscar.setBackground(new Color(204, 255, 255));
		btnBuscar.setBounds(125, 325, 100, 30);
		contentPanel.add(btnBuscar);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setIcon(new ImageIcon(MantProducto.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setBounds(235, 325, 40, 30);
		contentPanel.add(btnDarkMode);
		
		btnDeleteSupreme = new JButton("");
		btnDeleteSupreme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultasProducto ccp = new consultasProducto();
				SystemConsole.txtSConsole.append("You truncate the table Producto.\n");
				ccp.spDelete();
				reCrear("call selectProducto()");
			}
		});
		btnDeleteSupreme.setIcon(new ImageIcon(MantProducto.class.getResource("/imagenes/pluma.png")));
		btnDeleteSupreme.setBackground(SystemColor.controlHighlight);
		btnDeleteSupreme.setBounds(235, 57, 40, 30);
		
		btnSaveFile = new JButton("");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {			        
		        	SystemConsole.txtSConsole.append("Ventana de guardado abierto.\n");
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("Guardar Archivo : Especifica la ruta y nombre con su extensión");   
			         
			        int userSelection = fileChooser.showSaveDialog(fileChooser);
			         
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToSave = fileChooser.getSelectedFile();
			            SystemConsole.txtSConsole.append("Guardado en: " + fileToSave.getAbsolutePath() + "\n");
			            SystemConsole.txtSConsole.append("Si no ha colocado extensión, deberá abrirlo manualmente. (right click -> open with)" + "\n");
			            
			            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));

			            for (int i = 0 ; i < tblProducto.getRowCount(); i++) { //row
			            	
			                for(int j = 0 ; j < tblProducto.getColumnCount(); j++) { //column
			                	
			                    bfw.write((String)(tblProducto.getValueAt(i, j)));
			                    
			                    if (j < tblProducto.getColumnCount() -1) {
			                        bfw.write(",");
			                    }
			                }
			                bfw.newLine();
			            }
			            bfw.close();
			            SystemConsole.txtSConsole.append("Archivo abierto\n");
			            Desktop.getDesktop().open(new File(fileToSave.getAbsolutePath()));
			        }
		        } 
		        
		        catch (IOException ex) {
		        	SystemConsole.txtSConsole.append("ERROR : " + ex.getMessage());
		        } 
		        
		        catch(NullPointerException ex) {
		        	System.out.println("Ejecute el programa desde el Menú, es necesario el uso de consola");
		        }
			}
		});
		btnSaveFile.setIcon(new ImageIcon(MantProducto.class.getResource("/imagenes/document.png")));
		btnSaveFile.setBackground(SystemColor.controlHighlight);
		btnSaveFile.setBounds(235, 97, 40, 30);
		
		btnClear = new JButton("");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setBackground(SystemColor.controlHighlight);
		btnClear.setIcon(new ImageIcon(MantProducto.class.getResource("/imagenes/eraser.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setText(null);
				txtProducto.setText(null);
				txtMarca.setText(null);
				txtLote.setText(null);
				txtPrecio.setText(null);
			}
		});
		btnClear.setBounds(235, 284, 40, 30);
		contentPanel.add(btnClear);
		
		btnAdmin = new JButton("");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input1 = (String) JOptionPane.showInputDialog(null,
						"Por favor, ingrese su sentencia", "Modo SuperUsuario", JOptionPane.WARNING_MESSAGE);
				
				try {
					if(input1.equals(Menu.key)) {

						try {
							SystemConsole sysc = new SystemConsole();
							sysc.setVisible(true);
													
							contentPanel.add(btnDeleteSupreme);
							contentPanel.add(btnSaveFile);
						}
						
						catch (Exception ex) {
							return;
						}
					}
					
					else {
						informacion("No eres un super usuario.");
					}
				}
				
				catch (Exception ex) {
					return;
				}
			}
		});
		btnAdmin.setIcon(new ImageIcon(MantProducto.class.getResource("/imagenes/admin.png")));
		btnAdmin.setForeground(Color.BLACK);
		btnAdmin.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		btnAdmin.setBackground(SystemColor.controlHighlight);
		btnAdmin.setBounds(235, 14, 40, 30);
		contentPanel.add(btnAdmin);
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
		txtCodigo.setText((String) modelo.getValueAt(fila, 0));
		txtProducto.setText(String.valueOf(modelo.getValueAt(fila, 1)));
		txtMarca.setText(String.valueOf(modelo.getValueAt(fila, 2)));
		txtLote.setText(String.valueOf(modelo.getValueAt(fila, 3)));
		txtPrecio.setText(String.valueOf(modelo.getValueAt(fila, 4)));
	}
	
	void reProd() {
		int fila;
		fila = tblProducto.getSelectedRow();
		
		if(fila == -1)
			return;
		
		else
			seeProd(fila);
	}
	
	@SuppressWarnings("static-access")
	void reCrear(String s) {
		try {
			modelo.setRowCount(0);
			conexion con = new conexion();
			Connection conexion = con.getConnection();
			
			String sql = s;
			Statement st;
			
			st = conexion.createStatement();
			ResultSet result = st.executeQuery(sql);
			String[] dato = new String[5];
			
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
			System.out.println(e.getMessage());
		}
	}
	
	public void alerta(String s) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, s,"Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	public void informacion(String s) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, s,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
