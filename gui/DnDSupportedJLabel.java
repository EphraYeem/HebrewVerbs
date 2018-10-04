package gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import rootExtracting.AdvancedLetter;
import rootExtracting.NikkudUpgraded;

public class DnDSupportedJLabel extends JLabel {

	private AdvancedLetter ALetter;
	private String nikkudSignName;

	private boolean letterOrNikkud;// true for letter, false for nikkud;

	public DnDSupportedJLabel(String letter, List<AdvancedLetter> verbPunctuated, int index) {
		super(letter);
		setALetter(verbPunctuated.get(index));
		setLetterOrNikkud(true);
		initDnD();
	}

	public DnDSupportedJLabel(NikkudUpgraded nikkudSign) {
		super(nikkudSign.getImg());
		setNikkudSignName(nikkudSign.getName());
		setLetterOrNikkud(false);
		initDnD();
	}

	public DnDSupportedJLabel(String letter) {
		super(letter);
		setALetter(new AdvancedLetter(letter));
		setLetterOrNikkud(true);
		initDnD();
	}

	private void initDnD() {
		setTransferHandler(new NikkudTransferHandler());
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent) e.getSource();
				TransferHandler th = c.getTransferHandler();
				th.exportAsDrag(c, e, TransferHandler.COPY);
			}
		};
		addMouseListener(ml);
	}

	private class NikkudTransferHandler extends TransferHandler {
		@Override
		public int getSourceActions(JComponent comp) {
			if (((DnDSupportedJLabel) comp).isLetterOrNikkud()) {
				return NONE;
			}
			return COPY;
		}

		@Override
		protected Transferable createTransferable(JComponent comp) {
			return new StringSelection(((DnDSupportedJLabel) comp).getNikkudSignName());
		}

		@Override
		public boolean canImport(TransferSupport ts) {
			try {
				// checks if the imported thing is actually a nikkud sign
				First_screen.nikkud.containsKey(ts.getTransferable().getTransferData(DataFlavor.stringFlavor));
			} catch (Exception e) {
				First_screen.dispMessegeQuietly(First_screen.displayWords.get("wrongDropErr"));
			}
			return true;
		}

		@Override
		public boolean importData(JComponent comp, Transferable t) {
			if (!((DnDSupportedJLabel) comp).isLetterOrNikkud()) {
				First_screen.dispMessegeQuietly(First_screen.displayWords.get("wrongDropErr"));
				return false;
			}
			else {
				String sign = null;
				try {
					sign = (String) t.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException | IOException e) {
					e.printStackTrace();
				}
				if (sign.contains("DageshOrMapiq")){
					((DnDSupportedJLabel) comp).getALetter().setDageshOrMapiq(First_screen.nikkud.get(sign).getUnicodeSign());
				}
				else if (sign.contains("Sin dot") || sign.contains("Shin dot")) {
					if (((DnDSupportedJLabel) comp).getALetter().getLetter().contains("ù"))
						((DnDSupportedJLabel) comp).getALetter().setSinOrShin(First_screen.nikkud.get(sign).getUnicodeSign());
					else
						First_screen.dispMessegeQuietly(First_screen.displayWords.get("dotToNotSinErr"));
				}
				else if (sign.contains("Shuruq")) {
					if (((DnDSupportedJLabel) comp).getALetter().getLetter().contains("å"))
						((DnDSupportedJLabel) comp).getALetter().setNikkud(First_screen.nikkud.get(sign));
					else
						First_screen.dispMessegeQuietly(First_screen.displayWords.get("shuruqToNotVavErr"));
				}
				else if (sign.contains("A") ||  sign.contains("E") || sign.contains("I") || sign.contains("O") || sign.contains("U")) {
					((DnDSupportedJLabel) comp).getALetter().getNikkud().setSound(First_screen.nikkud.get(sign).getSound());
				}
				else {
					((DnDSupportedJLabel) comp).getALetter().setNikkud(First_screen.nikkud.get(sign));
				}
			}
			((DnDSupportedJLabel) comp).setText(((DnDSupportedJLabel) comp).getALetter().toString());
			return true;
		}
	}

	public AdvancedLetter getALetter() {
		return ALetter;
	}

	public void setALetter(AdvancedLetter aLetter) {
		ALetter = aLetter;
	}

	public boolean isLetterOrNikkud() {
		return letterOrNikkud;
	}

	public void setLetterOrNikkud(boolean letterOrNikkud) {
		this.letterOrNikkud = letterOrNikkud;
	}

	public String getNikkudSignName() {
		return nikkudSignName;
	}

	public void setNikkudSignName(String nikkudSignName) {
		this.nikkudSignName = nikkudSignName;
	}

}
