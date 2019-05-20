package com.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.rest.entity.Order;

@Component
public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>>{

  @Override
  public Resource<Order> toResource(Order order) {

    return new Resource<>(order,
      linkTo(methodOn(OrderController.class).identificaOrdine(order.getId())).withSelfRel());
  }
}
