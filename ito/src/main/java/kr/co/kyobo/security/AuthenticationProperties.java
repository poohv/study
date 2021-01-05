package kr.co.kyobo.security;

import java.time.Duration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.auth")
public class AuthenticationProperties {

    @NotNull
    private String login;

    @NotNull
    private SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");


    @NotNull
    private JWT jwt;

    @Getter
    @Setter
    public static class JWT {

        @NotNull
        private String secret = "kyobo123!@#";

        @NotNull
        private Duration ttl;

        @NotNull
        private SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    }


    public String loginUrl() {
        return login.split(",")[0];
    }

    public String loginMethod() {
        return login.split(",")[1];
    }

}
