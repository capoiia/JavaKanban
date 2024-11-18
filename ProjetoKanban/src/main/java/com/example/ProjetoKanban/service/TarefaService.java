package com.example.ProjetoKanban.service;

import com.example.ProjetoKanban.model.TarefaModel;
import com.example.ProjetoKanban.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaModel> listarTodas() {
        return tarefaRepository.findAll();
    }

    public TarefaModel criarTarefa(TarefaModel tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public TarefaModel atualizarTarefa(TarefaModel tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Integer id) {
        tarefaRepository.deletePorID(id);
    }

    public void moverTarefa(Integer id, String novoStatus) {
        TarefaModel tarefa = tarefaRepository.findPorID(id);
        tarefa.setStatus(novoStatus);
        tarefaRepository.save(tarefa);
    }

    public List<TarefaModel> filtrarTarefasPorPrioridadeEDataLimite(String prioridade, LocalDate dataLimite) {
        return tarefaRepository.findByPrioridadeAndDataLimiteBefore(prioridade, dataLimite);
    }

    public Map<String, List<TarefaModel>> gerarRelatorioTarefas() {
        LocalDate hoje = LocalDate.now();

        List<TarefaModel> tarefas = tarefaRepository.findAll();

        // Marcar tarefas atrasadas
        for (TarefaModel tarefa : tarefas) {
            if (tarefa.getDataLimite() != null && tarefa.getDataLimite().isBefore(hoje) &&
                    !tarefa.getStatus().equalsIgnoreCase("Concluído")) {
                tarefa.setAtrasada(true);
            }
        }
        return tarefas.stream()
                .collect(Collectors.groupingBy(TarefaModel::getStatus));
    }


    public List<TarefaModel> listarTarefasOrdenadas() {
        return List.of();
    }

    public Map<String, List<TarefaModel>> gerarRelatorioDeTarefas() {
        return Map.of();
    }
}