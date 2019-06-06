/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import br.com.maruno.app.service.TokenService;
import br.com.maruno.app.support.DateUtils;
import br.com.maruno.scaf.domain.vw.VWLancamento;
import br.com.maruno.scaf.persistence.DashBoardDao;
import br.com.maruno.scaf.persistence.vw.VWAgendaDao;
import br.com.maruno.scaf.persistence.vw.VWAtividadeMesDao;
import br.com.maruno.scaf.persistence.vw.VWAtividadesMesesDao;
import br.com.maruno.scaf.persistence.vw.VWLancamentoDao;
import br.com.maruno.scaf.persistence.vw.VWProximaAtividadeDao;
import br.com.maruno.scaf.vo.dashboard.UltimaAtividadeVO;

/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Mar 6, 2019 5:05:30 PM
 * @Pacote br.com.maruno.scaf.service
 * @Nome DashBoardService.java
 * @NomeCompleto br.com.maruno.scaf.service.DashBoardService.java
 */
@Service
public class DashBoardService {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private DashBoardDao dashBoardDao;
	
	@Autowired
	private VWAgendaDao vwAgendaDao;
	
	@Autowired
	private VWAtividadesMesesDao vwAtividadesMesesDao;
	
	@Autowired
	private VWAtividadeMesDao vwAtividadeMesDao;
	
	@Autowired
	private VWLancamentoDao vwLancamentoDao;
	
	@Autowired
	private VWProximaAtividadeDao vwProximaAtividadeDao;
	

	private static final String ULTIMAS_ATIVIDADES = "ultimasAtividades";
	private static final String PROXIMAS_ATIVIDADES = "proximasAtividades";
	private static final String ATIVIDADES_MES = "atividadesMes";
	private static final String ATIVIDADES_MESES = "atividadesMeses";
	private static final String AGENDA = "agenda";
	
	public Map<String, Object> findDashBoard(){
		Integer codUsuario = tokenService.getCodUsuario();
		Map<String, Object> mapa = new TreeMap<String, Object>();
		
		mapa.put(ULTIMAS_ATIVIDADES, converterUltimasAtividades(dashBoardDao.findUltimasAtividades(codUsuario, DateUtils.convertDateToString(new Date(), "yyyy/MM"))));
		mapa.put(PROXIMAS_ATIVIDADES, vwProximaAtividadeDao.find(codUsuario));
		mapa.put(ATIVIDADES_MES, vwAtividadeMesDao.find(codUsuario));
		mapa.put(ATIVIDADES_MESES, vwAtividadesMesesDao.find(codUsuario));
		mapa.put(AGENDA, vwAgendaDao.find(codUsuario));
		
		return mapa;
	}
 

	private List<UltimaAtividadeVO> converterUltimasAtividades(List<Object[]> list){
		List<UltimaAtividadeVO> lista = new ArrayList<UltimaAtividadeVO>();
		for(Object[] o: list){
			lista.add(new UltimaAtividadeVO(o));
		}
		return lista;
	}   
  
	public List<VWLancamento> findRelacaoLancamentos(Integer codTipo){
		return vwLancamentoDao.find(tokenService.getCodUsuario(), DateUtils.convertDateToString(new Date(), "yyyy/MM"), codTipo); 
	}
	
}
