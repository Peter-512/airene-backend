package be.kdg.airene.ports.in;

import be.kdg.airene.domain.user.User;

public interface CreateAccountIfNotExistsUseCase {
	boolean createAccountIfNotExists(User user);
}
