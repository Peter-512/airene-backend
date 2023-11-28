package be.kdg.airene.adapters.in.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

	// for regular users
	@GetMapping ("/test")
	public String test() {
		return "test";
	}


	// for datascientists
	@PreAuthorize("hasAuthority('SCOPE_App.Datascientist')")
	@GetMapping ("/test2")
	public String test2() {
		return "test2";
	}
}
