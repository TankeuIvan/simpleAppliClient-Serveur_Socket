package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("Attente de connexion au client... ");
		Socket s = ss.accept();
		System.out.println("Connexion établie avec"+s.getRemoteSocketAddress());
		System.in.wait(5000);
		System.out.println("\nEn attente de réponse..");
		
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr );
		String reponseC = br.readLine();
		String message = "Bonjour M. "+reponseC+". Vous venez de réaliser avec succès le test de la combinaisons des Streams de comm et de traitement.\n\n Brraahvoooh !";
				
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		
		pw.println(message);
		System.out.println("Message traité et envoyé avec succès !");
		
		s.close();

	}

}
