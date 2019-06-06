/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maruno.app.domain.Perfil;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:04 AM
 * @Pacote br.com.maruno.app.persistence
 * @Nome PerfilDao.java
 * @NomeCompleto br.com.maruno.app.persistence.PerfilDao.java
 */
public interface PerfilDao extends JpaRepository<Perfil, Integer> {

	Perfil findByCodigo(Integer codigo);
}
