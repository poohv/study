package kr.co.kyobo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

     //   log.debug("Starting authentication - {}", authentication);
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
     //   log.debug("Sending authenticate[{}:{}]", username, password);
        
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if (username.equals("admin") && password.equals("1111")) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles);

        } else {
            throw new UsernameNotFoundException(String.format("Invalid username (%s)", username));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
