package br.com.maruno.scaf.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.support.DateUtils;

 
public class DataFaturaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataReferencia;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String dataFatura;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal valor;

	
	public DataFaturaVO() {}


	public DataFaturaVO(Object[] dados) {
		super();
		this.dataReferencia = DateUtils.convertDateToString((Date) dados[0])   ;
		this.dataFatura = DateUtils.convertDateToString((Date) dados[1]);
		this.valor = (BigDecimal) dados[2];
	}


	public String getDataReferencia() {
		return dataReferencia;
	}


	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}


	public String getDataFatura() {
		return dataFatura;
	}


	public void setDataFatura(String dataFatura) {
		this.dataFatura = dataFatura;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{dataReferencia:");
		builder.append(dataReferencia);
		builder.append(", dataFatura:");
		builder.append(dataFatura);
		builder.append(", valor:");
		builder.append(valor);
		builder.append("}");
		return builder.toString();
	}
 
	
	 
}
