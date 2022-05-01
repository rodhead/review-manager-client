package org.block.analytics.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DataTableRequest {

    private Integer draw;
    private Integer length;
    private Integer start;
    private List<Columns> columns;
    private List<Order> order;
    private ColumnSearch search;
}
