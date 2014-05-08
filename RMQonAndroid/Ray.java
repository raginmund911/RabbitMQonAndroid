package com.android.td2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import java.io.*;
import java.util.Scanner;

public class TD2 extends Activity 
{
	// so here is the handler declared inside the activity
	final Handler Callback = new Handler();

	// Variables stockant la valeur courante de l'etat memoire et cpu.
	private String meminfo;
	private String cpuinfo;    

	// Here is the Runnable method the handler will launch
	final Runnable update_meminfo = new Runnable() 
	{
		public void run() {
			EditText txt_meminfo;
			// grab a reference to a screen widget
			txt_meminfo = (EditText) findViewById(R.id.txtmem);
			txt_meminfo.setText("Memoire libre (kB): " + meminfo);
		}	
	};

	//  Here is a thread that, once launched, 
    //  plays the role of our service
	final Thread mem_thread = new Thread() 
	{
		public void run() 
		{		    
			while (true)
			{
				// Recuperer l'info
				meminfo = get_meminfo();

				// here the separate thread notifies the handler that
				// it needs to launch the runnable
				// If this were inside the service (or more to the
                // point, inside a thread launched from the Service,
                // we would need a separate reference to the Handler,
                // perhaps a MyApplication static public variable.
				Callback.post(update_meminfo);

				// "dormir" 2 secondes
				try
				{ 
					Thread.sleep(2000); 
				} 
				catch (InterruptedException e) 
				{ }
			}
		}
	};


	/** Methode appelee lors de la creation de l'Activity */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		// Appel de la methode de base Activity.onCreate.
		super.onCreate(savedInstanceState);

		// Affichage du layout XML dans l'ecran associe a notre Activity. 
		setContentView(R.layout.main);

		// here we launch the thread (or in our casse, the service)
		mem_thread.start();
	}	


	/**
	 * Trouver la qtt RAM libre dans /proc/meminfo
	 *
	 * @return quantite de RAM libre
	 */
	String get_meminfo() {
		try 
		{
			// Ouverture du fichier /proc/meminfo, mise en place du scanner
			File file = new File("/proc/meminfo");
			Scanner scan_file = new Scanner(file);

			// On lit la ligne 1, sans l'enregistrer, c'est la ligne 2 qui
			// contient
			// l'information utile : on la met dans une variable.
			scan_file.nextLine();
			String second_line = scan_file.nextLine();

			// Mise en place d'un second scanner, pour decomposer
			// cette ligne en deux elements, delimites par ':'
			Scanner scan_line = new Scanner(second_line);
			scan_file.useDelimiter(":");

			// la premiere partie contient la chaine "MemFree:", la seconde
			// partie contient
			// l'information recherchee.
			scan_line.next();
			String free_memory = scan_line.next();

			// On ferme ce qu'on a ouvert, et en renvoie la qtt de
			// memoire libre
			scan_line.close();
			scan_file.close();
			return new String(free_memory);
		} 
		catch(Exception e)
		{
			return new String("unknown");
		}
	}

	/**
	 * Trouver dans /proc/stat le % de CPU utilise
	 * @TODO
	 */
	String get_cpuinfo() 
	{
		return new String("unknown");
	}

	/**
	 * Creation d'une methode "callback" appelee lorsqu'on clique
	 * sur le bouton "save"
	 */
	OnClickListener save_to_log = new OnClickListener() 
	{
		public void onClick(View v) 
		{
			// @TODO
		}
	};

}
