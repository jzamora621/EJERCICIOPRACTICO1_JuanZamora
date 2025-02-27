package service;

import domain.Medicamento;
import java.util.List;

public interface MedicamentosService {
    List<Medicamento> listarTodos();
    void guardar(Medicamento medicamento);
    Medicamento obtenerPorId(Long id);
    void eliminar(Long id);
}
