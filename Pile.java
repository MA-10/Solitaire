
class Pile {
	Carte[] tab;
	int taille; //la taille actuelle de la pile
	int taille_max; //la taille maximale de la pile
	
	public Pile(int taille_max) {
		taille=0;
		tab=new Carte[taille_max];
		this.taille_max=taille_max;
	}
	boolean est_vide() {
		if (taille==0)
			return true;
		return false;
	}
	boolean est_pleine() {
		if (taille==taille_max) { //au dernier empilement on incrémente la taille 
			return true;
		}
		return false;
	}
	void empiler(Carte c) {
		if (this.est_pleine()==false) {
			tab[taille]=c;
			taille++;}
		else {
			System.out.println("pas d'empilement/n");
		}
		
	}
	
	Carte dépiler() {
		if (est_vide()==false) {
			taille--;
			return tab[taille];			
		}
		return null;
	}
	
	Carte sommet(){
		if (est_vide()==true) {
			return null;
		}
		else return tab[taille-1];
	}
	
	
	}
	