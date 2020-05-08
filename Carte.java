public class Carte {
	char couleur;
	int rang; // de 1..13 avec 11=Valet, 12=Dame, 13=Roi
	String type; //PI , CO, TR, CA
	boolean visible; //true si visible
	
	public Carte(char couleur, int rang , String type , boolean visible) {
		this.couleur=couleur;
		this.rang=rang;
		this.type=type;
		this.visible=visible;
	}
	
	void rendre_visible() {
		if (visible==false) {
			visible=true;
		}
	}
	void rendre_invisible() {
		if (visible==true) {
			visible=false;
		}
	}
	void affiche() {
		if (visible==false) {
			System.out.format("%-10s","[x]");
		}
		else {
		switch(rang) {
		case 13:
			System.out.format("%-10s","[Roi-"+ type+"] ");
			break;
		case 12:
			System.out.format("%-10s","[Dame-"+ type+"] ");
			break;
		case 11:
			System.out.format("%-10s","[Valet-"+ type+"] ");
			break;
		default:
			System.out.format("%-10s","["+rang+"-"+ type+"] ");
		}
		}
	}
	
}