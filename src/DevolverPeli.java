import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DevolverPeli extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idUsuario = 0;
	String user = "";
	private JLayeredPane contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void crear(int id, String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolverPeli frame = new DevolverPeli(id, username);
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
	public DevolverPeli(int id, String username) {
		setTitle("Devolver Peliculas Rentadas");
		this.idUsuario = id;
		this.user = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblNoCuentaCon = new JLabel("No cuenta con peliculas rentadas!");
		lblNoCuentaCon.setVisible(false);
		try {
			/*Revisar si existe la clase*/
			Class.forName("com.mysql.jdbc.Driver");
			/*Crear la conexion*/
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
														"root", 
														"");
			/*Crear sentencia*/
			Statement stmt=con.createStatement();
			/*Ejecutar consulta*/
			ResultSet rs=stmt.executeQuery("SELECT "
											+ "p.idPeli AS id, "
											+ "p.nombrePeli AS \"Nombre de Pelicula\", " 
											+ "p.director AS Director, " 
											+ "p.anio AS Año, "
											+ "u.username AS \"Rentada Por\", "
											+ "CASE "
												+ "WHEN p.rentadoPeli "
													+ "THEN 'Devolver' "
												+ "END "
											+ "AS Devolver "
										+ "FROM peliculasRentadas pr "
										+ "INNER JOIN usuarios u  ON u.idUsuario = pr.idUsuario "
										+ "INNER JOIN peliculas p ON p.idPeli = pr.idPeli "
										+ "WHERE pr.idUsuario = " + idUsuario + ";");
			
				
				table = new JTable(buildTableModel(rs)) {

						/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {                
							return false;               
						};
				};
				table.getTableHeader().setReorderingAllowed(false);
				if(!rs.absolute(1)) {
					lblNoCuentaCon.setVisible(true);
				}else {
					lblNoCuentaCon.setVisible(false);
				}
				rs.close();
				stmt.close();
				con.close();
		}
			catch(Exception e)
		{
				System.out.println(e);
		}
		
		scrollPane.setViewportView(table);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        Object rentar = table.getValueAt(row,col);
		        Object idPeli = table.getValueAt(row,0);
		        if (rentar.equals("Devolver") && col == 5) {
		        	Object[] opciones = {"Sí","No"};
					int dialogResult = JOptionPane.showOptionDialog(null, 
																	  "Seguro Que Queire Devolver?", 
																	  "¿Devolver?", 
																	  JOptionPane.YES_NO_OPTION, 
																	  JOptionPane.QUESTION_MESSAGE, 
																	  null, 
																	  opciones, 
																	  opciones[0]);
		        	if(dialogResult == JOptionPane.YES_OPTION){
		        		try {
		        			/*Revisar si existe la clase*/
		        			Class.forName("com.mysql.jdbc.Driver");
		        			/*Crear la conexion*/
		        			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoFinal",
		        														"root", 
		        														"");
		        			/*Crear sentencia*/
		        			Statement stmt=con.createStatement();
		        			/*Ejecutar consulta*/
		        			stmt.execute("UPDATE peliculas "
		        						+ "SET rentadoPeli = 0 "
		        						+ "WHERE idPeli = " +idPeli + ";");
		        						
		        			stmt.execute("DELETE FROM peliculasRentadas "
		        						+ "WHERE idUsuario = "+ idUsuario + " "
		        							+ "AND idPeli = " + idPeli + ";");
		        				stmt.close();
		        				con.close();
		        				DevolverPeli dp = new DevolverPeli(idUsuario, user);
		        				dp.crear(idUsuario, user);
		    					dispose();
		        		}
		        			catch(Exception e)
		        		{
		        				System.out.println(e);
		        		}
		        	}
		        }
		    }
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
		);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio ini = new Inicio(idUsuario, user);
				ini.crear(idUsuario, user);
				dispose();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVolver)
					.addGap(36)
					.addComponent(lblNoCuentaCon, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(52))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVolver)
						.addComponent(lblNoCuentaCon))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	/*METODO PARA LLENAR LAS TABLAS*/
	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException{
		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for(int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		
				
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while(rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for(int columnIndex = 1;
					columnIndex <= columnCount;
					columnIndex++) {
					vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);
	}
}
