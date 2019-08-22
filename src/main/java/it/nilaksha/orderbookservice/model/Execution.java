package it.nilaksha.orderbookservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Execution {

    @ApiModelProperty(value = "Quantity of the Execution.", required = true, example = "10")
    private Integer quantity;

    @ApiModelProperty(value = "Price of the Execution.", required = true, example = "10.50")
    private Double price;

}
