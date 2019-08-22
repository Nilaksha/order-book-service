package it.nilaksha.orderbookservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class Order {

    @ApiModelProperty(value = "Type of the Order.", required = true, allowableValues = "MARKET_ORDER, LIMIT_ORDER", example = "MARKET_ORDER")
    private OrderType orderType;

    @ApiModelProperty(value = "Quantity of the Order.", required = true, example = "10")
    private Integer quantity;

    @ApiModelProperty(value = "Price of the Order.", required = true, example = "10.50")
    private Double price;

    @ApiModelProperty(value = "Order Entry Date.", required = true, example = "2019-05-24T00:00:00Z")
    private Date entryDate;

}
