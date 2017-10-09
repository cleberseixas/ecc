package br.com.ecc.converter;

import br.com.ecc.model.Circulo;
import br.com.ecc.util.BaseConverter;
import br.com.ecc.service.CirculoService;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Circulo.class)
public class CirculoConverter extends BaseConverter<Circulo, CirculoService> {
}	
