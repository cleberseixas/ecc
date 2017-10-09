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
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"login"}, name="uk_login"))
@SequenceGenerator(name = "usuarios", sequenceName = "seq_usuarios", allocationSize=1)
public class Usuario implements Serializable {
	private static final long serialVersionUID = 5783412212859462833L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_usuarios")
	@Column(name="USUARIO")
	private Long id;

	@Column(name="nome", length=50)
	private String nome;

	@Column(length=20)
	private String login;
	
	private String senha;
	private boolean ativo;
	private String email;
	
	private String perfil;
	
	@Column(length=40)
	private String permissao;
	

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result
				+ ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (perfil == null) {
			if (other.perfil != null) {
				return false;
			}
		} else if (!perfil.equals(other.perfil)) {
			return false;
		}
		if (permissao == null) {
			if (other.permissao != null) {
				return false;
			}
		} else if (!permissao.equals(other.permissao)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		return true;
	}
}