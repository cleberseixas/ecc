package br.com.ecc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ministério Público do Estado de Rondônia
 * Diretoria de Tecnologia da Informação
 * Departamento de Desenvolvimento de Sistemas
 *
 * RegexUtil.java (CFT)
 * Classe responsável pelo controle de expressões regulares.
 *
 * Projeto template
 * Pacote [main]br.mp.mpro.template.util
 *
 * @see "https://pt.wikipedia.org/wiki/Express%C3%A3o_regular"
 * @see "http://aurelio.net/regex/"
 * @see "http://turing.com.br/material/regex/introducao.html"
 *
 * @since 26.07.2016
 */
public class RegexUtil {

    // =======================================================
    // PUBLIC
    // =======================================================

    // =======================================================
    // Expressões regulares comuns.
    // =======================================================
    public static final String CNPJ_PATTERN = "^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}\\-[0-9]{2}$";
    public static final String CPF_PATTERN = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9]+(\\.?[_A-Za-z0-9]+)*@[A-Za-z0-9]{2,}(\\.[A-Za-z]{2,3})?(\\.[A-Za-z]{2})?$";

    /**
     * Valida um valor de acordo com a expressão regular informada; deve levar em conta diferenças
     * entre maiúsculas e minúsculas com o valor booleano informado.
     *
     * @param valor
     * @param expressao
     * @param diferenciar
     * @return boolean
     * @since 26.07.2016
     */
    public static boolean valida(String valor, String expressao, boolean diferenciar) {
        if (null != valor && null != expressao) {
            Pattern pattern;
            if (diferenciar) {
                pattern = Pattern.compile(expressao);
            } else {
                pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
            }
            Matcher matcher = pattern.matcher(valor);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}