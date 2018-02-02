package br.com.ecc.converter;

import br.com.ecc.model.Equipe;
import br.com.ecc.service.EquipeService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Equipe.class, value="equipeConverter")
public class EquipeConverter extends BaseConverter<Equipe, EquipeService> {
}	
