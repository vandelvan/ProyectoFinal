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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Config extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idUsuario = 0;
	String username = "usuario";
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdNewpass;
	private JPasswordField pwdContraseaactual;

	/**
	 * Launch the application.
	 */
	public void crear(int id, String user) {
		try {
			Config dialog = new Config(id,user);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public Config(int id, String user) {
		this.idUsuario = id;
		this.username = user;
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblConfiguracin = new JLabel("Configuración");
		lblConfiguracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracin.setFont(new Font("Dialog", Font.BOLD, 22));
		
		JLabel lblCambiarNombreDe = new JLabel("Cambiar Nombre de Usuario");
		
		txtUsuario = new JTextField();
		txtUsuario.setText(username);
		txtUsuario.setColumns(10);
		
		JLabel lblCambiarContrasea = new JLabel("Cambiar Contraseña");
		
		pwdNewpass = new JPasswordField();
		
		JLabel lblContraseaActual = new JLabel("Contraseña Actual");
		
		pwdContraseaactual = new JPasswordField();
		
		JLabel lblContraseaActualIncorrecta = new JLabel("<html>CONTRASEÑA<br>ACTUAL<br>INCORRECTA</html>");
		lblContraseaActualIncorrecta.setVisible(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(154)
					.addComponent(lblConfiguracin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(111))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(169)
					.addComponent(txtUsuario)
					.addGap(147))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContraseaActualIncorrecta)
					.addGap(64)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblContraseaActual, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(173))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCambiarNombreDe, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
							.addGap(36))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(32)
									.addComponent(pwdNewpass, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
								.addComponent(lblCambiarContrasea, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(38)
									.addComponent(pwdContraseaactual, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
							.addGap(159))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConfiguracin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCambiarNombreDe)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCambiarContrasea)
						.addComponent(lblContraseaActualIncorrecta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdNewpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContraseaActual)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdContraseaactual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String newUsername = txtUsuario.getText();
						String newPassword = pwdNewpass.getText();
						String oldPassword = pwdContraseaactual.getText();
						
						if((oldPassword.equals("")) != true) {
							pwdContraseaactual.setBackground(Color.WHITE);
							lblContraseaActualIncorrecta.setVisible(false);
							try {
								Class.forName("com.mysql.jdbc.Driver");
								/*Crear la conexion*/
								Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
																			"root", 
																			"");
								/*Ejecutar consulta*/
								
								Statement stmt = con.createStatement();
								ResultSet rs= stmt.executeQuery("SELECT pass "
															  + "FROM usuarios "
															  + "WHERE idUsuario = " + idUsuario + ";");
								if(rs.next()) {
									if(rs.getString(1).equals(oldPassword)) {
									
										if(!newPassword.equals("")) {
											stmt.execute("UPDATE usuarios "
														+ "SET username = \"" + newUsername + "\", "
															+ "pass = \"" + newPassword + "\" "
															+ "WHERE idUsuario = "+ idUsuario + ";");
										}else {
											stmt.execute("UPDATE usuarios "
														+ "SET username = \"" + newUsername + "\" "
														+ "WHERE idUsuario = "+ idUsuario + ";");
										}
										
										rs.close();
										stmt.close();
										con.close();
										dispose();
									
									}else {
										pwdContraseaactual.setBackground(Color.RED);
										lblContraseaActualIncorrecta.setVisible(true);
										}
									}
								}
								catch(Exception er)
								{
										System.out.println(er);
								}
							}else {
								pwdContraseaactual.setBackground(Color.RED);
								lblContraseaActualIncorrecta.setVisible(true);
							}
						}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
