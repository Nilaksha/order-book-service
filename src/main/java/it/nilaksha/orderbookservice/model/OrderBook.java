package it.nilaksha.orderbookservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@ApiModel
public class OrderBook {

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Id of the Order Book.", readOnly = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "Id of the Instrument.", required = true, example = "1")
    private Long instrumentId;

    @ApiModelProperty(value = "List of the Orders.", required = true, example = "1")
    private Collection<Order> orders;

    @ApiModelProperty(value = "List of the Executions.", required = true, example = "1")
    private Collection<Execution> executions;

    @ApiModelProperty(value = "Status of the Order Book.", required = true, allowableValues = "OPEN, CLOSE", example = "OPEN")
    private OrderBookStatus orderBookStatus;

}
