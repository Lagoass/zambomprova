package br.insper.prova.Jogador;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JogadorRepository extends MongoRepository<Jogador, String> {
}
