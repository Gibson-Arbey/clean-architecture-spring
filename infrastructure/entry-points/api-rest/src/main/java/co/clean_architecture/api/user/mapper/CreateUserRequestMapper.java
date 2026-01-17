package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.usecase.user.command.CreateUserCommand;

public class CreateUserRequestMapper {

    public static CreateUserCommand toCreateUserCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getRoleNames()
        );
    }
}
