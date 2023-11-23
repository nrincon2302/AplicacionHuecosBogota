package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Hueco;
import uniandes.edu.co.proyecto.repositorio.HuecoRepository;
import uniandes.edu.co.proyecto.repositorio.UbicacionRepository;

@Controller
public class HuecoController {

    @Autowired
    private HuecoRepository huecoRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping("/huecos")
    public String huecos(Model model) {
        model.addAttribute("huecos", huecoRepository.darHuecos());
        return "huecos";
    }

    @GetMapping("/huecos/new")
    public String huecoForm(Model model) {
        model.addAttribute("hueco", new Hueco());
        model.addAttribute("ubicaciones", ubicacionRepository.darUbicaciones());
        return "huecosNuevo";
    }

    @PostMapping("/huecos/new/save")
    public String huecoGuardar(@ModelAttribute Hueco hueco) {
        huecoRepository.agregarHueco(hueco.getVolumen(), hueco.getEstado(), hueco.getUbicacion().getId());
        return "redirect:/huecos";
    }

    @GetMapping("/huecos/{id}/edit")
    public String huecoEditarForm(@PathVariable("id") long id, Model model) {
        Hueco hueco = huecoRepository.darHuecoPorId(id);
        if (hueco != null) {
            model.addAttribute("hueco", hueco);
            model.addAttribute("ubicaciones", ubicacionRepository.darUbicaciones());
            return "huecosEditar";
        } else {
            return "redirect:/huecos";
        }
    }

    @PostMapping("/huecos/{id}/edit/save")
    public String huecoEditarGuardar(@PathVariable("id") long id, @ModelAttribute Hueco hueco) {
        huecoRepository.actualizarHueco(id, hueco.getVolumen(), hueco.getEstado(), hueco.getUbicacion().getId());
        return "redirect:/huecos";
    }

    @GetMapping("/huecos/{id}/delete")
    public String huecoBorrar(@PathVariable("id") long id) {
        huecoRepository.eliminarHueco(id);
        return "redirect:/huecos";
    }
    
}
