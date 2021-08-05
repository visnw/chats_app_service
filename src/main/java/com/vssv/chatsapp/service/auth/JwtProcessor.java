package com.vssv.chatsapp.service.auth;

import com.vssv.chatsapp.entity.TokenEntity;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtProcessor {

    private final String SECRET_KEY;
    private final String ISSUER = "chat-app-auth";

    public JwtProcessor(String secret) {
        SECRET_KEY = secret;
    }

    public String generateJWT(String id, String subject, long ttlMillis) {

        io.jsonwebtoken.SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setIssuer(ISSUER)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public TokenEntity getTokenData(String token){
        try{
            Claims claims = decodeJWT(token);
            return TokenEntity.getToken(claims.getSubject());
        } catch (JwtException | IOException jwtException){
            System.out.println("validating -- cookie parse exception" + jwtException);
            Map<String, Object> response = new HashMap<>();
            response.put("isTokenInvalid", true);
            return null;
        }
    }


    private Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
