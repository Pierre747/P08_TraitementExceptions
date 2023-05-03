/*
 * Étudier les exceptions, leur propagation, les rattraper, comment lever des exceptions,
 * comment créer des classes d'exceptions personnalisées.
 */

package fr.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class MonApplication
{

	public static void main(String[] args)
	{
		// BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

		// Vu que l'instruction passée est trop longue, on va utiliser :
		
		Scanner clavier = new Scanner(System.in); // On importe : import java.util.Scanner;
		
		// Je veux lire le clavier à chque utilisation de la touche entrée
		// et, à chaque lecture, traiter une exception qui a été levée de façon aléatoire.
		// Je veux également sortir de cette boucle éternelle en utilisant Q<ENTREE>.
		
		String saisie;
		
		while(true)
		{
			System.out.println("Appuyez sur la touche 'entrée' pour continuer ou 'Q + entrée' pour quitter l'application");
			
			// lecture de la saisie:
			
			saisie = clavier.nextLine();
			
			// On affiche la saisie de l'utilisateur
			
			System.out.println(saisie);
			
			if(saisie.equalsIgnoreCase("Q"))
				System.exit(0); // On met 0 car cette sortie n'est pas relative à une erreur
			
			f1();
			
		} // Fin du while
		
		
		
		
	} // Fin du Main

	private static void f1()
	{
		// Pour faire de l'aléatoire en Java, on utilise la classe util
		Random rnd = new Random();
		int i = rnd.nextInt(4);
		
		try
		{
			switch(1)
			{
			case 0:
				System.out.println("Appel de la méthode : f2a()");
				f2a();
				break;
			case 1:
				System.out.println("Appel de la méthode : f2b()");
				f2b();
				break;
			default:
				throw new IllegalArgumentException("Unexpected Value : " + i);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Le message de l'exception est : " + ex.getMessage());
			System.out.println("Le type d'exception : " + ex.getClass().getName() + " dans f1()");
			System.out.println("Ceci est un traitement personnalisé pour la division par 0");
		}
		
		
		
	} // Fin de f1
	
	private static void f2a()
	{
		String maChaine = null;
		try
		{
			maChaine.trim();
		}
		catch(NullPointerException ex)
		{
			System.out.println("Le type d'exception : " + ex.getClass().getName() + " dans f2a()");
			System.out.println("Le message de l'exception est : " + ex.getMessage());
			System.out.println("Pile du programme : ");
			ex.printStackTrace();
			
			System.out.println("Je traite l'exception au niveau local mais par erreur, j'en lève une autre.");
			
			int zero = 0;
			
			int i = 5 / zero; // Ici une exception sera levée. Comme elle n'est pas traitée localement,
			// elle va se propager vers la méthode qui l'appelle (bubbling) : f1()
			
			System.out.println("Je suis juste après la division par zéro");
		}
		finally // Ce bloc est optionnel et s'exécute dans tous les cas (avec ou sans exception)
		{
			System.out.println("Je passe par le bloc finally de f2a()");
		}
		
	} // fin de f2a()

	private static void f2b()
	{
		// Lever une exception d'indexation
		
		// On va créer un tableau de 3 éléments
		
		int[] tb = new int[3];
		
		tb[8] = 2;
		try
		{
			
		}
		catch(Exception ex)
		{
			System.out.println("Le message de l'exception est : " + ex.getMessage());
			System.out.println("Le type d'exception : " + ex.getClass().getName() + " dans f2b()");
			System.out.println("Ceci est un traitement personnalisé pour l'exception d'indexation");
		}

	}
	
} // Fin de l'Application


