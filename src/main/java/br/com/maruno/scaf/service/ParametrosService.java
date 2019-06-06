/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.support.Util;
import br.com.maruno.scaf.domain.Categoria;
import br.com.maruno.scaf.domain.Grupo;
import br.com.maruno.scaf.domain.Periodo;
import br.com.maruno.scaf.persistence.CategoriaDao;
import br.com.maruno.scaf.persistence.GrupoDao;
import br.com.maruno.scaf.persistence.PeriodoDao;
import br.com.maruno.scaf.vo.GrupoVO;
   
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:01:36 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome ParametrosService.java
 * @NomeCompleto br.com.maruno.scaf.service.ParametrosService.java
 */
@Service
public class ParametrosService {

	@Autowired
	private PeriodoDao periodoDao;

	@Autowired
	private GrupoDao grupoDao;

	@Autowired
	private CategoriaDao categoriaDao;  
	
	 
	
	public List<Categoria> findCategorias(Integer codGrupo){ 
		if(Util.isEmpty(codGrupo)) { 
			return categoriaDao.findAtivos();
		}
		return categoriaDao.findAtivosByGrupo(codGrupo);
	}
	
	public List<GrupoVO> findGrupos(){ 
		List<GrupoVO> lista = new ArrayList<GrupoVO>();
		for(Grupo grupo: grupoDao.findAtivos()) {
			lista.add(new GrupoVO(grupo.getCodigo(), grupo.getNome(), categoriaDao.findAtivosByGrupo(grupo.getCodigo())));  
		}
		return lista;
	}
	
	public List<Periodo> findPeriodos(){ 
		return periodoDao.findAll();
	}
	
}
