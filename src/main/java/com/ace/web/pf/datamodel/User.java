package com.ace.web.pf.datamodel;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.findPasswordById", query = "SELECT u.password FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE lower(u.userName) = lower(:userName)")})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User extends BaseDataModel {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "position")
	private String position;

	@Column(name = "active")
	private boolean active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AUTHORITY", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_id", referencedColumnName = "authority_id") })
	private List<Authority> authorities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
