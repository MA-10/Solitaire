public class Zone22 {
	 Pile [] tab=new Pile[7];
	public Zone22(Carte [] itab)//distribution des cartes initialement trouvées dans itab vers tous les piles de zone 2
	{
		tab[0]=new Pile(52);//la 1er pile qui contient une seule carte(visible)
		Carte c=itab[0];
		c.rendre_visible();
		tab[0].empiler(c);
		
		int k=0;
		for (int i=1;i<7;i++)
		{
			
			tab[i]=new Pile(52);
			for(int j=0;j<=i-1;j++)//distribution des cartes invisibles
			{
				k++;
				c=itab[k];
				c.rendre_invisible();
				tab[i].empiler(c);
			}
			//distribution des cartes visibles
			k++;
			c=itab[k];
			c.rendre_visible();
			tab[i].empiler(c);
		}
	}
	public void afficher()//afficher les cartes de tous les piles de la zone 2
	{
		for (int i=0;i<7;i++)
		{
			System.out.print("\n\nTas n "+(i+1)+" :  " );
			if (!tab[i].est_vide())
			{
				Pile p1=tab[i];
				Pile p2=new Pile(p1.taille);//pile intermédiaire
				while(!p1.est_vide())//on depile les cartes dans la pile intermédiare pour inverser l'ordre des cartes d'une pile
				{
					p2.empiler(p1.dépiler());
				}
				int j=1;
				while(!p2.est_vide())
				{
					System.out.print("\t"+j+"# ");
					Carte c=p2.dépiler();
					p1.empiler(c);
					c.affiche();
					j++;
				}
				
			}else System.out.print("\t[vide]");
			
		}
	}
	public boolean mouvement_possible(Carte c,int i)//cette methode est implémentée pour verifier les conditions de l'ajout d'une carte à la zone 2 
	{
			if (c.rang==13 && tab[i].est_vide())//le roi s'ajoute à la zone 2 ssi la pile est vide
			{
			   return true;
			} 
			else if(c.rang!=13 && !tab[i].est_vide())//l'ajout d'une carte diff de roi doit verifier les conditions de type(differents) et de rang(decroissants)
			{
				Carte cc=tab[i].sommet();
				if (c.rang==cc.rang-1 && c.couleur!=cc.couleur) 
				{
				   return true;
				}
                else 
				{
				   return false;
				}
			}
			else//autre mouvement 
			{	
				return false;
			}
		
	}
	public boolean ajouter_carte(Carte c,int i)//ajouter une carte c si elle verfie tous les conditions de la suite 
	{
		if (i<0 || i>6) {
			System.out.println("entrer un chiffre entre 1 et 7");
			return false;}
		else 
		{
			if (mouvement_possible(c,i))
			{
				tab[i].empiler(c);
				System.out.println("Mouvement éffectué !");
				return true;
			}
			else
			{
				System.out.println("Mouvement invaldie  !");
				return false ;
			}
		}
	}
	public boolean deplacer_zone1(int colz2,int colz1,Zone1 z1)
	{
		if (colz2<1 || colz2>7 || colz1<1 ||colz1 >4) {
			System.out.println("Erreur de saisie des indices !");
			return false;
		}
		colz2--;
		Carte c=tab[colz2].sommet();
		if (z1.ajouter_carte(c, colz1))// si on peut ajouter la derniere carte du tas dans zone 1 
		{
			tab[colz2].dépiler();//on depile la carte 
			if(!tab[colz2].est_vide())//et on rend la carte qui la precede visible
			{
				tab[colz2].sommet().rendre_visible();
				
			}
			return true;
		}
		else {//si le deplacement est invalide
			return false;
		}
	}
	public boolean deplacer_zone2(int col1,int pos,int col2) // deplacer une ou plusieur carte d'une pile vers une autre en verifiant l'accord de la suite
	{
		if (col2<1 || col2>7 || col1<1 ||col1 >7 || pos>tab[col1-1].taille+1 || pos<1) {
			System.out.println("Erreur de saisie des indices !");
			return false;
		}
		col1--; pos--; col2--;
		Pile p=new Pile(52); // pile intermédiaire(contient les cartes à deplacer)
		while((tab[col1].taille-pos)!=0)// on depile les cartes à deplacer dans p (pile intermediare) 
		{
			
			p.empiler(tab[col1].dépiler());
			
			if (p.taille>1)
			{
				if (tab[col1].sommet().type==p.sommet().type || tab[col1].sommet().rang<=p.sommet().rang )
					/*si les cartes à deplacer ne suivent pas les conditions de type(!=) et de rang (<) on returne les cartes à leur pile initiale(rien n'est change)*/																								
				{
					while(!p.est_vide())
					{
						p.sommet().affiche();
						tab[col1].empiler(p.dépiler());
					}
					return false;
				}
			}
		}
		
		Carte c=p.dépiler();
		if (ajouter_carte(c,col2)) // on verifie l'accord de la suite // si la 1er carte subit à les condition de l'ajout on fait deplacer les autres cartes de p
		{
			while(!p.est_vide())
			{
				tab[col2].empiler(p.dépiler());
			}
			if (!tab[col1].est_vide()) // rendre la dernier carte de col1 visible si elle existe
			{
				Carte cc=tab[col1].dépiler();
				cc.rendre_visible();
				tab[col1].empiler(cc);
			}
			return true;
		}else // on retourne les cartes dans col1 (rien n'a changé)
		{
			tab[col1].empiler(c);
			while(!p.est_vide())
			{
				tab[col1].empiler(p.dépiler());
			}
			return false;
		}
	}
	public void permutter(int col)// cette methode consiste à permutter les deux derniers cartes visibles d'une pile de rang col dans zone 2 
	{
		col--;
		if (tab[col].taille>=2)
		{
			Carte c1=tab[col].dépiler();
			Carte c2=tab[col].dépiler();
			if (c1.visible==true && c2.visible==true)
			{
				tab[col].empiler(c1);
				tab[col].empiler(c2);
				System.out.println("La permutation est faite  !");
			}else
			{
				tab[col].empiler(c2);
				tab[col].empiler(c1);
				System.out.println("Vous ne pouvez pas permutter dans cette pile !");
			}
		}

	}
	
	
	

}
