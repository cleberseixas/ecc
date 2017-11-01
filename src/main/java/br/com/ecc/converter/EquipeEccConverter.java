package br.com.ecc.converter;

import br.com.ecc.model.EquipeEcc;
import br.com.ecc.service.EquipeEccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=EquipeEcc.class)
public class EquipeEccConverter extends BaseConverter<EquipeEcc, EquipeEccService> {
}	
