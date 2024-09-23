package br.insper.prova.Cursos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cursos")
@Getter
@Setter
public class Curso {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private Integer numero_alunos_max;
    private String cpf_do_professor;
    private List<String> lista_alunos;
}
