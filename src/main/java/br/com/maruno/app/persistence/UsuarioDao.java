/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.maruno.app.domain.Usuario;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:56:49 AM
 * @Pacote br.com.maruno.app.persistence
 * @Nome UsuarioDao.java
 * @NomeCompleto br.com.maruno.app.persistence.UsuarioDao.java
 */
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

	Usuario findByLoginIgnoreCase(String login);
	
	Usuario findByLoginIgnoreCaseAndExcluido(String login, Boolean excluido);

	Usuario findByCodigo(Integer codigo);

	Usuario findByCodigoAndExcluido(Integer codigo, Boolean excluido);

	Page<Usuario> findByNomeContainingIgnoreCaseAndExcluido(String nome, Boolean excluido, Pageable page);

	Page<Usuario> findByLoginContainingIgnoreCaseAndExcluido(String login, Boolean excluido, Pageable page);

	Page<Usuario> findByNomeContainingIgnoreCaseAndLoginContainingIgnoreCaseAndExcluido(String nome, String login, Boolean excluido, Pageable page);

	Page<Usuario> findByExcluido(Boolean excluido, Pageable page);


	List<Usuario> findByNomeContainingIgnoreCaseAndExcluido(String nome, Boolean excluido);

	List<Usuario> findByLoginContainingIgnoreCaseAndExcluido(String login, Boolean excluido);
	
	List<Usuario> findByNomeContainingIgnoreCaseAndLoginContainingIgnoreCaseAndExcluido(String nome, String login, Boolean excluido);
	
	List<Usuario> findByExcluido(Boolean excluido);

	@Query(value="SELECT CASE WHEN COUNT(codigo) > 0 THEN true ELSE false END"
			   + "\n FROM Usuario o "
			   + "\n WHERE excluido = false "
			   + "\n AND login = :login"
			   + "\n AND senha =:senha")	
	Boolean autenticar(@Param("login") String login, @Param("senha") String senha); 
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario SET foto = ?2 WHERE codigo = ?1 ")
	int saveFotoUsuario(Integer codigo, String foto);

	@Query(value = "SELECT o FROM Usuario o WHERE login = :login AND excluido = false")
	Usuario findByLogin(@Param("login") String login); 
		
	@Modifying
	@Transactional
	@Query(value ="UPDATE Usuario SET senha = :senha WHERE UPPER(login) = UPPER(:login)")
	int updateSenha(@Param("login") String login, @Param("senha") String senha);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario SET excluido = :excluido, dataAtualizacao = :dataAtualizacao WHERE codigo = :codUsuario ")
	int updateStatus(@Param("codUsuario") Integer codUsuario, @Param("excluido") Boolean excluido, @Param("dataAtualizacao") Date dataAtualizacao);
	
}
