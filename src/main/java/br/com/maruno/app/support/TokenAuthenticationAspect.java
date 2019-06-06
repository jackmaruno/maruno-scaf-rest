/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.support;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.maruno.app.exceptions.AccesTokenException;
import br.com.maruno.app.exceptions.OperacaoNaoRealizadaException;
import br.com.maruno.app.service.TokenService;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:58:25 AM
 * @Pacote br.com.maruno.app.support
 * @Nome TokenAuthenticationAspect.java
 * @NomeCompleto br.com.maruno.app.support.TokenAuthenticationAspect.java
 */
@Aspect
@Component
public class TokenAuthenticationAspect {

	@Autowired
	private TokenService tokenService;
	
	private static final String BEARER = "Bearer";

    @Pointcut("@annotation(br.com.maruno.app.annotation.TokenAuthentication)")
    private void tokenAuthenticationAspect() {
    }

    @Around("br.com.maruno.app.support.TokenAuthenticationAspect.tokenAuthenticationAspect()")
    public Object doSomething(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = getRequest(); 
        
        String authorization = request.getHeader("Authorization");
        String method = request.getMethod(); 
		
 		if(Util.isNotEmpty(authorization)){ 
 			String tipoToken = getTipoToken(authorization.trim());
 			String accesToken = getToken(authorization.trim());

 			if(tipoToken.equalsIgnoreCase(BEARER)){
 				String urlRecurso = getUrl(request); 
 				System.out.println("accesToken: "+accesToken+", urlRecurso: "+urlRecurso+", METHOD: "+method); 
 				tokenService.findAccessToken(accesToken, urlRecurso, method);
 				
 			}else{
 				throw new AccesTokenException("Não foi localizado no Header Authorization o tipo '"+BEARER+"'.");
 			}
		}else{
			throw new AccesTokenException("Não foi localizado o Authorization no Header.");
 		}
        return pjp.proceed();
    }

	private String getTipoToken(String header) {
		return header.substring(0, 6);
	}
	
	private String getToken(String header) {
		return header.substring(7, header.length());
	}

	private String getUrl(HttpServletRequest request) throws Exception {
		try {
			String url[] = null;
			if(Util.isNotEmpty(request.getPathInfo()) && request.getPathInfo().split("/").length > 2){
				url = request.getPathInfo().split("/");
				
			}else if(Util.isNotEmpty(request.getServletPath())){
				url = request.getServletPath().split("/");
			}
			return "/" + url[1] + "/" + url[2];
		} catch (Exception e) { 
			throw new OperacaoNaoRealizadaException("A URL não está no padrão modulo/recurso.");
		} 	
	}
	
	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	 
}
