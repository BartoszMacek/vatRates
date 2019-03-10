package pl.cytawek.vatRates.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cytawek.vatRates.services.VatService;

@Controller
public class HistoryDBController {

    private final VatService vatService;

    public HistoryDBController(VatService vatService) {
        this.vatService = vatService;
    }

    @GetMapping ("/history")
    public String history (Model model) {
        model.addAttribute( "historyLog", vatService.getVatEntityLog() );
        return "history";
    }

    @RequestMapping(value = "/history/delete/", method = RequestMethod.GET)
    public String deleteRowFromDatabase(@RequestParam(name = "id") int idToDelete) {
        vatService.toDelete( idToDelete );

        return "redirect:/history";
    }

}