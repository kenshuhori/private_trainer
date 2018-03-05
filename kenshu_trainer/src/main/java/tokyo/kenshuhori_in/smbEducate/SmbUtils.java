package tokyo.kenshuhori_in.smbEducate;

import java.net.MalformedURLException;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class SmbUtils {

	/**
	 * バックスラッシュをスラッシュへ置換します。
	 * exp) nas177\share\個人用\hori_ke → //nas177/share/個人用/hori_ke/
	 * @param path
	 * @return
	 */
	public static String convert(String path) {
    	System.out.println("  " + path);
    	path = path.startsWith("\\\\") ? path.replaceFirst("\\\\\\\\", "") : path;
    	path = path.startsWith("\\") ? path.replaceFirst("\\\\", "") : path;
    	path = path.replace("\\", "/");
    	path = path.endsWith("/") ? path : path + "/";
    	return path;
	}

	/**
	 * smb形式のurlに変換します
	 * @param path SmbUtils.convertされた後のurlを渡す必要があります
	 * @param user
	 * @param pass
	 * @return
	 */
	public static String buildUrl(String path, String user, String pass) {
		StringBuilder sb = new StringBuilder();
    	sb.append("smb://").append(user).append(":").append(pass).append("@").append(path);
    	return sb.toString();
	}

	/**
	 * smb形式のディレクトリの存在確認を行います
	 * @param url SmbUtils.buildされた後のurlを渡す必要があります
	 * @return
	 */
	public static boolean exists(String url) {

		try {
			SmbFile smbFile = new SmbFile(url);
			if (smbFile.exists()) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	/**
	 * smb形式のディレクトリ先のディスク容量を算出します
	 * @param url SmbUtils.buildされた後のurlを渡す必要があります
	 * @return
	 */
	public static long calcFreeSpace(String url) {
		long freeSize = 0;
		try {
			SmbFile smbFile = new SmbFile(url);
			double pow = Math.pow(1024, 3);
			freeSize = smbFile.getDiskFreeSpace();
		} catch (MalformedURLException | SmbException e) {

		}
		return freeSize;
	}

	public static void root(String url) {
		try {
			SmbFile smbFile = new SmbFile(url);
			System.out.println("url : " + smbFile.getURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}
