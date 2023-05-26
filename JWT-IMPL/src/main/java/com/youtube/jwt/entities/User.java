package com.youtube.jwt.entities;

import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@Id
	@Column(unique = true)
	private String userName;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userPassword;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", 
			joinColumns = {
					@JoinColumn(name = "USER_ID")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID")
			}
	)
	private Set<Role> role;
	
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
