/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain;

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

import br.com.maruno.app.domain.Domain;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:11:26 AM
 * @Pacote br.com.maruno.scaf.domain
 * @Nome Agendamento.java
 * @NomeCompleto br.com.maruno.scaf.domain.Agendamento.java
 */
@Entity
@Table(name = "TB_AGENDAMENTO")//, schema = Domain.SCHEMA)
public class Agendamento extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_AGENDAMENTO")
	private Integer codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  
	
	@Column(name = "NOM_AGENDAMENTO")
	private String nome;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_AGENDAMENTO")
	private String descricao;   
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_INICIO")
	private Date dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_INCLUSAO")
	private Date dataInclusao = new Date();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_ALTERACAO")
	private Date dataAlteracao;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_ATIVO")
	private boolean ativo = true;
	
	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido = false;
 

	@ManyToOne
	@JoinColumn(name = "COD_CATEGORIA")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "NUM_PERIODO")
	private Periodo periodo;
	
	
	public Agendamento() {}
	
	public Agendamento(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	} 

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codUsuario:");
		builder.append(codUsuario);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", descricao:");
		builder.append(descricao);
		builder.append(", dataInicio:");
		builder.append(dataInicio);
		builder.append(", dataInclusao:");
		builder.append(dataInclusao);
		builder.append(", dataAlteracao:");
		builder.append(dataAlteracao);
		builder.append(", ativo:");
		builder.append(ativo);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append(", categoria:");
		builder.append(categoria);
		builder.append(", periodo:");
		builder.append(periodo);
		builder.append("}");
		return builder.toString();
	}
 
 
}
