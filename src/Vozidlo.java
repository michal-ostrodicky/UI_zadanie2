public class Vozidlo {

	private String farba;
	private int velkost;
	private int suradnicaX;
	private int suradnicaY;
	private char posun;
	
	public Vozidlo(String farba, int i, int k, int j,char posun) {
		this.farba = farba;
		this.velkost = i;
		this.suradnicaX = j;
		this.suradnicaY = k;
		this.posun = posun;
	}
	public String getFarba() {
		return farba;
	}
	public void setFarba(String farba) {
		this.farba = farba;
	}
	public int getVelkost() {
		return velkost;
	}
	public void setVelkost(byte velkost) {
		this.velkost = velkost;
	}
	public int getSuradnicaX() {
		return suradnicaX;
	}
	public void setSuradnicaX(byte suradnicaX) {
		this.suradnicaX = suradnicaX;
	}
	public int getSuradnicaY() {
		return suradnicaY;
	}
	public void setSuradnicaY(byte suradnicaY) {
		this.suradnicaY = suradnicaY;
	}
	public char getPosun() {
		return posun;
	}
	public void setPosun(char posun) {
		this.posun = posun;
	}
	
	public void vypisDetailyVozidla() {
		System.out.println("farba = " + this.farba + "\n");
		System.out.println("suradnice x= " + this.suradnicaX + ", y= " + this.suradnicaY + "\n");
		System.out.println("posun = " + this.posun + "\n");
		
	}
}
