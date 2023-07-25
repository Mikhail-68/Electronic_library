package ru.egorov.electroniclibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.dao.ClientDAO;
import ru.egorov.electroniclibrary.dao.RentDAO;
import ru.egorov.electroniclibrary.models.Client;
import ru.egorov.electroniclibrary.models.Validator.ClientValidator;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientDAO clientDAO;
    private final ClientValidator clientValidator;
    private final RentDAO rentDAO;
    @Autowired
    public ClientController(ClientDAO clientDAO, ClientValidator clientValidator, RentDAO rentDAO) {
        this.clientDAO = clientDAO;
        this.clientValidator = clientValidator;
        this.rentDAO = rentDAO;
    }

    @GetMapping
    public String showListClients(Model model) {
        model.addAttribute("clients", clientDAO.getAll());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String showClientInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientDAO.get(id));
        model.addAttribute("rents", rentDAO.getByClient(id));
        return "clients/client";
    }

    // Create

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "clients/new";
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors())
            return "clients/new";
        clientDAO.add(client);
        return "redirect:/clients";
    }

    // update

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientDAO.get(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@PathVariable("id") int id,
                               @ModelAttribute("client") @Valid Client client,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "/clients/edit";
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors())
            return "/clients/edit";
        client.setId(id);
        clientDAO.update(client);
        return "redirect:/clients/" + id;
    }

    // delete

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        clientDAO.delete(id);
        return "redirect:/clients";
    }
}
