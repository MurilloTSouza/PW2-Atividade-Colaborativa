package br.edu.ifpb.atividadecolaborativa.controller;


import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import br.edu.ifpb.atividadecolaborativa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AlunoController {


    @Autowired
    AlunoService service;

    @RequestMapping("/erro")
    public String erro() {
        return "erro";
    }

    @RequestMapping(path = {"/alunos"}, method = {RequestMethod.GET})
    public ModelAndView getAlunos() {
        ModelAndView mv = new ModelAndView("listaAlunos");
        List<Aluno> alunos = service.findAll();
        mv.addObject("alunos", alunos);

        return mv;
    }

    @RequestMapping(path = {"/aluno/{id}"}, method = {RequestMethod.GET})
    public ModelAndView getAluno(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("aluno");
      Aluno aluno = service.findById(id);
        mv.addObject("aluno", aluno);

        return mv;
    }


    @RequestMapping(path = {"/alunoAtualizar/{id}"}, method = {RequestMethod.GET})
    public ModelAndView getAtualizar(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("editarAluno");
        Aluno aluno = service.findById(id);
        mv.addObject("aluno", aluno);

        return mv;
    }

    @RequestMapping(path = {"/novoAluno"}, method = {RequestMethod.GET})
    public String getCadastrarAluno() {
        return "cadastrarAluno";
    }


    @RequestMapping(path = {"/novoAluno"}, method = {RequestMethod.POST})
    public String novoAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos.");
            return "redirect:/novoAluno";
        }
        service.save(aluno);
        return "redirect:/alunos";
    }

    @RequestMapping(path = {"/editarAluno/{id}"})
    public ModelAndView editarAluno( @Valid @PathVariable("id") long id, @Valid Aluno aluno, BindingResult result) {

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("editarAluno");
            mv.addObject("aluno", aluno);
            mv.addObject("mensagem", "Verifique se os campos obrigatórios foram preenchidos.");
            return mv;
        }

        service.update(id,aluno);
        ModelAndView mv = new ModelAndView("listaAlunos");
        return mv;

    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/listaAlunos";

    }

}
