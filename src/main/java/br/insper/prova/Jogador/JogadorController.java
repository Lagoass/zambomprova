package br.insper.prova.Jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    public void salvarJogador(@RequestBody Jogador jogador) {
        jogadorService.salvarJogador(jogador);
    }

    @GetMapping
    public List<Jogador> listarJogadores() {
        return jogadorService.listarJogadores();
    }

    @PostMapping("/adicionaTime/{id_jogador}/{id_time}")
    public void adicionaJogadorTime(@PathVariable String id_jogador, @PathVariable Integer id_time) {
        jogadorService.adicionaJogadorTime(id_jogador, id_time);
    }

}
