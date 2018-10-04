package gui;

import javax.swing.JPanel;

import rootExtracting.AdvancedVerb;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Second_screen extends JPanel implements ActionListener{
	
	private AdvancedVerb theVerb;

	private JButton btnGoBack;
	private JFrame frmHebrewVerbs;

	public Second_screen(AdvancedVerb av, JFrame fhv) {
		this.theVerb=av;
		this.frmHebrewVerbs = fhv;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblPlzWait = new JLabel(First_screen.displayWords.get("waitPlease"));
		lblPlzWait.setFont(new Font("David", Font.PLAIN, 18));
		lblPlzWait.setAlignmentY(Component.CENTER_ALIGNMENT);
		lblPlzWait.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblPlzWait);
        
        add(Box.createVerticalGlue());
        
        Icon icon = new ImageIcon(System.getProperty("user.dir")+"/resources/loadingGif.gif");
        JLabel loading = new JLabel(icon);
        loading.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loading);
                
        add(Box.createVerticalGlue());
        
        JButton btnGoBack = new JButton(First_screen.displayWords.get("goBack"));
        btnGoBack.addActionListener(this);
		btnGoBack.setFont(new Font("David", Font.PLAIN, 18));
		btnGoBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnGoBack=btnGoBack;
		add(btnGoBack);
		
		theVerb.autoCompleteNikkud();
		theVerb.extractRoot();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnGoBack) {
			frmHebrewVerbs.setVisible(false);
			frmHebrewVerbs.remove(this);
			frmHebrewVerbs.setContentPane(First_screen.pnl1st);
			frmHebrewVerbs.setVisible(true);
		}
	}
	
}
