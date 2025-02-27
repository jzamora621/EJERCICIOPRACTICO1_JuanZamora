package dao;

import domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoDao extends JpaRepository<Medicamento, Long> {}
