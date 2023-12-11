package be.kdg.airene.ports.in;

import be.kdg.airene.domain.user.User;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadUserPort {
	Optional<User> loadUser(UUID id);
}
