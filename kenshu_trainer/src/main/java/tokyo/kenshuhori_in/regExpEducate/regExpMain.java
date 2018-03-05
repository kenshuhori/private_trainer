package tokyo.kenshuhori_in.regExpEducate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tokyo.kenshuhori_in.SubMainInterface;

public class regExpMain implements SubMainInterface {

	public static void main(String[] args) {
		regExpMain.check("jdbc:oracle:thin:@192.168.56.1:1521:cp07");
//		new regExpMain().execute();
	}

	private List<VersionStruc> versionList;
	private String oraVersionNum;

	public regExpMain() {
		versionList = new ArrayList<VersionStruc>();
		versionList.add(new VersionStruc("PL/SQL Release 12.1.0.2.0 - Production"));
		versionList.add(new VersionStruc("CORE	12.1.0.2.0	Production"));
		versionList.add(new VersionStruc("Oracle Database 12c Enterprise Edition Release 12.1.0.2.0 - 64bit Production"));
		versionList.add(new VersionStruc("TNS for 64-bit Windows: Version 12.1.0.2.0 - Production"));
		versionList.add(new VersionStruc("NLSRTL Version 12.1.0.2.0 - Production"));
	}

	@Override
	public void execute() {
//		checkJdbcVersion();
//		checkOracleVersion6();
		checkOracleVersion();
	}

	private void checkJdbcVersion() {
		System.out.println("JDBCのバージョンチェック開始");

		String regEx ="jdbc:oracle:thin:@(.*):(.*):(.*)";
        String href = "jdbc:oracle:thin:@172.16.164.255:1521:cp07";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(href);

        if( m.find()){
            System.out.print("group(0)は文字列全体 "+ m.group(0)+"\n");
            System.out.print("grpuo(1)は1グループ目の一致 "+ m.group(1)+"\n");
            System.out.print("grpuo(2)は2グループ目の一致 "+ m.group(2)+"\n");
            System.out.print("grpuo(3)は3グループ目の一致 "+ m.group(3)+"\n");
        }
	}

//	private void checkOracleVersion6() {
//		System.out.println("ORACLEのバージョンチェック開始");
//
//		String regEx ="Oracle Database (.*)";
//        String href = "Oracle Database 12c Enterprise Edition Release 12.1.0.2.0 - 64bit Production";
//        String regExForVersion = "[0-9][0-9]";
//        String href2;
//
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(href);
//
//        if( m.find()){
//            System.out.print("group(0)は文字列全体 "+ m.group(0)+"\n");
//            System.out.print("grpuo(1)は1グループ目の一致 "+ m.group(1)+"\n");
//            href2 = m.group(1);
//
//            Pattern p2 = Pattern.compile(regExForVersion);
//            Matcher m2 = p2.matcher(href2);
//            if(m2.find()) {
//            	System.out.println("Oracleのバージョンは : " + m2.group());
//            }
//        }
//	}

	private void checkOracleVersion() {
		System.out.println("ORACLEのバージョンチェック開始forJava8");
		String regExForOracle ="Oracle Database (.*)";
		String targetString;
		Pattern p1 = Pattern.compile(regExForOracle);
		Matcher m;

		for(VersionStruc version : versionList) {
			targetString = version.getBanner();
			m = p1.matcher(targetString);
			if (m.find()) {
				checkVerNum(m.group(1));
			} else {
				continue;
			}
		}
	}

	private void checkVerNum(String targetString) {
		String regExForOraVer = "[0-9][0-9]";
		Pattern p = Pattern.compile(regExForOraVer);
		Matcher m = p.matcher(targetString);
		if(m.find()) {
			oraVersionNum = m.group();
			System.out.println("oracle_version : " + oraVersionNum);
		}
	}

	public static boolean check(String jdbc_url) {

		boolean result = false;
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			String localIpAddress = localHost.getHostAddress();
			String localHostName = localHost.getHostName();

			String jdbcIp = extractJdbcIp(jdbc_url);

			if (jdbcIp.equals(localIpAddress) || jdbcIp.equals(localHostName)) {
				result = true;
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String extractJdbcIp(String href) throws Exception {
		final String JDBC_REGEX ="jdbc:oracle:thin:@(.*):(.*):(.*)";
        Pattern p = Pattern.compile(JDBC_REGEX);
        Matcher m = p.matcher(href);
        if( m.find()){
            return m.group(1);
        } else {
        	throw new Exception("Cannot extract IP from JDBC_URL");
        }
	}
}
