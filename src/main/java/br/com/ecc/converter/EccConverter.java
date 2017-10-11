package br.com.ecc.converter;

import br.com.ecc.model.Ecc;
import br.com.ecc.service.EccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Ecc.class)
public class EccConverter extends BaseConverter<Ecc, EccService> {
}	
