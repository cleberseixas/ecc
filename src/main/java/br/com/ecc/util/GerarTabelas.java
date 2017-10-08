package br.com.ecc.util;

import javax.persistence.Persistence;

public class GerarTabelas {
	public static void main(String[] args) {
		 Persistence.createEntityManagerFactory("astec");
		 System.out.println("Sucesso!");
	}	
}	