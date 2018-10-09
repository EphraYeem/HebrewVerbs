package rootExtracting;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import gui.DnDSupportedJLabel;
import gui.First_screen;
import javafx.geometry.VPos;

public class AdvancedVerb {
	private List<AdvancedLetter> verbPunctuated;

	public AdvancedVerb() {
		verbPunctuated = new ArrayList<AdvancedLetter>();
	}

	public List<AdvancedLetter> getVerbPunctuated() {
		return verbPunctuated;
	}

	public void addAdvancedLetter(AdvancedLetter al) {
		verbPunctuated.add(0, al);;
	}
	
	public void autoCompleteNikkud() {
		for (AdvancedLetter al : verbPunctuated) {
			if (al.getNikkud()==null) {
				if (al.getLetter().contains("ç")) {
					
				}
			}
		}
	}
	
	public void extractRoot(JPanel pnlVerbStatus) {
		//TODO the actual algorithm guys (probably won't return void tho)
		List<AdvancedLetter> verbCopy = new ArrayList<AdvancedLetter>(verbPunctuated);//for future use
		//cleans out the mosafiot in order to leave the root words only
		String thilit = wipeOutThiliot(verbPunctuated);
		System.out.println(verbPunctuated);
		statusPanelRefresh(pnlVerbStatus, verbPunctuated);
		String sofit = wipeOutSofiot(verbPunctuated);
		System.out.println(verbPunctuated);
		statusPanelRefresh(pnlVerbStatus, verbPunctuated);
		String Tochit = wipeOutTochiot(verbPunctuated);
		System.out.println(verbPunctuated);
		statusPanelRefresh(pnlVerbStatus, verbPunctuated);
		//now checks if it or part of it is in the database
		//pour the root words to the binyanim to double check if it's the actually the root words
		//deep check including nikkud
	}
	
	private void statusPanelRefresh(JPanel pnlVerbStatus, List<AdvancedLetter> verbPunctuated2) {
		pnlVerbStatus.removeAll();
		for (AdvancedLetter letter : verbPunctuated) {
        	pnlVerbStatus.add(new DnDSupportedJLabel(letter), 0);
        	pnlVerbStatus.getComponent(0).setFont(new Font("Times New Roman", Font.PLAIN, 35));
		}		
	}

	private String wipeOutThiliot(List<AdvancedLetter> vp) {
		String returnValue = null;
		if(vp.get(0).getLetter().equals(First_screen.thiliot.get(0))) {//checks for Vav hachibur
			returnValue = vp.get(0).toString();
			vp.remove(0);
		}
		if(isMosafit(vp.subList(0, 2),First_screen.thiliot)) {//checks for other two-letter mosafiot
			returnValue = vp.get(0).toString() + vp.get(1).toString();
			vp.remove(0);
			vp.remove(0);
		}
		else if (isMosafit(vp.get(0),First_screen.thiliot)) {//checks for other one-letter mosafiot
			returnValue = vp.get(0).toString();
			vp.remove(0);
		}
		return returnValue;
	}
	
	private String wipeOutSofiot(List<AdvancedLetter> vp) {
		String returnValue = null;
		if(isMosafit(vp.subList(vp.size()-2, vp.size()),First_screen.sofiot)) {//checks for other two-letter mosafiot
			returnValue = vp.get(vp.size()-1).toString() + vp.get(vp.size() - 2).toString();
			vp.remove(vp.size()-1);
			vp.remove(vp.size()-1);
		}
		else if (isMosafit(vp.get(vp.size()-1),First_screen.sofiot)) {//checks for other one-letter mosafiot
			returnValue = vp.get(vp.size()-1).toString();
			vp.remove(vp.size()-1);
		}
		return returnValue;
	}
	
	private String wipeOutTochiot(List<AdvancedLetter> vp) {
		String returnValue = null;
		String for1stAnd3rdCases = vp.get(1).getLetter();
		if(for1stAnd3rdCases.equals(First_screen.tochiot.get(0))) {//checks if the second letter is tochit vav (kinda special case in present kal 2&3 - e.g. lovesh
			returnValue = vp.get(1).toString();
			vp.remove(1);
		}
		else if (vp.get(2).getLetter().equals(First_screen.tochiot.get(1)) ||
				 vp.get(2).getLetter().equals(First_screen.tochiot.get(0))) {//e.g. hilbish
			returnValue = vp.get(2).toString();
			vp.remove(2);
		}
		else if (for1stAnd3rdCases.equals(First_screen.tochiot.get(2)) ||
				 for1stAnd3rdCases.equals(First_screen.tochiot.get(3)) ||
				 for1stAnd3rdCases.equals(First_screen.tochiot.get(4))) {
			returnValue = vp.get(1).toString();
			vp.remove(1);
		}
		return returnValue;
	}
	
	private boolean isMosafit(List<AdvancedLetter> maybeMosafit, List<String> checkingList) {//for two-letter Mosafit
		String actualMaybeMosafit = maybeMosafit.get(0).getLetter()+maybeMosafit.get(1).getLetter();
		for (String possibleMosafit : checkingList) {
			if (actualMaybeMosafit.equals(possibleMosafit))
				return true;
		}
		return false;
	}
	
	private boolean isMosafit(AdvancedLetter maybeMosafit, List<String> checkingList) {//for one-letter Mosafit
		for (String possibleMosafit : checkingList) {
			if (maybeMosafit.getLetter().equals(possibleMosafit))
				return true;
		}
		return false;
	}
}
