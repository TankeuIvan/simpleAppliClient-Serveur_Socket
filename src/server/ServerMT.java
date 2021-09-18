package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread{
	private int nombreClient=0;
	public static void main(String[] args) {
		new ServerMT().start(); //Lorsqu'on crée un objet ServerMT automatiquement on cré un Thread.
								//La methode start() fait automatiquement appel à la methode run()
		
	}
	
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(1234);	//Crée le serveur
			System.out.println("Démarrage du serveur !");
			while(true) {		//boucle pour attendre la connexion d'un client
				Socket s = ss.accept();		//methode bloquante, attend qu'un client se connecte puis à chaque fois crée pour un client qui est connecté
				nombreClient++;
				new Conversation(s, nombreClient).start();		//Crée à chaque fois un thread qui va communiquer avec la socket s de chaque client
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class Conversation extends Thread{  //class Conversation --> classe interne
		private Socket socket;
		private int nombreClient;
		
		public Conversation(Socket s, int nomb) {
			this.nombreClient=nomb;
			this.socket = s;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("Connexion établi avec le client numéro "+nombreClient+ "  "+socket.getRemoteSocketAddress());
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.println("Bienvenu client numéro "+nombreClient);
				while(true) {
					String requette = br.readLine();
					pw.println(requette.length());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
}


