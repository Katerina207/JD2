package com.refunits.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewProduct {

    private Integer id;
    private ViewBasicUnitInfo unit;
    private List<ViewOptionInfo> options;
    Integer sum;
    Integer number;
    Integer subtotal;
}