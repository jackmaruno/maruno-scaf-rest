/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.maruno.app.domain.Domain;
import br.com.maruno.scaf.domain.Agendamento;
  
/**
 * @Aplicativo 
 * @Modulo quaemo-scaf
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data 11 de set de 2018 13:05:43
 * @Pacote br.com.quaemo.scaf.persistence
 * @Nome AgendamentoDao.java
 * @NomeCompleto br.com.quaemo.scaf.persistence.AgendamentoDao.java
 */
public interface AgendamentoDao extends JpaRepository<Agendamento, Integer> { 

	Agendamento findByCodigoAndCodUsuario(Integer codigo, Integer codUsuario);
	
	@Query("SELECT o FROM Agendamento o WHERE o.excluido = false AND o.ativo = true AND o.codUsuario = :codUsuario ORDER BY o.nome")
	List<Agendamento> findAtivos(@Param("codUsuario") Integer codUsuario);
	
	@Query("SELECT o FROM Agendamento o WHERE o.excluido = false AND o.codUsuario = :codUsuario ORDER BY o.nome")
	List<Agendamento> find(@Param("codUsuario") Integer codUsuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE "+Domain.SCHEMA+".TB_AGENDAMENTO SET IND_ATIVO = :ativo, DAT_ALTERACAO = :dataAtualizacao WHERE COD_USUARIO = :codUsuario AND COD_AGENDAMENTO = :codigo ", nativeQuery = true)
	int alterarStatus(@Param("codUsuario") Integer codUsuario, @Param("codigo") Integer codigo, @Param("dataAtualizacao") Date dataAtualizacao, @Param("ativo") boolean ativo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE "+Domain.SCHEMA+".TB_AGENDAMENTO SET IND_EXCLUIDO = 1, IND_ATIVO = 0, DAT_ALTERACAO = :dataAtualizacao WHERE COD_USUARIO = :codUsuario AND COD_AGENDAMENTO = :codigo ", nativeQuery = true)
	int remove(@Param("codUsuario") Integer codUsuario, @Param("codigo") Integer codigo, @Param("dataAtualizacao") Date dataAtualizacao);
}
