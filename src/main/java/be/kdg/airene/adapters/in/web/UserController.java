package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.NewUserDTO;
import be.kdg.airene.adapters.out.mapper.UserMapper;
import be.kdg.airene.ports.in.CreateAccountIfNotExistsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/users")
@AllArgsConstructor
public class UserController {

	private final CreateAccountIfNotExistsUseCase createAccountIfNotExistsUseCase;
	private final UserMapper mapper = UserMapper.INSTANCE;

	@PostMapping
	public ResponseEntity<?> createAccountIfNotExists(@RequestBody NewUserDTO newUserDTO) {
		boolean isAccountNew = createAccountIfNotExistsUseCase.createAccountIfNotExists(mapper.toDomain(newUserDTO));
		if (isAccountNew) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.accepted().build();
		}
	}
}
