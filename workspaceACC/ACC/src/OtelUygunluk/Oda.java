package OtelUygunluk;

public class Oda {

	private int OdaNo;
	private String OdaTip;
	private String Durum;
	private String baslamaTarihi;
	private String bitisTarihi;
	
	public int getOdaNo() {
		return OdaNo;
	}
	public String getOdaTip() {
		return OdaTip;
	}
	public String getDurum() {
		return Durum;
	}
	public String getBaslamaTarihi() {
		return baslamaTarihi;
	}
	public String getBitisTarihi() {
		return bitisTarihi;
	}
	public void setOdaNo(int odaNo) {
		OdaNo = odaNo;
	}
	public void setOdaTip(String odaTip) {
		OdaTip = odaTip;
	}
	public void setDurum(String durum) {
		Durum = durum;
	}
	public void setBaslamaTarihi(String baslamaTarihi) {
		this.baslamaTarihi = baslamaTarihi;
	}
	public void setBitisTarihi(String bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}


}
