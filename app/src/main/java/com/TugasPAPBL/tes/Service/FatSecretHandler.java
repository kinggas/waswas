package com.TugasPAPBL.tes.Service;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FatSecretHandler {

    final static private String APP_KEY = "621e349c73d644b6ae3f875bf9ebbf90";
    final static private String APP_SECRET = "4de507bf47df4e5c888d88883a10ca70";
    final static private String APP_SIGNATURE_METHOD = "HmacSHA1";




    private static String nonce() {
        Random r = new Random();
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < r.nextInt(8) + 2; i++)
            n.append(r.nextInt(26) + 'a');
        return n.toString();
    }

    //mengembalikan oauth parameter
    public String[] getOauthParameters() {
        return new String[]{
                "oauth_consumer_key=" + APP_KEY,
                "oauth_signature_method=HMAC-SHA1",
                "oauth_timestamp=" + new Long(System.currentTimeMillis() / 1000).toString(),
                "oauth_nonce=" + nonce(),
                "oauth_version=1.0"
        };
    }

    //menggabungkan variabel parameter dan separator
    //hasil : "parameter[0]"+"separator"+parameter[1]+...
    public String join(String[] parameter, String separator) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < parameter.length; i++) {
            if (i > 0) {
                b.append(separator);
            }
            b.append(parameter[i]);
        }
        return b.toString();
    }

    //menambahkan "&" setelah setiap parameter kecuali diakhir
    public String paramify(String[] params) {
        String[] p = Arrays.copyOf(params, params.length);
        Arrays.sort(p);
        return join(p, "&");
    }

    //mengubah string url menjadi encoding persen
    public String encode(String url) {
        if (url == null) {
            return "";
        }
        try {
            return URLEncoder.encode(url, "utf-8")
                    .replace("+", "%20")
                    .replace("!", "%21")
                    .replace("*", "%2A")
                    .replace("\\", "%27")
                    .replace("(", "%28")
                    .replace(")", "%29");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    //membuat Signature Base String untuk memanggil API FatSecret
    //<HTTP Method>&<RequestUrl URL>&<Normalized Parameters>
    //Step 2 : Calculating the Signature value (oauth_signature)
    //mengembalikan oauth_signature
    public String signature(String method, String uri, String[] params) throws UnsupportedEncodingException {
        String encodedURI = encode(uri);
        String encodedParams = encode(paramify(params));

        String[] p = {method, encodedURI, encodedParams};

        String text = join(p, "&");
        String key = APP_SECRET + "&";
        SecretKey sk = new SecretKeySpec(key.getBytes(), APP_SIGNATURE_METHOD);
        String sign = "";
        try {
            Mac m = Mac.getInstance(APP_SIGNATURE_METHOD);
            m.init(sk);
            sign = encode(new String(Base64.encode(m.doFinal(text.getBytes()), Base64.DEFAULT)).trim());
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException: " + e.getMessage());
        } catch (java.security.InvalidKeyException e) {
            System.out.println("InvalidKeyException: " + e.getMessage());
        }
        return sign;
    }

}
