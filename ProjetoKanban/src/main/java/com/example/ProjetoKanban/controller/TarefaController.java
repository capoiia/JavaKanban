package com.example.ProjetoKanban.controller;

import com.example.ProjetoKanban.model.TarefaModel;
import com.example.ProjetoKanban.repository.TarefaRepository;
import com.example.ProjetoKanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<TarefaModel> listarTodas() {
        return tarefaService.listarTodas();
    }

    @PostMapping
    public TarefaModel criarTarefa(@RequestBody TarefaModel tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public TarefaModel atualizarTarefa(@PathVariable Integer id, @RequestBody TarefaModel tarefa) {
        tarefa.setId(id);
        return tarefaService.atualizarTarefa(tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Integer id) {
        tarefaService.excluirTarefa(id);
    }

    @PutMapping("/{id}/mover")
    public void moverTarefa(@PathVariable Integer id, @RequestParam String novoStatus) {
        tarefaService.moverTarefa(id, novoStatus);
    }

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/ordenadas")
    public List<TarefaModel> listarTarefasOrdenadas() {
        return tarefaService.listarTarefasOrdenadas();
    }

    @GetMapping("/relatorio")
    public Map<String, List<TarefaModel>> gerarRelatorioDeTarefas() {
        return tarefaService.gerarRelatorioDeTarefas();
    }

    @GetMapping("/filtrar")
    public List<TarefaModel> filtrarTarefasPorPrioridadeEDataLimite(
            @RequestParam String prioridade,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLimite) {
        return tarefaService.filtrarTarefasPorPrioridadeEDataLimite(prioridade, dataLimite);
    }

}



