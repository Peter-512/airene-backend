package be.kdg.airene.adapters.in.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NewUserDTO {
	// TODO if time, exception handling
	private UUID id;
	private String name;
	private String email;
}
