package com.zeeshan.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name="applicationuser")
public class ApplicationUser {

	@Id
	@GeneratedValue(generator = "id_generator")
	@GenericGenerator(name = "id_generator", strategy = "increment")
	private Long id;
	private String username;
	private String password;

}
