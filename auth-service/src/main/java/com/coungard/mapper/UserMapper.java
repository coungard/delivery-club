package com.coungard.mapper;

import com.coungard.entity.User;
import com.coungard.security.UserPrincipal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "authorities",
      expression = "java(com.coungard.utils.MapUtils.rolesToAuthorities(user.getRoles()))")
  UserPrincipal toPrincipal(User user);

  List<UserPrincipal> toPrincipalList(List<User> users);
}
