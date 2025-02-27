package domain;


import com.farmacia.model.Medicamento;
import com.farmacia.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public String listarMedicamentos(Model model) {
        List<Medicamento> medicamentos = medicamentoService.listarTodos();
        model.addAttribute("medicamentos", medicamentos);
        return "medicamentos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        return "medicamentos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarMedicamento(@ModelAttribute Medicamento medicamento) {
        medicamentoService.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable int id, Model model) {
        Medicamento medicamento = medicamentoService.obtenerPorId(id);
        model.addAttribute("medicamento", medicamento);
        return "medicamentos/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMedicamento(@PathVariable int id, @ModelAttribute Medicamento medicamento) {
        medicamento.setId(id);
        medicamentoService.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable int id) {
        medicamentoService.eliminar(id);
        return "redirect:/medicamentos";
    }
}
