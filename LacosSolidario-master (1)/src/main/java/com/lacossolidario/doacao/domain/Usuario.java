package com.lacossolidario.doacao.domain;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.lacossolidario.doacao.app.dto.DadosAtualizacaoUsuario;
import com.lacossolidario.doacao.infra.model.DadosCadastroUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "tb_usuarios")
@Entity(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String telefone;
    private Boolean ativo;
   
    @Lob
	private byte[] imagem;
   
   
    @Column(name = "tipo_de_usuario")
    private String tipoDeUsuario;

    
    
    public Usuario() {
		super();
	}

	public Usuario(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.login = dados.login();
        this.telefone = dados.telefone();
        this.senha = dados.senha();
        this.tipoDeUsuario = dados.tipoDeUsuario();
      
 

    }

    public Usuario(String nome, String login, String encryptedPassword, String telefone2, String tipoDeUsuario2, String cpf) {
    	this.ativo = true;
    	  this.nome = nome;
          this.login = login;
          this.telefone = telefone2;
          this.senha = encryptedPassword;
          this.tipoDeUsuario = tipoDeUsuario2;
          
	}

    public void atualizarDados(DadosAtualizacaoUsuario dados, MultipartFile imagem) throws IOException {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.login() != null) {
            this.login = dados.login();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (imagem != null && !imagem.isEmpty()) {
            this.imagem = imagem.getBytes();
        }
    }


	
	
   

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void desativar() {
        this.ativo = false;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("USER_ROLE"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}