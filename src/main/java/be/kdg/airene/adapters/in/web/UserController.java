package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.NewUserDTO;
import be.kdg.airene.adapters.in.web.dto.SubscriptionDTO;
import be.kdg.airene.adapters.in.web.dto.SubscriptionRequestDTO;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.adapters.out.mapper.SubscriptionMapper;
import be.kdg.airene.adapters.out.mapper.UserMapper;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.CreateAccountIfNotExistsUseCase;
import be.kdg.airene.ports.in.GetUserSubscriptionsUseCase;
import be.kdg.airene.ports.in.SubscribeToLocationUseCase;
import be.kdg.airene.ports.in.UnsubscribeFromLocationUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final CreateAccountIfNotExistsUseCase createAccountIfNotExistsUseCase;
	private final GetUserSubscriptionsUseCase getUserSubscriptionsUseCase;
	private final SubscribeToLocationUseCase subscribeToLocationUseCase;
	private final UnsubscribeFromLocationUseCase unsubscribeFromLocationUseCase;
	private final LocationMapper locationMapper = LocationMapper.INSTANCE;

	private final UserMapper mapper = UserMapper.INSTANCE;
	private final SubscriptionMapper subscriptionMapper = SubscriptionMapper.INSTANCE;

	@PostMapping
	public ResponseEntity<?> createAccountIfNotExists(@RequestBody NewUserDTO newUserDTO) {
		boolean isAccountNew = createAccountIfNotExistsUseCase.createAccountIfNotExists(mapper.toDomain(newUserDTO));
		if (isAccountNew) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.accepted().build();
		}
	}

	@GetMapping("/{id}/subscriptions")
	public ResponseEntity<?> getSubscriptions(@PathVariable UUID id) {
		return ResponseEntity.ok(subscriptionMapper.toDTO(getUserSubscriptionsUseCase.getUserSubscriptions(id)));
	}

	@PostMapping("/{id}/subscriptions")
	public ResponseEntity<SubscriptionDTO> subscribeToLocation(@RequestBody @Valid SubscriptionRequestDTO subscribtionRequestDTO, @PathVariable UUID id) {
		Subscription subscription = subscribeToLocationUseCase.subscribeToLocation(id, locationMapper.toDomain(subscribtionRequestDTO));
		if (subscription != null) {
			return ResponseEntity.created(null).body(subscriptionMapper.toDTO(subscription));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}/subscriptions/{subscriptionId}/unsubscribe")
	public ResponseEntity<Void> unsubscribeToLocation(@PathVariable UUID id, @PathVariable UUID subscriptionId) {
		boolean isUnsubscribed = unsubscribeFromLocationUseCase.unsubscribeFromLocation(id, subscriptionId);
		if (isUnsubscribed) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
