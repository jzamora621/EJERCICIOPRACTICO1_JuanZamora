package serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.MedicamentoDao;
import EjercicioPractico1_SebastianMendezArtavia.EjercicioPractico1.domain.Medicamento;
import service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    
    @Autowired
    private MedicamentoDao medicamentoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Medicamento> getMedicamentos() {
        return medicamentoDao.findAll();
    }
}