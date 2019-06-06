/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.domain.Domain;
import br.com.maruno.scaf.enums.TipoPagamentoEnum;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:09:18 AM
 * @Pacote br.com.maruno.scaf.domain
 * @Nome Lancamento.java
 * @NomeCompleto br.com.maruno.scaf.domain.Lancamento.java
 */
@Entity
@Table(name = "TB_LANCAMENTO", schema = Domain.SCHEMA)
@Where(clause = "IND_EXCLUIDO = 0")
public class Lancamento extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_LANCAMENTO")
	private Integer codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  
	 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Digits(integer = 8, fraction = 2, message = "Formato inválido para o campo valor previsto. Ex: ##########.##")
	@Column(name = "VAL_PREVISTO")
	private BigDecimal valorPrevisto;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Digits(integer = 8, fraction = 2, message = "Formato inválido para o campo valor lançamento. Ex: ##########.##")
	@NotNull(message = "O campo \"valor de lancamento\" é obrigatório.")
	@Column(name = "VAL_LANCAMENTO")
	private BigDecimal valor;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Size(max = 4000, message = "O campo nome não pode conter mais de 4000 caracteres.")
	@Column(name = "DES_TAGS")
	private String tags;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_LANCAMENTO")
	private String descricao;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@NotNull(message = "O campo \"data de referência\" é obrigatório.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_REFERENCIA")
	private Date dataReferencia;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_LANCAMENTO")
	private Date dataLancamento = new Date();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_FATURA")
	private Date dataFatura;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_ALTERACAO")
	private Date dataAlteracao;  
	
	@Column(name = "COD_TIPO_PAGAMENTO")
	private Integer codTipoPagamento = 0;  
 

	


	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotNull(message = "O campo \"categoria\" é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "COD_CATEGORIA")
	private Categoria categoria;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "COD_AGENDAMENTO")
	private Agendamento agendamento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "COD_CARTAO_CREDITO")
	private CartaoCredito cartaoCredito;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "COD_PARCELA")
	private Parcela parcela;  
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private List<Parcela> listParcelas;
	
	
	public Lancamento() {}
	
	public Lancamento(Integer codigo) {
		this.codigo = codigo;
	}

	


	@Transient
	public String getDescTipoPagamento() { 
		return TipoPagamentoEnum.getNome(codTipoPagamento);
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

	public BigDecimal getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	} 

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Integer getCodTipoPagamento() {
		return codTipoPagamento;
	}

	public void setCodTipoPagamento(Integer codTipoPagamento) {
		this.codTipoPagamento = codTipoPagamento;
	}

	public List<Parcela> getListParcelas() {
		return listParcelas;
	}

	public void setListParcelas(List<Parcela> listParcelas) {
		this.listParcelas = listParcelas;
	} 
 
}
