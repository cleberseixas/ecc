package br.com.ecc.converter;

import br.com.ecc.model.Palestra;
import br.com.ecc.service.PalestraService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Palestra.class, value="palestraConverter")
public class PalestraConverter extends BaseConverter<Palestra, PalestraService> {
}	
