package tokyo.kenshuhori_in.checkIpAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

import tokyo.kenshuhori_in.SubMainInterface;

public class IpAddressCheckerMain implements SubMainInterface {

	public static void main(String[] args) {
		new IpAddressCheckerMain().execute();
	}

	public IpAddressCheckerMain () {
		super();
	}

	public void execute() {
		try {
			InetAddress addrList[] = InetAddress.getAllByName("cjk70t.internal.worksap.com");

			for(int i=0; i<addrList.length; ++i) {
                System.out.println("①" + addrList[i].getHostName()); //ホスト名
                System.out.println("②" + addrList[i].getHostAddress()); //IPアドレス (文字列形式)

                byte bytes[] = addrList[i].getAddress(); //IPアドレス (バイト形式 (IPv4であれば4バイト))
                for(int j=0; j<bytes.length; ++j) {
                    if (bytes[j]>=0) {
                    	System.out.print(bytes[j]);
                    } else {
                    	System.out.print(bytes[j] + 256); //2の補数で扱われる関係で負の数になってしまった場合の補正
                    }
                    if (j<bytes.length-1) {
                    	System.out.print(".");
                    }
                }
                System.out.println("");
            }
			// ローカルホストのIP情報の取得に特化したメソッド
            InetAddress localHost = InetAddress.getLocalHost();
            // IP情報表示
            System.out.println("③" + localHost.getHostName());
            System.out.println("④" + localHost.getHostAddress());
            System.out.println("⑤" + localHost);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
