package digytal.desktop.components;

public enum Extensao {
	JPG,
	JPEG,
	PNG,
	ICO,
	TIF,
	GIF,
	TXT;
	@Override
	public String toString() {
		return "."+name().toLowerCase();
	}
}
