package tokyo.kenshuhori_in.smbEducate;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import tokyo.kenshuhori_in.SubMainInterface;

public class SmbEducatorMain implements SubMainInterface {

	String smbPath = "smb://pe:pe@nas177/share/個人用/hori_ke/";
	String smbPath205 = "smb://cjkmain:cjkmain05@192.168.182.209/worksap/";
	String smbPath209 = "smb://cjkmain:cjkmain05@//192.168.182.209/worksap/";
	String smbPath70T = "smb://Administrator:cjkmain05@172.16.164.225/";

	public static void main(String[] args) {
//		new SmbEducatorMain().execute();

		Map<String, String> map = new HashMap<String, String>();
		map.put("EXP_SMB_USER", "pe");
		map.put("EXP_SMB_PASS", "pe");
		map.put("EXP_SMB_DIR", "nas177/share/個人用/hori_ke");
		boolean result = validDbBackupSmbDirectory(map);
		System.out.println("result : " + result);
	}

	@Override
	public void execute() {

		try {
			SmbFile smbFile = new SmbFile(smbPath209);
			if (smbFile.exists()) {
				for (SmbFile oneFile : smbFile.listFiles()) {
					System.out.println("★" + oneFile.getPath());
				}
				checkSpace(smbFile);
			}
		} catch (MalformedURLException | SmbException e1) {
			e1.printStackTrace();
		}

		System.out.println("SMBテスト");
	}

	private void checkSpace(SmbFile targetFile) throws SmbException {
		double pow = Math.pow(1024, 3);
		long free = targetFile.getDiskFreeSpace();
		System.out.println("空き容量GB : " + Math.floor(free / pow) + "GB");
	}

	public static boolean validDbBackupSmbDirectory(Map<String, String> data) {
    	String expSmbUser = data.get("EXP_SMB_USER");
    	String expSmbPass = data.get("EXP_SMB_PASS");
    	String expSmbDir = SmbUtils.convert(data.get("EXP_SMB_DIR"));
    	String smbDir = SmbUtils.buildUrl(expSmbDir, expSmbUser, expSmbPass);
//    	String file = new File(smbDir).getAbsoluteFile().toPath().getRoot().toString();
//    	System.out.println("aiueo : " + file);
    	SmbUtils.root(smbDir);
    	return SmbUtils.exists(smbDir);
	}

	private static boolean isOk(String smbDir) {
		return smbDir.equals("//nas177/share/個人用/hori_ke/");
	}
}
