package br.com.ecc.util;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.Normalizer;
import java.text.ParseException;

/**
 * Ministério Público do Estado de Rondônia
 * Diretoria de Tecnologia da Informação
 * Departamento de Desenvolvimento de Sistemas
 *
 * StringUtil.java (CFT)
 * Classe com métodos utilitários para strings.
 *
 * Projeto template
 * Pacote [main]br.mp.mpro.template.util
 *
 * @since 15.07.2016
 */
public class StringUtil {

	// =======================================================
	// Tamanho máximo de uma string retornada pelo método
	// encurta().
	// =======================================================
	private static final int TAMANHO_MAXIMO_STRING = 25;

	// =======================================================
	// PUBLIC
	// =======================================================

	/**
	 * Insere determinado prefixo na string informada, tantas vezes quantas forem necessárias,
	 * até que o tamanho final seja alcançado. Caso o tamanho final informado seja menor que o
	 * tamanho da string inicial, ou a diferença entre estes seja menor que o tamanho do prefixo,
	 * apenas a string informada será retornada.
	 *
	 * @param valor
	 * @param prefixo
	 * @param tamanhoFinal
	 * @return String
	 * @since 15.07.2016
	 */
	public static String inserePrefixo(String valor, String prefixo, long tamanhoFinal) {
		if (!contemNulo(valor)) {
			StringBuffer novoValor = new StringBuffer();
			long diferenca = tamanhoFinal - valor.length();
			if ((diferenca > 0L) && (prefixo.length() <= diferenca)) {
				while (novoValor.length() + prefixo.length() <= diferenca) {
					novoValor.append(prefixo);
				}
			}
			novoValor.append(valor);
			return novoValor.toString();
		}
		return valor;
	}

	/**
	 * Insere determinado sufixo na string informada, tantas vezes quantas forem necessárias,
	 * até que o tamanho final seja alcançado. Caso o tamanho final informado seja menor que o
	 * tamanho da string inicial, ou a diferença entre estes seja menor que o tamanho do sufixo,
	 * apenas a string informada será retornada.
	 *
	 * @param valor
	 * @param sufixo
	 * @param tamanhoFinal
	 * @return String
	 * @since 15.07.2016
	 */
	public static String insereSufixo(String valor, String sufixo, long tamanhoFinal) {
		if (!contemNulo(valor)) {
			StringBuffer novoValor = new StringBuffer(valor);
			long diferenca = tamanhoFinal - valor.length();
			if ((diferenca > 0L) && (sufixo.length() <= diferenca)) {
				while (novoValor.length() + sufixo.length() <= tamanhoFinal) {
					novoValor.append(sufixo);
				}
			}
			return novoValor.toString();
		}
		return valor;
	}

	/**
	 * Verifica se determinada string possui apenas números.
	 *
	 * @param valor
	 * @return boolean
	 * @since 21.07.2016
	 */
	public static boolean isNumero(String valor) {
		if (!contemNulo(valor)) {
			for (char c : valor.toCharArray()) {
				if (!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Retorna uma string apenas com os números contidos no argumento.
	 *
	 * @param valor
	 * @return String
	 * @since 26.07.2016
	 */
	public static String transformaEmNumero(String valor) {
		if (!contemNulo(valor)) {
			StringBuffer novoValor = new StringBuffer();
			for (char c : valor.toCharArray()) {
				if (Character.isDigit(c)) {
					novoValor.append(c);
				}
			}
			return novoValor.toString();
		}
		return valor;
	}

	/**
	 * Retorna uma string sem os números contidos no argumento.
	 *
	 * @param valor
	 * @return String
	 * @since 26.07.2016
	 */
	public static String removeNumero(String valor) {
		if (!contemNulo(valor)) {
			StringBuffer novoValor = new StringBuffer();
			for (char c : valor.toCharArray()) {
				if (!Character.isDigit(c)) {
					novoValor.append(c);
				}
			}
			return novoValor.toString();
		}
		return valor;
	}

	/**
	 * Retorna uma string em minúsculas.
	 *
	 * @param valor
	 * @return String
	 * @since 26.07.2016
	 */
	public static String minusculas(String valor) {
		if (!contemNulo(valor)) {
			return valor.toLowerCase();
		}
		return valor;
	}

	/**
	 * Retorna uma string em maiúsculas.
	 *
	 * @param valor
	 * @return String
	 * @since 26.07.2016
	 */
	public static String maiusculas(String valor) {
		if (!contemNulo(valor)) {
			return valor.toUpperCase();
		}
		return valor;
	}

	/**
	 * Verifica se determinada string está contida em outra.
	 *
	 * @param valor
	 * @param valorASerBuscado
	 * @return boolean
	 * @since 28.07.2016
	 */
	public static boolean contem(String valor, String valorASerBuscado) {
		if (!contemNulo(valor, valorASerBuscado)) {
			return valor.contains(valorASerBuscado);
		}
		return false;
	}

	/**
	 * Verifica se alguma string contida nos argumentos passados como parâmetros
	 * está nula ou vazia.
	 *
	 * @param strings
	 * @return boolean
	 * @since 16.09.2016
	 */
	public static boolean contemNulo(String... strings) {
		for (String s : strings) {
			if (null == s || s.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a primeira string de um nome qualquer.
	 *
	 * @param nome
	 * @return String
	 * @since 11.10.2016
	 */
	public static String primeiroNome(String nome) {
		if (!contemNulo(nome)) {
			String[] partes = nome.split(" ");
			return partes[0];
		}
		return nome;
	}

	/**
	 * Retorna a última string de um nome qualquer.
	 *
	 * @param nome
	 * @return String
	 * @since 11.10.2016
	 */
	public static String ultimoNome(String nome) {
		if (!contemNulo(nome)) {
			String[] partes = nome.split(" ");
			return partes[partes.length - 1];
		}
		return nome;
	}

	/**
	 * Retorna o valor passado como argumento com tamanho limitado em X caracteres (25 por padrão),
	 * para melhor exibição.
	 *
	 * @param valor
	 * @return String
	 * @since 31.10.2016
	 */
	public static String encurta(String valor) {
		if (!contemNulo(valor)) {
			if (valor.length() > TAMANHO_MAXIMO_STRING) {
				return String.format("%s...", valor.substring(0, TAMANHO_MAXIMO_STRING - 3));
			}
		}
		return valor;
	}

	/**
	 * Formata uma string com determinado padrão.
	 *
	 * @param valor
	 * @param padrao
	 * @return String
	 * @since 23.01.2017
	 */
	public static String formata(String valor, String padrao) {
		String resultado = valor;
		if (!contemNulo(valor) && !contemNulo(padrao)) {
			try {
				MaskFormatter mascara = new MaskFormatter(padrao);
				JFormattedTextField formato = new JFormattedTextField(mascara);
				formato.setText(valor);
				resultado = formato.getText();
			} catch (ParseException e) {
				//LogUtil.error(String.format("Erro de formatação da string '%s'!", valor), StringUtil.class);
			}
		}
		return resultado;
	}

	/**
	 * Remove os acentos dos caracteres de uma string.
	 *
	 * @param valor
	 * @return String
	 * @since 10.04.2017
	 */
	public static String removeAcentos(String valor) {
		if (!contemNulo(valor)) {
			return Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}
		return valor;
	}
}