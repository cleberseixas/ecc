package br.com.ecc.util;

import org.springframework.security.authentication.BadCredentialsException;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;

/**
 * Classe para autenticação no Active Directory.
 * Extraída de: http://projetos.mpro.gov/projects/gabinete/repository/revisions/master/entry/src/main/java/br/mp/mpro/gabinete/util/LdapUtil.java
 * */

public class LdapUtil {
    static InitialLdapContext ldapContext;

	@SuppressWarnings("unused")
	public static boolean login(String cadastro, String senha)	throws BadCredentialsException {
		try {

			Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.PROVIDER_URL, "ldap://cdm-pvh.mpro.gov:389");
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, cadastro+"@mpro.gov");
			ldapEnv.put(Context.SECURITY_CREDENTIALS, senha);
			ldapContext = new InitialLdapContext(ldapEnv, null);

			// Create the search controls
			SearchControls searchCtls = new SearchControls();

			// Specify the attributes to return
			String returnedAtts[] = { "sn", "givenName", "sAMAccountName","UnicodePwd", "mail" };
			searchCtls.setReturningAttributes(returnedAtts);

			// Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			SearchControls searchControls = new SearchControls(SearchControls.SUBTREE_SCOPE, 100, 0, returnedAtts, false, false);

			// specify the LDAP search filter
			String searchFilter = "(&(objectClass=*))";
			String searchFilter2 = "(&(objectCategory=person)(objectClass=user)(sAMAccountName="+cadastro+"))";

			// Specify the Base for the search
			String searchBase = "dc=mpro,dc=gov";

			// Search for objects using the filter
			NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter2, searchControls);
			
			return true;

		} catch (NamingException e) {
			return false;
		}
	}
}
