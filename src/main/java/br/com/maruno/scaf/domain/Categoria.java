/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.maruno.app.domain.Domain;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:00:55 AM
 * @Pacote br.com.maruno.scaf.domain
 * @Nome Categoria.java
 * @NomeCompleto br.com.maruno.scaf.domain.Categoria.java
 */
@Entity
@Table(name = "TB_CATEGORIA", schema = Domain.SCHEMA)
public class Categoria extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_CATEGORIA")
	private Integer codigo;  
	
	@Column(name = "NOM_CATEGORIA")
	private String nome;  
	
	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_ATIVO")
	private boolean ativo = true;

	@ManyToOne
	@JoinColumn(name = "COD_GRUPO")
	private Grupo grupo;
	
	public Categoria() {}
	
	public Categoria(Integer codigo) {
		this.codigo = codigo;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", ativo:");
		builder.append(ativo);
		builder.append(", grupo:");
		builder.append(grupo);
		builder.append("}");
		return builder.toString();
	}
  
  
}
