import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class About extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public void crear() {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblOrozcoTorrezJos = new JLabel("OROZCO TORREZ JOSÉ IVÁN");
		
		JLabel lblTpsiaTm = new JLabel("TPSI 8°A T/M");
		
		JLabel lblEscuelaPolitcnicaDe = new JLabel("ESCUELA POLITÉCNICA DE GUADALAJARA");
		
		JTextPane txtpnGPL = new JTextPane();
		txtpnGPL.setEditable(false);
		txtpnGPL.setText("GNU GENERAL PUBLIC LICENSE\n                       Version 3, 29 June 2007\n\n Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>\n Everyone is permitted to copy and distribute verbatim copies\nof this license document, but changing it is not allowed.");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(209)
					.addComponent(lblTpsiaTm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(343))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(144)
					.addComponent(lblOrozcoTorrezJos, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addGap(101))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(108)
					.addComponent(lblEscuelaPolitcnicaDe, GroupLayout.PREFERRED_SIZE, 376, Short.MAX_VALUE)
					.addGap(153))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(txtpnGPL, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(96, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOrozcoTorrezJos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTpsiaTm)
					.addGap(18)
					.addComponent(lblEscuelaPolitcnicaDe)
					.addGap(65)
					.addComponent(txtpnGPL)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
