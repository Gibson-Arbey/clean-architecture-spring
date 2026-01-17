package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.response.UserResponse;
import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseMapper {

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail().value())
                .status(user.getStatus())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(UserResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
