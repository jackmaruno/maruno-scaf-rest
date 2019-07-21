/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:53:44 AM
 * @Pacote br.com.maruno.app.domain
 * @Nome Usuario.java
 * @NomeCompleto br.com.maruno.app.domain.Usuario.java
 */
@Entity
@Table(name = "TB_USUARIO")//, schema = Domain.SCHEMA)
public class Usuario extends Domain {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_USUARIO") 
	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_USUARIO")
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_LOGIN")
	private String login;

	@JsonIgnore
	@Column(name = "DES_SENHA")
	private String senha; 
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_EMAIL")
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_FOTO")
	private String foto;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_CADASTRO")
	private Date dataCadastro; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_ATUALIZACAO")
	private Date dataAtualizacao;  

	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido = false;

	@ManyToOne
	@JoinColumn(name = "COD_PERFIL")
	private Perfil perfil;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", login:");
		builder.append(login);
		builder.append(", senha:");
		builder.append(senha);
		builder.append(", email:");
		builder.append(email);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", dataAtualizacao:");
		builder.append(dataAtualizacao);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append(", perfil:");
		builder.append(perfil);
		builder.append("}");
		return builder.toString();
	}
	
	 
}
