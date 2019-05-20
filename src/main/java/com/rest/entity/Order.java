package com.rest.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Ordine")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_ordine")
	private Long id;
	
	@Column(name="data_inserimento")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataInserimento;
	
	@Column(name="data_consegna")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataConsegna;
	
	private String stato;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	@NotNull
	@JsonBackReference
	private Client client;
	
	public Order() {
		
	}
	
	public Order(LocalDate dataInserimento, LocalDate dataConsegna, String stato, Client client) {
		this.dataInserimento = dataInserimento;
		this.dataConsegna = dataConsegna;
		this.stato = stato;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
