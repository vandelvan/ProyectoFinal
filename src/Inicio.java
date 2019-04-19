import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;

public class Inicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idUsuario = 0;
	String username = "Usuario";
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public void crear(int id, String user) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio(id, user);
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
	public Inicio(int id, String user) {
		this.idUsuario = id;
		this.username  = user;
		//System.out.print("\nInicio "+idUsuario + " " + username);
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About abt = new About();
				abt.crear();
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				dispose();
			}
		});
		
		JMenuItem mntmConfiguracin = new JMenuItem("Configuración");
		mntmConfiguracin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Config c = new Config(idUsuario, username);
				c.crear(idUsuario, username);
			}
		});
		
		mnAyuda.add(mntmConfiguracin);
		mnAyuda.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel start = new JPanel();
		tabbedPane.addTab("Inicio", null, start, null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+username+"!");
		
		JLabel lblqueDeseaHacer = new JLabel("¿Que desea hacer hoy?");
		
		JButton btnRentarPeliculas = new JButton("Rentar Peliculas");
		btnRentarPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		JButton btnDevolverPeliculas = new JButton("Devolver Peliculas");
		btnDevolverPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DevolverPeli dp = new DevolverPeli(idUsuario, username);
				dp.crear(idUsuario, username);
				dispose();
			}
		});
		
		JButton btnRentarSeries = new JButton("Rentar Series");
		btnRentarSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		JButton btnDevolverSeries = new JButton("Devolver Series");
		btnDevolverSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DevolverSerie ds = new DevolverSerie(idUsuario, username);
				ds.crear(idUsuario, username);
				dispose();
			}
		});
		GroupLayout gl_start = new GroupLayout(start);
		gl_start.setHorizontalGroup(
			gl_start.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_start.createSequentialGroup()
					.addGap(82)
					.addGroup(gl_start.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRentarSeries, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(btnRentarPeliculas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(114)
					.addGroup(gl_start.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnDevolverSeries, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDevolverPeliculas, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
					.addGap(173))
				.addGroup(gl_start.createSequentialGroup()
					.addGap(308)
					.addComponent(lblBienvenido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(366))
				.addGroup(gl_start.createSequentialGroup()
					.addGap(274)
					.addComponent(lblqueDeseaHacer, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
					.addGap(46))
		);
		gl_start.setVerticalGroup(
			gl_start.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_start.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBienvenido)
					.addGap(18)
					.addComponent(lblqueDeseaHacer)
					.addGap(26)
					.addGroup(gl_start.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRentarPeliculas)
						.addComponent(btnDevolverPeliculas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_start.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDevolverSeries)
						.addComponent(btnRentarSeries))
					.addContainerGap(370, Short.MAX_VALUE))
		);
		start.setLayout(gl_start);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Peliculas", null, layeredPane, null);
		
		JPanel peliculas = new JPanel();
		
		GroupLayout gl_peliculas = new GroupLayout(peliculas);
		gl_peliculas.setHorizontalGroup(
			gl_peliculas.createParallelGroup(Alignment.LEADING)
				.addGap(0, 785, Short.MAX_VALUE)
		);
		gl_peliculas.setVerticalGroup(
			gl_peliculas.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 674, Short.MAX_VALUE)
		);
		
		JScrollPane scrollPane = new JScrollPane();
		layeredPane.setLayer(scrollPane, 0);
		if(idUsuario == 1) {
			JButton btnAgregarPelicula = new JButton("Agregar Pelicula");
			btnAgregarPelicula.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NuevaPeli np = new NuevaPeli(idUsuario, username);
					np.crear(idUsuario, username);
					dispose();
				}
				
			});
			gl_peliculas.setHorizontalGroup(
					gl_peliculas.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_peliculas.createSequentialGroup()
							.addContainerGap(627, Short.MAX_VALUE)
							.addComponent(btnAgregarPelicula)
							.addContainerGap())
				);
			
		gl_peliculas.setVerticalGroup(
			gl_peliculas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_peliculas.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAgregarPelicula)
					.addContainerGap(475, Short.MAX_VALUE))
		);
		}else{
			JButton btnRetornarPelicula = new JButton("Devolver Pelicula");
			btnRetornarPelicula.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DevolverPeli dp = new DevolverPeli(idUsuario, username);
					dp.crear(idUsuario, username);
					dispose();
				}
				
			});
			gl_peliculas.setHorizontalGroup(
					gl_peliculas.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_peliculas.createSequentialGroup()
							.addContainerGap(627, Short.MAX_VALUE)
							.addComponent(btnRetornarPelicula)
							.addContainerGap())
				);
			
		gl_peliculas.setVerticalGroup(
			gl_peliculas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_peliculas.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRetornarPelicula)
					.addContainerGap(475, Short.MAX_VALUE))
		);
		}
		
		
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
											+ "idPeli, "
											+ "nombrePeli AS \"Nombre de Pelicula\", "
											+ "director AS Director, "
											+ "anio AS Año, "
											+ "CASE "
												+ "WHEN rentadoPeli "
													+ "THEN 'No' "
													+ "ELSE 'Rentar' "
												+ "END "
											+ "AS \"¿Disponible?\" "
										 + "FROM peliculas;");
			
				

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
			        if (rentar.equals("Rentar") && col == 4) {
			        	Object[] opciones = {"Sí","No"};
						int dialogResult = JOptionPane.showOptionDialog(null, 
																		  "Seguro Que Queire Rentarla?", 
																		  "¿Rentar?", 
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
			        						+ "SET rentadoPeli = 1 "
			        						+ "WHERE idPeli = " +idPeli + ";");
			        						
			        			stmt.execute("INSERT INTO peliculasRentadas(idUsuario, idPeli) "
			        						+ "VALUES ("+ idUsuario + ", " + idPeli + ");");
			        				stmt.close();
			        				con.close();
			        				Inicio frame = new Inicio(id, user);
			    					frame.setVisible(true);
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
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(peliculas, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(peliculas, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
		);
		layeredPane.setLayout(gl_layeredPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Series", null, layeredPane_1, null);
		
		JPanel series = new JPanel();
		GroupLayout gl_series = new GroupLayout(series);
		gl_series.setHorizontalGroup(
			gl_series.createParallelGroup(Alignment.LEADING)
				.addGap(0, 785, Short.MAX_VALUE)
		);
		gl_series.setVerticalGroup(
			gl_series.createParallelGroup(Alignment.LEADING)
				.addGap(0, 48, Short.MAX_VALUE)
		);
		series.setLayout(gl_series);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		if(idUsuario == 1) {
			JButton btnAgregarSerie = new JButton("Agregar Serie");
			btnAgregarSerie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NuevaSerie ns = new NuevaSerie(idUsuario, username);
					ns.crear(idUsuario, username);
					dispose();
				}
				
			});
			gl_series.setHorizontalGroup(
					gl_series.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_series.createSequentialGroup()
							.addContainerGap(627, Short.MAX_VALUE)
							.addComponent(btnAgregarSerie)
							.addContainerGap())
				);
			
			gl_series.setVerticalGroup(
					gl_series.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_series.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAgregarSerie)
					.addContainerGap(475, Short.MAX_VALUE))
		);
		}else{
			JButton btnRetornarSerie = new JButton("Devolver Serie");
			btnRetornarSerie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DevolverSerie ds = new DevolverSerie(idUsuario, username);
					ds.crear(idUsuario, username);
					dispose();
				}
				
			});
			gl_series.setHorizontalGroup(
					gl_series.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_series.createSequentialGroup()
							.addContainerGap(627, Short.MAX_VALUE)
							.addComponent(btnRetornarSerie)
							.addContainerGap())
				);
			
			gl_series.setVerticalGroup(
					gl_series.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_series.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRetornarSerie)
					.addContainerGap(475, Short.MAX_VALUE))
		);
		}
		
		
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
											+ "idSerie, "
											+ "nombreSerie AS \"Nombre de Serie\", "
											+ "temporadas AS Temporadas, "
											+ "productora AS Productora, "
											+ "CASE "
												+ "WHEN rentadoSerie "
													+ "THEN 'No' "
													+ "ELSE 'Rentar' "
												+ "END "
											+ "AS \"¿Disponible?\" "
										 + "FROM series;");
			
				

				 table_1 = new JTable(buildTableModel(rs)) {

						/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {                
					        return false;               
						};
				 };
				 table_1.getTableHeader().setReorderingAllowed(false);
				rs.close();
				stmt.close();
				con.close();
		}
			catch(Exception e)
		{
				System.out.println(e);
		}
		
		scrollPane_1.setViewportView(table_1);
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table_1.rowAtPoint(evt.getPoint());
		        int col = table_1.columnAtPoint(evt.getPoint());
		        Object rentar = table_1.getValueAt(row,col);
		        Object idSerie = table_1.getValueAt(row,0);
		        if (rentar.equals("Rentar") && col == 4) {
		        	Object[] opciones = {"Sí","No"};
					int dialogResult = JOptionPane.showOptionDialog(null, 
																	  "Seguro Que Queire Rentarla?", 
																	  "¿Rentar?", 
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
		        			stmt.execute("UPDATE series "
		        						+ "SET rentadoSerie = 1 "
		        						+ "WHERE idSerie = " +idSerie + ";");
		        						
		        			stmt.execute("INSERT INTO seriesRentadas(idUsuario, idSerie) "
		        						+ "VALUES ("+ idUsuario + ", " + idSerie + ");");
		        				stmt.close();
		        				con.close();
		        				Inicio frame = new Inicio(id, user);
		    					frame.setVisible(true);
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
		GroupLayout gl_layeredPane_1 = new GroupLayout(layeredPane_1);
		gl_layeredPane_1.setHorizontalGroup(
			gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addComponent(series, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
		);
		gl_layeredPane_1.setVerticalGroup(
			gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addComponent(series, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
		);
		layeredPane_1.setLayout(gl_layeredPane_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 790, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane)
		);
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