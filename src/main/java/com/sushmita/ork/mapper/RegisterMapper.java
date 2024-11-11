package com.sushmita.ork.mapper;

import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.entity.OrkUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Sushmita Budhathoki on 2024-10-08
 */

@Mapper(componentModel = "spring")
public interface RegisterMapper {

   RegisterMapper INSTANCE = Mappers.getMapper( RegisterMapper.class );

   @Mapping(source = "actualRole", target = "role")
   OrkUser registerDtoToUser(RegisterDto user);
}
