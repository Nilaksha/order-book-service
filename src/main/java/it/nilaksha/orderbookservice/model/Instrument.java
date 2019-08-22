package it.nilaksha.orderbookservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel
public class Instrument {

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Id of the Instrument.", readOnly = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "Name the Instrument.", required = true, example = "Shares")
    private String name;

}
