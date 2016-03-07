package br.com.connectWorld.projeto.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		System.out.println(dateFormat.format(date));
		
		int a  = 10;
		double b  = 20;
		double soma = a * b;
		System.out.println(soma);
				
			
	}

}
