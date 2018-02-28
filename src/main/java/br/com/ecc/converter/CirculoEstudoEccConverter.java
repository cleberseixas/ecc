package br.com.ecc.converter;

import br.com.ecc.model.CirculoEstudoEcc;
import br.com.ecc.service.CirculoEstudoEccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=CirculoEstudoEcc.class)
public class CirculoEstudoEccConverter extends BaseConverter<CirculoEstudoEcc, CirculoEstudoEccService> {
}	
