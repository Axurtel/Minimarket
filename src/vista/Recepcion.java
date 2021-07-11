package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import sql.conexion;
import mod.darkRecepcion;
import modelo.recepcion;
import superAdmin.Asistencia;
import superAdmin.ConfigAdmin;
import superAdmin.SystemConsole;
import controlador.consultasRecepcion;
import interfaces.intRecepcion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ComboBoxModel;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class Recepcion extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public final JPanel contentPanel = new JPanel();
	public DefaultTableModel modelo = new DefaultTableModel();
	DefaultComboBoxModel caja = new DefaultComboBoxModel();
	DefaultComboBoxModel caja2 = new DefaultComboBoxModel();
	public JTable tblRecepcion;
	public JSeparator spt1;
	public JSeparator spt2;
	public JScrollPane scp;
	public JTextField txtMotivo;
	public JTextField txtEmisor;
	public JTextField txtCargo;
	public JLabel lblSentencia;
	public JLabel lblEmisor;
	public JLabel lblCargo;
	public JLabel lblFecha;
	public JLabel lblMotivo;
	public JLabel lblAccion;
	public JDateChooser dtcRecepcion;
	public JButton btnDeleteSupreme;
	public JButton btnDarkMode;
	public JButton btnSaveFile;
	public JButton btnPdf;
	public JButton btnConfiguracion;
	public JButton btnAsistencia;
	public JButton btnProcesar;
	public JComboBox cboAccion;
	public JComboBox cboSentencia;
	public JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			recepcion rcp = new recepcion();
			consultasRecepcion conRcp = new consultasRecepcion();
			Recepcion dialog = new Recepcion();
						
			intRecepcion ctrl = new intRecepcion(rcp, conRcp, dialog);
			darkRecepcion drp = new darkRecepcion(dialog);
			
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
	
	public Recepcion() {
		setResizable(false);
		setTitle("Mantenimiento | | Recepci\u00F3n ~ Modo SuperUsuario");
		setBounds(100, 100, 970, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		spt1 = new JSeparator();
		spt1.setOrientation(SwingConstants.VERTICAL);
		spt1.setBounds(120, 10, 11, 385);
		contentPanel.add(spt1);
		
		scp = new JScrollPane();
		scp.setBounds(141, 10, 670, 180);
		contentPanel.add(scp);
		
		modelo.addColumn("Sentencia");
		modelo.addColumn("Emisor");
		modelo.addColumn("Cargo");
		modelo.addColumn("Razón - Motivo");
		modelo.addColumn("Fecha");
		
		tblRecepcion = new JTable(modelo);
		tblRecepcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				reRecep();
			}
		});
		tblRecepcion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reRecep();
			}
		});
		tblRecepcion.setRowHeight(20);
		tblRecepcion.setFillsViewportHeight(true);
		tblRecepcion.setGridColor(SystemColor.controlShadow);
		tblRecepcion.setSelectionBackground(new Color(204, 255, 204));
		tblRecepcion.setSelectionForeground(new Color(0, 0, 0));
		tblRecepcion.getTableHeader().setBackground(new Color(32, 155, 253));
		tblRecepcion.getTableHeader().setForeground(new Color(255, 255, 255));
		tblRecepcion.setForeground(SystemColor.controlDkShadow);
		tblRecepcion.getTableHeader().setOpaque(false);
		tblRecepcion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		scp.setViewportView(tblRecepcion);
		
		reCrear("select * from recepcion");
		
		lblSentencia = new JLabel("Sentencia :");
		lblSentencia.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblSentencia.setBounds(141, 210, 100, 20);
		contentPanel.add(lblSentencia);
		
		lblEmisor = new JLabel("Emisor :");
		lblEmisor.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblEmisor.setBounds(141, 250, 100, 20);
		contentPanel.add(lblEmisor);
		
		txtEmisor = new JTextField();
		txtEmisor.setEditable(false);
		txtEmisor.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtEmisor.setColumns(10);
		txtEmisor.setBounds(251, 244, 120, 30);
		contentPanel.add(txtEmisor);
		
		lblCargo = new JLabel("Cargo :");
		lblCargo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblCargo.setBounds(141, 290, 100, 20);
		contentPanel.add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		txtCargo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtCargo.setColumns(10);
		txtCargo.setBounds(251, 284, 120, 30);
		contentPanel.add(txtCargo);
		
		lblFecha = new JLabel("Fecha :");
		lblFecha.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblFecha.setBounds(141, 330, 100, 20);
		contentPanel.add(lblFecha);
		
		dtcRecepcion = new JDateChooser();
		dtcRecepcion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		dtcRecepcion.setBounds(251, 324, 120, 30);
		contentPanel.add(dtcRecepcion);
		
		lblMotivo = new JLabel("Motivo :");
		lblMotivo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblMotivo.setBounds(460, 210, 100, 20);
		contentPanel.add(lblMotivo);
		
		txtMotivo = new JTextField();
		txtMotivo.setFont(new Font("Candara Light", Font.PLAIN, 13));
		txtMotivo.setToolTipText("");
		txtMotivo.setBounds(460, 240, 300, 30);
		contentPanel.add(txtMotivo);
		txtMotivo.setColumns(10);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//#Ejecutar el programa desde el menú
				try {
					Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("reports/registroAuditoria.pdf"));
					doc.open();
					
					Paragraph titulo = new Paragraph();
					titulo.setAlignment(Paragraph.ALIGN_CENTER);
					titulo.setFont(FontFactory.getFont("Candara", 30, Font.PLAIN,BaseColor.CYAN));
					titulo.add("Regístro de Auditoría\n\n");
					
					doc.add(titulo);
					
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(95);
					
					tabla.addCell("Sentencia");
					tabla.addCell("Emisor");
					tabla.addCell("Cargo");
					tabla.addCell("Razón - Motivo");
					tabla.addCell("Fecha");
					
					for(int i = 0; i < tblRecepcion.getRowCount(); i++) {
						tabla.addCell(String.valueOf(tblRecepcion.getValueAt(i, 0)));
						tabla.addCell(String.valueOf(tblRecepcion.getValueAt(i, 1)));
						tabla.addCell(String.valueOf(tblRecepcion.getValueAt(i, 2)));
						tabla.addCell(String.valueOf(tblRecepcion.getValueAt(i, 3)));
						tabla.addCell(String.valueOf(tblRecepcion.getValueAt(i, 4)));
					}
					
					doc.add(tabla);
					doc.close();
					
					Desktop.getDesktop().open(new File("reports/registroAuditoria.pdf"));
					SystemConsole.txtSConsole.append("Pdf generado\n");
				}
				
				catch(FileNotFoundException ex) {
					alerta("El archivo a guardar está siendo utilizado desde otro programa, por favor, termine el proceso para ejecutar.");
				}
				
				catch(NullPointerException ex) {
					System.out.println("Ejecute el programa desde el Menú, es necesario el uso de consola");
				}
				
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnPdf.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/pdf.png")));
		btnPdf.setBackground(SystemColor.controlHighlight);
		btnPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPdf.setBounds(35, 50, 50, 40);
		contentPanel.add(btnPdf);
		
		btnSaveFile = new JButton("");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {			        
		        	JFileChooser fileChooser = new JFileChooser();
		        	SystemConsole.txtSConsole.append("Ventana de guardado abierto.\n");
			        fileChooser.setDialogTitle("Guardar Archivo : Especifica la ruta y nombre con su extensión");   
			         
			        int userSelection = fileChooser.showSaveDialog(fileChooser);
			         
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToSave = fileChooser.getSelectedFile();
			            SystemConsole.txtSConsole.append("Guardado en: " + fileToSave.getAbsolutePath() + "\n");
			            SystemConsole.txtSConsole.append("Si no ha colocado extensión, deberá abrirlo manualmente. (right click -> open with)" + "\n");
			            
			            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));

			            for (int i = 0 ; i < tblRecepcion.getRowCount(); i++) { //row
			            	
			                for(int j = 0 ; j < tblRecepcion.getColumnCount(); j++) { //column
			                	
			                    bfw.write((String)(tblRecepcion.getValueAt(i, j)));
			                    
			                    if (j < tblRecepcion.getColumnCount() -1) {
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
		btnSaveFile.setBackground(SystemColor.controlHighlight);
		btnSaveFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveFile.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/document.png")));
		btnSaveFile.setBounds(35, 130, 50, 40);
		contentPanel.add(btnSaveFile);
		
		btnDeleteSupreme = new JButton("");
		btnDeleteSupreme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultasRecepcion crp = new consultasRecepcion();
				crp.spDelete();
				reCrear("select * from recepcion");
			}
		});
		btnDeleteSupreme.setBackground(SystemColor.controlHighlight);
		btnDeleteSupreme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteSupreme.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/pluma.png")));
		btnDeleteSupreme.setBounds(35, 210, 50, 40);
		contentPanel.add(btnDeleteSupreme);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setBounds(35, 290, 50, 40);
		contentPanel.add(btnDarkMode);
		
		spt2 = new JSeparator();
		spt2.setOrientation(SwingConstants.VERTICAL);
		spt2.setBounds(831, 10, 11, 385);
		contentPanel.add(spt2);
		
		btnAsistencia = new JButton("");
		btnAsistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Asistencia asis = new Asistencia();	
					asis.setLocationRelativeTo(null);
					asis.setVisible(true);
				}
				
				catch(Exception ex) {
					ex.getMessage();
				}
			}
		});
		btnAsistencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAsistencia.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/asistencia.png")));
		btnAsistencia.setBackground(SystemColor.controlHighlight);
		btnAsistencia.setBounds(865, 210, 50, 40);
		contentPanel.add(btnAsistencia);
		
		btnConfiguracion = new JButton("");
		btnConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SystemConsole.txtSConsole.append("Configuración de clave abierta. \n");
					ConfigAdmin obj = new ConfigAdmin();
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch(NullPointerException ex) {
		        	System.out.println("Ejecute el programa desde el Menú, es necesario el uso de consola");
		        }
				
				catch(Exception ex) {
					ex.getMessage();
				}
			}
		});
		btnConfiguracion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfiguracion.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/configuracion.png")));
		btnConfiguracion.setBackground(SystemColor.controlHighlight);
		btnConfiguracion.setBounds(865, 130, 50, 40);
		contentPanel.add(btnConfiguracion);
		
		lblAccion = new JLabel("Acci\u00F3n :");
		lblAccion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblAccion.setBounds(460, 290, 80, 20);
		contentPanel.add(lblAccion);
		
		caja.addElement("Insertar");
		caja.addElement("Eliminar");
		
		cboAccion = new JComboBox(caja);
		cboAccion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		cboAccion.setBounds(550, 283, 150, 30);		
		contentPanel.add(cboAccion);
		
		btnProcesar = new JButton("");
		btnProcesar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProcesar.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/procesar.png")));
		btnProcesar.setBackground(SystemColor.controlHighlight);
		btnProcesar.setBounds(719, 283, 40, 30);
		contentPanel.add(btnProcesar);
		
		caja2.addElement("X3-FG4-0WM");
		caja2.addElement("L1-35T-NXZ");
		caja2.addElement("1R-POO-301");
		caja2.addElement("7V-MVC-2B6");
		caja2.addElement("KR-9E0-AWX");
		caja2.addElement("OP-4VL-AAC");
		
		cboSentencia = new JComboBox(caja2);
		cboSentencia.setFont(new Font("Candara Light", Font.PLAIN, 13));
		cboSentencia.setBounds(251, 200, 120, 30);
		contentPanel.add(cboSentencia);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblError.setForeground(Color.RED);
		lblError.setBounds(460, 332, 300, 20);
		contentPanel.add(lblError);

	}
	
	public void alerta(String s) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, s,"Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	public void informacion(String s) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, s,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
	
	void seeRecep (int fila) {
		cboSentencia.setSelectedItem((String) modelo.getValueAt(fila, 0));
		txtEmisor.setText((String) modelo.getValueAt(fila, 1));
		txtCargo.setText((String) modelo.getValueAt(fila, 2));
		txtMotivo.setText((String) modelo.getValueAt(fila, 3));
		dtcRecepcion.setDate(Date.valueOf((String) modelo.getValueAt(fila, 4)));
	}
	
	void reRecep () {
		int fila;
		fila = tblRecepcion.getSelectedRow();
		
		if(fila == -1) {
			return;
		}
		
		else {
			seeRecep(fila);
		}
	}
	
	@SuppressWarnings("static-access")
	public void reCrear(String s) {
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
}
