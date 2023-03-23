package com.dw.vetapp.veterinary.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dw.vetapp.veterinary.helper.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Kullanılmak üzere Token üretilen service
 * Gelen Token ı verify edip parçalar
 */
@Component
public class TokenService {

    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;


    public String generateToken(Authentication auth) {
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        /**
         * .withClaim("mail","aaaa")
         * ile istediğimiz alanı ekleyebiliriz
         */
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public String generateSaveToken(Authentication auth) {
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        /**
         * .withClaim("mail","aaaa")
         * ile istediğimiz alanı ekleyebiliriz
         */
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (30 * 1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }


    public DecodedJWT verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        /**
         * acceptExpiresAt-> token ın süresi geçse bile içerisinde belirtilen süre kadar daha
         * geçerli olmasını sağlar mesela 15 dakika + 20saniye
         */
        JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(20).build();
        try {
            /**
             * Tokenı oluştururken eklediğimiz alanları bu şekilde alabiliriz
             * decodeToken = verifier.verify(token);
             * mail = decodeToken.getClaims().get("mail")
             */
            return verifier.verify(token);
        } catch (Exception e) {
            throw new GenericException( HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
