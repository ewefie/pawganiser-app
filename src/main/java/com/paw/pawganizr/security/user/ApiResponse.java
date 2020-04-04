package com.paw.pawganizr.security.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @ApiModelProperty(notes = "")//todo:
    private boolean success;
    @ApiModelProperty(notes = "")//todo:
    private String message;
}
