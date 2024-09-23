package br.insper.prova.Cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso salvarCurso(Curso curso) {
        curso.setId(UUID.randomUUID().toString());
        if (curso.getNome() == null) {
            throw new IllegalArgumentException("O nome do curso é obrigatório.");
        }
        if (curso.getDescricao() == null) {
            throw new IllegalArgumentException("A descrição do curso é obrigatória.");
        }
        if (curso.getNumero_alunos_max() == null) {
            throw new IllegalArgumentException("O número máximo de alunos é obrigatório e deve ser maior que zero.");
        }
        if (curso.getCpf_do_professor() == null) {
            throw new IllegalArgumentException("O CPF do professor é obrigatório.");
        }
        if (curso.getLista_alunos() != null) {
            for (String cpf_aluno : curso.getLista_alunos()) {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<ReturnDTO> alunoResponse = restTemplate.getForEntity(
                        "http://184.72.80.215:8080/usuario/" + cpf_aluno, ReturnDTO.class);
                if (!alunoResponse.getStatusCode().is2xxSuccessful()) {
                    throw new IllegalArgumentException("O aluno não foi encontrado no sistema.");
                }
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReturnDTO> professorResponse = restTemplate.getForEntity(
                "http://184.72.80.215:8080/usuario/" + curso.getCpf_do_professor(), ReturnDTO.class);
        if (!professorResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("O professor não foi encontrado no sistema.");
        }
        cursoRepository.save(curso);
        return curso;
    }

    public List<Curso> listarCurso(String nome) {
        if (nome != null) {
            return cursoRepository.findByName(nome);
        }
        return cursoRepository.findAll();
    }
    public Curso adicionarAluno(String idCurso, String cpf_aluno) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() ->
                new IllegalArgumentException("O curso não foi encontrado no sistema.")
        );
        if (curso.getNumero_alunos_max() <= curso.getLista_alunos().size()) {
            throw new IllegalArgumentException("O curso já atingiu o número máximo de alunos.");
        }
        if (curso.getLista_alunos().contains(cpf_aluno)) {
            throw new IllegalArgumentException("O aluno já está matriculado no curso.");
        }
        if (cpf_aluno == null || cpf_aluno.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do aluno é obrigatório.");
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReturnDTO> alunoResponse = restTemplate.getForEntity(
                "http://184.72.80.215:8080/usuario/" + cpf_aluno, ReturnDTO.class);
        if (!alunoResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("O aluno não foi encontrado no sistema.");
        }
        curso.getLista_alunos().add(cpf_aluno);
        cursoRepository.save(curso);
        return curso;
    }
}