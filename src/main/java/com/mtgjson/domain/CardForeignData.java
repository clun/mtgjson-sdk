package com.mtgjson.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CardForeignData {
    private String language;
    private String name;
    private String text;
    private String type;
}
