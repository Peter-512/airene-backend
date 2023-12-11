package be.kdg.airene.ports.in;

import be.kdg.airene.domain.user.User;

@FunctionalInterface
public interface CreateUserPort {
	boolean createUser(User user);
}
