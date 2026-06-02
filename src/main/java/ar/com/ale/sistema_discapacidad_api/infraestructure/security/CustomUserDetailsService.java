package ar.com.ale.sistema_discapacidad_api.infraestructure.security;

import ar.com.ale.sistema_discapacidad_api.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var user = userRepository
                .findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .disabled(!user.getActive())
                .build();
    }
}