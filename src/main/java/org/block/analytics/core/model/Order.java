package org.block.analytics.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {

    private Integer column;
    private String dir;
}
