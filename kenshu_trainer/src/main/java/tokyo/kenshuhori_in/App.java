package tokyo.kenshuhori_in;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tokyo.kenshuhori_in.jdbcEducate.TruncateEducatorForDBCopy;

public class App {
    public static void main( String[] args ) {
    	new TruncateEducatorForDBCopy(args).execute();
//    	String QUERY_FORMAT = "SELECT MAX(VERNO) AS VERSION FROM %s.VER_KANRI WHERE (PRDCT_CD = '%s') ";
//    	String schema = "JINJI";
//    	String product = "CJK";
//    	System.out.println(String.format(QUERY_FORMAT, schema, product));
//    	System.out.println(String.format(QUERY_FORMAT, schema, product));
    }

    Map<String, String> map;
    public App() {
    	map = new HashMap<String, String>();
    	map.put("backup_dir", "\\\\nas177\\share");
    	map.put("backup_user", "pe");
    	map.put("backup_pass", "pe");
    	map.put("export_dir", "C:\\testFolder");
    	map.put("export_user", "pe");
    	map.put("export_pass", "pe");
    }

    public void execute() {
//    	System.out.println(buildFileOption());
    	for (String key : map.keySet()) {
    		if (key.endsWith("_dir")) {
    			if (map.get(key).startsWith("\\\\")) {
    				System.out.println("SMB : " + key);
    			} else {
    				System.out.println("Normal : " + key);
    			}
    		}
    	}
    }

    private String buildFileOption() {
        StringBuilder sb = new StringBuilder("(");
        List<String> files = searchTargetDmp();
        for (int i=0; i<files.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(files.get(i));
        }
        sb.append(")");
        return sb.toString();
    }

    private List<String> searchTargetDmp() {
        List<String> result = new ArrayList<String>();
        String fileName = buildFormat();
        String path = getAccessImpdpPath();
        File[] impDmpFiles = new File(path).listFiles();
        for (File file : impDmpFiles) {
        	if (file.getName().matches(fileName)) {
        		result.add(getAccessImpdpPath() + File.separator + file.getName());
        	}
        }
        return result;
    }

    protected String getImportFileFormat() {
        return "hori_ke<JOBID>%s.DMP";
    }

    protected String getAccessImpdpPath() {
        return "work";
    }

    protected int getJobId() {
        return 5987;
    }

    private String buildFormat() {
    	String fileName = getImportFileFormat();
    	fileName = fileName.replaceAll("<JOBID>", String.valueOf(getJobId()));
    	fileName = fileName.replaceAll("%s", "([0-9]+)");
    	return fileName;
    }
}
