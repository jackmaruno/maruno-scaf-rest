/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.support;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec; 

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:00:13 AM
 * @Pacote br.com.maruno.app.support
 * @Nome CryptUtil.java
 * @NomeCompleto br.com.maruno.app.support.CryptUtil.java
 */
public class CryptUtil {

    private static final String KEY_STRING = "sienssic";
    private static final String KEY_SPEC = "dwpass";
    private static final String KEY_HASH = "PBEWithMD5AndDES";
	
	/**
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str) throws Exception {
		if (Util.isEmpty(str)) {
			return "";
		}
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(KEY_STRING.getBytes(), 8);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(KEY_SPEC.toCharArray());
		try {
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance(KEY_HASH);
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
			Cipher cipher = Cipher.getInstance(KEY_HASH);
			cipher.init(1, pbeKey, pbeParamSpec);
			byte[] cleartext = str.getBytes();
			byte[] ciphertext = cipher.doFinal(cleartext);
			return Base64.getEncoder().encodeToString(ciphertext);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			throw new Exception(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str) throws Exception {
		if (Util.isEmpty(str)) {
			return "";
		}
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(KEY_STRING.getBytes(), 8);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(KEY_SPEC.toCharArray());
		try {
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance(KEY_HASH);
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
			Cipher cipher = Cipher.getInstance(KEY_HASH);
			cipher.init(2, pbeKey, pbeParamSpec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			throw new Exception(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new Exception(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public static String hashEncrypt(String str) {
		if (Util.isEmpty(str)) {
			return "";
		}
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(str.getBytes());
			return Base64.getEncoder().encodeToString(digest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * @param nomeSistema
	 * @return
	 * @throws Exception
	 */
	public static String[] gerarSenhaSistema(String nomeSistema) throws Exception {
		String s = new StringBuilder("#123@$").append(nomeSistema).append("@123#").toString(); 
		return new String[] {hashEncrypt(nomeSistema), hashEncrypt(s)};
	}
	
	/**
	 * @param nomeSistema
	 * @return
	 * @throws Exception
	 */
	public static String getUrlAuth(String nomeSistema) throws Exception {
		String s = new StringBuilder("#123@$").append(nomeSistema).append("@123#").toString(); 
		return new StringBuilder().append("http://localhost:8080/quaemo-autenticacao/api/oauth2/token?grant_type=password&client_id=")
		                          .append(hashEncrypt(nomeSistema))
		                          .append("&client_secret=")
		                          .append(hashEncrypt(s))
		                          .append("&username=${username}&password=${password}")
		                          .toString(); 
	}
	
	public static void main(String[] args) {
		try {
//			System.out.println(encrypt("teste"));
//			System.out.println(decrypt("bSfQrIZejhM="));
//			System.out.println(hashEncrypt("NfM2pTq868")+"\n\n");
			
			String senha = "willian.maruno";
			String encrypt = encrypt(senha);
			String decrypt = decrypt(encrypt);

			System.out.println("senha:       "+senha);
			System.out.println("encrypt:     "+encrypt);
			System.out.println("decrypt:     "+decrypt);
			
			
			String ponto = "PONTO", scaf = "SCAF", corporativo = "CORPORATIVO";
			

			System.out.println("___________________________________________________________________________________________________________________");
			String[] senhaPonto = gerarSenhaSistema(ponto);
			System.out.println("client_id="+senhaPonto[0]+"&client_secret="+senhaPonto[1]);
			System.out.println(getUrlAuth(ponto).replace("${username}", "maruno").replace("${password}", "Metroid3")); 

			System.out.println("___________________________________________________________________________________________________________________");
			String[] senhaScaf = gerarSenhaSistema(scaf);
			System.out.println("client_id="+senhaScaf[0]+"&client_secret="+senhaScaf[1]);
			System.out.println(getUrlAuth(scaf).replace("${username}", "maruno").replace("${password}", "Metroid3")); 

			System.out.println("___________________________________________________________________________________________________________________");
			String[] senhaCorporativo = gerarSenhaSistema(corporativo);
			System.out.println("client_id="+senhaCorporativo[0]+"&client_secret="+senhaCorporativo[1]);
			System.out.println(getUrlAuth(corporativo).replace("${username}", "maruno").replace("${password}", "Metroid3")); 
			
			System.out.println("___________________________________________________________________________________________________________________");
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 
}
