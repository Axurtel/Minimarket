package vista;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import mod.darkTienda;

public class AcercaTienda extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public final JPanel contentPanel = new JPanel();
	public JSeparator separator;
	public JLabel lblAutores;
	public JLabel lblIntegrante1;
	public JLabel lblSection;
	public JLabel lblSectionR;
	public JLabel lblCurso;
	public JLabel lblCursoR;
	public JLabel lblCodigoEstudiante;
	public JLabel lblCodigoEstudianteR;
	public JLabel lblBasadoEn;
	public JLabel lblJava;
	public JLabel lblMysql;
	public JLabel lblImplementacion;
	public JLabel lblImplemento1;
	public JLabel lblImplemento2;
	public JLabel lblImplemento3;
	public JLabel lblImplemento4;
	public JLabel lblImplemento5;
	public JButton btnDarkMode;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			AcercaTienda dialog = new AcercaTienda();
			darkTienda dt = new darkTienda(dialog);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AcercaTienda() {
		setModal(true);
		setType(Type.UTILITY);
		setTitle("Acerca de Tienda | | Ayuda");
		setResizable(false);
		setBounds(100, 100, 700, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblAutores = new JLabel("Autores :");
		lblAutores.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutores.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblAutores.setBounds(10, 10, 100, 30);
		contentPanel.add(lblAutores);
		
		lblIntegrante1 = new JLabel("\u2022 Minaya Rojas Donatto.R ~ Lider");
		lblIntegrante1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				genCode("(i2019-22388)");
			}
		});
		lblIntegrante1.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblIntegrante1.setBounds(40, 50, 250, 20);
		contentPanel.add(lblIntegrante1);
		
		lblSection = new JLabel("Secci\u00F3n :");
		lblSection.setHorizontalAlignment(SwingConstants.LEFT);
		lblSection.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblSection.setBounds(10, 80, 100, 30);
		contentPanel.add(lblSection);
		
		lblSectionR = new JLabel("\u2022 T3AC ");
		lblSectionR.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblSectionR.setBounds(40, 120, 200, 20);
		contentPanel.add(lblSectionR);
		
		lblCurso = new JLabel("Curso :");
		lblCurso.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurso.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblCurso.setBounds(10, 150, 100, 30);
		contentPanel.add(lblCurso);
		
		lblCursoR = new JLabel("\u2022 Lenguaje de Programaci\u00F3n I");
		lblCursoR.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblCursoR.setBounds(40, 190, 200, 20);
		contentPanel.add(lblCursoR);
		
		lblCodigoEstudiante = new JLabel("");
		lblCodigoEstudiante.setForeground(SystemColor.control);
		lblCodigoEstudiante.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigoEstudiante.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblCodigoEstudiante.setBounds(10, 280, 100, 30);
		lblCodigoEstudiante.setForeground(Color.red);
		contentPanel.add(lblCodigoEstudiante);
		
		lblCodigoEstudianteR = new JLabel("");
		lblCodigoEstudianteR.setForeground(SystemColor.control);
		lblCodigoEstudianteR.setFont(new Font("Candara Light", Font.PLAIN, 15));
		lblCodigoEstudianteR.setBounds(40, 320, 200, 20);
		lblCodigoEstudianteR.setForeground(Color.red);
		contentPanel.add(lblCodigoEstudianteR);
		
		lblBasadoEn = new JLabel("Basado en :");
		lblBasadoEn.setHorizontalAlignment(SwingConstants.LEFT);
		lblBasadoEn.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblBasadoEn.setBounds(352, 10, 100, 30);
		contentPanel.add(lblBasadoEn);
		
		lblJava = new JLabel("\u2022 Just Another Vague Acronym (Java)");
		lblJava.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblJava.setBounds(399, 50, 220, 20);
		contentPanel.add(lblJava);
		
		lblMysql = new JLabel("\u2022 My Structured Query Language (MySQL)");
		lblMysql.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblMysql.setBounds(399, 80, 250, 20);
		contentPanel.add(lblMysql);
		
		lblImplementacion = new JLabel("Implementaciones :");
		lblImplementacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblImplementacion.setFont(new Font("Candara Light", Font.BOLD, 15));
		lblImplementacion.setBounds(352, 112, 150, 30);
		contentPanel.add(lblImplementacion);
		
		lblImplemento1 = new JLabel("\u2022 Sistema CRUD en MVC con POO");
		lblImplemento1.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblImplemento1.setBounds(399, 150, 220, 20);
		contentPanel.add(lblImplemento1);
		
		lblImplemento2 = new JLabel("\u2022 Conector | | JDBC : Driver");
		lblImplemento2.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblImplemento2.setBounds(399, 182, 220, 20);
		contentPanel.add(lblImplemento2);
		
		lblImplemento3 = new JLabel("\u2022 Modo Oscuro");
		lblImplemento3.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblImplemento3.setBounds(399, 217, 220, 20);
		contentPanel.add(lblImplemento3);
		
		lblImplemento4 = new JLabel("\u2022 Conversi\u00F3n de datos a PDF para imprimir");
		lblImplemento4.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblImplemento4.setBounds(399, 252, 240, 20);
		contentPanel.add(lblImplemento4);
		
		btnDarkMode = new JButton("");
		btnDarkMode.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDarkMode.setIcon(new ImageIcon(AcercaTienda.class.getResource("/imagenes/darkMode.png")));
		btnDarkMode.setFont(new Font("Candara Light", Font.PLAIN, 13));
		btnDarkMode.setBackground(SystemColor.controlHighlight);
		btnDarkMode.setForeground(Color.BLACK);
		btnDarkMode.setBounds(300, 40, 40, 30);
		contentPanel.add(btnDarkMode);
		
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(320, 10, 1, 340);
		contentPanel.add(separator);
		
		lblImplemento5 = new JLabel("\u2022 Modo SuperUsuario para mejores opciones");
		lblImplemento5.setFont(new Font("Candara Light", Font.PLAIN, 13));
		lblImplemento5.setBounds(399, 290, 250, 20);
		contentPanel.add(lblImplemento5);
	}
	
	void genCode(String s) {
		lblCodigoEstudiante.setText("Código :");
		lblCodigoEstudianteR.setText(s);
	}
}
