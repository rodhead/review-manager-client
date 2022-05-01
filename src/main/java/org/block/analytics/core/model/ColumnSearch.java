package org.block.analytics.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColumnSearch {

    private String value;
    private boolean regex;
}
