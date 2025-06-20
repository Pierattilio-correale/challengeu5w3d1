package it.epicode.challengeu5w3d1.controller;

import it.epicode.challengeu5w3d1.dto.ViaggioDto;
import it.epicode.challengeu5w3d1.exception.AlreadyExistException;
import it.epicode.challengeu5w3d1.exception.NotFoundException;
import it.epicode.challengeu5w3d1.exception.ValidationException;
import it.epicode.challengeu5w3d1.model.Stato;
import it.epicode.challengeu5w3d1.model.Viaggio;
import it.epicode.challengeu5w3d1.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @PostMapping("")
    public Viaggio creaViaggio(@RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult) throws ValidationException, AlreadyExistException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));

        }
        return viaggioService.saveViaggio(viaggioDto);
    }
    @GetMapping("")
    public List<Viaggio> getAllViaggi(){
        return  viaggioService.getAllViaggi();
    }
    @GetMapping("/{id}")
    public Viaggio getViaggio(@PathVariable  int id) throws NotFoundException {
        return  viaggioService.getViaggio(id);
    }
    @PutMapping("/{id}")
    public Viaggio aggiornaViaggio(@PathVariable int id ,@RequestBody @Validated ViaggioDto viaggioDto,BindingResult bindingResult) throws ValidationException, NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));

        }
        return  viaggioService.aggiornaViaggio(id,viaggioDto);


    }
    @PatchMapping("/{id}")
    public Viaggio patchViaggio(@PathVariable int id , @RequestBody Stato stato) throws NotFoundException, IOException {
        return viaggioService.patchStatoViaggio(id,stato);
    }
    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable int id) throws NotFoundException {
        viaggioService.deleteViaggio(id);
    }
}
