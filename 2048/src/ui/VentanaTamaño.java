package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaTamaño extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private VentanaTablero tab;

	/**
	 * Create the dialog.
	 */
	public VentanaTamaño(VentanaTablero tb) {
		this.tab = tb;
		setTitle("Tipo de tablero...");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTamaño.class.getResource("/img/Numbers-2-icon.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Selecciona el tipo de tablero de juego:");
			lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnx = new JButton("4x4");
				btnx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						establecerTam(4);
					}
				});
				btnx.setFont(new Font("Tahoma", Font.PLAIN, 30));
				panel.add(btnx);
			}
			{
				JButton btnx_1 = new JButton("8x8");
				btnx_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						establecerTam(8);
					}
				});
				btnx_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
				panel.add(btnx_1);
			}
		}
	}
	
	private void establecerTam(int tam) {
		tab.setTamaño(tam);
		tab.inicializar();
		this.dispose();
	}

}
