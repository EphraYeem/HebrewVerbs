package rootExtracting;

import java.util.ArrayList;
import java.util.List;

import gui.First_screen;

public class AdvancedVerb {
	private final int THILIT = 1;
	private final int TOCHIT = 2;
	private final int SOFIT = 3;
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
	
	public void extractRoot() {
		//TODO the actual algorithm guys (probably won't return void tho)
		wipeOutThiliot(verbPunctuated);
		wipeOutSofiot(verbPunctuated);
		wipeOutTochiot(verbPunctuated);
	}
	
	private void wipeOutThiliot(List<AdvancedLetter> vp) {
		System.out.println(vp);
		if(vp.get(0).getLetter().equals(First_screen.thiliot.get(0)))//checks for Vav hachibur
			vp.remove(0);
		if(isMosafit(vp.subList(0, 2),First_screen.thiliot)) {//checks for other two-letter mosafiot
			vp.remove(0);
			vp.remove(0);
		}
		else if (isMosafit(vp.get(0),First_screen.thiliot))//checks for other one-letter mosafiot
			vp.remove(0);
		System.out.println(vp);
	}
	
	private void wipeOutSofiot(List<AdvancedLetter> vp) {
		System.out.println(vp);
		if(isMosafit(vp.subList(vp.size()-2, vp.size()),First_screen.sofiot)) {//checks for other two-letter mosafiot
			vp.remove(vp.size()-1);
			vp.remove(vp.size()-1);
		}
		else if (isMosafit(vp.get(vp.size()-1),First_screen.sofiot))//checks for other one-letter mosafiot
			vp.remove(vp.size()-1);
		System.out.println(vp);
	}
	
	private void wipeOutTochiot(List<AdvancedLetter> vp) {
		System.out.println(vp);
		String for1stAnd3rdCases = vp.get(1).getLetter();
		if(for1stAnd3rdCases.equals(First_screen.tochiot.get(0)))//checks if the second letter is tochit vav (kinda special case in present kal 2&3 - e.g. lovesh
			vp.remove(1);
		else if (vp.get(2).getLetter().equals(First_screen.tochiot.get(1)))//e.g. hilbish
			vp.remove(2);
		else if (for1stAnd3rdCases.equals(First_screen.tochiot.get(2)) ||
				 for1stAnd3rdCases.equals(First_screen.tochiot.get(3)) ||
				 for1stAnd3rdCases.equals(First_screen.tochiot.get(4)))
			vp.remove(1);
		System.out.println(vp);
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
