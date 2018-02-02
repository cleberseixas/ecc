package br.com.ecc.converter;

import br.com.ecc.model.CirculoEcc;
import br.com.ecc.service.CirculoEccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=CirculoEcc.class)
public class CirculoEccConverter extends BaseConverter<CirculoEcc, CirculoEccService> {
}	
