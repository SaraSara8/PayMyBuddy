package com.paymybuddy.entity;

import java.util.List;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String username;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false )
	private String passeword;
	
	@ManyToMany // un utilisateur peut avoir plusieurs connections, et plusieurs connections peuvent etre associées à un utilisateur
	@JoinTable(
			name = "user_connections",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn ( name = "connection_id")
	)
	private List<Users> connections;
	

}
