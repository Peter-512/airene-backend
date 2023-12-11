package be.kdg.airene.adapters.out.mapper;

import be.kdg.airene.adapters.in.web.dto.NewUserDTO;
import be.kdg.airene.adapters.out.db.user.UserJPA;
import be.kdg.airene.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	 NewUserDTO toDTO(User user);
	 User toDomain(NewUserDTO newUserDTO);
	 User toDomain(UserJPA user);
	 UserJPA toJPA(User user);
}
