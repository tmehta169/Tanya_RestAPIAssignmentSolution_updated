package com.greatLearning.app.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class User {
	    @Id
		@Column(name = "user_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		private String username;

		private String password;

		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
		private List<Role> roles = new ArrayList<>();

		public User(String username, String password, List<Role> roles) {
			super();
			this.username = username;
			this.password = password;
			this.roles = roles;
		}

		public User(int id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
		}

	
		

	

}
