package agent;

import java.sql.*;

import fipa.FipaMessage;


public class MatcherDB {
	String driver = "com.mysql.jdbc.Driver";
	String dbName = "plandb"; 
	private Connection conn = null;
	private Statement st;
	private PreparedStatement sorguPlanBul;
	
	public MatcherDB(){
		try {
			Class.forName(driver); // S�r�c�m�z� de y�klemi� olduk
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plandb","root",""); // Veritaban�n� ba�latt�k
//			st = conn.createStatement(); //�zerinden sorgu yapmak i�in bir statement olu�turduk
			sorguPlanBul = conn.prepareStatement("SELECT name FROM plan WHERE language=? AND action=?");
		} catch (ClassNotFoundException e) {  // E�er ki driver s�n�f� bulunamazsa;
			System.err.println("Hata: Veritaban� ba�lant� s�r�c�s� bulunamad�.");
			System.err.println(e.getMessage());
		} catch (SQLException e) {  //connectionURL'ye ba�lan�lamazsa;
			System.err.println("Hata:" + e.getMessage());
		}
	}
	public void addPlan(String lang,String action,String planName){
		try {
			Class.forName(driver); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plandb","root",""); 
		} catch (ClassNotFoundException e) { 
			System.err.println("Hata: Veritabanı bağlantı sürücüsü bulunamadı");
			System.err.println(e.getMessage());
		} catch (SQLException e) { 
			System.err.println("Hata:" + e.getMessage());
		}
		
	}
	
	
	/**
	 * Bu metod g�nderilen parametrelere g�re uygun plan� bulur ve
	 * geriye plan ismini bir <code> String </code> olarak g�nderir.
	 * @param actor sorguda kullan�lacak dili belirler.
	 * @param action ise sorgulanacak action� belirler
	 * @return E�er var ise uygun plan ismini, yok ise null g�nderecektir.
	 */
	public String getPlan(FipaMessage fm, String action){
		String plan = null;
		String language=fm.getAcl().getLanguage();	
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			 // S�r�c�m�z� de y�klemi� olduk
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plandb","root",""); // Veritaban�n� ba�latt�k
		st = conn.createStatement(); //�zerinden sorgu yapmak i�in bir statement olu�turduk
		ResultSet rs = st.executeQuery("SELECT * FROM plan WHERE language='"+language+"' AND action='"+action+"'");
			if (rs != null){
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
	
	public void closeConnection(){
		try {
			conn = DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
