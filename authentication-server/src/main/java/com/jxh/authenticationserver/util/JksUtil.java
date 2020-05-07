package com.jxh.authenticationserver.util;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jingxiaohu
 * @version 1.0
 * @date 2020-5-8 1:09
 */
@Slf4j
public class JksUtil {
    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = getPrivateKey("jxh.jks", "123456", "jxh");
        log.info("-------privateKey---------");
        log.info("" + privateKey);
        PublicKey publicKey = getPublicKey("jxh.jks", "123456", "jxh");
        log.info("-------publicKey---------");
        log.info("" + publicKey);

    }

    private static PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return (PrivateKey) keyStore.getKey("jxh", "123456".toCharArray());

    }

    private static PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return keyStore.getCertificate("jxh").getPublicKey();

    }

}
