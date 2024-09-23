package br.insper.prova.Cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarCurso(@RequestParam(required = false) String nome) {
        return cursoService.listarCurso(nome);
    }

    @PostMapping
    public Curso salvarCurso(@RequestBody Curso curso) {
        return cursoService.salvarCurso(curso);
    }

    @PostMapping("/{id_do_curso}")
    public Curso adicionarAluno(@PathVariable String id_do_curso, @RequestParam String cpf_do_aluno) {
        return cursoService.adicionarAluno(id_do_curso, cpf_do_aluno);
    }
}
