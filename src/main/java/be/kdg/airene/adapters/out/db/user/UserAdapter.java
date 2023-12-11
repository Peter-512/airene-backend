package be.kdg.airene.adapters.out.db.user;

import be.kdg.airene.adapters.out.mapper.UserMapper;
import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.in.CreateUserPort;
import be.kdg.airene.ports.in.LoadUserPort;
import be.kdg.airene.ports.in.NearestUsersLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserAdapter implements LoadUserPort, CreateUserPort, NearestUsersLoadPort {

	private final UserRepository userRepository;
	private final UserMapper userMapper = UserMapper.INSTANCE;


	@Override
	public Optional<User> loadUser(UUID id) {
		return userRepository.findById(id).map(userMapper::toDomain);
	}

	@Override
	public boolean createUser(User user) {
		userRepository.save(userMapper.toJPA(user));
		return true;
	}

	@Override
	public List<User> loadUsersSubscribedToNearestAnomaly(double latitude, double longitude) {
		// TODO: implement
		return new ArrayList<>();
	}
}
