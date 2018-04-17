package tokyo.kenshuhori_in.jdbcEducate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tokyo.kenshuhori_in.SubMainInterface;

public class JdbcEducator implements SubMainInterface {

	List<String> tableList;
	String url = "jdbc:oracle:thin:@192.168.182.158:1521:cp06";
	String gymUser = "jinji";
	String gymPass = "jinji";
	String comUser = "companycom";
	String comPass = "companycom";

	public static void main(String[] args) {
		new JdbcEducator(args[0], args[1], args[2]).execute();
	}

	@Override
	public void execute() {
		buildComSchemaList();
		tableList.stream().forEach(System.out::println);
		System.out.println(tableList.size());
	}

	public JdbcEducator(String url, String user, String pass) {
		tableList = new ArrayList<String>();
		this.url = url;
		this.gymUser = user;
		this.gymPass = pass;
	}

	public List<String> createComTables() {
		tableList.clear();
		buildComSchemaList();
		return tableList;
	}

	public List<String> createGymTables() {
		tableList.clear();
		buildGymSchemaList();
		return tableList;
	}

	public void buildComSchemaList() {
		try {
			Connection conn = DriverManager.getConnection(url, comUser, comPass);
			Statement st = conn.createStatement();
			String sql = "select * from USER_TABLES order by TABLE_NAME";
			ResultSet result =  st.executeQuery(sql);
			while(result.next()) {
				tableList.add(result.getString(1));
			}
			st.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void buildGymSchemaList() {
		try {
			Connection conn = DriverManager.getConnection(url, gymUser, gymPass);
			Statement st = conn.createStatement();
			String sql = "select * from USER_TABLES order by TABLE_NAME";
			ResultSet result =  st.executeQuery(sql);
			while(result.next()) {
				tableList.add(result.getString(1));
			}
			st.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void truncateComTable(List<String> tableList) {
		try {
			Connection conn = DriverManager.getConnection(url, comUser, comPass);
			Statement st = conn.createStatement();
			String sql = "DROP TABLE %s";
			int result = 0;
			for (String table : tableList) {
				String tableName = String.format(sql, table);
				System.out.println("tableName : " + tableName);
				result =  st.executeUpdate(tableName);
			}
			System.out.println("result num : " + result);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void truncateGymTable(List<String> tableList) {
		try {
			Connection conn = DriverManager.getConnection(url, gymUser, gymPass);
			Statement st = conn.createStatement();
			String sql = "TRUNCATE TABLE %s";
			int result = 0;
			for (String table : tableList) {
				String tableName = String.format(sql, table);
				System.out.println("tableName : " + tableName);
				result =  st.executeUpdate(tableName);
			}
			System.out.println("result num : " + result);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
