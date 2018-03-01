package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa os usuários do sistema. 
 * São dois os perfis possíveis: Dirigente e Usuário.
 * O ECC possui recursos diferenciados para cada um dos
 * perfis contemplados.
 *  
 * @author Cleber Seixas
 * @since 08/10/2017
 */
@Audited
@Entity
@Table(name = "USUARIOS", uniqueConstraints=@UniqueConstraint(columnNames={"login"}, name="uk_login"))
@SequenceGenerator(name = "seq_usuarios", sequenceName = "seq_usuarios", allocationSize=1)
public class Usuario implements Serializable {
	private static final long serialVersionUID = 5783412212859462833L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_usuarios")
	@Column(name = "USUARIO", nullable = false)
	private Long id;


	@Column(name="NOME", length=50)
	private String nome;

	@Column(name="login", length=50)
	private String login;

	@Column(name="SENHA", length=255)
	private String senha;

	@Column(name="ATIVO", nullable = false)
	private boolean ativo;

	@Column(name="EMAIL", length=255)
	private String email;

	@Column(name="PERFIL", length=50)
	private String perfil;
	
	@Column(name="PERMISSAO", length=40)
	private String permissao;

	@Column(name="DIRIGENTE", nullable = false)
	private boolean dirigente;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public boolean getDirigente() {
		return dirigente;
	}

	public void setDirigente(boolean dirigente) {
		this.dirigente = dirigente;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Usuario usuario = (Usuario) o;

		return id != null ? id.equals(usuario.id) : usuario.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}