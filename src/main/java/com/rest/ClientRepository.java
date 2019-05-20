package com.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{
	
	List<Client> findByRagioneSocialeContaining(String ragione_sociale);
}
