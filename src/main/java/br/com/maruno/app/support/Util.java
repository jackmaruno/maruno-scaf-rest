/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.support;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:58:15 AM
 * @Pacote br.com.maruno.app.support
 * @Nome Util.java
 * @NomeCompleto br.com.maruno.app.support.Util.java
 */
public final class Util {

	/**
	 * 
	 */
	private Util() {
		super();
	}
	
	private static final int ZERO = 0;
	
	/*#############################################################################
	 * String is Empty
	 #############################################################################*/
	public static boolean isEmpty(String v1){  
		return (v1 == null || v1.trim().equals(""))? true : false;
	}

	/*#############################################################################
	 * Long is Empty
	 #############################################################################*/
	public static boolean isEmpty(Long v1){  
		return (v1 == null || v1.longValue() == ZERO)? true : false;
	}

	/*#############################################################################
	 * Integer is Empty
	 #############################################################################*/
	public static boolean isEmpty(Integer v1){  
		return (v1 == null || v1.intValue() == ZERO)? true : false;
	}

	/*#############################################################################
	 * Double is Empty
	 #############################################################################*/
	public static boolean isEmpty(Double v1){  
		return (v1 == null || v1.doubleValue() == ZERO)? true : false;
	}

	/*#############################################################################
	 * BigDecimal is Empty
	 #############################################################################*/
	public static boolean isEmpty(BigDecimal v1){  
		return (v1 == null || v1.compareTo(BigDecimal.ZERO) <= ZERO)? true : false;
	}

	/*#############################################################################
	 * Date is Empty
	 #############################################################################*/
	public static boolean isEmpty(Date v1){  
		return v1 == null;
	}

	/*#############################################################################
	 * Object is Empty
	 #############################################################################*/
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object v){  
		if(v == null){
			return true;
		}else{
			if(v instanceof String){
				return isEmpty((String) v);
				
			}else if(v instanceof Integer){
				return isEmpty((Integer) v);
				
			}else if(v instanceof Long){
				return isEmpty((Long) v);  
				
			}else if(v instanceof Double){
				return isEmpty((Double) v);  
				
			}else if(v instanceof BigDecimal){
				return isEmpty((BigDecimal) v);  
				
			}else if(v instanceof List){
				return ((List)v).isEmpty(); 
			}
			return false;
		}
	}
	
	/*#############################################################################
	 * String is NOT Empty
	 #############################################################################*/
	public static boolean isNotEmpty(String v1){  
		return (v1 != null && !v1.trim().equals(""))? true : false;
	}

	/*#############################################################################
	 * Long is NOT Empty
	 #############################################################################*/
	public static boolean isNotEmpty(Long v1){  
		return (v1 != null && v1.longValue() > ZERO)? true : false;
	}

	/*#############################################################################
	 * Integer is NOT Empty
	 #############################################################################*/
	public static boolean isNotEmpty(Integer v1){  
		return (v1 != null && v1.intValue() > ZERO)? true : false; 
	}

	/*#############################################################################
	 * BigDecimal is NOT Empty
	 #############################################################################*/
	public static boolean isNotEmpty(BigDecimal v1){  
		return (v1 != null && v1.compareTo(BigDecimal.ZERO) > ZERO)? true : false; 
	}

	/*#############################################################################
	 * Object is NOT Empty
	 #############################################################################*/
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object v){  
		if(v != null){
			if(v instanceof String){
				return isNotEmpty((String) v);
				
			}else if(v instanceof Integer){
				return isNotEmpty((Integer) v);
				
			}else if(v instanceof BigDecimal){
				return isNotEmpty((BigDecimal) v);
				
			}else if(v instanceof Boolean){
				return true;
				
			}else if(v instanceof Long){
				return isNotEmpty((Long) v); 
				
			}else if(v instanceof Date){
				return true;  
				
			}else if(v instanceof List){
				return !((List)v).isEmpty(); 
			}
		}
		return false;
	}

	
	/*#############################################################################
	 * is Anyone Null
	 #############################################################################*/
	public static boolean isAnyoneNull(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(v == null){
				return true;
			}
		}
		return false;
	}

	/*#############################################################################
	 * is Anyone NOT Null
	 #############################################################################*/
	public static boolean isAllNotNull(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(v == null){
				return false;
			}
		}
		return true;
	}
	
	/*#############################################################################
	 * is Anyone Empty
	 #############################################################################*/
	public static boolean isAnyoneEmpty(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(isEmpty(v)){
				return true;
			}
		}
		return false;
	}

	/*#############################################################################
	 * is Anyone NOT Empty
	 #############################################################################*/
	public static boolean isAnyoneNotEmpty(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(isNotEmpty(v)){
				return true;
			}
		}
		return false;
	}


	public static String getStringSql(String s){
		return isEmpty(s) ? "%" : s;
	}
	
	public static Integer getIntegerSql(Integer s){
		return isEmpty(s) ? ZERO : s;
	}

	public static String getString(String s, int qtd) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < qtd; i++) {
			sb.append(s);
		}   
		return sb.toString();
	}

	/*#############################################################################
	 * 
	 #############################################################################*/

	public static boolean validar(Object[] dados, int i){
		return (dados.length - 1) >= i;
	}
	
	public static Integer getInteger(Object[] dados, int i){
		if(validar(dados, i)) {
			return getInteger(dados[i]);
		}
		return null;
	}

	public static Long getLong(Object[] dados, int i){
		if(validar(dados, i)) {
			return getLong(dados[i]);
		}
		return null;
	}
	
	public static Double getDouble(Object[] dados, int i){
		if(validar(dados, i)) {
			return getDouble(dados[i]);
		}
		return null;
	}
	
	public static BigDecimal getBigDecimal(Object[] dados, int i){
		if(validar(dados, i)) {
			return getBigDecimal(dados[i]);
		}
		return null;
	}
	
	public static Date getDate(Object[] dados, int i){
		if(validar(dados, i)) {
			return getDate(dados[i]);
		}
		return null;
	}
	
	public static String getString(Object[] dados, int i){
		if(validar(dados, i)) {
			return getString(dados[i]);
		}
		return null;
	}
	
	public static Integer getInteger(Object o){
		if(o != null){
			try {
				if(o instanceof Integer){
					return (Integer) o;
				}else if(o instanceof Long){
					return ((Long) o).intValue();
				}else if(o instanceof BigInteger){
					return ((BigInteger) o).intValue();
				}else if(o instanceof Double){
					return ((Double) o).intValue();
				}else if(o instanceof BigDecimal){
					return ((BigDecimal) o).intValue();
				}else if(o instanceof String){
					return Integer.parseInt((String) o);
				}
			} catch (Exception e) {
			} 
		}
		return null;
	}

	public static Long getLong(Object o){
		if(o != null){
			try {
				if(o instanceof Integer){
					return ((Integer) o).longValue() ;
				}else if(o instanceof Long){
					return (Long) o;
				}else if(o instanceof BigInteger){
					return ((BigInteger) o).longValue();
				}else if(o instanceof Double){
					return ((Double) o).longValue();
				}else if(o instanceof BigDecimal){
					return ((BigDecimal) o).longValue();
				}else if(o instanceof String){
					return Long.parseLong((String) o);
				}
			} catch (Exception e) {
			} 
		}
		return null;
	}
	
	public static Double getDouble(Object o){
		if(o != null){
			try {
				if(o instanceof Integer){
					return ((Integer) o).doubleValue() ;
				}else if(o instanceof Long){
					return ((Long) o).doubleValue() ;
				}else if(o instanceof BigInteger){
					return ((BigInteger) o).doubleValue();
				}else if(o instanceof Double){
					return (Double) o;
				}else if(o instanceof BigDecimal){
					return ((BigDecimal) o).doubleValue();
				}else if(o instanceof String){
					return Double.parseDouble((String) o);
				}
			} catch (Exception e) {
			} 
		}
		return null;
	}
	
	public static BigDecimal getBigDecimal(Object o){
		if(o != null){
			try {
				if(o instanceof Integer){
					return new BigDecimal((Integer) o) ;
				}else if(o instanceof Long){
					return new BigDecimal((Long) o) ;
				}else if(o instanceof BigInteger){
					return new BigDecimal((BigInteger) o);
				}else if(o instanceof Double){
					return new BigDecimal((Double) o);
				}else if(o instanceof BigDecimal){
					return (BigDecimal) o;
				}else if(o instanceof String){
					return new BigDecimal((String) o);
				}else {
					return new BigDecimal(o.toString());
				}
			} catch (Exception e) {
			} 
		}
		return null;
	}

	public static Date getDate(Object o){
		if(o != null){
			if(o instanceof Date){
				return (Date) o ; 
			}
		}
		return null;
	}

	public static String getString(Object o){
		if(o != null){
			if(o instanceof String){
				return (String) o ; 
			}else{
				return o.toString();
			}
		}
		return null;
	}
	
	/*#############################################################################
	 * 
	 #############################################################################*/
	public static Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }
        return result;
    }
}
