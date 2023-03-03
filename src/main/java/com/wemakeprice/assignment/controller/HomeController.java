package com.wemakeprice.assignment.controller;

import com.wemakeprice.assignment.model.ParserResult;
import com.wemakeprice.assignment.dto.Request;
import com.wemakeprice.assignment.dto.FormResult;
import com.wemakeprice.assignment.component.URLHTMLParser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    private final URLHTMLParser parser;

    public HomeController(URLHTMLParser parser) {
        this.parser = parser;
    }

    @GetMapping("/")
    public String home(@ModelAttribute("request") Request req, @ModelAttribute("result") FormResult res) {
        return "index";
    }

    @GetMapping("/parse")
    public RedirectView parse(@Valid @ModelAttribute("request") Request req, RedirectAttributes attributes) {
        ParserResult parserResult = parser.parse(req.getUrlLink(), req.getOptionId(), req.getUnit());
        FormResult formResult = new FormResult(parserResult.getQuotient(), parserResult.getRemainder());

        attributes.addFlashAttribute("request", req);
        attributes.addFlashAttribute("result", formResult);

        return new RedirectView("/");
    }
}
