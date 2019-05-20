package com.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.rest.entity.Client;

@Component
public class ClientResourceAssembler implements ResourceAssembler<Client, Resource<Client>>{
	  @Override
	  public Resource<Client> toResource(Client client) {

	    return new Resource<>(client,
	      linkTo(methodOn(ClientController.class).identificaCliente(client.getId())).withSelfRel());
	  }
	  
}
