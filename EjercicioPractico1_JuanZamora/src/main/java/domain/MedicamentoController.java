package domain;

import service.MedicamentosService;
import domain.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentosService medicamentoService;

    @GetMapping
    public String listarMedicamentos(Model model) {
        List<Medicamento> medicamentos = medicamentoService.listarTodos();
        model.addAttribute("medicamentos", medicamentos);
        return "productos";
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
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Medicamento medicamento = medicamentoService.obtenerPorId(id);
        model.addAttribute("medicamento", medicamento);
        return "medicamentos/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarmedicamento(@PathVariable Long id, @ModelAttribute Medicamento medicamento) {
        medicamento.setId(id);
        medicamentoService.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarmedicamento(@PathVariable Long id) {
        medicamentoService.eliminar(id);
        return "redirect:/medicamentos";
    }
}
