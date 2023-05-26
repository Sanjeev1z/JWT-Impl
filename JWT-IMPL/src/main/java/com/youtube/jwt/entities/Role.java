package com.youtube.jwt.entities;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Entity
@Data
public class Role {
	
	@Id
	@Column(unique = true)
	private String roleName;
	
	private String roleDescription;
	
	private OffsetDateTime createdAt;

	private OffsetDateTime updatedAt;
	
	@PrePersist
	public void updateCreationTime() {
		createdAt = OffsetDateTime.now();
		updatedAt = OffsetDateTime.now();
	}

	@PreUpdate
	public void updateModificationTime() {
		updatedAt = OffsetDateTime.now();
	}
}