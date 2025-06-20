package it.epicode.challengeu5w3d1.controller;

import it.epicode.challengeu5w3d1.dto.PrenotazioneDto;
import it.epicode.challengeu5w3d1.exception.AlreadyExistException;
import it.epicode.challengeu5w3d1.exception.NotFoundException;
import it.epicode.challengeu5w3d1.exception.ValidationException;
import it.epicode.challengeu5w3d1.model.Prenotazione;
import it.epicode.challengeu5w3d1.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("")
    public Prenotazione creaPrenotazione(@RequestBody @Validated PrenotazioneDto prenotazioneDto,
                                         BindingResult bindingResult)
            throws ValidationException, AlreadyExistException, NotFoundException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));

        }

        return prenotazioneService.savePrenotazione(prenotazioneDto);
    }

    @GetMapping("")
    public Page<Prenotazione> getAllPrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneService.getAllPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazione(@PathVariable int id) throws NotFoundException {
        return prenotazioneService.getPrenotazione(id);
    }

    @PutMapping("/{id}")
    public Prenotazione aggiornaPrenotazione(@PathVariable int id,
                                             @RequestBody @Validated PrenotazioneDto prenotazioneDto,
                                             BindingResult bindingResult)
            throws ValidationException, NotFoundException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));

        }


        return prenotazioneService.aggiornaPrenotazione(id, prenotazioneDto);
    }

    @DeleteMapping("/{id}")
    public void eliminaPrenotazione(@PathVariable int id) throws NotFoundException {
        prenotazioneService.deletePrenotazione(id);
    }
}

