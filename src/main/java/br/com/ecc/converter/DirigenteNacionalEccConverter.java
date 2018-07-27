package br.com.ecc.converter;

import br.com.ecc.model.DirigenteNacionalEcc;
import br.com.ecc.service.DirigenteNacionalEccService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=DirigenteNacionalEcc.class)
public class DirigenteNacionalEccConverter extends BaseConverter<DirigenteNacionalEcc, DirigenteNacionalEccService> {
}	
