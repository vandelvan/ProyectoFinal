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

public class NuevaSerie extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idUsuario = 0;
	String username = "";
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreserie;
	private JTextField txtProductora;

	/**
	 * Launch the application.
	 */
	public void crear(int id, String user) {
		try {
			NuevaSerie dialog = new NuevaSerie(id, user);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaSerie(int id, String user) {
		this.idUsuario = id;
		this.username = user;
		setResizable(false);
		setTitle("Nueva Serie");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAgregarSerie = new JLabel("Agregar Serie");
		JLabel lblNombreDeSerie = new JLabel("Nombre de serie:");
		
		txtNombreserie = new JTextField();
		txtNombreserie.setToolTipText("");
		txtNombreserie.setColumns(10);
		
		JLabel lblTemporadas = new JLabel("Temporadas:");
		
		txtProductora = new JTextField();
		txtProductora.setColumns(10);
		
		JLabel lblProductora = new JLabel("Productora:");
		
		JSpinner spinner = new JSpinner();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(164)
					.addComponent(lblAgregarSerie, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(164))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblNombreDeSerie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(lblTemporadas, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
						.addComponent(lblProductora))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(spinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(txtNombreserie, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(txtProductora, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addGap(113))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAgregarSerie)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeSerie)
						.addComponent(txtNombreserie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTemporadas)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductora)
						.addComponent(txtProductora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			        		String serie = txtNombreserie.getText();
			        		int temporadas = (int) spinner.getValue();
			        		String productora = txtProductora.getText();
								try {
									Class.forName("com.mysql.jdbc.Driver");
									/*Crear la conexion*/
									Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
																				"root", 
																				"");
									/*Ejecutar consulta*/
								
									Statement stmt = con.createStatement();
										stmt.execute("INSERT INTO series (nombreSerie, productora, temporadas) "
												+ "VALUES("
													+ "\""   +	serie 	 + "\", "
													+ "\""   +	productora + "\", "
															 +	temporadas 	 + ");");
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
