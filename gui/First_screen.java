package gui;

import rootExtracting.AdvancedVerb;
import rootExtracting.NikkudUpgraded;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

public class First_screen extends JPanel {

	private JFrame frmHebrewVerbs;
	private JPanel pnlLetters;
	public static JPanel pnl1st; //it's static because DnDSJL functions needs access to it

	public static Map<String, String> displayWords;
	public static Map<String, NikkudUpgraded> nikkud;
	public static List<String> thiliot;
	public static List<String> tochiot;
	public static List<String> sofiot;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new First_screen("he");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 * 
	 * @throws FileNotFoundException
	 */
	public First_screen(String lang) throws FileNotFoundException {
		try {localize(lang);}
		catch (IOException e) {e.printStackTrace();}
		nikkudInit();
		thiliotInit();
		tochiotInit();
		sofiotInit();

		frmHebrewVerbs = new JFrame();
		frmHebrewVerbs.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblWelcome = new JLabel(displayWords.get("welcome"));
		lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblWelcome);

		JLabel lblInstructions = new JLabel(displayWords.get("instructions"));
		lblInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblInstructions);

		add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());

        pnlLetters = new JPanel();
        pnlLetters.setLayout(new BoxLayout(pnlLetters, BoxLayout.X_AXIS));
        pnlLettersInit(pnlLetters);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setPreferredSize(new Dimension(100,40));
        p.setMinimumSize(new Dimension(50,40));
        p.setMaximumSize(new Dimension(600,40));
        p.add(Box.createHorizontalGlue());
        p.add(pnlLetters);
        p.add(Box.createHorizontalGlue());
        add(p);
 
        add(Box.createVerticalGlue());
        add(Box.createVerticalStrut(20));

		JPanel pnlNikkud = new JPanel();
		pnlNikkud.setLayout(new BoxLayout(pnlNikkud, BoxLayout.X_AXIS));
		pnlNikkudInit(pnlNikkud);
		add(pnlNikkud);

        add(Box.createVerticalStrut(20));

		JButton btnContinue = new JButton(displayWords.get("continue"));
		btnContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdvancedVerb av = new AdvancedVerb();
				boolean isLetter = false;
				for (Component c : pnlLetters.getComponents()) {
					if (isLetter)
						av.addAdvancedLetter(((DnDSupportedJLabel) c).getALetter());
					isLetter = isLetter ? false : true;//so it skips the spaces
				}
				frmHebrewVerbs.setVisible(false);
				frmHebrewVerbs.remove(pnl1st);
				frmHebrewVerbs.setContentPane(new Second_screen(av, frmHebrewVerbs));
				frmHebrewVerbs.setVisible(true);
			}
		});
		add(btnContinue);

		pnl1st = this;

		frmHebrewVerbs.setTitle(displayWords.get("title"));
        frmHebrewVerbs.setPreferredSize(new Dimension(800, 700));
		frmHebrewVerbs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHebrewVerbs.setContentPane(this);
        frmHebrewVerbs.pack();
        frmHebrewVerbs.setVisible(true);
	}

	private void pnlNikkudInit(JPanel pnlNikkud) {
		String path = System.getProperty("user.dir")+"/resources//";
		JLabel boundary = new JLabel(new ImageIcon(path + "boundary.png"));
		JLabel BlankForOrganization = new JLabel(new ImageIcon(path + "BlankForOrganization.png"));
		pnlNikkud.add(Box.createVerticalGlue());
		JPanel noSoundNikkud = new JPanel();
		noSoundNikkud.setLayout(new BoxLayout(noSoundNikkud, BoxLayout.Y_AXIS));
		noSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("DageshOrMapiq")));
		noSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Shin dot")));
		noSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Sin dot")));
		noSoundNikkud.add(new JLabel(BlankForOrganization.getIcon()));
		pnlNikkud.add(noSoundNikkud);
		noSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(boundary);
		((JComponent) pnlNikkud.getComponent(pnlNikkud.getComponentCount() - 1)).setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel aSoundNikkud = new JPanel();
		aSoundNikkud.setLayout(new BoxLayout(aSoundNikkud, BoxLayout.Y_AXIS));
		aSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Hataf patah")));
		aSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Patah")));
		aSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Kamats")));
		aSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("A")));
		pnlNikkud.add(aSoundNikkud);
		aSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(new JLabel(boundary.getIcon()));
		((JComponent) pnlNikkud.getComponent(pnlNikkud.getComponentCount() - 1)).setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel eSoundNikkud = new JPanel();
		eSoundNikkud.setLayout(new BoxLayout(eSoundNikkud, BoxLayout.Y_AXIS));
		eSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Hataf segol")));
		eSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Tzere")));
		eSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Segol")));
		eSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("E")));
		pnlNikkud.add(eSoundNikkud);
		eSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(new JLabel(boundary.getIcon()));
		((JComponent) pnlNikkud.getComponent(pnlNikkud.getComponentCount() - 1)).setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel iSoundNikkud = new JPanel();
		iSoundNikkud.setLayout(new BoxLayout(iSoundNikkud, BoxLayout.Y_AXIS));
		iSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Hiriq")));
		iSoundNikkud.add(BlankForOrganization);
		iSoundNikkud.add(new JLabel(BlankForOrganization.getIcon()));
		iSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("I")));
		pnlNikkud.add(iSoundNikkud);
		iSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(new JLabel(boundary.getIcon()));
		((JComponent) pnlNikkud.getComponent(pnlNikkud.getComponentCount() - 1)).setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel oSoundNikkud = new JPanel();
		oSoundNikkud.setLayout(new BoxLayout(oSoundNikkud, BoxLayout.Y_AXIS));
		oSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Hataf kamats")));
		oSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("KamatsKatan")));
		oSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Holam")));
		oSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("O")));
		pnlNikkud.add(oSoundNikkud);
		oSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(new JLabel(boundary.getIcon()));
		((JComponent) pnlNikkud.getComponent(pnlNikkud.getComponentCount() - 1)).setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel uSoundNikkud = new JPanel();
		uSoundNikkud.setLayout(new BoxLayout(uSoundNikkud, BoxLayout.Y_AXIS));
		uSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Kubuts")));
		uSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("Shuruq")));
		uSoundNikkud.add(new JLabel(BlankForOrganization.getIcon()));
		uSoundNikkud.add(new DnDSupportedJLabel(nikkud.get("U")));
		pnlNikkud.add(uSoundNikkud);
		uSoundNikkud.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlNikkud.add(Box.createVerticalGlue());
	}

	private void pnlLettersInit(JPanel pnlLetters) {
		pnlLetters.add(Box.createHorizontalStrut(10));
        pnlLetters.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		pnlLetters.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					try {
						pnlLetters.getComponent(1);
						pnlLetters.remove(0);
						pnlLetters.remove(0);
					} catch (ArrayIndexOutOfBoundsException e2) {
						System.out.println("gotcha punk");
					}
				} else if (isInHebrew(e.getKeyChar())) {
					pnlLetters.add(new DnDSupportedJLabel("" + e.getKeyChar()), 0);
					pnlLetters.getComponent(0).setFont(new Font("Times New Roman", Font.PLAIN, 28));
					pnlLetters.add(Box.createHorizontalStrut(10), 0);
				}//Microsoft Sans Serif, Arial and Times New Roman supprots the qamats qatan, TNR best shows the difference between it and normal qamats
				pnl1st.validate();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				/* System.out.println("needs implementation they said"); */
			}

			@Override
			public void keyReleased(KeyEvent e) {
				/* System.out.println("you'll need it they said"); */
			}
		});
		pnlLetters.setBackground(Color.WHITE);
		pnlLetters.setFocusable(true);
		pnlLetters.requestFocusInWindow();
        frmHebrewVerbs.getInputContext().selectInputMethod(new Locale("he", "IL"));
	}

	private void nikkudInit() {
		First_screen.nikkud = new HashMap<String, NikkudUpgraded>();
		String path = System.getProperty("user.dir") + "/resources/nikkud//";
		First_screen.nikkud.put("Shva", new NikkudUpgraded(new ImageIcon(path + "Shva.png"), "\u05B0", "Shva",NikkudUpgraded.Sound.NO_SOUND_SPECIFIED));
		First_screen.nikkud.put("Hataf segol", new NikkudUpgraded(new ImageIcon(path + "Hataf segol.png"), "\u05B1","Hataf segol", NikkudUpgraded.Sound.E_SOUND));
		First_screen.nikkud.put("Hataf patah", new NikkudUpgraded(new ImageIcon(path + "Hataf patah.png"), "\u05B2","Hataf patah", NikkudUpgraded.Sound.A_SOUND));
		First_screen.nikkud.put("Hataf kamats", new NikkudUpgraded(new ImageIcon(path + "Hataf kamats.png"), "\u05B3","Hataf kamats", NikkudUpgraded.Sound.O_SOUND));
		First_screen.nikkud.put("Hiriq",new NikkudUpgraded(new ImageIcon(path + "Hiriq.png"), "\u05B4", "Hiriq", NikkudUpgraded.Sound.I_SOUND));
		First_screen.nikkud.put("Tzere",new NikkudUpgraded(new ImageIcon(path + "Tzere.png"), "\u05B5", "Tzere", NikkudUpgraded.Sound.E_SOUND));
		First_screen.nikkud.put("Segol",new NikkudUpgraded(new ImageIcon(path + "Segol.png"), "\u05B6", "Segol", NikkudUpgraded.Sound.E_SOUND));
		First_screen.nikkud.put("Patah",new NikkudUpgraded(new ImageIcon(path + "Patah.png"), "\u05B7", "Patah", NikkudUpgraded.Sound.A_SOUND));
		First_screen.nikkud.put("Kamats", new NikkudUpgraded(new ImageIcon(path + "Kamats.png"), "\u05B8", "Kamats",NikkudUpgraded.Sound.A_SOUND));
		First_screen.nikkud.put("KamatsKatan", new NikkudUpgraded(new ImageIcon(path + "KamatsKatan.png"), "\u05C7","KamatsKatan", NikkudUpgraded.Sound.O_SOUND));
		First_screen.nikkud.put("Holam",new NikkudUpgraded(new ImageIcon(path + "Holam.png"), "\u05B9", "Holam", NikkudUpgraded.Sound.O_SOUND));
		First_screen.nikkud.put("Kubuts", new NikkudUpgraded(new ImageIcon(path + "Kubuts.png"), "\u05BB", "Kubuts",NikkudUpgraded.Sound.U_SOUND));
		First_screen.nikkud.put("Shuruq", new NikkudUpgraded(new ImageIcon(path + "Shuruq.png"), "\u05BC", "Shuruq",NikkudUpgraded.Sound.U_SOUND));
		First_screen.nikkud.put("DageshOrMapiq", new NikkudUpgraded(new ImageIcon(path + "DageshOrMapiq.png"), "\u05BC","DageshOrMapiq", NikkudUpgraded.Sound.NO_SOUND_SPECIFIED));
		First_screen.nikkud.put("Shin dot", new NikkudUpgraded(new ImageIcon(path + "Shin dot.png"), "\u05C1","Shin dot", NikkudUpgraded.Sound.NO_SOUND_SPECIFIED));
		First_screen.nikkud.put("Sin dot", new NikkudUpgraded(new ImageIcon(path + "Sin dot.png"), "\u05C2", "Sin dot",NikkudUpgraded.Sound.NO_SOUND_SPECIFIED));
		First_screen.nikkud.put("A",new NikkudUpgraded(new ImageIcon(path + "A.png"), "\u0041", "A", NikkudUpgraded.Sound.A_SOUND));
		First_screen.nikkud.put("E",new NikkudUpgraded(new ImageIcon(path + "E.png"), "\u0045", "E", NikkudUpgraded.Sound.E_SOUND));
		First_screen.nikkud.put("I",new NikkudUpgraded(new ImageIcon(path + "I.png"), "\u0049", "I", NikkudUpgraded.Sound.I_SOUND));
		First_screen.nikkud.put("O",new NikkudUpgraded(new ImageIcon(path + "O.png"), "\u004F", "O", NikkudUpgraded.Sound.O_SOUND));
		First_screen.nikkud.put("U",new NikkudUpgraded(new ImageIcon(path + "U.png"), "\u0055", "U", NikkudUpgraded.Sound.U_SOUND));
	}

	private boolean isInHebrew(char c) {
		if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.HEBREW) {
			String msg = ((JLabel) this.getComponent(0)).getText();
			if (!msg.contains(displayWords.get("notInHebrewErr"))) {
				dispMessegeQuietly(displayWords.get("notInHebrewErr"));
			}
			return false;
		}
		return true;
	}

	public static void dispMessegeQuietly(String msg) {
		((JLabel) pnl1st.getComponent(0)).setText(msg);
		((JLabel) pnl1st.getComponent(0)).setForeground(Color.RED);
	}

	private void localize(String lang) throws IOException {
		First_screen.displayWords = new HashMap<String, String>();
        Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/languages//"+lang+".txt"), "UTF-8");
        while (s.hasNext()) {
        	String key = s.nextLine();
			if (s.hasNext()) 
				First_screen.displayWords.put(key, s.nextLine());
			if (s.hasNext())
				s.nextLine();//skips enters
		}
        s.close();
	}

	private void thiliotInit() throws FileNotFoundException {
		thiliot = new ArrayList<String>();
		Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/resources/thiliot.txt"), "UTF-8");
		while (s.hasNext()) {
			thiliot.add(s.nextLine());
		}
		s.close();
	}

	private void tochiotInit() throws FileNotFoundException {
		tochiot = new ArrayList<String>();
		Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/resources/tochiot.txt"), "UTF-8");
		while (s.hasNext()) {
			tochiot.add(s.nextLine());
		}
		s.close();
	}

	private void sofiotInit() throws FileNotFoundException {
		sofiot = new ArrayList<String>();
		Scanner s = new Scanner(new File(System.getProperty("user.dir") + "/resources/sofiot.txt"), "UTF-8");
		while (s.hasNext()) {
			sofiot.add(s.nextLine());
		}
		s.close();	}

}