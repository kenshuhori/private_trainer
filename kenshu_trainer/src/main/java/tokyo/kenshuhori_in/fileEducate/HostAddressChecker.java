package tokyo.kenshuhori_in.fileEducate;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostAddressChecker {

	String host70T = "cjk70t.internal.worksap.com";
//	String host70T = "172.16.164.225";

	public void check() {
		try {
			System.out.println(host70T);
			InetAddress ia = InetAddress.getByName(host70T);
			byte[] bs = ia.getAddress();
			StringBuilder sb = new StringBuilder();
			sb.append(bs[0]&0xFF);
			for(int i=1; i<bs.length; i++) {
				sb.append(".").append((bs[i]&0xFF));
			}
			host70T = sb.toString();
			System.out.println(host70T);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
