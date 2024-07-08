package com.example.test_runners.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findByID(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunNotFoundException();
        }
        return  run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        //runRepository.save(run); // giving error as cannot save duplicate id
        if (!run.id().equals(id) && runRepository.existsById(run.id())) {
            throw new RuntimeException("The new ID " + run.id() + " already exists.");
        }
//        runRepository.deleteById(id);
//        runRepository.save(run);
        Run runWithExistingVersion = new Run(
                run.id(),
                run.title(),
                run.startOn(),
                run.completeOn(),
                run.miles(),
                run.location(),
                runRepository.findById(id).get().version() // Preserve the existing version
        );

        runRepository.deleteById(id); // Delete the existing run
        runRepository.save(runWithExistingVersion); // Save the updated run
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findAllByLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }
}
