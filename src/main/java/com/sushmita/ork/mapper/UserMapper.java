package com.sushmita.ork.mapper;

import com.sushmita.ork.dtos.UserDto;
import com.sushmita.ork.entity.OrkUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Sushmita Budhathoki on 2024-10-08
 */

@Mapper(componentModel = "spring") //defining framework
public interface UserMapper {

   UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

   @Mapping(source = "emailAddress", target = "email")
   @Mapping(source = "actualRole", target = "role") //I think this is very useful when frontend and backend team are diff and there is field name clash
   OrkUser userDtoToUser(UserDto user);

   @Mapping(source = "email", target = "emailAddress")
   default UserDto mapEntityToDto(OrkUser orkUser) {
       return UserDto.builder()
              .role(orkUser.getRole().getName())
              .username(orkUser.getUsername())
              .emailAddress(orkUser.getEmail())
              .contactNumber(orkUser.getContactNumber())
              .build();
   }
}
