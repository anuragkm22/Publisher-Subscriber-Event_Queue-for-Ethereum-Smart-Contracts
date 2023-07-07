package com.implemantation.dynamicpublisher.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class CryptoController {

    private PublicKey pubKey;
    byte[] signAndHash(final String message){

        byte[] ret=null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            pubKey = keyPair.getPublic();
            Signature signature = Signature.getInstance("SHA512withDSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(message.getBytes());
            ret = signature.sign();
        } catch (Exception e) {
            return null;
        }
        return ret;
    }

    PublicKey getPubKey(){
        return pubKey;
    }

    boolean verify(final byte[] message,final byte[] sign,byte[] publicKeyBytes){
        try {
            PublicKey publicKey = KeyFactory.getInstance("DSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            Signature signature = Signature.getInstance("SHA512withDSA");
            signature.initVerify(publicKey);
            signature.update(message);

            return signature.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }
    String getHash(final String eventQ,final String algo) {
        String ret = null;
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algo);
            final byte[] hashbytes = digest.digest(eventQ.getBytes());
            ret = convertToHex(hashbytes);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return ret;
    }

    String convertToHex(byte[] hashbytes) {
        BigInteger bigint = new BigInteger(1, hashbytes);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}
