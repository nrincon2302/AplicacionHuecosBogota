package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Ubicacion;
import uniandes.edu.co.proyecto.repositorio.UbicacionRepository;

@Controller
public class UbicacionController {
    
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping("/ubicaciones")
    public String ubicaciones(Model model) {
        model.addAttribute("ubicaciones", ubicacionRepository.darUbicaciones());
        return "ubicaciones";
    }

    @GetMapping("/ubicaciones/new")
    public String ubicacionForm(Model model) {
        model.addAttribute("ubicacion", new Ubicacion());
        return "ubicacionesNuevo";
    }

    @PostMapping("/ubicaciones/new/save")
    public String ubicacionGuardar(@ModelAttribute Ubicacion ubicacion) {
        ubicacionRepository.agregarUbicacion(ubicacion.getDireccion(), ubicacion.getCiudad());
        return "redirect:/ubicaciones";
    }

    @GetMapping("/ubicaciones/{id}/edit")
    public String ubicacionEditarForm(@PathVariable("id") long id, Model model) {
        Ubicacion ubicacion = ubicacionRepository.darUbicacionPorId(id);
        if (ubicacion != null) {
            model.addAttribute("ubicacion", ubicacion);
            return "ubicacionesEditar";
        } else {
            return "redirect:/ubicaciones";
        }
    }

    @PostMapping("/ubicaciones/{id}/edit/save")
    public String ubicacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute Ubicacion ubicacion) {
        ubicacionRepository.actualizarUbicacion(id, ubicacion.getDireccion(), ubicacion.getCiudad());
        return "redirect:/ubicaciones";
    }

    @GetMapping("/ubicaciones/{id}/delete")
    public String ubicacionBorrar(@PathVariable("id") long id) {
        ubicacionRepository.eliminarUbicacion(id);
        return "redirect:/ubicaciones";
    }
}
