package rootExtracting;

import javax.swing.ImageIcon;

public class NikkudUpgraded {
	public enum Sound {
		A_SOUND, E_SOUND, I_SOUND, O_SOUND, U_SOUND, NO_SOUND_SPECIFIED
	};
	
	private ImageIcon img;
	private String unicodeSign;
	private String name;
	private Sound sound;
	
	public NikkudUpgraded(ImageIcon img, String uniSign, String name, Sound s) {
		setImg(img);
		setUnicodeSign(uniSign);
		setName(name);
		setSound(s);
	}
	
	
	public NikkudUpgraded() {
		setImg(null);
		setUnicodeSign(null);
		setName(null);
		setSound(Sound.NO_SOUND_SPECIFIED);
	}

	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public String getUnicodeSign() {
		return unicodeSign;
	}
	public void setUnicodeSign(String unicodeSign) {
		this.unicodeSign = unicodeSign;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sound getSound() {
		return sound;
	}
	public void setSound(Sound sound) {
		this.sound = sound;
	}
}