package be.kdg.airene.ports.out;

import be.kdg.airene.domain.user.User;

@FunctionalInterface
public interface CreateUserPort {
	boolean createUser(User user);
}
