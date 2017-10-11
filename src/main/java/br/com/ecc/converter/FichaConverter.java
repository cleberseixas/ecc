package br.com.ecc.converter;

import br.com.ecc.model.Circulo;
import br.com.ecc.model.Ficha;
import br.com.ecc.service.CirculoService;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Ficha.class, value="fichaConverter")
public class FichaConverter extends BaseConverter<Ficha, FichaService> {
}	
