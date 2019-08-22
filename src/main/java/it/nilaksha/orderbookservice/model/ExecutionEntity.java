package it.nilaksha.orderbookservice.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ExecutionEntity {

    private Integer id;

    private Integer quantity;

    private Double price;

}
