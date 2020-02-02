package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.BasicPetInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasicPetInfos {
    private List<BasicPetInfo> basicPetInfos;
}
