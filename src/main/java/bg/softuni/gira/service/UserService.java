package bg.softuni.gira.service;

import bg.softuni.gira.config.UserSession;
import bg.softuni.gira.dto.UserLoginDto;
import bg.softuni.gira.dto.UserRegisterDto;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDto data) {
        Optional<User> existingUser = userRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        User user = new User();

        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEmail(data.getEmail());

        this.userRepository.save(user);

        return true;

    }


    public boolean login(UserLoginDto data) {
        Optional<User> byEmail = userRepository.findByEmail(data.getEmail());

        if(byEmail.isEmpty()) {
            return false;
        }

        boolean passMatch = passwordEncoder.matches(data.getPassword(), byEmail.get().getPassword());

        if(!passMatch) {
            return false;
        }
        userSession.login(byEmail.get().getId(), data.getPassword());

        return true;
    }

}
