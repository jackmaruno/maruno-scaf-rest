package br.com.maruno.scaf.vo;

import java.io.Serializable;

 
public class DataParcelaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String anoMes;
	private String data;

	
	public DataParcelaVO() {}


	public DataParcelaVO(Object[] dados) {
		super();
		this.anoMes = (String) dados[0]   ;
		this.data = (String) dados[1]; 
	}


	public String getAnoMes() {
		return anoMes;
	}


	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{anoMes:");
		builder.append(anoMes);
		builder.append(", data:");
		builder.append(data);
		builder.append("}");
		return builder.toString();
	}

 
 
	
	 
}
