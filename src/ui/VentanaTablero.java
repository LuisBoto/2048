package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import logic.Juego;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.InputEvent;
import javax.swing.JSeparator;

public class VentanaTablero extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBotones;
	private JPanel pnTablero;
	private JLabel lbControles;
	private JPanel pnControl;
	private JButton btArriba;
	private JButton btAbajo;
	private JButton btIzquierda;
	private JButton btDerecha;
	private Juego juego;
	private JButton btAutoPlay;
	private GestionaBoton gB;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNuevaPartida;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmSalir;
	private JMenu mnOpciones;
	private JMenuItem mntmTipoDeTablero;
	private JSeparator separator;
	private int tamaño;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTablero frame = new VentanaTablero();
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
	public VentanaTablero() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTablero.class.getResource("/img/Numbers-2-icon.png")));
		setResizable(false);
		tamaño=4;
		juego = new Juego(tamaño);
		gB = new GestionaBoton();
		setTitle("Java 2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 400);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
		contentPane.add(getPnTablero(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		crearTablero();
		pintar();
		getBtArriba().addKeyListener(gB);
		getBtAbajo().addKeyListener(gB);
		getBtAutoPlay().addKeyListener(gB);
		getBtDerecha().addKeyListener(gB);
		getBtIzquierda().addKeyListener(gB);
		//getPnTablero().getInputMap().put(KeyStroke.getKeyStroke("UP"), "arriba");
		//getPnTablero().getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "abajo");
		//getPnTablero().getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "izquierda");
		//getPnTablero().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "derecha");
		
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBackground(Color.WHITE);
			pnBotones.setLayout(new BorderLayout(0, 0));
			pnBotones.add(getLbControles(), BorderLayout.NORTH);
			pnBotones.add(getPnControl(), BorderLayout.CENTER);
		}
		return pnBotones;
	}
	
	private void crearTablero() {
		getPnTablero().removeAll();
		pnTablero.setLayout(new GridLayout(this.tamaño, this.tamaño, 5, 5));
		for (int i=0; i<juego.getTablero().length*juego.getTablero().length; i++) {
			JLabel casilla = new JLabel();
			casilla.setText("-");
			casilla.setFont(new Font("Sylfaen", Font.BOLD, 13));
			casilla.setForeground(Color.BLACK);
			casilla.setBorder(new LineBorder(Color.GRAY));
			casilla.setHorizontalAlignment(SwingConstants.CENTER);
			//casilla.setEnabled(false);
			getPnTablero().add(casilla);
		}
	}
	
	private void pintar() {
		int[][] casillas = juego.getTablero();
		int contador = 0;
		for (int i=0; i<juego.getTablero().length; i++) {
			for (int j=0; j<juego.getTablero().length; j++) {
				if (casillas[i][j] == 0) {
					((JLabel) getPnTablero().getComponents()[contador]).setText("-");
				}
				else {
					((JLabel) getPnTablero().getComponents()[contador]).setText(""+casillas[i][j]);
				}
				((JLabel) getPnTablero().getComponents()[contador]).setOpaque(true);
				((JLabel) getPnTablero().getComponents()[contador]).setBackground(Color.decode(casillas[i][j]*1000+99999900+""));
				if (casillas[i][j] == 0)
					((JLabel) getPnTablero().getComponents()[contador]).setOpaque(false);
				contador++;
			}
		}
	}
	
	private JPanel getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanel();
			pnTablero.setBackground(SystemColor.control);
			pnTablero.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnTablero.setLayout(new GridLayout(this.tamaño, this.tamaño, 5, 5));
		}
		return pnTablero;
	}
	private JLabel getLbControles() {
		if (lbControles == null) {
			lbControles = new JLabel("Controles (tambien flechas)");
			lbControles.setBackground(Color.LIGHT_GRAY);
			lbControles.setHorizontalAlignment(SwingConstants.CENTER);
			lbControles.setFont(new Font("Sylfaen", Font.BOLD, 13));
		}
		return lbControles;
	}
	private JPanel getPnControl() {
		if (pnControl == null) {
			pnControl = new JPanel();
			pnControl.setBackground(Color.WHITE);
			GridBagLayout gbl_pnControl = new GridBagLayout();
			gbl_pnControl.columnWidths = new int[]{77, 275, 73, 0};
			gbl_pnControl.rowHeights = new int[]{23, 23, 23, 0};
			gbl_pnControl.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_pnControl.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnControl.setLayout(gbl_pnControl);
			GridBagConstraints gbc_btArriba = new GridBagConstraints();
			gbc_btArriba.anchor = GridBagConstraints.NORTH;
			gbc_btArriba.fill = GridBagConstraints.HORIZONTAL;
			gbc_btArriba.insets = new Insets(0, 0, 5, 5);
			gbc_btArriba.gridx = 1;
			gbc_btArriba.gridy = 0;
			pnControl.add(getBtArriba(), gbc_btArriba);
			GridBagConstraints gbc_btIzquierda = new GridBagConstraints();
			gbc_btIzquierda.anchor = GridBagConstraints.NORTHWEST;
			gbc_btIzquierda.insets = new Insets(0, 0, 5, 5);
			gbc_btIzquierda.gridx = 0;
			gbc_btIzquierda.gridy = 1;
			pnControl.add(getBtIzquierda(), gbc_btIzquierda);
			GridBagConstraints gbc_btAutoPlay = new GridBagConstraints();
			gbc_btAutoPlay.anchor = GridBagConstraints.NORTH;
			gbc_btAutoPlay.insets = new Insets(0, 0, 5, 5);
			gbc_btAutoPlay.gridx = 1;
			gbc_btAutoPlay.gridy = 1;
			pnControl.add(getBtAutoPlay(), gbc_btAutoPlay);
			GridBagConstraints gbc_btDerecha = new GridBagConstraints();
			gbc_btDerecha.anchor = GridBagConstraints.NORTHWEST;
			gbc_btDerecha.insets = new Insets(0, 0, 5, 0);
			gbc_btDerecha.gridx = 2;
			gbc_btDerecha.gridy = 1;
			pnControl.add(getBtDerecha(), gbc_btDerecha);
			GridBagConstraints gbc_btAbajo = new GridBagConstraints();
			gbc_btAbajo.insets = new Insets(0, 0, 0, 5);
			gbc_btAbajo.anchor = GridBagConstraints.NORTH;
			gbc_btAbajo.fill = GridBagConstraints.HORIZONTAL;
			gbc_btAbajo.gridx = 1;
			gbc_btAbajo.gridy = 2;
			pnControl.add(getBtAbajo(), gbc_btAbajo);
		}
		return pnControl;
	}
	private JButton getBtArriba() {
		if (btArriba == null) {
			btArriba = new JButton("Arriba");
			btArriba.setMnemonic('a');
			btArriba.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hacerJugada(0);//0UP 1DOWN 2LEFT 3RIGHT
				}
			});
		}
		return btArriba;
	}
	
	private void hacerJugada(int dir) {
		juego.jugada(dir);
		pintar();
	}
	
	private JButton getBtAbajo() {
		if (btAbajo == null) {
			btAbajo = new JButton("Abajo");
			btAbajo.setMnemonic('b');
			btAbajo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hacerJugada(1);
				}
			});
		}
		return btAbajo;
	}
	private JButton getBtIzquierda() {
		if (btIzquierda == null) {
			btIzquierda = new JButton("Izquierda");
			btIzquierda.setMnemonic('i');
			btIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hacerJugada(2);
				}
			});
		}
		return btIzquierda;
	}
	private JButton getBtDerecha() {
		if (btDerecha == null) {
			btDerecha = new JButton("Derecha");
			btDerecha.setMnemonic('d');
			btDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hacerJugada(3);
				}
			});
		}
		return btDerecha;
	}
	private JButton getBtAutoPlay() {
		if (btAutoPlay == null) {
			btAutoPlay = new JButton("Juego Automatico\r\n");
			btAutoPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					autoJuego();
				}
			});
			btAutoPlay.setMnemonic('j');
			btAutoPlay.setToolTipText("Como lo oyes, nene\r\n");
		}
		return btAutoPlay;
	}
	
	private void autoJuego() {
		for (int i=0; i<10000; i++) {
			hacerJugada((int) (Math.random()*3));
		}
	}
	
	private class GestionaBoton extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				hacerJugada(0);
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				hacerJugada(1);
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				hacerJugada(2);
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				hacerJugada(3);
		}
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnOpciones());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("Nuevo");
			mnFile.add(getMntmNuevaPartida());
			mnFile.add(getSeparator());
			mnFile.add(getMntmSalir());
		}
		return mnFile;
	}
	private JMenuItem getMntmNuevaPartida() {
		if (mntmNuevaPartida == null) {
			mntmNuevaPartida = new JMenuItem("Nueva partida");
			mntmNuevaPartida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNuevaPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		}
		return mntmNuevaPartida;
	}
	
	protected void setTamaño(int tam) {
		this.tamaño = tam;
	}
	
	protected void inicializar() {
		this.juego = new Juego(this.tamaño);
		crearTablero();
		pintar();
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Luis Boto / 2018\n2048 v1.2", "Acerca de", 2);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		}
		return mntmSalir;
	}
	private JMenu getMnOpciones() {
		if (mnOpciones == null) {
			mnOpciones = new JMenu("Opciones");
			mnOpciones.add(getMntmTipoDeTablero());
		}
		return mnOpciones;
	}
	private JMenuItem getMntmTipoDeTablero() {
		if (mntmTipoDeTablero == null) {
			mntmTipoDeTablero = new JMenuItem("Tipo de tablero...");
			mntmTipoDeTablero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaTamaño();
				}
			});
		}
		return mntmTipoDeTablero;
	}
	
	private void mostrarVentanaTamaño() {
		VentanaTamaño vt = new VentanaTamaño(this);
		vt.setModal(true);
		vt.setLocationRelativeTo(this);
		vt.setVisible(true);
	}
	
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
}
