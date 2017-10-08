package br.com.ecc.util;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//imMort org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe utilizada para fazer a autenticação do usuário no sistema, com base no Active Directory e 
 * no Perfil cadastrado no banco de dados do sistema.
 * 
 * Extraído de: http://www.cleancode.co.nz/blog/937/customization-spring-security-authentication
 * 04-07-2014
 * */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static EntityManager manager;
 
	public CustomAuthenticationProvider(){
		if (manager != null && manager.isOpen()) 
            manager.close(); 
		manager = JpaUtil.getEntitiManager(); 
	}
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String cadastro = authentication.getName();
        String senha = authentication.getCredentials().toString();
        
        //--------------------------------------------------------------------------------------------------
        //trecho de código necessário pq não foi possível injetar um bean CDI para usuarioRN durante a 
        //autenticação. Por conta disso, efetuamos a busca pelo usuario logado adicionando o a pesquia
        //diretamente no código. 
//        UsuarioAstec usuario = null;
//
//        TypedQuery<UsuarioAstec> query = manager.createQuery("from UsuarioAstec where login = :LG and ativo = :ST", UsuarioAstec.class);
//		query.setParameter("LG", cadastro);
//		query.setParameter("ST", true);
//		if (query.getResultList().size() > 0)
//			usuario = query.getSingleResult();
//        if (usuario == null)
//        	return null;
//        //--------------------------------------------------------------------------------------------------
//
//    	if (LdapUtil.login(cadastro, senha)){
//    		String permissao = usuario.getPermissao();
//
//            List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            grantedAuths.add(new SimpleGrantedAuthority(permissao));
//            Authentication auth = new UsernamePasswordAuthenticationToken(cadastro, senha, grantedAuths);
//            return auth;
//        }
        return null;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}