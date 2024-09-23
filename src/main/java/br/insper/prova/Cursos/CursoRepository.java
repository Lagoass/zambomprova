package br.insper.prova.Cursos;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursoRepository extends MongoRepository<Curso, String> {
    List<Curso> findByName(String nome);
}
