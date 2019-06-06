/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.maruno.scaf.domain.vw.VWRelatorio;

/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Mar 5, 2019 4:51:36 PM
 * @Pacote br.com.maruno.scaf.vo
 * @Nome NodeVO.java
 * @NomeCompleto br.com.maruno.scaf.vo.NodeVO.java
 */
public class NodeVO {

	private VWRelatorio data;

	private List<NodeVO> children = new ArrayList<NodeVO>();

	public NodeVO() {
	}
	
	public NodeVO(VWRelatorio data) {
		this.data = data;
	}

	public VWRelatorio getData() {
		return data;
	}

	public void setData(VWRelatorio data) {
		this.data = data;
	}

	public List<NodeVO> getChildren() {
		return children;
	}

	public void setChildren(List<NodeVO> children) {
		this.children = children;
	}
	
}
