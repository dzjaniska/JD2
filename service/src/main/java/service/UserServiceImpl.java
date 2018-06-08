package service;

import converter.UserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserDetailsConverter detailsConverter;

    @Autowired
    public UserServiceImpl(UserRepository adminRepository, UserDetailsConverter detailsConverter) {
        this.userRepository = adminRepository;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(login)
                .map(userRepository::findFirstByLogin)
                .map(detailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
    }
}
