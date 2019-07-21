/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
 * @Data Jun 6, 2019 10:53:52 AM
 * @Pacote br.com.maruno.app.domain
 * @Nome Recurso.java
 * @NomeCompleto br.com.maruno.app.domain.Recurso.java
 */
@Entity
@Table(name = "TB_RECURSO")//, schema = Domain.SCHEMA)
public class Recurso  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore 
	@Column(name = "COD_RECURSO")
	private Integer codigo; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_RECURSO")
	private String nome; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "URL_RECURSO")
	private String url;
 
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_CADASTRO")
	private Date dataCadastro;

	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_RECURSO", insertable = false, updatable = false)
	private List<PermissaoRecurso> listPermissoes = new ArrayList<PermissaoRecurso>();

	public Recurso() {
		excluido = false;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	} 

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	} 

	public List<PermissaoRecurso> getListPermissoes() {
		return listPermissoes;
	}

	public void setListPermissoes(List<PermissaoRecurso> listPermissoes) {
		this.listPermissoes = listPermissoes;
	}
 

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", url:");
		builder.append(url);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append(", listPermissoes:");
		builder.append(listPermissoes);
		builder.append("}");
		return builder.toString();
	}
	
	
}
