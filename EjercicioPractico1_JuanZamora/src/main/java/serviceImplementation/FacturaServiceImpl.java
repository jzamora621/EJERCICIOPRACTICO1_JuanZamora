package serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.FacturaDao;
import EjercicioPractico1_SebastianMendezArtavia.EjercicioPractico1.domain.Factura;
import service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {
    
    @Autowired
    private FacturaDao facturaDao;

    @Override
    @Transactional(readOnly=true)
    public List<Factura> getFacturas() {
        return facturaDao.findAll();
    }
}