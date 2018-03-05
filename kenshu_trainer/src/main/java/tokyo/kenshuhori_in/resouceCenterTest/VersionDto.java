package tokyo.kenshuhori_in.resouceCenterTest;

public class VersionDto {

	private String version;
	private boolean isRemote;

	public VersionDto(String version, boolean isRemote) {
		this.version = version;
		this.isRemote = isRemote;
	}

	public String getVersion() {
		return version;
	}
	public boolean isRemote() {
		return isRemote;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("version : " + version);
		sb.append(", ");
		sb.append("isRemote : " + isRemote);
		sb.append("}");
		return sb.toString();
	}

//    public static final VersionDto ERROR_VERSION_DTO = builder().id(-1).currentUserAccessible(true).build();
//
//    public String getProductCode(){
//        return name.replaceAll(PRODUCT_REGEX, PRODUCT_REGEX_CD);
//    }
//
//    public String getVersionNumber(){
//        return name.replaceAll(PRODUCT_REGEX, PRODUCT_REGEX_NUMBER_WITH_FAST_RELEASE_NAME);
//    }
}
