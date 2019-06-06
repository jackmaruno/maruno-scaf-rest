/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:54:04 AM
 * @Pacote br.com.maruno.app.domain
 * @Nome PermissaoRecurso.java
 * @NomeCompleto br.com.maruno.app.domain.PermissaoRecurso.java
 */
@Entity
@Immutable
@Table(name = "VW_PERMISSAO_RECURSO", schema = Domain.SCHEMA)
public class PermissaoRecurso  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_PERMISSAO")
	private String codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_METODO")
	private String metodo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(schema = Domain.SCHEMA
			 , name = "VW_PERMISSAO_RECURSO_PERFIL"
	         , joinColumns = {@JoinColumn(name = "COD_PERMISSAO") }
	         , inverseJoinColumns = {@JoinColumn(name = "COD_PERFIL") })
	private List<Perfil> listPerfis = new ArrayList<Perfil>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public List<Perfil> getListPerfis() {
		return listPerfis;
	}

	public void setListPerfis(List<Perfil> listPerfis) {
		this.listPerfis = listPerfis;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", metodo:");
		builder.append(metodo);
		builder.append(", listPerfis:");
		builder.append(listPerfis);
		builder.append("}");
		return builder.toString();
	}

}
