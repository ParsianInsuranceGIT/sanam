package com.bitarts.parsian.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.script.*;


public class SecurityUtil {
    private static byte[] key = {
            0x50,0x61,0x72,0x73,0x69,0x61,0x6e,0x49,0x6e,0x73,0x75,0x72,0x61,0x6e,0x63,0x65
    };//"ParsianInsurance";

    public static String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
            return encryptedString;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;

    }

    public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
            return decryptedString;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(encrypt("orcl"));
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings();
            String script = "function add(op1,op2){return op1+op2} add(a, b)";
            CompiledScript jsFunction = compilable.compile(script);
            bindings.put("a", 1);bindings.put("b", 2); //put java object into js runtime environment
            Object result = jsFunction.eval(bindings);
            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
