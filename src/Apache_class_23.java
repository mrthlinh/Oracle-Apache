

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Apache_class_23 {
	String Key = "z/0qfiE+ScjxHy2gSwmHqP0rZ6fT9zhVgsNt";
	String signatureMethod = "HmacSHA256";
	String data = "sandbox.amazon.com/cobranded-ui/actions/start?callerKey=AKIAJZOKEUCXF7RKSCNA&callerReference=callerReferenceSingleUse&currencyCode=USD&paymentReason=HarryPotter%201-5%20DVD%20set&pipelineName=SingleUse&returnURL=http%3A%2F%2Flocalhost%3A8888%2Famazon&signatureMethod=HmacSHA256&signatureVersion=2&transactionAmount=5&version=2009-01-09";

	    private static String sign(String data, String key, String signatureMethod) throws SignatureException
	    {
	        System.out.println(" In sign block ");
	        String signature = "";
	        try {
	            System.out.println(" In sign Try block ");
	            Mac mac = Mac.getInstance(signatureMethod);
	            mac.init(new SecretKeySpec(key.getBytes(), signatureMethod));
//	            signature = new String(Base64.encodeBase64(mac.doFinal(data.getBytes())));
//	            		getBytes(UTF_8_Encoding))));
	            System.out.println(" In sign Try block ");
	        } catch (Exception e) {
	            System.out.println(" In sign catch block ");
	            throw new SignatureException("Failed to generate signature: " + e.getMessage(), e);         
	        }
	        System.out.println(" End sign block " + signature);
	        return signature;
	    }
}
