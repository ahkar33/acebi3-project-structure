package com.ace.web.pf.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ace.web.pf.enums.AuthorityName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "AUTHORITY")
@NamedQueries(value = { @NamedQuery(name = "Authority.findAllByName", query = "SELECT authority FROM Authority authority "
		+ "WHERE name IN :authorityNames")})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Authority {

	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
	@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
	private Long id;

	@Column(name = "authority_name", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}
}