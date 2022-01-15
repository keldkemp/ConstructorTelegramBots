package keldkemp.telegram.services.impl;

import keldkemp.telegram.models.Users;
import keldkemp.telegram.repositories.UsersRepository;
import keldkemp.telegram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Users> findCurrentUser() {
        return findCurrentUser(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public Optional<Users> findCurrentUser(Authentication authentication) {
        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(principal -> {
                    if (principal instanceof Users) {
                        return (Users) principal;
                    } else if (principal instanceof User user) {
                        return getByUsername(user.getUsername());
                    } else if (principal instanceof String) {
                        return getByUsername((String) principal);
                    } else {
                        throw new RuntimeException(String.format("Unknown principal type %s", principal.getClass().getName()));
                    }
                });
    }

    @Override
    public Users getCurrentUser() {
        return findCurrentUser().orElseThrow(() -> new RuntimeException("Unable to find current user!"));
    }

    @Override
    public Users getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return Optional.ofNullable(getByUsername(username));
    }

    @Override
    public String getHash(String input) {
        Assert.hasLength(input, "Input string is required");
        return passwordEncoder.encode(input);
    }

    @Override
    public void save(Users user) {
        Users userFromDb = getByUsername(user.getUsername());

        if (userFromDb != null) {
            throw new RuntimeException("User already exists");
        }

        user.setIsLocked(false);
        user.setPassword(getHash(user.getPassword()));
        usersRepository.save(user);
    }
}
