import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Jeu {
	public static void help(Pioche p,Zone1 z1,Zone22 z2)


{
	int nbr=0;//nombre de solutions trouvés
	// 1) On va étudier les mouvements possibles du pioche vers la zone1 : 
	if(p.p2.est_vide()==false)
	{
	for(int i=0;i<4;i++)
	{
	   if(z1.mouvement_possible(p.p2.sommet(),i))
	   {
	       System.out.println("Pioche -->Tas n°"+(i+1)+" de la zone1");nbr++;
	   }
	      
	}
	}
	// 2)On va étudier les mouvements possibles de la zone2 vers la zone1:
	for(int i=0;i<7;i++)
	{
	   if(z2.tab[i].est_vide()==false)
	   {
	   for(int j=0;j<4;j++)
	   { 
	          if(z1.mouvement_possible(z2.tab[i].sommet(),j))
	   {
	       System.out.println("Tas n°"+(i+1)+" de la zone2 --> tas n° "+(j+1)+" de la zone1");nbr++;
	   }
	   }
	   }
	}
	// 3)On va étudier les mouvements possibles de la zone1 vers la zone2:
	 for(int i=0;i<4;i++)
	{
	   if(z1.Tab[i].est_vide()==false)
	   {
	   for(int j=0;j<7;j++)
	   { 
	          if(z2.mouvement_possible(z1.Tab[i].sommet(),j))
	   {
	       System.out.println("Tas n°"+(i+1)+" de la zone1 --> tas n° "+(j+1)+" de la zone2");nbr++;
	   }
	   }
	   }
	}
	// 4) On va étudier les mouvements possibles du pioche vers la zone2 : 
	if(p.p2.est_vide()==false)
	{
	for(int i=0;i<7;i++)
	{
	   if(z2.mouvement_possible(p.p2.sommet(),i))
	   {
	       System.out.println("Pioche -->Tas n°"+(i+1)+" de la zone2");nbr++;
	   }
	      
	}
	}
	// 3)On va étudier les mouvements possibles à l'intérieur de la zone2:
	
	    for(int i=0;i<7;i++)
	    {
	       for(int j=0;j<z2.tab[i].taille;j++)
		    {
	            if(z2.tab[i].tab[j].visible==true)   
	             { 
	                  for(int k=0;k<7;k++)
	                      {
	                	     if(k!=i &&z2.mouvement_possible(z2.tab[i].tab[j],k))
	                         {System.out.println("Tas n°"+(i+1)+" de la zone 2,à partir de la carte n°"+(j+1)+" vers le tas n°"+(k+1)+" de la zone2.");nbr++;}
	                      }

	             }
	         }
	    }
	    if(nbr==0) {System.out.println("Aucun mouvement possible! Afficher une carte de pioche");}	    
	
}
	public static int nbre_mouvement(Pioche p,Zone1 z1,Zone22 z2)
	{
		int nbr=0;//nombre de solutions trouvés
		 
		if(p.p2.est_vide()==false)
		{
		for(int i=0;i<4;i++)
		{
		   if(z1.mouvement_possible(p.p2.sommet(),i))
		   {
		       nbr++;
		   }
		      
		}
		}
		
		for(int i=0;i<7;i++)
		{
		   if(z2.tab[i].est_vide()==false)
		   {
		   for(int j=0;j<4;j++)
		   { 
		          if(z1.mouvement_possible(z2.tab[i].sommet(),j))
		   {
		       nbr++;
		   }
		   }
		   }
		}
		 for(int i=0;i<4;i++)
		{
		   if(z1.Tab[i].est_vide()==false)
		   {
		   for(int j=0;j<7;j++)
		   { 
		          if(z2.mouvement_possible(z1.Tab[i].sommet(),j))
		   {
		       nbr++;
		   }
		   }
		   }
		}
		
		if(p.p2.est_vide()==false)
		{
		for(int i=0;i<7;i++)
		{
		   if(z2.mouvement_possible(p.p2.sommet(),i))
		   {
		       nbr++;
		   }
		      
		}
		}
		
		
		    for(int i=0;i<7;i++)
		    {
		       for(int j=0;j<z2.tab[i].taille;j++)
			    {
		            if(z2.tab[i].tab[j].visible==true)   
		             { 
		                  for(int k=0;k<7;k++)
		                      {
		                	     if(k!=i &&z2.mouvement_possible(z2.tab[i].tab[j],k))
		                         {nbr++;}
		                      }

		             }
		         }
		    }
		   
		    return nbr; 
		    
		
	}
	public static boolean jeu_bloqué(Pioche p,Zone1 z1,Zone22 z2)
	{
		int t=p.p1.taille+p.p2.taille+1;
		int n=0;
		for(int i=0;i<t;i++)
		{n+=nbre_mouvement(p,z1,z2);
		p.afficher_carte();
		}
		return(n==0);
		 
	}
	public static void main(String [] args)
	{
		Carte [] tab=new Carte[52];
		String [] type= {"TR","PI","CO","CA"};		
		int l=-1;
		for (int i=1;i<=13;i++)
		{
			for (int j=0;j<=3;j++)
			{
					Carte c=null;
					l++;
					if (type[j]=="TR" || type[j]=="PI")
					{
						 c=new Carte('N', i , type[j] , true);
						
					}
					if (type[j]=="CO" || type[j]=="CA")
					{
						 c=new Carte('R', i , type[j] , true);
						
					}
					
					tab[l]=c;
				
			}
		}
		
		List<Carte> tablist = Arrays.asList(tab);
		Collections.shuffle(tablist);
		
		Zone1 z1=new Zone1();		
		Carte []tabzone2=new Carte [28];
		for (int i=0;i<28;i++)
		{
			tabzone2[i]=tablist.get(i);
		}
		
		Carte [] tabpioche=new Carte [24];
		for (int i=0;i<24;i++)
		{
			tabpioche[i]=tablist.get(i+28);
		}		
		Pioche p=new Pioche(tabpioche);
		Zone22 z2=new Zone22(tabzone2);
    	long start = System.currentTimeMillis();
    	int move=0;
    	int score=0;
		while(true)
		{    	long finish = System.currentTimeMillis();
	    		long tempsSecond =(finish - start)/1000;
	    		String time=String.format("%02d:%02d", (int)(tempsSecond/60),tempsSecond%60) ;
	    		System.out.println("*************************************************************************************************************************************");
	        	System.out.println("Time : "+time+"\t\t\tMove : "+move+"\t\t\tScore : "+score);
	    		System.out.println("*************************************************************************************************************************************");

			p.afficher_pioche();
			z1.affiche();
			z2.afficher();
    		System.out.println("\n***************************************************************************************************************************************");
			System.out.println("\n1-Afficher une carte du pioche" + 
					"\t\t\t2-Utiliser la carte du pioche"+
					"\n3-Deplacer une carte du zone 2 vers Zone 1" + 
					"\t4-Deplacer une carte du Zone 1 vers Zone 2" + 
					"\n5-Deplacer une carte dans Zone 2"+
					"\n10-aide\t\t\t\t\t\t0-Quitter\n");
			Scanner scanner=new Scanner(System.in);
			int n=scanner.nextInt();
			
			
			switch(n)
			{
			case 0:
				System.out.println("Votre score est "+score+" !");
				System.out.println("Vous avez quittez le jeu !");
				System.exit (-1);
				break;
			case 1:
				if(p.afficher_carte())
				{
					move++;
				}
				break;
			case 2:
				System.out.println("1-Zone 1 ? || 2-Zone 2 ?");
				Scanner scanner2=new Scanner(System.in);
				 int repn=scanner2.nextInt();
				
				if(repn==1)
				{
					System.out.println("Entrer numero de pile :");
					Scanner scanner3=new Scanner(System.in);
					int col=scanner3.nextInt();
					if(p.déplacer_zone1(col, z1))
					{
						move++;
						score+=10;
					}
					
				}else if(repn==2)
				{
					System.out.println("Entrer numero de pile :");
					Scanner scanner4=new Scanner(System.in);
					int col=scanner4.nextInt();
					if(p.déplacer_zone2(col, z2))
					{
						move++;
						score+=2;
					}
					
					
				}
				break;
			case 3:
				System.out.println("Entrer numero de pile de zone 2 puis zone 1 : ");
				Scanner scanner5=new Scanner(System.in);
				int colz2=scanner5.nextInt();
				int colz1=scanner5.nextInt();
				if(z2.deplacer_zone1(colz2, colz1, z1))
				{
					move++;
					score+=10;
				}
				break;
			case 4:
				System.out.println("Entrer numero de pile de zone 1 puis zone 2 : ");
				Scanner scanner6=new Scanner(System.in);
				int colz11=scanner6.nextInt();
				int colz22=scanner6.nextInt();
				if(z1.deplacer_zone2(colz11, colz22, z2))
				{
					move++;
					score+=2;
				}
				break;
			case 5:
				System.out.println("Entrer numero de pile de la/les carte à deplacer | sa position | la pile finale : ");
				Scanner scanner7=new Scanner(System.in);
				int col1=scanner7.nextInt();
				int pos=scanner7.nextInt();
				int col2=scanner7.nextInt();
				if(z2.deplacer_zone2(col1, pos, col2))
				{
					move++;
					score+=2;
				}
				break;
			case 6:
				System.out.println("Entrer numero de pile : ");
				Scanner scanner8=new Scanner(System.in);
				int per=scanner8.nextInt();
				z2.permutter(per);
				break;
			case 10:
				System.out.println("***********************************Aide**********************************");
				help(p,z1,z2);
				break;
				
			}
			
			
			if (z1.est_terminé()==true)
			{
				System.out.println("Félicitation ! Vous avez gagné.");
				System.exit(-1);
			}
			if(jeu_bloqué(p,z1,z2))
			{
				System.out.println("0-Quitter\t1-Permutter");
				Scanner scanner9=new Scanner(System.in);
				int i=scanner9.nextInt();
				scanner9.close();
				switch(i)
				{
				case 0:
					System.out.println("Votre score est "+score+" !");
					System.out.println("Vous avez quittez le jeu !");
					System.exit(-1);
					break;
				case 1:
					System.out.println("Entrer numero de pile : ");
					Scanner scanner10=new Scanner(System.in);
					int col=scanner10.nextInt();
					z2.permutter(col);
					break;
				}
			}
		}
		
		
		
		
		
	
	
	
}
}