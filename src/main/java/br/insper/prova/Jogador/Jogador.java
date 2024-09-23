package br.insper.prova.Jogador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document
@Getter
@Setter
public class Jogador {

    @Id
    private String id;
    private String nome;
    private Integer idade;
    private List<Integer> times;



}
