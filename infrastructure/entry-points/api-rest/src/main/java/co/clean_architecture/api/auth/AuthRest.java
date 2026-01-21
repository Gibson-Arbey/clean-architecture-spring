package co.clean_architecture.api.auth;

import co.clean_architecture.api.auth.mapper.AuthResponseMapper;
import co.clean_architecture.api.auth.request.AuthRequest;
import co.clean_architecture.api.auth.response.AuthResponse;
import co.clean_architecture.usecase.auth.AuthenticateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRest {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(AuthResponseMapper.toResponse(authenticateUserUseCase.execute(request.getUsername(), request.getPassword())));
    }
}
