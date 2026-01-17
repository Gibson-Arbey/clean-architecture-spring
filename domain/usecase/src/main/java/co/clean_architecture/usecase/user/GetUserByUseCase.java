package co.clean_architecture.usecase.user;


import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByUseCase {

    private final UserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public  User getByEmail(String email) {
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
