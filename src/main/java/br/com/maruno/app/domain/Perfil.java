/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:54:10 AM
 * @Pacote br.com.maruno.app.domain
 * @Nome Perfil.java
 * @NomeCompleto br.com.maruno.app.domain.Perfil.java
 */
@Entity
@Table(name = "TB_PERFIL")//, schema = Domain.SCHEMA)
public class Perfil  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name = "COD_PERFIL")
	private Integer codigo;  

	@Column(name = "NOM_PERFIL")
	private String nome;  

	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido = false;
	

	public Perfil() { 
	}
	
	public Perfil(Integer codigo) {
		super();
		this.codigo = codigo; 
	}
	
	public Perfil(Integer codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

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
 

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append("}");
		return builder.toString();
	}
 

}
