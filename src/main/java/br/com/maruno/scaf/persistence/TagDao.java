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

import br.com.maruno.scaf.domain.Tag;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:14:12 AM
 * @Pacote br.com.maruno.scaf.persistence
 * @Nome TagDao.java
 * @NomeCompleto br.com.maruno.scaf.persistence.TagDao.java
 */
public interface TagDao extends JpaRepository<Tag, Integer> { 
	
	@Query("SELECT o FROM Tag o WHERE o.excluido = false AND o.codUsuario = :codUsuario ORDER BY o.nome")
	List<Tag> find(@Param("codUsuario") Integer codUsuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Tag SET excluido = true, dataAlteracao = :dataAtualizacao WHERE codUsuario = :codUsuario AND codigo = :codigo ")
	int remove(@Param("codUsuario") Integer codUsuario, @Param("codigo") Integer codigo, @Param("dataAtualizacao") Date dataAtualizacao);
}
