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
import com.entity.Seed;
import com.service.ISeedService;


@RestController
@RequestMapping("/products")
public class SeedController{
	
	@Autowired
	private ISeedService seedService;
	
	@PostMapping("/admin/seed")
	public ResponseEntity<Seed> addSeed(@Valid @RequestBody Seed seed){
		seedService.addSeed(seed);
		return new ResponseEntity<Seed>(seed,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/seeds")
	public ResponseEntity<Seed> updateSeed(@RequestBody Seed seed) {
		seedService.updateSeed(seed);
		return new ResponseEntity<Seed>(seed,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/seeds/{commonName}/{stock}")
	public ResponseEntity<Seed> addStock(@PathVariable String commonName,@PathVariable int stock){
		return new ResponseEntity<Seed>(seedService.addStock(commonName, stock),HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/admin/seeds/id/{id}")
	public ResponseEntity<Seed> updateSeed(@PathVariable int id, @RequestBody Map<Object,Object> fields){
		return new ResponseEntity<Seed>(seedService.updateSeed(id,fields),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/seeds")
	public ResponseEntity<Seed> deleteSeed(@RequestBody Seed seed){
		return new ResponseEntity<Seed> (seedService.deleteSeed(seed),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/seeds/name/{commonName}/{stock}")
	public ResponseEntity<Seed> buySeeds(@PathVariable String commonName,@PathVariable int stock){
		return new ResponseEntity<Seed>(seedService.buySeeds(commonName, stock),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/seeds/id/{id}/{stock}")
	public ResponseEntity<Seed> buySeeds(@PathVariable int id,@PathVariable int stock){
		return new ResponseEntity<Seed>(seedService.buySeeds(id, stock),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/seeds/id/{id}")
	public ResponseEntity<Seed> deleteSeedById(@PathVariable int id){
		return new ResponseEntity<Seed>(seedService.deleteSeedById(id),HttpStatus.OK);
	}
		
	@GetMapping({"/admin/seeds/id/{id}","/customers/seeds/id/{id}"})
	public ResponseEntity<Seed> getSeed(@PathVariable int id){
		return new ResponseEntity<Seed>(seedService.getSeed(id),HttpStatus.OK);	
	}
	
	@GetMapping({"/admin/seeds/commonname/{commonName}","/customers/seeds/commonname/{commonName}"})
	public ResponseEntity<Seed> getSeed(@PathVariable String commonName) {
		return new ResponseEntity<Seed>(seedService.getSeed(commonName),HttpStatus.ACCEPTED);	
	}
	
	@GetMapping({"/admin/seeds","/customers/seeds"})
	public ResponseEntity<List<Seed>> getSeeds(){
		List<Seed> seedList = seedService.getSeeds();
		return new ResponseEntity<List<Seed>>(seedList,HttpStatus.ACCEPTED);
	}
	
	@GetMapping({"/admin/seeds/typeOfSeed/{typeOfSeed}","/customer/seeds/typeOfSeed/{typeOfSeed}"})
	public ResponseEntity<List<Seed>> getSeeds(@PathVariable String typeOfSeed){
		List<Seed> seedList = seedService.getSeeds(typeOfSeed);
		return new ResponseEntity<List<Seed>>(seedList,HttpStatus.ACCEPTED);
	}
	
	@GetMapping({"/admin/seeds/costLowToHigh","/customers/seeds/costLowToHigh"})
	public ResponseEntity<List<Seed>> costLowToHigh(){
		List<Seed> seeds = seedService.costLowToHigh();
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
		
	@GetMapping({"/admin/seeds/costHighToLow","/customers/seeds/costHighToLow"})
	public ResponseEntity<List<Seed>> costHighToLow(){
		List<Seed> seeds = seedService.costHighToLow();
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
	
	@GetMapping({"/admin/seeds/filterbybloomtime/{bloomTime}","/customer/seeds/filterbybloomtime/{bloomTime}"})
	public ResponseEntity<List<Seed>> filterByBloomTime(@PathVariable BloomTime bloomTime){
		List<Seed> seeds = seedService.filterSeedByBloomTime(bloomTime);
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
	
	@GetMapping({"/admin/seeds/filterbydifficulty/{difficulty}","/customer/seeds/filterbydifficulty/{difficulty}"})
	public ResponseEntity<List<Seed>> filterByDifficulty(@PathVariable Difficulty difficulty){
		List<Seed> seeds = seedService.filterSeedByDifficulty(difficulty);
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
}