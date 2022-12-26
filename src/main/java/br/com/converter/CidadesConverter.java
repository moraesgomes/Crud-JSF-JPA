package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Cidades;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Cidades.class , value = "cidadeConverter")
public class CidadesConverter implements Converter , Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override /*retorna objeto inteiro*/
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		
		
		EntityManager  entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Cidades cidades =(Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
		
		return cidades;
	}

	@Override /*Retorna apenas o código em String */
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		
		if(cidade ==null) {
			
			return null;
		}
		
		if(cidade instanceof Cidades) {
			
			return ((Cidades) cidade).getId().toString();
			
		}else {
			
			return cidade.toString();
		}
			
			
	
	}


}
