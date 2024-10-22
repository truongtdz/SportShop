package com.sportshop.sportshop.mapper;


import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity toUserEntity(CreateUserRequest newUser);

    void updateUserEntity(@MappingTarget UserEntity userEntity, UpdateUserRequest updateUserRequest);
}
