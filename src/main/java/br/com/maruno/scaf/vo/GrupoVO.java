/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.maruno.scaf.domain.Categoria;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:01:23 AM
 * @Pacote br.com.maruno.scaf.vo
 * @Nome GrupoVO.java
 * @NomeCompleto br.com.maruno.scaf.vo.GrupoVO.java
 */
public class GrupoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;  
	
	private String nome;  

	private List<Categoria> listCategorias = new ArrayList<Categoria>();
	
	public GrupoVO() {}

	/**
	 * @param codigo
	 * @param nome
	 * @param listCategorias
	 */
	public GrupoVO(Integer codigo, String nome, List<Categoria> listCategorias) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.listCategorias = listCategorias;
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

	public List<Categoria> getListCategorias() {
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", listCategorias:");
		builder.append(listCategorias);
		builder.append("}");
		return builder.toString();
	}
	
	
	 
}
