package org.main.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class SimpleMap {
    Double d;
    String s;

    public SimpleMap(Double d, String s) {
        this.d = d;
        this.s = s;
    }
}
