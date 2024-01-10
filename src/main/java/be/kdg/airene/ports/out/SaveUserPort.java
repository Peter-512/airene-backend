package be.kdg.airene.ports.out;

import be.kdg.airene.domain.user.User;

@FunctionalInterface
public interface SaveUserPort {
	void saveUser(User user);
}
