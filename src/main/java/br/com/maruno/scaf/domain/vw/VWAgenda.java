/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain.vw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.domain.Domain;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:12:05 AM
 * @Pacote br.com.maruno.scaf.domain.vw
 * @Nome VWAgenda.java
 * @NomeCompleto br.com.maruno.scaf.domain.vw.VWAgenda.java
 */
@Entity
@Table(name = "VW_AGENDA_ATUAL")//, schema = Domain.SCHEMA)
public class VWAgenda implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@JsonIgnore
	@Column(name = "ID")
	private String codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_AGENDAMENTO")
	private Integer codAgendamento;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_PARCELA")
	private Integer codParcela;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_CARTAO_CREDITO")
	private Integer codCartaoCredito;  

	@Column(name = "COD_CATEGORIA")
	private Integer codCategoria;  
	
	@Column(name = "DES_CATEGORIA")
	private String nomeCategoria;  

	@Column(name = "VAL_PREVISTO")
	private BigDecimal valorPrevisto;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "VAL_PAGO")
	private BigDecimal valorPago;  

	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_PREVISTA")
	private Date dataPrevista;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_PAGAMENTO")
	private Date dataPagamento;  

	
	public VWAgenda() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Integer getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(Integer codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

	public Integer getCodParcela() {
		return codParcela;
	}

	public void setCodParcela(Integer codParcela) {
		this.codParcela = codParcela;
	}

	public Integer getCodCartaoCredito() {
		return codCartaoCredito;
	}

	public void setCodCartaoCredito(Integer codCartaoCredito) {
		this.codCartaoCredito = codCartaoCredito;
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

	public BigDecimal getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
