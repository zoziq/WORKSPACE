package agent;

import java.sql.*;

import fipa.FipaMessage;

public class MatcherDB {
	
	String driver = "com.mysql.jdbc.Driver";
	String dbName = "plandb";
	private Connection conn = null;
	private Statement st;
	private PreparedStatement sorguPlanBul;

	public MatcherDB() {
		try {
			Class.forName(driver); // Sürücümüzü yüklemiþ olduk.
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/plandb", "root", ""); // Veritabanini baþlattýk.
			
			// bir statement oluþturduk
			sorguPlanBul = conn
					.prepareStatement("SELECT name FROM plan WHERE language=? AND action=?");
		} catch (ClassNotFoundException e) { // Eðer ki driver sýnýfý bulunamazsa;
			System.err
					.println("Hata: Veritabaný baðlantý sürücüsü bulunamadý.");
			System.err.println(e.getMessage());
		} catch (SQLException e) { // connectionURL'ye baðlanýlamazsa;
			System.err.println("Hata:" + e.getMessage());
		}
	}

	public void addPlan(String lang, String action, String planName) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/plandb", "root", "");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Hata: Veritabaný baðlantð sürücüsü bulunamadý.");
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err.println("Hata:" + e.getMessage());
		}

	}

	/**
	 * Bu metot, gonderilen parametrelere gore uygun plani bulur ve geriye
	 * plan ismini bir <code> String </code> olarak gonderir.
	 * 
	 * @param actor
	 *            sorguda kullanilacak dili belirler.
	 * @param action
	 *            sorgulanacak actioni belirler.
	 * @return Eger var ise uygun plan ismini, yok ise null gonderecektir.
	 */
	public String getPlan(FipaMessage fm, String action) {
		String plan = null;
		String language = fm.getAcl().getLanguage();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Sürücü yüklendi.
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/plandb", "root", ""); // Veritabaný baþlatýldý.
			st = conn.createStatement(); // Üzerinde sorgu yapmak için bir statement oluþturuldu.
			
			ResultSet rs = st
					.executeQuery("SELECT * FROM plan WHERE language='"
							+ language + "' AND action='" + action + "'");
			if (rs != null) {
				rs.next();
				plan = rs.getString(1);
				System.out.println(plan);
				return plan;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plan;
	}

	public void closeConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:derby:" + dbName
					+ ";shutdown=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
