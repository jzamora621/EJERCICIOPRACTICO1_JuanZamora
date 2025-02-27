package serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.FacturaDetalleDao;
import EjercicioPractico1_SebastianMendezArtavia.EjercicioPractico1.domain.FacturaDetalle;
import service.FacturaDetalleService;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {
    
    @Autowired
    private FacturaDetalleDao facturaDetalleDao;

    @Override
    @Transactional(readOnly=true)
    public List<FacturaDetalle> getFacturaDetalles() {
        return facturaDetalleDao.findAll();
    }
}