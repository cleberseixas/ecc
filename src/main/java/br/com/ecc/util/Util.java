package br.com.ecc.util;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {
	
	public static int getAno(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}
	
	public static String getPrimeiroNome(String nome) {
		return nome.substring(0,nome.indexOf(" "));
	}
	
	public static String getPrimeiroESegundoNome(String nome) {
		String nomeResult = "";
		String[] nomes = nome.split(" ");
		if (nomes.length == 1) {
			nomeResult = nomes[0];
		} else {
			if (validaNome(nomes[1]) == false) {
				nomeResult = nomes[0] + " " + nomes[1] + " " + nomes[2];
			} else {
				nomeResult = nomes[0] + " " + nomes[1];
			}
		}
		return nomeResult;
	}
	public static String retornaPathFotoSistemaOperacional() {
		String result = "";
		String sistemaOperacional = System.getProperty("os.name");
		if (sistemaOperacional.equals("Linux")) {
			result = Constantes.CAMINHO_FOTOS_LINUX;
		} else {
			result = Constantes.CAMINHO_FOTOS_WINDOWS;
		}
		return result;
	}

	private static boolean validaNome(String nome) {
		boolean valido = true;
		String[] nomes = {"dos","das","de","da","e","do"};
		for (String pl : nomes) {
			if (pl.equals(nome)) {
				valido = false;
				break;
			}
		}
		return valido;
	}
	
	public static Integer diasEntreDatas(Date dataInicial, Date dataFinal) {
		DateTime dI = new DateTime(dataInicial);
		DateTime dF = new DateTime(dataFinal);
		return Days.daysBetween(dI, dF).getDays();
	}

	public static String getDataFormatada(Date date) {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		return dt.format(date);
	}
	
	public static String getDataExtenso(Date date) {
		Locale local = new Locale("pt","BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",local); 
		return "Porto Velho/RO, " + dateFormat.format(date) + ".";
	}

	public static String getFormataDataExtenso(Date date) {
		Locale local = new Locale("pt","BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",local); 
		return dateFormat.format(date).toLowerCase();
	}

	public static String getSaudacao() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.HOUR_OF_DAY) <= 12 ? "Bom dia." : "Boa tarde.";
	}
	
	public static Date strToDate(String data) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(data);
		} catch (ParseException e) {
			throw new NegocioException("Erro ao recuperar data atual.");
		}
	}
	
	public static String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]","");
		str = str.replaceAll(" ", "_");
		return str;
	}


	public static String formataNumero(Long numero) {
		if (numero == 0) {
			return "--";
		} else if (numero < 10) {
			return "0"+numero;
		} else {
			return ""+numero;
		}
	}
	
	public static String formataNumero(BigInteger numero) {
		if (numero != null) {
			Integer n = new Integer(numero.intValue());
			if (n == 0) {
				return "--";
			} else if (n < 10) {
				return "0"+n;
			} else {
				return ""+n;
			}
		} else {
			return "--";
		}
	}
	
	public static Date primeiroDiaAno() {
		int ano = getAno(new Date());
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String primeiroDia = "01/01/"+ano;
		try {
			return format.parse(primeiroDia);
		} catch (ParseException e) {
			return new Date();
		}
	}

	
	public static Date ultimoDiaAno() {
		int ano = getAno(new Date());
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String primeiroDia = "31/12/"+ano;
		try {
			return format.parse(primeiroDia);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static Integer diasSemFinaisDeSemana(Date start, Date end){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);
		int w1 = c1.get(Calendar.DAY_OF_WEEK);
		c1.add(Calendar.DAY_OF_WEEK, -w1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		int w2 = c2.get(Calendar.DAY_OF_WEEK);
		c2.add(Calendar.DAY_OF_WEEK, -w2);

		//end Saturday to start Saturday 
		long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
		long daysWithoutSunday = days-(days*2/7);

		Long dias = daysWithoutSunday-w1+w2;
		return dias.intValue();

	}
	
	public static String criptografiaNAT(String entrada) {
		List<String> cripto = new ArrayList<String>();
		cripto.add("R2D2");
		cripto.add("ML23");
		cripto.add("S481");
		cripto.add("FF2G");
		cripto.add("389T");
		cripto.add("HH29");
		cripto.add("LVBS");
		cripto.add("K29K");
		cripto.add("RS23");
		cripto.add("XPRS");
		String senha = entrada;
		String senhaNova = "";
		for (int i=0; i<senha.length(); i++) {
			int r = senha.charAt(i) - 48;
			senhaNova += cripto.get(r);
		}
		return senhaNova;
	}
	
	public static String descriptografiaNAT(String entrada) {
		List<String> cripto = new ArrayList<String>();
		cripto.add("R2D2");
		cripto.add("ML23");
		cripto.add("S481");
		cripto.add("FF2G");
		cripto.add("389T");
		cripto.add("HH29");
		cripto.add("LVBS");
		cripto.add("K29K");
		cripto.add("RS23");
		cripto.add("XPRS");
		String trecho = "";
		String result = "";
		int cont = 0;
		for (int i = 0; i<entrada.length(); i++) {
			cont++;
			trecho += entrada.charAt(i);
			if (cont == 4) {
				int r = cripto.indexOf(trecho);
				result += r;
				trecho = "";
				cont = 0;
			}
		}
		return result;
	}

	private static String removeMascaras(String numero) {
		String apenasNumeros = "";
		String digitos = "0123456789";
		for (int i=0; i<numero.length(); i++) {
			for (int j=0; j<digitos.length(); j++) {
				if (numero.charAt(i) == digitos.charAt(j)) {
					apenasNumeros += numero.charAt(i);
					break;
				}
			}
		}
		return apenasNumeros;
	}
}
