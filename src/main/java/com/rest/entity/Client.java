package com.rest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Cliente")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long id;
	
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	private String indirizzo;
	private String telefono;
	private String mail;
	
	@OneToMany(mappedBy= "client",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Order> ordini;
	
	public Client() {
	}
	
	public Client(String ragioneSociale, String indirizzo, String telefono, String mail) {
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.mail = mail;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Order> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Order> ordini) {
		this.ordini = ordini;
	}
	
}
