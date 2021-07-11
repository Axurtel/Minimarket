package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.consultasCliente;
import controlador.consultasProducto;
import controlador.consultasVenta;
import interfaces.intCliente;
import interfaces.intConsCliente;
import interfaces.intConsProducto;
import interfaces.intProducto;
import interfaces.intVenta;
import mod.darkClient;
import mod.darkConsClient;
import mod.darkConsProd;
import mod.darkMenu;
import mod.darkProduct;
import mod.darkRecepcion;
import mod.darkReporte;
import mod.darkTienda;
import mod.darkTransact;
import modelo.cliente;
import modelo.producto;
import modelo.venta;
import superAdmin.SystemConsole;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String key = "KR-9E0-AWX";
	
	private JPanel contentPane;
	private JLabel lblFondo;
	public JMenuItem mntmCliente;
	public JMenuItem mntmProducto1;
	public JMenuItem mntmCliente1;
	public JMenuItem mntmVentas;
	public JMenuItem mntmCaja;
	public JMenuItem mntmSalir;
	public JMenuItem mntmProducto;
	public JMenuItem mntmAcercaTienda;
	public JMenuItem mntmRecepcion;
	public JMenu mnArchivo;
	public JMenu mnMantenimiento;
	public JMenu mnConsultar;
	public JMenu mnTransaccion;
	public JMenu mnReporte;
	public JMenuBar menuBar;
	public JMenu mnAcercaTienda;
	public JButton btnDarkMode;

	/**
	 * Launch the application.
	 */

	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					darkMenu dm = new darkMenu(frame);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					Login log = new Login();
					log.setDefaultCloseOperation(log.DO_NOTHING_ON_CLOSE);
					log.setLocationRelativeTo(null);
					log.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/imagenes/icono.png")));
		setTitle("Proyecto Ciclo III -- Lenguaje de Programación I ~ Java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 1096, 80);
		contentPane.add(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		mnArchivo.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/archivo.png")));
		mnArchivo.setFont(new Font("Candara Light", Font.PLAIN, 15));
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSalir.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setHorizontalAlignment(SwingConstants.CENTER);
		mnMantenimiento.setFont(new Font("Candara Light", Font.PLAIN, 15));
		mnMantenimiento.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/mantenimiento.png")));
		menuBar.add(mnMantenimiento);
		
		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCliente.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmCliente.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					cliente cli = new cliente();
					consultasCliente consCli = new consultasCliente();
					MantCliente obj = new MantCliente();
					
					intCliente ctrl = new intCliente(cli, consCli, obj);
					darkClient dc = new darkClient(obj);
					ctrl.clear();
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch(Exception ex) {
					return;
				}
			}
		});
		mnMantenimiento.add(mntmCliente);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmProducto.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmProducto.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					producto prod = new producto();
					consultasProducto conProd = new consultasProducto();
					MantProducto obj = new MantProducto();
					
					intProducto ctrl = new intProducto(prod, conProd, obj);
					darkProduct dp = new darkProduct(obj);
					ctrl.clear();
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch (Exception ex) {
					return;
				}
			}
		});
		mnMantenimiento.add(mntmProducto);
		
		mntmRecepcion = new JMenuItem("Recepcion");
		mntmRecepcion.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				String input1 = (String) JOptionPane.showInputDialog(null,
						"Por favor, ingrese su sentencia", "Modo SuperUsuario", JOptionPane.WARNING_MESSAGE);
				
				try {
					if(input1.equals(key)) {
						try {
							SystemConsole scs = new SystemConsole();
							scs.setVisible(true);
							
							Recepcion obj = new Recepcion();

							darkRecepcion drp = new darkRecepcion(obj);
							obj.setLocationRelativeTo(null);
							obj.setVisible(true);
						}
						
						catch (Exception ex) {
							ex.getMessage();
						}
					}
					
					else
						informacion("No eres un super usuario.");
				}
				
				catch(Exception ex) {
					return;
				}
			}
		});
		mntmRecepcion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmRecepcion.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mnMantenimiento.add(mntmRecepcion);
		
		mnConsultar = new JMenu("Consultar");
		mnConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		mnConsultar.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/consultar.png")));
		mnConsultar.setFont(new Font("Candara Light", Font.PLAIN, 15));
		menuBar.add(mnConsultar);
		
		mntmCliente1 = new JMenuItem("Cliente");
		mntmCliente1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCliente1.setFont(new Font("Candara Light", Font.PLAIN, 15));
		mntmCliente1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					cliente cli = new cliente();
					consultasCliente consCli= new consultasCliente();
					ConsCliente obj = new ConsCliente();
					
					intConsCliente icc = new intConsCliente(cli, consCli, obj);
					darkConsClient dcc = new darkConsClient(obj);
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch(Exception ex) {
					return;
				}
			}
		});
		mnConsultar.add(mntmCliente1);
		
		mntmProducto1 = new JMenuItem("Producto");
		mntmProducto1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmProducto1.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmProducto1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused") 
			public void actionPerformed(ActionEvent e) {
				try {
					producto prod = new producto();
					consultasProducto conProd = new consultasProducto();
					ConsProducto obj = new ConsProducto();
					
					darkConsProd dcp = new darkConsProd(obj);
					intConsProducto icp = new intConsProducto(prod, conProd, obj);
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch(Exception ex) {
					return;
				}
			}
		});
		mnConsultar.add(mntmProducto1);
		
		mnTransaccion = new JMenu("Transacci\u00F3n");
		mnTransaccion.setHorizontalAlignment(SwingConstants.CENTER);
		mnTransaccion.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/transaccion.png")));
		mnTransaccion.setFont(new Font("Candara Light", Font.PLAIN, 15));
		menuBar.add(mnTransaccion);
		
		mntmCaja = new JMenuItem("Caja");
		mntmCaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCaja.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmCaja.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				String input1 = (String) JOptionPane.showInputDialog(null,
						"Por favor, ingrese su sentencia", "Modo SuperUsuario", JOptionPane.WARNING_MESSAGE);
				
				try {
					if(input1.equals(key)) {
						try {
							venta vent = new venta();
							consultasVenta conVent = new consultasVenta();					
							TransaccionCaja obj = new TransaccionCaja();
							intVenta ctrl = new intVenta(vent, conVent, obj);
							darkTransact dtt = new darkTransact(obj);
							obj.setLocationRelativeTo(null);
							obj.setVisible(true);
						}
						
						catch(Exception ex) {
							return;
						}
					}
					
					else
						informacion("No eres un super usuario.");
				} 
				
				catch(Exception e1) {
					return;
				}
			}
		});
		mnTransaccion.add(mntmCaja);
		
		mnReporte = new JMenu("Reporte");
		mnReporte.setHorizontalAlignment(SwingConstants.CENTER);
		mnReporte.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/reporte.png")));
		mnReporte.setFont(new Font("Candara Light", Font.PLAIN, 15));
		menuBar.add(mnReporte);
		
		mntmVentas = new JMenuItem("Ventas");
		mntmVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmVentas.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mntmVentas.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				String input1 = (String) JOptionPane.showInputDialog(null,
						"Por favor, ingrese su sentencia", "Modo SuperUsuario", JOptionPane.WARNING_MESSAGE);
				
				try {
					if(input1.equals(key)) {
						try {
							venta vent = new venta();
							consultasVenta conVent = new consultasVenta();
							ReporteVentas obj = new ReporteVentas();
						
							darkReporte dr = new darkReporte(obj);
							obj.setLocationRelativeTo(null);
							obj.setVisible(true);
						}
						
						catch(Exception ex) {
							return;
						}
					}
					
					else
						informacion("No eres un super usuario.");
				}
				
				catch(Exception e1) {
					return;
				}
			}
		});
		mnReporte.add(mntmVentas);
		
		mnAcercaTienda = new JMenu("Acerca de Tienda (?)");
		mnAcercaTienda.setHorizontalAlignment(SwingConstants.CENTER);
		mnAcercaTienda.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/ayuda.png")));
		mnAcercaTienda.setFont(new Font("Candara Light", Font.PLAIN, 15));
		menuBar.add(mnAcercaTienda);
		
		mntmAcercaTienda = new JMenuItem("Acerca");
		mntmAcercaTienda.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					AcercaTienda obj = new AcercaTienda();
					darkTienda dt = new darkTienda(obj);
					obj.setLocationRelativeTo(null);
					obj.setVisible(true);
				}
				
				catch(Exception ex) {
					return;
				}
			}
		});
		mntmAcercaTienda.setFont(new Font("Candara Light", Font.PLAIN, 13));
		mnAcercaTienda.add(mntmAcercaTienda);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setMnemonic('a');
		btnDarkMode.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarkMode.setBorder(null);
		btnDarkMode.setForeground(Color.BLACK);
		btnDarkMode.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setBounds(15, 460, 40, 30);
		contentPane.add(btnDarkMode);
		
		lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setIcon(new ImageIcon(Menu.class.getResource("/imagenes/fondo.jpg")));
		lblFondo.setBounds(0, 70, 1096, 443);
		contentPane.add(lblFondo);
	}
	
	void informacion(String mens) {
		getToolkit().beep();
		JOptionPane.showMessageDialog(this, mens,"Precaución",JOptionPane.INFORMATION_MESSAGE);
	}
}
