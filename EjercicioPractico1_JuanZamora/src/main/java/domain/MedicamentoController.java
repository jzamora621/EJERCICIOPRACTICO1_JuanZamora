package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentosService MedicamentosService;

    @GetMapping
    public String listarMedicamentos(Model model) {
        List<Medicamento> medicamentos = MedicamentosService.listarTodos();
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
        MedicamentosService.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Medicamento medicamento = MedicamentosService.obtenerPorId(id);
        model.addAttribute("medicamento", medicamento);
        return "medicamentos/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMedicamento(@PathVariable Long id, @ModelAttribute Medicamento medicamento) {
        medicamento.setId(id);
        MedicamentosService.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable Long id) {
        MedicamentosService.eliminar(id);
        return "redirect:/medicamentos";
    }
}
