package com.aula1.aula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class Paginas {

    @GetMapping("/pagina3")
    public String abrirPagina3() {
        return "pagina3";
    }

    @GetMapping("/pagina4")
    public String abrirPagina4(Model model) {
        String message = "Welcome to the fourth page";

        model.addAttribute("mensagem", message);

        return "pagina4";
    }

    @GetMapping("/pagina5")
    public String abrirPagina5(@RequestParam(name = "nome", required = false, defaultValue = "Sekai") String nome,
            Model model) {
        String texto = "Hello" + nome;

        model.addAttribute("mensagem", texto);

        return "pagina5";
    }


    @GetMapping("/tabuada")
    public String getTabuada(@RequestParam(name = "numero", required = true) int numero, Model model) {
        int[] resultados = new int[10];
        for (int i = 0; i < 10; i++) {
            resultados[i] = numero * (i + 1);
        }

        model.addAttribute("numero", numero);
        model.addAttribute("resultados", resultados);

        return "tabuada";
    }

}
