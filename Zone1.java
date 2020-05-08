
public class Zone1 {
	 Pile[] Tab=new Pile[4];
	public Zone1()//Le constructeur cr�e un Tableau de 4 piles de cartes initialement vides
	{
		for(int i=0;i<4;i++)
		{
			Tab[i]=new Pile(13);
		}
	}
	public boolean est_termin�()//La m�thode est_termin�() v�rifie si le jeu est termin� c-a-d tous les sommets des 4 piles sont de rang=13
	{
		if (!Tab[0].est_vide() && !Tab[1].est_vide() && !Tab[2].est_vide() && !Tab[3].est_vide()) {
			return((Tab[0].sommet().rang==13)&&(Tab[1].sommet().rang==13)&&(Tab[2].sommet().rang==13)&&(Tab[3].sommet().rang==13));
		}else return false;
	}
	
	//Une m�thode implement� dans la zone1 qui v�rifie si une carte c peut s'ajouter dans le tas N� n de la zone1 : tas de 0 � 3
	public boolean mouvement_possible(Carte c,int n)
			{
				if(Tab[n].est_vide())
				{
					if(c.rang==1)//Chaque pile de la zone 1 doit commencer par un As
					{return true;}
					else
					{return false;}
				}
				else
				{
					if(Tab[n].sommet().type==c.type)//Il faut que les cartes d'une meme pile soient de meme type
					{
						if(Tab[n].sommet().rang==c.rang-1)//Il faut placer les cartes dans la zone1 dans l'orde croissant
						{return true;}
						else
						{return false;}
					}
					else 
					{return false;}
				}
			}
	
	public boolean ajouter_carte(Carte c,int n)/*La m�thode ajouter_carte prend comme param�tre une carte et le num�ro de la pile(1,2,3,4) dans laquelle on souhaite 
	 empiler cette carte et retourne vraie si la carte a �t� empil� avec succ�s , faux sinon.*/
	
	{
		if(mouvement_possible(c,n-1)==true)
		{
			Tab[n-1].empiler(c);
			System.out.println("Mouvement �ffectu� !");
			return true;
		}else
		{
			System.out.println("Mouvement invalide !");
			return false;
		}
	}
	public boolean deplacer_zone2(int colz1,int colz2,Zone22 z2)//deplacer une carte de zone 1 vers zone 2 
	{
		if(colz1<1 || colz1>4 || colz2<1 || colz2>7) {
			
				System.out.println("Erreur de saisie des indices !");
				return false;
			
		}
		colz1--;colz2--;
		if (!Tab[colz1].est_vide())//la pile choisie de rang colz1 doit etre non vide
		{
			Carte c=Tab[colz1].sommet();
			if(z2.ajouter_carte(c, colz2))//si on peut ajouter la carte c � la pile de rang colz2 du zone 2 
			{
				Tab[colz1].d�piler();
				return true;
			}
			else 
			{
				return false;
			}
		}else {//la pile choisie dans la zone 1 ne contient aucune carte
			System.out.println("La pile choisie de zone 1 n'a aucune carte !");
			return false;
		}
	}
	public void affiche()
	/*La m�thode affiche() sert � afficher la les sommets des 4 piles sous la forme suivantes:
    1)Sommet1      2)Sommet2       3)sommet3       4)Sommet4                         */
	{
		for (int i=0;i<4;i++)
		{
			if (!Tab[i].est_vide())
			{	System.out.print(i+1+")");
				Tab[i].sommet().affiche();
			System.out.print("\t");}
			else System.out.print(i+1+")[vide]\t");
		}
	}
	
	
}
		
	


