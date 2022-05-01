package org.block.analytics.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Columns {

    private String data;
    private String name;
    private boolean orderable;
    private boolean searchable;
    private ColumnSearch search;
}
