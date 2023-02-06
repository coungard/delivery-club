package com.coungard.mapper;

import com.coungard.entity.Role;
import com.coungard.entity.User;
import com.coungard.model.RoleName;
import com.coungard.security.UserPrincipal;
import java.util.stream.Collectors;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Mapper(imports = {Collectors.class, SimpleGrantedAuthority.class, Role.class, RoleName.class})
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "authorities",
      expression = "java(user.getRoles()"
          + ".stream()"
          + ".map(role -> new SimpleGrantedAuthority(role.getName()))"
          + ".collect(Collectors.toList()))")
  UserPrincipal toPrincipal(User user);

  @InheritInverseConfiguration
  User toUser(UserPrincipal userPrincipal);
}
