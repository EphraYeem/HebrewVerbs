package rootExtracting;

public class AdvancedLetter {

	private String letter;
	private NikkudUpgraded nikkud;
	private String dageshOrMapiq;
	private String sinOrShin;

	public AdvancedLetter(String letter) {
		this.letter = letter;
		setNikkud(null);
		setDageshOrMapiq(null);
		setNikkud(new NikkudUpgraded());
		setSinOrShin(null);
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getDageshOrMapiq() {
		return dageshOrMapiq;
	}

	public void setDageshOrMapiq(String dageshOrMapiq) {
		this.dageshOrMapiq = dageshOrMapiq;
	}

	public NikkudUpgraded getNikkud() {
		return nikkud;
	}

	public void setNikkud(NikkudUpgraded nikkud) {
		this.nikkud = nikkud;
	}

	public String getSinOrShin() {
		return sinOrShin;
	}

	public void setSinOrShin(String sinOrShin) {
		this.sinOrShin = sinOrShin;
	}

	@Override
	public String toString() {
		return getLetter() + 
				(isActualNikkud(getNikkud().getName()) ? getNikkud().getUnicodeSign() : "") + 
				(getSinOrShin() != null ? getSinOrShin() : "") +
				(getDageshOrMapiq()!= null ? getDageshOrMapiq() : "");
	}

	private boolean isActualNikkud(String name) {
		if (name== null || name.contains("A") ||  name.contains("E") || name.contains("I") || name.contains("O") || name.contains("I")){
			return false;
		}
		return true;
	}
}
