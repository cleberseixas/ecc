package br.com.ecc.converter;

import br.com.ecc.model.Palestra;
import br.com.ecc.model.Palestrante;
import br.com.ecc.service.PalestraService;
import br.com.ecc.service.PalestranteService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Palestrante.class)
public class PalestranteConverter extends BaseConverter<Palestrante, PalestranteService> {
}	
