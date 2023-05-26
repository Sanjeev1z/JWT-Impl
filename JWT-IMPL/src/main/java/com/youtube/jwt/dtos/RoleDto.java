package com.youtube.jwt.dtos;

import java.time.OffsetDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleDto {

	@NotBlank
	private String roleName;
	
	@NotBlank
	private String roleDescription;
	
	private OffsetDateTime createdAt;

	private OffsetDateTime updatedAt;
	
}
