package OtelUygunluk;

import java.util.ArrayList;

import agent.AgentIdentifier;

public class Otel {

	private String ad;
	private String adres;
	private int yildiz;
	private AgentIdentifier AID;
	ArrayList<Oda> odaList=new ArrayList<Oda>();
	ArrayList<Servis> servisList=new ArrayList<Servis>();
	
	public String getAd() {
		return ad;
	}
	public String getAdres() {
		return adres;
	}
	public int getYildiz() {
		return yildiz;
	}
	public AgentIdentifier getAID() {
		return AID;
	}
	public ArrayList<Oda> getOdaList() {
		return odaList;
	}
	public ArrayList<Servis> getServisList() {
		return servisList;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public void setYildiz(int yildiz) {
		this.yildiz = yildiz;
	}
	public void setAID(AgentIdentifier aID) {
		AID = aID;
	}
	public void setOdaList(ArrayList<Oda> odaList) {
		this.odaList = odaList;
	}
	public void setServisList(ArrayList<Servis> servisList) {
		this.servisList = servisList;
	}
	
	public void ekleOda(Oda oda)
	{
		odaList.add(oda);
	}
	public void ekleServis(Servis servis)
	{
		servisList.add(servis);
	}
	
}
