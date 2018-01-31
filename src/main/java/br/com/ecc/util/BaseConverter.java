package br.com.ecc.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseConverter<T1, T2 > implements Converter {

	// =======================================================
	// Objeto de classe T1 e seu repositório. 
	// =======================================================
	protected T1 obj;
	protected T2 objRep;
		
	@SuppressWarnings("unchecked")
	protected BaseConverter() {
		Class<T2> clazzRep = (Class<T2>) ((ParameterizedType) getClass().
				getGenericSuperclass()).getActualTypeArguments()[1];
		objRep = CDILocator.getBean(clazzRep);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		T1 retorno = null;
		
		if (null != value &&  value.trim().length() > 0 && !value.contains("Selecione")) {
			//if (null != value &&  value.trim().length() > 0 && !value.equals("Selecione")) {
			try {
				Method	m = objRep.getClass().getMethod("carregar", new Class[]{Long.class});
				retorno = (T1) m.invoke(objRep, new Long(value));
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e.toString());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e.toString());
			}
		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (null != value) {
			obj = (T1) value;
			
			try {
				String classe = obj.getClass().getName();
				classe = classe.substring(classe.lastIndexOf(".")+1,classe.length());
				Long id = (Long) obj.getClass().getMethod("getId").invoke(obj);
				//Long id = (Long) obj.getClass().getMethod("getId"+classe).invoke(obj);
				return id == null ? null : id.toString();
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e.toString());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e.toString());
			}
		}
		return null;
	}
}