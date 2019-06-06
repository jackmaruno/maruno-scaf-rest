/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain.vw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.domain.Domain;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:11:40 AM
 * @Pacote br.com.maruno.scaf.domain.vw
 * @Nome VWRelatorio.java
 * @NomeCompleto br.com.maruno.scaf.domain.vw.VWRelatorio.java
 */
@Entity
@Table(name = "VW_RELATORIO", schema = Domain.SCHEMA)
public class VWRelatorio extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_LANCAMENTO")
	private Integer codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_REFERENCIA")
	private Date dataReferencia;


	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_LANCAMENTO")
	private Date dataLancamento;

	@Column(name = "ANO_MES")
	private String anoMes;  

	@Column(name = "VAL_PREVISTO")
	private BigDecimal valorPrevisto;  

	@Column(name = "VAL_LANCAMENTO")
	private BigDecimal valorLancamento;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_TAGS")
	private String tags;  
	

	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_CATEGORIA")
	private Integer codCategoria; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_CATEGORIA")
	private String nomeCategoria;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_GRUPO")
	private Integer codGrupo; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_GRUPO")
	private String nomeGrupo;  
	


	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_LANCAMENTO_PARCELA")
	private Integer codLancamentoParcela; 
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_PARCELA")
	private Integer codParcela; 
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_PARCELA")
	private Integer numParcela; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_TAGS_PARCELA")
	private String tagsParcela;  
	
	
	


	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_CATEGORIA_PARCELA")
	private Integer codCategoriaParcela; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_CATEGORIA_PARCELA")
	private String nomeCategoriaParcela;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_GRUPO_PARCELA")
	private Integer codGrupoParcela; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_GRUPO_PARCELA")
	private String nomeGrupoParcela;  
	
	
	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_AGENDAMENTO")
	private Integer codAgendamento; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_AGENDAMENTO")
	private String nomeAgendamento;  
	
	
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_CARTAO_CREDITO")
	private Integer codCartaoCredito; 
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_CARTAO_CREDITO")
	private String nomeCartaoCredito;  
	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_TIPO")
	private Integer codTipo; 
	

	@Transient
	private List<VWRelatorio> lista = new ArrayList<VWRelatorio>();
	
	public VWRelatorio() {}

	public VWRelatorio(String anoMes) {
		this.anoMes = anoMes;
		this.valorPrevisto = BigDecimal.ZERO;
		this.valorLancamento = BigDecimal.ZERO; 
	}
	
	public VWRelatorio(String anoMes, Integer codGrupo, String nomeGrupo) {
		this.anoMes = anoMes; 
		this.codGrupo = codGrupo; 
		this.nomeGrupo = nomeGrupo; 
		this.valorPrevisto = BigDecimal.ZERO;
		this.valorLancamento = BigDecimal.ZERO; 
	}
	
	public VWRelatorio(String anoMes, Integer codGrupo, String nomeGrupo, Integer codCategoria, String nomeCategoria) {
		this.anoMes = anoMes; 
		this.codGrupo = codGrupo; 
		this.nomeGrupo = nomeGrupo; 
		this.codCategoria = codCategoria; 
		this.nomeCategoria = nomeCategoria; 
		this.valorPrevisto = BigDecimal.ZERO;
		this.valorLancamento = BigDecimal.ZERO; 

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

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public BigDecimal getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Integer codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public Integer getCodLancamentoParcela() {
		return codLancamentoParcela;
	}

	public void setCodLancamentoParcela(Integer codLancamentoParcela) {
		this.codLancamentoParcela = codLancamentoParcela;
	}

	public Integer getCodParcela() {
		return codParcela;
	}

	public void setCodParcela(Integer codParcela) {
		this.codParcela = codParcela;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public String getTagsParcela() {
		return tagsParcela;
	}

	public void setTagsParcela(String tagsParcela) {
		this.tagsParcela = tagsParcela;
	}

	public Integer getCodCategoriaParcela() {
		return codCategoriaParcela;
	}

	public void setCodCategoriaParcela(Integer codCategoriaParcela) {
		this.codCategoriaParcela = codCategoriaParcela;
	}

	public String getNomeCategoriaParcela() {
		return nomeCategoriaParcela;
	}

	public void setNomeCategoriaParcela(String nomeCategoriaParcela) {
		this.nomeCategoriaParcela = nomeCategoriaParcela;
	}

	public Integer getCodGrupoParcela() {
		return codGrupoParcela;
	}

	public void setCodGrupoParcela(Integer codGrupoParcela) {
		this.codGrupoParcela = codGrupoParcela;
	}

	public String getNomeGrupoParcela() {
		return nomeGrupoParcela;
	}

	public void setNomeGrupoParcela(String nomeGrupoParcela) {
		this.nomeGrupoParcela = nomeGrupoParcela;
	}

	public Integer getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(Integer codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

	public String getNomeAgendamento() {
		return nomeAgendamento;
	}

	public void setNomeAgendamento(String nomeAgendamento) {
		this.nomeAgendamento = nomeAgendamento;
	}

	public Integer getCodCartaoCredito() {
		return codCartaoCredito;
	}

	public void setCodCartaoCredito(Integer codCartaoCredito) {
		this.codCartaoCredito = codCartaoCredito;
	}

	public String getNomeCartaoCredito() {
		return nomeCartaoCredito;
	}

	public void setNomeCartaoCredito(String nomeCartaoCredito) {
		this.nomeCartaoCredito = nomeCartaoCredito;
	}

	public Integer getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public List<VWRelatorio> getLista() {
		return lista;
	}

	public void setLista(List<VWRelatorio> lista) {
		this.lista = lista;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codUsuario:");
		builder.append(codUsuario);
		builder.append(", dataReferencia:");
		builder.append(dataReferencia);
		builder.append(", dataLancamento:");
		builder.append(dataLancamento);
		builder.append(", anoMes:");
		builder.append(anoMes);
		builder.append(", valorPrevisto:");
		builder.append(valorPrevisto);
		builder.append(", valorLancamento:");
		builder.append(valorLancamento);
		builder.append(", tags:");
		builder.append(tags);
		builder.append(", codCategoria:");
		builder.append(codCategoria);
		builder.append(", nomeCategoria:");
		builder.append(nomeCategoria);
		builder.append(", codGrupo:");
		builder.append(codGrupo);
		builder.append(", nomeGrupo:");
		builder.append(nomeGrupo);
		builder.append(", codLancamentoParcela:");
		builder.append(codLancamentoParcela);
		builder.append(", codParcela:");
		builder.append(codParcela);
		builder.append(", numParcela:");
		builder.append(numParcela);
		builder.append(", tagsParcela:");
		builder.append(tagsParcela);
		builder.append(", codCategoriaParcela:");
		builder.append(codCategoriaParcela);
		builder.append(", nomeCategoriaParcela:");
		builder.append(nomeCategoriaParcela);
		builder.append(", codGrupoParcela:");
		builder.append(codGrupoParcela);
		builder.append(", nomeGrupoParcela:");
		builder.append(nomeGrupoParcela);
		builder.append(", codAgendamento:");
		builder.append(codAgendamento);
		builder.append(", nomeAgendamento:");
		builder.append(nomeAgendamento);
		builder.append(", codCartaoCredito:");
		builder.append(codCartaoCredito);
		builder.append(", nomeCartaoCredito:");
		builder.append(nomeCartaoCredito);
		builder.append(", codTipo:");
		builder.append(codTipo);
		builder.append("}");
		return builder.toString();
	}
	
 
 
}
