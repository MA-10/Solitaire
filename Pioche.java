
class Pioche{
	 Pile p1; //24 cartes de visibilit� false
	 Pile p2; //pile vide au d�but
	
	public Pioche(Carte[] tab) { //tab est un tableau comportant 24 cartes al�atoires
		p1=new Pile(24);//pile de cartes non visibles
		p2=new Pile(24);//pile de cartes visibles(on va utiliser seulement la carte au sommet)
		for (int i=0;i<24;i++) {//remplir la pile p1 avec des cartes du tableau des cartes(cartes non visibles)
			tab[i].rendre_invisible();
			p1.empiler(tab[i]);
		}
	}
	public boolean afficher_carte() {//cette methode consiste � tourner la carte (visibilit�==true)et la mettre dans p2
		if (p1.est_vide() && p2.est_vide()) {//pioche ne contient aucune carte
			System.out.print("\nPioche est vide");
			return false;
		}
		else {
			Carte c;
			if (p1.est_vide()==false) {  //si la pile p1 n'est pas vide => on tourne une carte => p2
				c=p1.d�piler();
				c.rendre_visible();
				p2.empiler(c);
			}
			else if(p1.est_vide()) { //si p1 est vide et p2 contient des cartes => on met tous les cartes dans p1
				while(p2.est_vide()==false) {
					c=p2.d�piler();
					c.rendre_invisible();
					p1.empiler(c);
				}			
			}
			return true;
		}
			
	}
	public void afficher_pioche()//modelisation : [X][carte � utilis�e] //si la pioche est vide : [vide][vide]
	{
		System.out.print("\npioche:");
		if (!p1.est_vide())
		{
			p1.sommet().affiche();
			
		}else System.out.format("%-10s","[vide]");
		
		if (!p2.est_vide())
		{
			p2.sommet().affiche();
		}else System.out.format("%-10s","[vide]");
	}
	
	public boolean d�placer_zone1(int i,Zone1 z1) {//deplacer une carte du pioche vers zone 1
		if (i<1 || i>4) {
			System.out.println("Entrer un chiffre entre 1 et 4 !");
			return false;
		}
		
		if (!p2.est_vide())
		{
			Carte c=p2.sommet();
			if(z1.mouvement_possible(c, i-1))//si le mouvement de la carte c de pioche � zone 1 est possible
			{
				z1.ajouter_carte(c, i);// on ajoute la carte
				p2.d�piler();//on la depile de la pioche
				return true;
			}else return false;
				
		}else {
			System.out.println("Afficher une carte d'abord");//si aucune carte est affich�e dans la pioche
			return false;
		}
		
	}
	public boolean d�placer_zone2(int i,Zone22 z2)//deplacer une carte de pioche vers zone 2
	{
		if (i<1 || i>7) 
		{
			System.out.println("Entrer un chiffre entre 1 et 7 !");
			return false;
		}
		i--;
		if (!p2.est_vide())//comme on a vu dans deplacer_zone1()
		{
			Carte c=p2.d�piler();
			if(z2.ajouter_carte(c, i)==false) p2.empiler(c);
			return true;
		}else {
			System.out.print("\nAfficher une carte d'abord !");
			return false;
		}
		
	}
	
	}