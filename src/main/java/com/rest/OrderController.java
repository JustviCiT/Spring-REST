package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Order;
import com.rest.error.ClientNotFoundException;
import com.rest.error.OrderNotFoundException;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired 
	private OrderResourceAssembler assembler;
	
	// Restituisce un ordine oppure ordine non trovato
	@GetMapping("/orders/{id_ordine}")
	Resource<Order> identificaOrdine(@PathVariable Long id_ordine) {
	   	Order order = repository.findById(id_ordine)
	      .orElseThrow(() -> new OrderNotFoundException(id_ordine));
	   	
	   	return assembler.toResource(order);
	}
	
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clients/{id_client}/orders")
    Resource<Order> inserisciOrdine(@PathVariable Long id_client, @RequestBody Order newOrder) {
    	
    	Order order =  clientRepository.findById(id_client).map(client -> {
            newOrder.setClient(client);
            return repository.save(newOrder);
        })
    	.orElseThrow(() -> new ClientNotFoundException(id_client));
    	
	   	return assembler.toResource(order);
    }
    
    @PutMapping("/orders/{id_ordine}")
    Resource<Order> modificaOrdine(@RequestBody Order newOrder, @PathVariable Long id_ordine) {
        Order order = repository.findById(id_ordine)
                .map(x -> {
                	x.setDataInserimento(newOrder.getDataInserimento());
                	x.setDataConsegna(newOrder.getDataConsegna());
                	x.setStato(newOrder.getStato());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	newOrder.setId(id_ordine);
                    return repository.save(newOrder);
                });
        
        return assembler.toResource(order);
    }
    
    @DeleteMapping("/orders/{id_ordine}")
    void eliminaOrdine(@PathVariable Long id_ordine) {
        repository.deleteById(id_ordine);
    }
	
}
