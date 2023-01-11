package com.example.finway_uz.bot;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Test {
    private String  quastion;
    private List<String> answers;
    private int correct;

}
