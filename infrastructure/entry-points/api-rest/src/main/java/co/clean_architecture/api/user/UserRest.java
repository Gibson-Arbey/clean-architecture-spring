package co.clean_architecture.api.user;

import co.clean_architecture.api.user.mapper.CreateUserRequestMapper;
import co.clean_architecture.api.user.mapper.UpdateUserRequestMapper;
import co.clean_architecture.api.user.mapper.UserResponseMapper;
import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.api.user.request.UpdateUserRequest;
import co.clean_architecture.api.user.response.UserResponse;
import co.clean_architecture.model.user.criteria.UserCriteria;
import co.clean_architecture.usecase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRest {

    private final CreateUserUseCase createUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final GetUserByUseCase getUserByUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseMapper.toResponse(createUserUseCase.execute(CreateUserRequestMapper.toCreateUserCommand(request))));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsersByFilters(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "status", required = false) List<String> status,
            @RequestParam(name = "roles", required = false) Set<String> roles,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        UserCriteria criteria = UserCriteria.of(
                username,
                email,
                status,
                roles,
                limit,
                offset
        );
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseMapper.toResponseList(listUserUseCase.getUsersByCriteria(criteria)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseMapper.toResponse(getUserByUseCase.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseMapper.toResponse(updateUserUseCase.execute(id, UpdateUserRequestMapper.toUpdateUserCommand(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        deleteUserUseCase.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
