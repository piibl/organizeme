package com.talsoft.organizeme.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import com.talsoft.organizeme.core.util.time.JodaDateTimeConverter;

/**
 * Utilisateur final (EndUser)
 */
@Entity
@Table(name = "END_USER")
public class User {

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * Identifiant de l'utilisateur
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EndUserSeq")
	@SequenceGenerator(name = "EndUserSeq", sequenceName = "END_USER_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;
	/**
	 * Nom de famille de l'utilisateur
	 */
	@Column(name = "LAST_NAME")
	private String lastName;
	/**
	 * Prénom de l'utilisateur
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;
	/**
	 * Login de l'utilisateur
	 */
	@Column(name = "LOGIN")
	private String login;
	/**
	 * Mot de passe de l'utilisateur <br/>
	 * TODO à crypter !!!
	 */
	@Column(name = "PWD")
	private String password;
	/**
	 * Email de l'utilisateur
	 */
	@Column(name = "MAIL")
	private String email;

	/**
	 * Date de création du compte utilisateur
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime createdAt;

	/**
	 * Constructeur
	 * 
	 * @param lastName
	 *            : nom de famille
	 * @param firstName
	 *            : prénom
	 * @param login
	 *            : login
	 * @param password
	 *            : mot de passe
	 * @param email
	 *            : adresse email
	 */
	public User(String lastName, String firstName, String login, String password, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.login = login;
		this.password = password;
		this.email = email;
		// Date courante
		// TODO Rendre non système-dépendant
		this.createdAt = new DateTime();
	}

	/*************************
	 * MUTATEURS
	 ***********************/
	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

}
