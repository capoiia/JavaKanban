package com.example.ProjetoKanban.repository;

import com.example.ProjetoKanban.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    @Query("SELECT t FROM TarefaModel t ORDER BY t.status, t.prioridade")
    List<TarefaModel> findAllOrderedByPriorityAndStatus();

    TarefaModel findPorID(Integer id);
    void deletePorID(Integer id);

    List<TarefaModel> findByPrioridadeAndDataLimiteBefore(String prioridade, LocalDate dataLimite);
}
