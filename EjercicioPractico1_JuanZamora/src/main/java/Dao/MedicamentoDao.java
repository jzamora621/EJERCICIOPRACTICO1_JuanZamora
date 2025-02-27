package dao;

import domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoDao extends JpaRepository<Medicamento, Long> {
}
