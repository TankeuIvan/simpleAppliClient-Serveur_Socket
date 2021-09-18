package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class MyClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Connexion au serveur réalisée avec succès !");
		Socket s = new Socket("localhost", 1234);
		
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr );
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrer votre nom:");
		String nom = scan.nextLine();
	
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		pw.println(nom);
		System.out.println("Message envoyé au  serveur !\n");
		String reponseS = br.readLine().toString();
		
		System.out.println("La réponse du serveur est: \n\n"+reponseS);
		
	}

}
