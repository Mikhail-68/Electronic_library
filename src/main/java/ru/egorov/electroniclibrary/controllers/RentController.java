package ru.egorov.electroniclibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.dao.RentDAO;

@Controller
@RequestMapping("/rents")
public class RentController {

    private final RentDAO rentDAO;

    @Autowired
    public RentController(RentDAO rentDAO) {
        this.rentDAO = rentDAO;
    }

    @PostMapping("/new")
    public String createRent(@RequestParam("clientSelect")int clientId,
                             @RequestParam("book") int bookId) {
        rentDAO.add(clientId, bookId);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/edit")
    public String confirmReturnBook(@PathVariable("id") int rentId,
                                    @RequestParam(value = "typePage", required = false) String typePage,
                                    @RequestParam(value = "pageId", required = false) int pageId) {
        rentDAO.confirmReturnBook(rentId);

        if(typePage != null){
            if(typePage.equals("book"))
                return "redirect:/books/" + pageId;
            else if(typePage.equals("client"))
                return "redirect:/clients/" + pageId;
        }
        return "redirect:/";
    }
}
