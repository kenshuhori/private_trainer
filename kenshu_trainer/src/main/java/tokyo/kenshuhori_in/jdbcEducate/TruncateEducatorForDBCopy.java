package tokyo.kenshuhori_in.jdbcEducate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import tokyo.kenshuhori_in.SubMainInterface;

public class TruncateEducatorForDBCopy implements SubMainInterface {

	List<String> tableList;
	List<String> fileTableList;
	String url;
	String user;
	String pass;
	String product;

	public static void main(String[] args) {
		new TruncateEducatorForDBCopy(args).execute();
	}

	public TruncateEducatorForDBCopy(String[] args) {
		fileTableList = new ArrayList<String>();
		url = args[0];
		user = args[1];
		pass = args[2];
		product = args[3];
	}

	@Override
	public void execute() {
		executeGym();
		if (product.equals("CJK")) {
			executeCom();
		}
	}

	public void executeCom() {
		tableList = new JdbcEducator(url, user, pass).createComTables();
		System.out.println("tableList1 : " + tableList.size());
		File file = Paths.get("dbcopy_ini").resolve(product).toFile();
		if (file.exists()) {
			for (File one : file.listFiles()) {
				executeFile(one);
			}
		}
		System.out.println("tableList2 : " + tableList.size());
		new JdbcEducator(url, user, pass).truncateComTable(tableList);
	}

	public void executeGym() {
		tableList = new JdbcEducator(url, user, pass).createGymTables();
		System.out.println("tableList1 : " + tableList.size());
		File file = Paths.get("dbcopy_ini").resolve(product).toFile();
		if (file.exists()) {
			for (File one : file.listFiles()) {
				executeFile(one);
			}
		}
		System.out.println("tableList2 : " + tableList.size());
		new JdbcEducator(url, user, pass).truncateGymTable(tableList);
	}

	public void executeFile(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
			String tableName = null;
			while((tableName = br.readLine()) != null) {
				if (tableName.startsWith("/")) {
					tableName = tableName.split(",")[1].toUpperCase();
					fileTableList.add(tableName);
					tableList.remove(tableName);
				}
			}
			System.out.println("fileTableList : " + fileTableList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
