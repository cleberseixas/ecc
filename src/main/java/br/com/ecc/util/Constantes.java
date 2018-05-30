package br.com.ecc.util;

import javax.faces.context.FacesContext;

public class Constantes {
	public static final String CAMINHO_FOTOS_WINDOWS = "D:/Usuários/44260/fotos/";
	public static final String CAMINHO_FOTOS_LINUX = "/home/cleber/fotosECC/fichas/";

	public static final String ENCONTREIRO = "ENCONTREIRO";
	public static final String ENCONTRISTA = "ENCONTRISTA";

	public static final String urlAplicacao = "http://179.155.225.37:9970/ecc/login.xhtml";


	//public static final String URL_BIRT_LOCAL ="http://localhost:8090/birt-viewer/frameset?__report=mpro/protocoloM";

	//Formato PDF --> http://179.155.225.37:9970/birt/run?__report=relatorios/rptAptidoes.rptdesign&aptidao=12&__format=pdf";
	//Formato BIRT --> "http://179.155.225.37:9970/birt/frameset?__report=";

	//public static final String URL_BIRT = 	"http://179.155.225.37:9970/birt/run?__format=pdf&__report=relatorios";

	public static final String URL_BIRT_LOCAL = 	"http://localhost:9970/birt/run?__format=pdf&__report=report";
	public static final String URL_BIRT_SERVER = 	"http://ip.ddns.net:9970/birt/run?__format=pdf&__report=report";
	//public static final String URL_BIRT_SERVER = 	"http://179.155.225.37:9970/birt/run?__format=pdf&__report=report";
	//public static final String URL_BIRT_SERVER = 	"http://ip.bragasoft.com.br:9970/birt/run?__format=pdf&__report=report";

	//FacesContext facesContext = FacesContext.getCurrentInstance();


}
