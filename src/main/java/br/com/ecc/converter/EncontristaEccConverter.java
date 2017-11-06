package br.com.ecc.converter;

import br.com.ecc.model.EncontristaEcc;
import br.com.ecc.service.EncontristaEccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=EncontristaEcc.class)
public class EncontristaEccConverter extends BaseConverter<EncontristaEcc, EncontristaEccService> {
}	
