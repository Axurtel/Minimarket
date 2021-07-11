package vista;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import controlador.consultasCliente;
import interfaces.intCliente;
import mod.darkClient;
import modelo.cliente;
import sql.conexion;
import superAdmin.SystemConsole;

public class MantCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public final JPanel contentPanel = new JPanel();
	public DefaultTableModel modelo = new DefaultTableModel();
	public JLabel lblid;
	public JLabel lblNombre;
	public JTextField txtid;
	public JTextField txtApellido;
	public JTextField txtNombre;
	public JTextField txtDni;
	public JTextField txtDireccion;
	public JLabel lblApellido;
	public JLabel lblDni;
	public JLabel lblDireccion;
	public JScrollPane scrollPane;
	public JTable tblCliente;
	public JLabel lblError;
	public JButton btnAgregar;
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnDarkMode;
	public JButton btnClear;
	public JButton btnAdmin;
	public JButton btnDeleteSupreme;
	public JButton btnSaveFile;
	public JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			cliente cli = new cliente();
			consultasCliente consCli = new consultasCliente();
			MantCliente dialog = new MantCliente();
			
			intCliente ctrl = new intCliente(cli, consCli, dialog);
			darkClient dc = new darkClient(dialog);
			
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * Create the dialog.
	 */
	public MantCliente() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 900, 410);
		setTitle("Mantenimiento - Cliente");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblid = new JLabel("Identification :");
		lblid.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblid.setBounds(15, 15, 100, 25);
		contentPanel.add(lblid);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblNombre.setBounds(15, 100, 100, 25);
		contentPanel.add(lblNombre);
		
		lblApellido = new JLabel("Apellido :");
		lblApellido.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblApellido.setBounds(15, 145, 100, 25);
		contentPanel.add(lblApellido);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		txtid.setBounds(125, 14, 100, 25);
		contentPanel.add(txtid);
		txtid.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(125, 101, 100, 25);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		txtApellido.setColumns(10);
		txtApellido.setBounds(125, 146, 100, 25);
		contentPanel.add(txtApellido);
		
		lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblDni.setBounds(15, 57, 100, 25);
		contentPanel.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
				
				if(txtDni.getText().trim().length() >= 8) {
					e.consume();
					getToolkit().beep();
					informacion("El Dni no contiene más de ocho digitos.");
				}
			}
		});
		txtDni.setBounds(125, 56, 100, 25);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);
		
		lblDireccion = new JLabel("Direcci\u00F3n :");
		lblDireccion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblDireccion.setBounds(15, 190, 100, 25);
		contentPanel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Candara Light", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(125, 191, 100, 25);
		contentPanel.add(txtDireccion);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 14, 580, 340);
		contentPanel.add(scrollPane);
		
		modelo.addColumn("id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Dirección");
		modelo.addColumn("Dni");

		tblCliente = new JTable(modelo);		
		tblCliente.setFillsViewportHeight(true);
		tblCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				reClient();
			}
		});
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		scrollPane.setViewportView(tblCliente);

		reCrear("select * from cliente");
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(0, 0, 0));
		btnAgregar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnAgregar.setBackground(new Color(204, 255, 255));
		btnAgregar.setBounds(15, 285, 100, 30);
		contentPanel.add(btnAgregar);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(15, 230, 210, 30);
		contentPanel.add(lblError);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(0, 0, 0));
		btnEliminar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(204, 255, 255));
		btnEliminar.setBounds(125, 285, 100, 30);
		contentPanel.add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 0, 0));
		btnModificar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnModificar.setBackground(new Color(204, 255, 255));
		btnModificar.setBounds(15, 326, 100, 30);
		contentPanel.add(btnModificar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnBuscar.setBackground(new Color(204, 255, 255));
		btnBuscar.setBounds(125, 326, 100, 30);
		contentPanel.add(btnBuscar);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setIcon(new ImageIcon(MantCliente.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setForeground(Color.BLACK);
		btnDarkMode.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setBounds(235, 324, 40, 30);
		contentPanel.add(btnDarkMode);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 366, 610, 130);
		contentPanel.add(scrollPane_1);
		
		btnClear = new JButton("");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtApellido.setText(null);
				txtDireccion.setText(null);
				txtDni.setText(null);
				txtid.setText(null);
				txtNombre.setText(null);
			}
		});
		btnClear.setIcon(new ImageIcon(MantCliente.class.getResource("/imagenes/eraser.png")));
		btnClear.setBackground(SystemColor.controlHighlight);
		btnClear.setBounds(235, 285, 40, 30);
		contentPanel.add(btnClear);
		
		btnDeleteSupreme = new JButton("");
		btnDeleteSupreme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultasCliente cc = new consultasCliente();
				SystemConsole.txtSConsole.append("You truncate the table Cliente." + "\n");
				cc.spDelete();
				reCrear("select * from cliente");
			}
		});
		btnDeleteSupreme.setIcon(new ImageIcon(MantCliente.class.getResource("/imagenes/pluma.png")));
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
			            SystemConsole.txtSConsole.append("Si no ha colocado extensión, deberá abrirlo manualmente. (right click -> open with" + "\n");
			            
			            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));

			            for (int i = 0 ; i < tblCliente.getRowCount(); i++) { //row
			            	
			                for(int j = 0 ; j < tblCliente.getColumnCount(); j++) { //column
			                	
			                    bfw.write((String)(tblCliente.getValueAt(i,j)));
			                    
			                    if (j < tblCliente.getColumnCount() -1) {
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
			}			
		});
		btnSaveFile.setIcon(new ImageIcon(MantCliente.class.getResource("/imagenes/document.png")));
		btnSaveFile.setBackground(SystemColor.controlHighlight);
		btnSaveFile.setBounds(235, 97, 40, 30);
		
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
		btnAdmin.setIcon(new ImageIcon(MantCliente.class.getResource("/imagenes/admin.png")));
		btnAdmin.setForeground(Color.BLACK);
		btnAdmin.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		btnAdmin.setBackground(SystemColor.controlHighlight);
		btnAdmin.setBounds(235, 15, 40, 30);
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
	
	void seeClient(int fila) {		
		txtid.setText("" + modelo.getValueAt(fila, 0));
		txtNombre.setText("" + modelo.getValueAt(fila, 1));
		txtApellido.setText("" + modelo.getValueAt(fila, 2));
		txtDireccion.setText("" + modelo.getValueAt(fila, 3));
		txtDni.setText("" + modelo.getValueAt(fila, 4)); 
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
		
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
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
