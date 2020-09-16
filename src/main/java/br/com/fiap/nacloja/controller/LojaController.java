package br.com.fiap.nacloja.controller;

import br.com.fiap.nacloja.business.LojaBusiness;
import br.com.fiap.nacloja.model.LojaModel;
import br.com.fiap.nacloja.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    LojaBusiness business;

    @Autowired
    LojaRepository repository;

    @GetMapping
    public ResponseEntity findAll(){
        List<LojaModel> lojas = repository.findAll();

        return ResponseEntity.ok(lojas);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        LojaModel lojaModel = repository.findById(id).get();

        return ResponseEntity.ok(lojaModel);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid LojaModel lojaModel) throws Exception {
        LojaModel loja = business.save(lojaModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loja.getIdLoja())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid LojaModel lojaModel) throws Exception {
        lojaModel.setIdLoja(id);

        business.save(lojaModel);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
