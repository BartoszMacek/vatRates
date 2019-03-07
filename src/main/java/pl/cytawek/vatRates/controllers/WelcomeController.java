package pl.cytawek.vatRates.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cytawek.vatRates.services.VatService;

@Controller
public class WelcomeController {

    private final VatService vatService;

    @Autowired
    public WelcomeController(VatService vatService) {
        this.vatService = vatService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute( "countrylist", vatService.getCurrentVat().getRates() );
        return "index";
    }



}
