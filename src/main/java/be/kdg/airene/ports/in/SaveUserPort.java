package be.kdg.airene.ports.in;

import be.kdg.airene.domain.user.User;

@FunctionalInterface
public interface SaveUserPort {
	void saveUser(User user);
}
