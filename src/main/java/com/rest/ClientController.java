package com.rest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Client;
import com.rest.error.ClientNotFoundException;

@RestController
public class ClientController {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientResourceAssembler assembler;
	
	// Restituisce un cliente oppure cliente non trovato
	
	@GetMapping("/clients/{id_cliente}")
	Resource<Client> identificaCliente(@PathVariable Long id_cliente) {
		Client client = repository.findById(id_cliente)
	      .orElseThrow(() -> new ClientNotFoundException(id_cliente));
	    
	    return assembler.toResource(client);
	}
	
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clients")
    Resource<Client> inserisciCliente(@RequestBody Client newClient) {
    	Client client = repository.save(newClient);
        
        return assembler.toResource(client);
    }
    
    @PutMapping("/clients/{id_cliente}")
    Resource<Client> modificaCliente(@RequestBody Client newClient, @PathVariable Long id_cliente) {
    	Client client =  repository.findById(id_cliente)
                .map(x -> {
                	x.setIndirizzo(newClient.getIndirizzo());
                	x.setMail(newClient.getMail());
                	x.setRagioneSociale(newClient.getRagioneSociale());
                	x.setTelefono(newClient.getTelefono());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	newClient.setId(id_cliente);
                    return repository.save(newClient);
                });
        
        return assembler.toResource(client);
    }
    
    @DeleteMapping("/clients/{id_cliente}")
    void eliminaCliente(@PathVariable Long id_cliente) {
        repository.deleteById(id_cliente);
    }
    
    // Ricerca ragione sociale tipo like %ragionesociale%
    @GetMapping("/clients/search/{ragione_sociale}")
	Resources<Resource<Client>> ricercaCliente(@PathVariable String ragione_sociale) {
    	List<Resource<Client>> clientList = repository.findByRagioneSocialeContaining(ragione_sociale)
    			.stream()
    		    .map(assembler::toResource)
    		    .collect(Collectors.toList());
    	
    	return new Resources<>(clientList,
    			linkTo(methodOn(ClientController.class).ricercaCliente(ragione_sociale)).withSelfRel());
	}
}
