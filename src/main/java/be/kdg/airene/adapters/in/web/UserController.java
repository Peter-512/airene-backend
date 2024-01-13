package be.kdg.airene.adapters.in.web;

import be.kdg.airene.adapters.in.web.dto.NewUserDTO;
import be.kdg.airene.adapters.in.web.dto.Sort;
import be.kdg.airene.adapters.in.web.dto.SubscriptionDTO;
import be.kdg.airene.adapters.in.web.dto.SubscriptionRequestDTO;
import be.kdg.airene.adapters.out.mapper.LocationMapper;
import be.kdg.airene.adapters.out.mapper.NotificationMapper;
import be.kdg.airene.adapters.out.mapper.SubscriptionMapper;
import be.kdg.airene.adapters.out.mapper.UserMapper;
import be.kdg.airene.domain.subscription.Subscription;
import be.kdg.airene.ports.in.*;
import io.sentry.Sentry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/users")
public class UserController {

	private final CreateAccountIfNotExistsUseCase createAccountIfNotExistsUseCase;
	private final GetUserNotificationsLastTwoWeeksUseCase getUserNotificationsLastTwoWeeksUseCase;
	private final GetUserSubscriptionsUseCase getUserSubscriptionsUseCase;
	private final SubscribeToLocationUseCase subscribeToLocationUseCase;
	private final UnsubscribeFromLocationUseCase unsubscribeFromLocationUseCase;
	private final LocationMapper locationMapper = LocationMapper.INSTANCE;

	private final UserMapper mapper = UserMapper.INSTANCE;
	private final SubscriptionMapper subscriptionMapper = SubscriptionMapper.INSTANCE;
	private final NotificationMapper notificationMapper = NotificationMapper.INSTANCE;
	private final PauseUnpauseSubscriptionUseCase pauseUnpauseSubscriptionUseCase;

	@PostMapping
	public ResponseEntity<?> createAccountIfNotExists(@RequestBody NewUserDTO newUserDTO) {
		boolean isAccountNew = createAccountIfNotExistsUseCase.createAccountIfNotExists(mapper.toDomain(newUserDTO));
		if (isAccountNew) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.accepted().build();
		}
	}

	@GetMapping ("/sentry-test")
	public ResponseEntity<?> test() {
		try {
			throw new Exception("This is a test.");
		} catch (Exception e) {
			Sentry.captureException(e);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping ("/{id}/subscriptions")
	public ResponseEntity<?> getSubscriptions(@PathVariable UUID id, @RequestParam (required = false, name = "sortBy", defaultValue = "desc") Sort sortBy) {
		return ResponseEntity.ok(subscriptionMapper.toDTO(getUserSubscriptionsUseCase.getUserSubscriptions(id, sortBy.name())));
	}

	@GetMapping ("/{id}/notifications")
	public ResponseEntity<?> getNotifications(@PathVariable UUID id, @RequestParam (required = false, name = "sortBy", defaultValue = "desc") Sort sortBy) {
		return ResponseEntity.ok(notificationMapper.toDTO(getUserNotificationsLastTwoWeeksUseCase.getUserNotificationsLastTwoWeeks(id, sortBy.name())));
	}

	@PostMapping ("/{id}/subscriptions")
	public ResponseEntity<SubscriptionDTO> subscribeToLocation(@RequestBody @Valid SubscriptionRequestDTO subscribtionRequestDTO, @PathVariable UUID id) {
		Subscription subscription = subscribeToLocationUseCase.subscribeToLocation(id, locationMapper.toDomain(subscribtionRequestDTO));
		if (subscription != null) {
			return ResponseEntity.created(null).body(subscriptionMapper.toDTO(subscription));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping ("/{id}/subscriptions/{subscriptionId}")
	public ResponseEntity<Void> unsubscribeToLocation(@PathVariable UUID id, @PathVariable UUID subscriptionId) {
		boolean isUnsubscribed = unsubscribeFromLocationUseCase.unsubscribeFromLocation(id, subscriptionId);
		if (isUnsubscribed) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PatchMapping ("/{id}/subscriptions/{subscriptionId}/pause")
	public ResponseEntity<SubscriptionDTO> pauseSubscription(@PathVariable UUID id, @PathVariable UUID subscriptionId) {
		var subscription = pauseUnpauseSubscriptionUseCase.togglePauseSubscription(id, subscriptionId);
		return subscription.map(value -> ResponseEntity.ok(subscriptionMapper.toDTO(value)))
		                   .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
