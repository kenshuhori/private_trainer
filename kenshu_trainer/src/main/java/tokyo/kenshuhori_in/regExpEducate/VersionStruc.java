package tokyo.kenshuhori_in.regExpEducate;

public class VersionStruc {
	private String banner;
	private String con_id;

	public VersionStruc(String banner) {
		setBanner(banner);
	}

	public String getBanner() {
		return this.banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
}
