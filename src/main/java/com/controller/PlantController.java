package com.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BloomTime;
import com.entity.Difficulty;
import com.entity.Plant;
import com.exceptions.PlantIdNotFoundException;
import com.service.IPlantService;


@RestController
@RequestMapping("/products")
public class PlantController {	

	@Autowired
	private IPlantService plantService;

	@PostMapping("/admin/plant")
	public ResponseEntity<Plant> addNewPlant(@Valid @RequestBody Plant plant) {
		plantService.addNewPlant(plant);
		return new ResponseEntity<Plant>(plant, HttpStatus.CREATED);
	}
	
	@PutMapping({"/admin/plant/id/{id}"})
	public ResponseEntity<Plant> updatePlant(@Valid @RequestBody Plant plant, @PathVariable int id) {
		// logger.trace("updating the whole plant having id "+ id);  
		plantService.updatePlant(plant, id);
		return new ResponseEntity<Plant>(plant, HttpStatus.ACCEPTED);
	}

	@PutMapping("/admin/plant/{commonName}/{stock}")
	public ResponseEntity<Plant> addPlantStock(@PathVariable String commonName, @PathVariable int stock){
		//logger.trace("adding "+stock+" plants to the stock");
		return new ResponseEntity<Plant>(plantService.addPlantStock(commonName,stock), HttpStatus.ACCEPTED);
	}
	

	@PatchMapping("/admin/plant/id/{id}")
	public ResponseEntity<Plant> partialUpdate(@RequestBody Map<Object, Object> fields, @PathVariable("id") int id) {
	 // logger.trace("updating partially using partialUpadate");    
	  return new ResponseEntity<Plant>(plantService.partialUpdatePlant(fields, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/plant/id/{id}")
	public ResponseEntity<Plant> deletePlant(@PathVariable int id) throws PlantIdNotFoundException{
		//logger.trace("deleting the whole plant");	
		return new ResponseEntity<Plant>(plantService.deletePlant(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/plant/id/{id}/{stock}")       
	public ResponseEntity<Plant> decreaseStock(@PathVariable int id, @PathVariable int stock){
		//logger.trace("decreasing stock of plant "+commonName+" by quantity "+ stock);
		return new ResponseEntity<Plant>(plantService.decreaseStock(id,stock), HttpStatus.OK);
	}

	@GetMapping({"/admin/plant/id/{id}", "/customers/plant/id/{id}"})
	public ResponseEntity<Plant> viewPlant(@PathVariable Integer id) {
		//logger.trace("fetching the plant with id "+ id);
		return new ResponseEntity<Plant>(plantService.getPlant(id), HttpStatus.OK);
	}

	@GetMapping({"/admin/plant/commonName/{commonName}", "/customers/plant/commonName/{commonName}"})
	public ResponseEntity<Plant> viewPlant(@PathVariable String commonName){
	//	logger.trace("fetching "+commonName+" plant");
		return new ResponseEntity<Plant>(plantService.getPlant(commonName), HttpStatus.OK);
	}

	@GetMapping({"/admin/plants","/customers/plants"})
	public ResponseEntity<List<Plant>> viewAllPlants() {
		//logger.trace("fetching all the plants");
		return new ResponseEntity<List<Plant>>(plantService.getAllPlants(), HttpStatus.OK);
	}

	@GetMapping({"/admin/plants/typeOfPlant/{typeOfPlant}", "/customers/plants/typeOfPlant/{typeOfPlant}"})
	public ResponseEntity<List<Plant>> viewAllPlants(@PathVariable String typeOfPlant) {
		//logger.trace("searching the plants of type "+typeOfPlant);
		return new ResponseEntity<List<Plant>>(plantService.getAllPlants(typeOfPlant), HttpStatus.OK);
	}
	
	@GetMapping({"/admin/plants/costLowToHigh", "/customers/plants/costLowToHigh"})
	public ResponseEntity<List<Plant>> costLowToHigh(){
		return new ResponseEntity<List<Plant>>(plantService.costLowToHigh(),HttpStatus.OK);
	}
		
	@GetMapping({"/admin/plants/costHighToLow", "/customers/plants/costHighToLow"})
	public ResponseEntity<List<Plant>> costHighToLow(){
		return new ResponseEntity<List<Plant>>(plantService.costHighToLow(),HttpStatus.OK);
	}
	
	@GetMapping({"/admin/plants/filterByBloomTime/{bloomTime}", "/customers/plants/filterByBloomTime/{bloomTime}"})
	public ResponseEntity<List<Plant>> filterByType(@PathVariable BloomTime bloomTime){
		return new ResponseEntity<List<Plant>>(plantService.filterPlantByBloomTime(bloomTime),HttpStatus.OK);
	}
	
	@GetMapping({"/admin/plants/filterByDifficulty/{difficulty}","/customers/plants/filterByDifficulty/{difficulty}"})
	public ResponseEntity<List<Plant>> filterByDifficulty(@PathVariable Difficulty difficulty){
		return new ResponseEntity<List<Plant>>(plantService.filterPlantByDifficulty(difficulty),HttpStatus.OK);
	}
	
	@GetMapping({"/admin/plants/viewBloomStatus/id/{id}","/customers/plants/viewBloomStatus/id/{id}"})
	public String viewBloomingStatus(@PathVariable int id) {
		return plantService.getBloomingStatus(id);
	}

}