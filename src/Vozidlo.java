public class Vozidlo {

	private String farba;
	private int velkost;
	private int suradnicaX;
	private int suradnicaY;
	private char posun;
	
	public Vozidlo(String farba, int i, int k, int j,char posun) {
		this.farba = farba;
		this.velkost = i;
		//int suradnicaXvPoli = (j-1) % 7;
		//int indexVPoli = (k-1)*6 + suradnicaXvPoli;
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
	public void setVelkost(int velkost) {
		this.velkost = velkost;
	}
	public int getSuradnicaX() {
		return suradnicaX;
	}
	public void setSuradnicaX(int suradnicaX) {
		this.suradnicaX = suradnicaX;
	}
	public int getSuradnicaY() {
		return suradnicaY;
	}
	public void setSuradnicaY(int suradnicaY) {
		this.suradnicaY = suradnicaY;
	}
	public char getPosun() {
		return posun;
	}
	public void setPosun(char posun) {
		this.posun = posun;
	}
	
	public void vypisDetailyVozidla() {
		System.out.println("farba = " + this.farba);
		System.out.println("suradnice x= " + this.suradnicaX + ", y= " + this.suradnicaY);
		System.out.println("posun = " + this.posun + "\n");
		
	}
}
