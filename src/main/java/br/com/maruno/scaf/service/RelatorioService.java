/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.service.TokenService;
import br.com.maruno.scaf.domain.Categoria;
import br.com.maruno.scaf.domain.vw.VWRelatorio;
import br.com.maruno.scaf.enums.TipoLancamentoRelatorioEnum;
import br.com.maruno.scaf.persistence.vw.VWRelatorioDao;
import br.com.maruno.scaf.vo.GrupoVO;
import br.com.maruno.scaf.vo.NodeVO;

/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Mar 4, 2019 10:58:43 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome RelatorioService.java
 * @NomeCompleto br.com.maruno.scaf.service.RelatorioService.java
 */
@Service
public class RelatorioService {

	@Autowired
	private VWRelatorioDao relatorioDao;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private TagService tagService;


	public Map<String, Object> findRelatorio(){   
		Map<String, Object> mapa = new TreeMap<String, Object>();
		mapa.put(LISTA, relatorioDao.find(tokenService.getCodUsuario()));
		mapa.put(GRUPOS, parametrosService.findGrupos());
		mapa.put(TIPOS, TipoLancamentoRelatorioEnum.getvalues());
		mapa.put(TAGS, tagService.find());
		mapa.put(COMPETENCIAS, relatorioDao.findCompetencias(tokenService.getCodUsuario()));
		return mapa;
	}
	
	
	private static final String LISTA = "lista";
	private static final String GRUPOS = "grupos";
	private static final String TIPOS = "tipos";
	private static final String TAGS = "tags";
	private static final String COMPETENCIAS = "competencias";
	private static final String ROOT = "root";
	
	public Map<String, Object> findRelatorioAgrupado(){
		Map<String, Object> mapa = new TreeMap<String, Object>();
		
		List<VWRelatorio> lista = relatorioDao.find(tokenService.getCodUsuario());  
		List<String> listCompetencias = relatorioDao.findCompetencias(tokenService.getCodUsuario());
		List<GrupoVO> listGrupos = parametrosService.findGrupos();
		
//		mapa.put(LISTA, lista);
		mapa.put(GRUPOS, listGrupos);
		mapa.put(TIPOS, TipoLancamentoRelatorioEnum.getvalues());
		mapa.put(TAGS, tagService.find());
		mapa.put(COMPETENCIAS, listCompetencias);
		mapa.put(ROOT, getRoot(lista, listCompetencias, listGrupos));
		 
		return mapa;
	}
	

	
	private List<VWRelatorio> setRoot(List<VWRelatorio> listRelatorio, List<String> listCompetencias, List<GrupoVO> listGrupos){
		List<VWRelatorio> lista = new ArrayList<VWRelatorio>();
		
		for(String anoMes: listCompetencias){ 
			VWRelatorio competencia = new VWRelatorio(anoMes);
			
			for(GrupoVO grupoVO: listGrupos){ 
				VWRelatorio grupo = new VWRelatorio(anoMes, grupoVO.getCodigo(), grupoVO.getNome());
				
				for(Categoria categoriaVO: grupoVO.getListCategorias()){ 
					VWRelatorio categoria = new VWRelatorio(anoMes, grupoVO.getCodigo(), grupoVO.getNome(), categoriaVO.getCodigo(), categoriaVO.getNome());
					
					for(VWRelatorio lancamento: listRelatorio){  
						if(validar(anoMes, categoria, lancamento)){ 
							somar(categoria, lancamento); 
							categoria.getLista().add(lancamento);
						}
					}
					
					if(categoria.getLista().size() > 0){
						somar(grupo, categoria); 
						grupo.getLista().add(categoria);
					}
				} 
				if(grupo.getLista().size() > 0){
					somar(competencia, grupo); 
					competencia.getLista().add(grupo);
				} 
			}
			if(competencia.getLista().size() > 0){
				lista.add(competencia);
			}
		}  
		return lista;
	}

	private boolean validar(String anoMes, VWRelatorio categoria, VWRelatorio lancamento){
		if(!anoMes.equals(lancamento.getAnoMes())){ 
			return false;
		}
		if(!categoria.getCodGrupo().equals(lancamento.getCodGrupo())){
			return false;
		}  
		if(!categoria.getCodCategoria().equals(lancamento.getCodCategoria())){
			return false;
		}   
		return true;
	}
	
	private void somar(VWRelatorio n1, VWRelatorio n2){ 
		n1.setValorLancamento(somar(n1.getValorLancamento(), n2.getValorLancamento(), 2));
		n1.setValorPrevisto(somar(n1.getValorPrevisto(), n2.getValorPrevisto(), 2)); 
	}
	

	private static final String DATA = "data";
	
	private Map<String, List<NodeVO>> getRoot(List<VWRelatorio> listRelatorio, List<String> listCompetencias, List<GrupoVO> listGrupos){
		Map<String, List<NodeVO>> root = new TreeMap<String, List<NodeVO>>();
		root.put(DATA, new ArrayList<NodeVO>());
		
		for(String anoMes: listCompetencias){ 
			NodeVO competencia = new NodeVO(new VWRelatorio(anoMes));
			
			for(GrupoVO grupoVO: listGrupos){ 
				NodeVO grupo = new NodeVO(new VWRelatorio(anoMes, grupoVO.getCodigo(), grupoVO.getNome()));
				
				for(Categoria categoriaVO: grupoVO.getListCategorias()){ 
					NodeVO categoria = new NodeVO(new VWRelatorio(anoMes, grupoVO.getCodigo(), grupoVO.getNome(), categoriaVO.getCodigo(), categoriaVO.getNome()));
					
					for(VWRelatorio l: listRelatorio){  
						NodeVO lancamento = new NodeVO(l);
						if(validar(anoMes, categoria, lancamento)){ 
							somar(categoria, lancamento); 
							categoria.getChildren().add(lancamento);
						}
					}
					
					if(categoria.getChildren().size() > 0){
						somar(grupo, categoria); 
						grupo.getChildren().add(categoria);
					}
				} 
				if(grupo.getChildren().size() > 0){
					somar(competencia, grupo); 
					competencia.getChildren().add(grupo);
				} 
			}
			if(competencia.getChildren().size() > 0){
				root.get(DATA).add(competencia);
			}
		}  
		return root;
	}
	

	private boolean validar(String anoMes, NodeVO categoria, NodeVO lancamento){
		if(!anoMes.equals(lancamento.getData().getAnoMes())){ 
			return false;
		}
		if(!categoria.getData().getCodGrupo().equals(lancamento.getData().getCodGrupo())){
			return false;
		}  
		if(!categoria.getData().getCodCategoria().equals(lancamento.getData().getCodCategoria())){
			return false;
		}   
		return true;
	}
	
	private void somar(NodeVO n1, NodeVO n2){ 
		n1.getData().setValorLancamento(somar(n1.getData().getValorLancamento(), n2.getData().getValorLancamento(), 2));
		n1.getData().setValorPrevisto(somar(n1.getData().getValorPrevisto(), n2.getData().getValorPrevisto(), 2)); 
	}

	private BigDecimal somar(BigDecimal b1, BigDecimal b2, int casas){  
		return b1.add(b2).setScale(casas, RoundingMode.HALF_UP);
	}
}
