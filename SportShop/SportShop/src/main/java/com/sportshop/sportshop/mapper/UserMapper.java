package com.sportshop.sportshop.mapper;


import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "fullName", expression = "java(userEntity.getFirstName() + \" \" + userEntity.getLastName())")
    @Mapping(target = "address",
            expression = "java(userEntity.getStreet() + \" / \" + userEntity.getWard() + \" / \" + userEntity.getDistrict() + \" / \" + userEntity.getCity())")
    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity toUserEntity(CreateUserRequest request);

    void updateUserEntity(@MappingTarget UserEntity userEntity, UpdateUserRequest request);
}
