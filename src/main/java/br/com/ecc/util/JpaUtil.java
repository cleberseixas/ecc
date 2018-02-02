package br.com.ecc.util;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

public class JpaUtil {
	
	private static EntityManagerFactory factory;
	static {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("ecc");
	}
	
	public static EntityManager getEntitiManager(){
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("ecc");
		
		return factory.createEntityManager();
	}
	
	public static void closeEntityManager(){
		if (factory != null){
			factory.close();
		}
		
		factory = null;
	}
	
	public static boolean isEntityManagerActive(){
		return factory.isOpen();
	}

	public static String getDiretorioReal(String diretorio) {  
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
        return session.getServletContext().getRealPath(diretorio);  
    }
	
	public static String getContextPath() {
		HttpSession session = (HttpSession)	FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}
	
	
}
