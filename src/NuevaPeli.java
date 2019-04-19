import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class NuevaPeli extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idUsuario = 0;
	String username = "";
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombrepeli;
	private JTextField txtDirector;

	/**
	 * Launch the application.
	 */
	public void crear(int id, String user) {
		try {
			NuevaPeli dialog = new NuevaPeli(id, user);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaPeli(int id, String user) {
		this.idUsuario = id;
		this.username = user;
		setResizable(false);
		setTitle("Nueva Pelicula");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAgregarPelicula = new JLabel("Agregar Pelicula");
		JLabel lblNombreDePelicula = new JLabel("Nombre de pelicula:");
		
		txtNombrepeli = new JTextField();
		txtNombrepeli.setToolTipText("");
		txtNombrepeli.setColumns(10);
		
		JLabel lblDirector = new JLabel("Director:");
		
		txtDirector = new JTextField();
		txtDirector.setColumns(10);
		
		JLabel lblAo = new JLabel("Año:");
		
		JSpinner spinner = new JSpinner();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(164)
					.addComponent(lblAgregarPelicula, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(164))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblAo)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNombreDePelicula, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNombrepeli, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addComponent(lblDirector, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
									.addGap(89)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(spinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
										.addComponent(txtDirector, Alignment.TRAILING))))
							.addGap(113))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAgregarPelicula)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDePelicula)
						.addComponent(txtNombrepeli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDirector)
						.addComponent(txtDirector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAo)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
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
						Object[] opciones = {"Sí","No"};
						int dialogResult = JOptionPane.showOptionDialog(null, 
																		  "Seguro Que Queire Agregarla?", 
																		  "¿Agregar?", 
																		  JOptionPane.YES_NO_OPTION, 
																		  JOptionPane.QUESTION_MESSAGE, 
																		  null, 
																		  opciones, 
																		  opciones[0]);
			        	if(dialogResult == JOptionPane.YES_OPTION){
			        		String peli = txtNombrepeli.getText();
			        		String director = txtDirector.getText();
			        		int anio = (int) spinner.getValue();
								try {
									Class.forName("com.mysql.jdbc.Driver");
									/*Crear la conexion*/
									Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
																				"root", 
																				"");
									/*Ejecutar consulta*/
								
									Statement stmt = con.createStatement();
										stmt.execute("INSERT INTO peliculas (nombrePeli, director, anio) "
												+ "VALUES("
													+ "\""   +	peli 	 + "\","
													+ "\""   +	director + "\", "
															 +	anio 	 + ");");
										stmt.close();
										con.close();
								}
								catch(Exception er)
								{
									System.out.println(er);
								}
								Inicio i = new Inicio(idUsuario, username);
								i.crear(idUsuario, username);
								dispose();
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
						Inicio i = new Inicio(idUsuario, username);
						i.crear(idUsuario, username);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
