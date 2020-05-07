package com.jxh.authenticationserver.util;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.*;
import java.security.cert.Certificate;

/**
 * @author jingxiaohu
 * @version 1.0
 * @date 2020-5-8 1:16
 */
public class Jks2Util {

    private File keystoreFile;

    private String keyStoreType;

    private char[] password;

    private String alias;

    private File exportedFile;

    public KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {

        try {

            Key key = keystore.getKey(alias, password);

            if (key instanceof PrivateKey) {

                Certificate cert = keystore.getCertificate(alias);

                PublicKey publicKey = cert.getPublicKey();

                return new KeyPair(publicKey, (PrivateKey) key);

            }

        }catch (UnrecoverableKeyException e) {

        }catch (NoSuchAlgorithmException e) {

        }catch (KeyStoreException e) {

        }

        return null;

    }

    public void export()throws Exception {

        KeyStore keystore = KeyStore.getInstance(keyStoreType);

        BASE64Encoder encoder =new BASE64Encoder();

        keystore.load(new FileInputStream(keystoreFile), password);

        KeyPair keyPair = getPrivateKey(keystore, alias, password);

        PrivateKey privateKey = keyPair.getPrivate();

        String encoded = encoder.encode(privateKey.getEncoded());

        FileWriter fw =new FileWriter(exportedFile);

        fw.write("----BEGIN PRIVATE KEY----\n");

        fw.write(encoded);

        fw.write("\n");

        fw.write("----END PRIVATE KEY----\n");

        Certificate cert = keystore.getCertificate(alias);

        PublicKey publicKey = cert.getPublicKey();

        String encoded2 = encoder.encode(publicKey.getEncoded());

        fw.write("----BEGIN CERTIFICATE----\n");

        fw.write(encoded2);

        fw.write("\n");

        fw.write("----END CERTIFICATE----\n");

        fw.close();

    }

    public static void main(String args[])throws Exception {

        Jks2Util export =new Jks2Util();

        export.keystoreFile =new File("/D:/jxh.jks");

        export.keyStoreType ="JKS";

        export.password ="123456".toCharArray();

        export.alias ="jxh";

        export.exportedFile =new File("/D:/output.txt");

        export.export();
    }
}
