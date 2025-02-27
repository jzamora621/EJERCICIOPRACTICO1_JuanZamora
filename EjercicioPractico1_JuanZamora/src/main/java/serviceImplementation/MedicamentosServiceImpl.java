package serviceImplementation;

import domain.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import service.MedicamentosService;

@Service
public class MedicamentosServiceImpl implements MedicamentosService {

    @Autowired
    private MedicamentoDao medicamentoDao;

    @Override
    public List<Medicamento> listarTodos() {
        return medicamentoDao.findAll();
    }

    @Override
    public void guardar(Medicamento medicamento) {
        medicamentoDao.save(medicamento);
    }

    @Override
    public Medicamento obtenerPorId(Long id) {
        return medicamentoDao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        medicamentoDao.deleteById(id);
    }
}
