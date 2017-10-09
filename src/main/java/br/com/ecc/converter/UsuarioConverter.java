package br.com.ecc.converter;

import br.com.ecc.model.Usuario;
import br.com.ecc.service.UsuarioService;
import br.com.ecc.util.BaseConverter;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Usuario.class, value="usuarioConverter")
public class UsuarioConverter extends BaseConverter<Usuario, UsuarioService> {
}	
