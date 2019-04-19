import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwdContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("NOT-Blockbuster");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBienvenido = new JLabel("Bienvenido!");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBienvenido, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblYaTienesCuenta = new JLabel("Ya tienes cuenta?");
		lblYaTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIniciaSesion = new JLabel("Inicia sesion:");
		lblIniciaSesion.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Usuario");
		txtUsuario.setColumns(10);
		
		JLabel lblErr = new JLabel("USUARIO O CONTRASEÑA ERRONEAS!");
		lblErr.setVisible(false);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String username = txtUsuario.getText();
				String password = pwdContrasea.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					/*Crear la conexion*/
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
																"root", 
																"");
					/*Ejecutar consulta*/
					
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT idUsuario, username "
							+ "FROM usuarios "
							+ "WHERE username = \""+username+"\" "
									+ "AND pass = \""+password+"\";");

			    	  	if(rs.next()) {
			    	  		txtUsuario.setBackground(Color.WHITE);
			    	  		pwdContrasea.setBackground(Color.WHITE);
			    	  		int id = rs.getInt(1);
			    	  		String user = rs.getString(2);
			    	  		//System.out.print(id + " " + user+" Main\n");
			    	  		Inicio inicio = new Inicio(id, user);
			    	  		inicio.crear(id, user);
			    			lblErr.setVisible(false);
			    	  		dispose();
			    	  	}
			    	  	else {
			    	  		txtUsuario.setBackground(Color.ORANGE);
			    	  		pwdContrasea.setBackground(Color.ORANGE);
			    			lblErr.setVisible(true);
			    	  	}
			    	  	rs.close();
						stmt.close();
						con.close();

				}
					catch(Exception er){
						System.out.println(er);
				}
			}
		});
		
		JLabel lblCrearCuenta = new JLabel("Crear cuenta");
		lblCrearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCrearCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registro registro = new Registro();
				registro.crear();
			}
		});
		lblCrearCuenta.setForeground(Color.BLUE);
		lblCrearCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		
		pwdContrasea = new JPasswordField();
		pwdContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		pwdContrasea.setText("contraseña");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYaTienesCuenta, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addComponent(lblIniciaSesion, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
					.addGap(0))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(183)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCrearCuenta, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(169))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnIniciar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(181))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(131)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pwdContrasea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addComponent(txtUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addGap(118))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(97)
					.addComponent(lblErr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(92))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblYaTienesCuenta, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblIniciaSesion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdContrasea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnIniciar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCrearCuenta)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblErr)
					.addGap(21))
		);
		panel.setLayout(gl_panel);
	}
}
