package be.kdg.airene.core;

import be.kdg.airene.domain.user.User;
import be.kdg.airene.ports.in.CreateAccountIfNotExistsUseCase;
import be.kdg.airene.ports.out.CreateUserPort;
import be.kdg.airene.ports.out.LoadUserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultCreateAccountIfNotExists implements CreateAccountIfNotExistsUseCase {

	private final LoadUserPort loadUserPort;
	private final CreateUserPort createUserPort;


	@Override
	public boolean createAccountIfNotExists(User user) {
		if (loadUserPort.loadUser(user.getId()).isEmpty()) {
			log.debug("User with id {} does not exist, creating new user", user.getId());
			boolean created = createUserPort.createUser(user);
			if (created) {
				log.debug("User with id {} created", user.getId());
				return true;
			} else {
				log.debug("User with id {} could not be created", user.getId());
				return false;
			}
		} else {
			log.debug("User with id {} already exists", user.getId());
			return false;
		}
	}
}
