package com.newCurd.Curdproject.Controller;


import com.newCurd.Curdproject.Exception.ResourceNotFoundException;
import com.newCurd.Curdproject.Reposotory.BeerRepository;
import com.newCurd.Curdproject.modal.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* The listeners in the Controller are complicated chunks of Java code (including REST API'S)
 * that detect an action by the user and call the method in the Model that takes care of that action. */

// CrossOrigin needed to prevent CORS ERROR - Access to XMLHttpRequest at 'http://localhost:8080/api/v1/beers' from origin 'http://localhost:3000' has been blocked by CORS policy)
@CrossOrigin(origins = "http://localhost:3000") //V1 = version 1 = standard for Rest API's
@RestController
@RequestMapping("/api/v1/") //Maps HTTP requests to handler methods of MVC and REST controllers.
public class BeerController {

    @Autowired //Inject Beer Repository by Spring Container
    private BeerRepository beerRepository;


    //API to get all Beers
    @GetMapping("/beers")  //getMapping is a shortcut for RequestMapping GET request
    public List<Beer> getAllBeers(){
        return beerRepository.findAll();
    }


    // Request Body = Indicating a method parameter should be bound to the body of the HTTP request
// Create Beer REST API
    @PostMapping("/beers")  // For mapping HTTP POST requests onto specific handler methods.
    public Beer createBeer(@RequestBody Beer beer) {
        return beerRepository.save(beer);
    }


    // Path Variable = Indicates that a method parameter should be bound to a URI template variable
//GET Beer by ID REST API:
    @GetMapping("/beers/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Integer id) {
        Beer beer = beerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Beer does not exist with ID:" + id));
        return ResponseEntity.ok(beer);

    }
    //Update Beer REST API
    @PutMapping("/beers/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Integer id, @RequestBody Beer beerDetails){
        Beer beer = beerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beer not exist with id :" + id));
        beer.setBeerName(beerDetails.getBeerName());
        beer.setDesc(beerDetails.getDesc());
        beer.setBeerABV(beerDetails.getBeerABV());
        beer.setBeerIBU(beerDetails.getBeerIBU());

        Beer updatedBeer = beerRepository.save(beer);
        return ResponseEntity.ok(updatedBeer);
    }

    // DELETE BEER REST API
    @DeleteMapping("/beers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBeer(@PathVariable Integer id){
        Beer beer = beerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beer not exist with id :" + id));
        beerRepository.delete(beer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}