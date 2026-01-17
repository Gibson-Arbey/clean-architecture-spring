package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.api.user.request.UpdateUserRequest;
import co.clean_architecture.usecase.user.command.CreateUserCommand;
import co.clean_architecture.usecase.user.command.UpdateUserCommand;

public class UpdateUserRequestMapper {

    public static UpdateUserCommand toUpdateUserCommand(UpdateUserRequest request) {
        return new UpdateUserCommand(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getRoleNames()
        );
    }
}
