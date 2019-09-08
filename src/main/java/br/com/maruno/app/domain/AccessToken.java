/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:54:28 AM
 * @Pacote br.com.maruno.app.domain
 * @Nome AccessToken.java
 * @NomeCompleto br.com.maruno.app.domain.AccessToken.java
 */
@Entity
@Table(name = "TB_ACCES_TOKEN")//, schema = Domain.SCHEMA)
public class AccessToken  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DES_ACCES_TOKEN")
	private String access_token;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_TIPO_TOKEN")
	private String token_type;  

	@JsonIgnore
	@Column(name = "DES_USER_AGENT")
	private String userAgent;

	@JsonIgnore
	@Column(name = "DES_BROWSER")
	private String browser;

	@JsonIgnore
	@Column(name = "DES_VERSAO_BROWSER")
	private String versaoBrowser;

	@JsonIgnore
	@Column(name = "DES_SISTEMA_OPERACIONAL")
	private String sistemaOperacional;

	@JsonIgnore
	@Column(name = "DES_IP")
	private String ip;

	@JsonIgnore
	@Column(name = "DAT_CADASTRO")
	private Date dataCadastro;  

	@JsonIgnore
	@Column(name = "DAT_ATUALIZACAO")
	private Date dataAtualizacao;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_TEMPO_VALIDADE")
	private Long expires_in; 
	
	@JsonIgnore
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "IND_VALIDO")
	private boolean valido = true;  
	
	@ManyToOne
	@JoinColumn(name = "COD_USUARIO")
	private Usuario usuario;
 

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVersaoBrowser() {
		return versaoBrowser;
	}

	public void setVersaoBrowser(String versaoBrowser) {
		this.versaoBrowser = versaoBrowser;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
 
	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{access_token:");
		builder.append(access_token);
		builder.append(", token_type:");
		builder.append(token_type);
		builder.append(", userAgent:");
		builder.append(userAgent);
		builder.append(", browser:");
		builder.append(browser);
		builder.append(", versaoBrowser:");
		builder.append(versaoBrowser);
		builder.append(", sistemaOperacional:");
		builder.append(sistemaOperacional);
		builder.append(", ip:");
		builder.append(ip);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", dataAtualizacao:");
		builder.append(dataAtualizacao);
		builder.append(", valido:");
		builder.append(valido);
		builder.append(", expires_in:");
		builder.append(expires_in);
		builder.append(", usuario:");
		builder.append(usuario);
		builder.append("}");
		return builder.toString();
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.access_token == null ? 0 : this.access_token.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccessToken other = (AccessToken) obj;
		if (this.access_token == null) {
			if (other.access_token != null) {
				return false;
			}
		} else if (!this.access_token.equals(other.access_token)) {
			return false;
		}
		return true;
	}
}