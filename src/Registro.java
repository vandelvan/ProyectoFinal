import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Registro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdPassword;
	private JPasswordField pwdPass2;

	/**
	 * Launch the application.
	 */
	public void crear() {
		try {
			Registro dialog = new Registro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Registro() {
		setTitle("Registrarse");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Usuario");
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		
		pwdPassword = new JPasswordField();
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña:");
		
		pwdPass2 = new JPasswordField();
		
		JLabel incorrecto = new JLabel("<html>CONTRASEÑAS<br>NO<br>COINCIDEN!</html>");
		incorrecto.setVisible(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(incorrecto)
					.addGap(94)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pwdPass2, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addGap(127))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblConfirmarContrasea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(113))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(pwdPassword, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addGap(116))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
										.addComponent(lblNombreDeUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(121)))
							.addGap(12))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombreDeUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblContrasea)
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(incorrecto))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblConfirmarContrasea)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdPass2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						String username = txtUsuario.getText();
						String password = pwdPassword.getText();
						String pass2 = pwdPass2.getText();
						if((password.equals(pass2)) == true) {
							pwdPassword.setBackground(Color.WHITE);
							pwdPass2.setBackground(Color.WHITE);
							incorrecto.setVisible(false);
							try {
								Class.forName("com.mysql.jdbc.Driver");
								/*Crear la conexion*/
								Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
																			"root", 
																			"");
								/*Ejecutar consulta*/
								
								Statement stmt = con.createStatement();
									stmt.execute("INSERT INTO usuarios (username, pass) "
											+ "VALUES("
											+ "\""   +	username+"\","
											+ "\""   +	password+"\");");
									stmt.close();
									con.close();
							}
								catch(Exception er)
							{
									System.out.println(er);
							}
							dispose();
						}else {
							pwdPassword.setBackground(Color.RED);
							pwdPass2.setBackground(Color.RED);
							incorrecto.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
