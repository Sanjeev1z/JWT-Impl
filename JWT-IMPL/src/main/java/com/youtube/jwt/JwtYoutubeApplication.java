package com.youtube.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SpringBootApplication
public class JwtYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtYoutubeApplication.class, args);
	}
	
	public static void main() {
		
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzbmp2MjIiLCJleHAiOjE2ODQ1OTcxODUsImlhdCI6MTY4NDU3OTE4NX0.dw1YJ-1T75rANdRBVmnHOmYsg92I9MsfCTUlE91LJoR7QCB4hm4PAevh-3HL_7cdW2pamZcT4sM3j27a_lUstQ";
		extractUserRole(token);
	}
	
	private static final String SECRET_KEY = "$@NjEEv$oNi23";

    public static void extractUserRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        
        System.out.println(claims);
    }
    

}
