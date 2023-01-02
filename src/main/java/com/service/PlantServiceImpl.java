package com.service;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.entity.BloomTime;
import com.entity.Difficulty;
import com.entity.Plant;
import com.exceptions.PlantIdNotFoundException;
import com.repository.IPlantRepository;
@Transactional
@Service
public class PlantServiceImpl implements IPlantService{
	
	@Autowired
	private IPlantRepository plantRepo;
	
	
	@Override
	public Plant addNewPlant(Plant plant) {
		plantRepo.save(plant);	
		return plant;
	}
	
	@Override
	public Plant addPlantStock(String commonName, int stock){
			
		Optional<Plant> plantOptional = plantRepo.findByname(commonName);
		if(plantOptional.isPresent()) {
			Plant plant = plantOptional.get();
			int newStock = plant.getStock()+stock;
			plant.setStock(newStock);
			plantRepo.save(plant); 
			return plant;
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}

	@Override
	public Plant updatePlant(Plant plant, int id){
		Optional<Plant> plantOptional = plantRepo.findById(id);
		if(plantOptional.isPresent()) {
			Plant plantToBeUpdated = plantOptional.get();
			plantToBeUpdated.setBloomTime(plant.getBloomTime());
			plantToBeUpdated.setName(plant.getName());
			plantToBeUpdated.setCost(plant.getCost());
			plantToBeUpdated.setDescription(plant.getDescription());
			plantToBeUpdated.setDifficultyLevel(plant.getDifficultyLevel());
			plantToBeUpdated.setHeight(plant.getHeight());
			plantToBeUpdated.setMedicinalOrCulinaryUse(plant.getMedicinalOrCulinaryUse());
			plantToBeUpdated.setSpread(plant.getSpread());
			plantToBeUpdated.setStock(plant.getStock());
			plantToBeUpdated.setTemparature(plant.getTemparature());
			plantToBeUpdated.setTypeOfPlant(plant.getTypeOfPlant());
			plantRepo.save(plantToBeUpdated);
			return plantToBeUpdated;
			
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}
	
	@Override
	public Plant partialUpdatePlant(Map<Object, Object> fields, int id){
		
		Optional<Plant> plantOptional = plantRepo.findById(id);
		if(plantOptional.isPresent()) {
		
			Plant plant = plantRepo.findById(id).get();
			fields.forEach((k,v)->{
				Field field = ReflectionUtils.findRequiredField(Plant.class, (String)k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, plant, v);	
			});
			return plantRepo.save(plant);
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}

	@Override
	public Plant deletePlant(int plantId){
		
		Optional<Plant> plantOptional = plantRepo.findById(plantId);
		
		if(plantOptional.isPresent()) {
			Plant here = plantOptional.get();
			plantRepo.delete(here);
			return here;
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}
	@Override
	public Plant decreaseStock(int id, int stock) {
		Optional<Plant> plantOptional = plantRepo.findById(id);
		if(plantOptional.isPresent()) {
			Plant plant = plantOptional.get();
			int newStock = plant.getStock()-stock;
			if(newStock<0) {
				throw new PlantIdNotFoundException("Enough stock is not present.");
			}
			plant.setStock(newStock);
			plantRepo.save(plant); 
			return plant;
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}



	@Override
	public Plant getPlant(int plantId){
		Optional<Plant> plantOptional = plantRepo.findById(plantId);
		if(plantOptional.isPresent()) {
			
			return plantRepo.findById(plantId).get();
		}
		
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}


	@Override
	public Plant getPlant(String commonName){
		Optional<Plant> plantOptional = plantRepo.findByname(commonName);
		if(plantOptional.isPresent()) {
			Plant plant = plantOptional.get();
			return plant;
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}


	@Override
	public List<Plant> getAllPlants() {
		return plantRepo.findAll();
	}


	@Override
	public List<Plant> getAllPlants(String typeOfPlant) {
		if(plantRepo.findByTypeOfPlant(typeOfPlant).isEmpty()) {
			throw new PlantIdNotFoundException("No plants of this types are present");
		}
		return plantRepo.findByTypeOfPlant(typeOfPlant);
	
	}

	@Override
	public List<Plant> costLowToHigh() {
		List<Plant> plants = plantRepo.findAll();
		List<Plant> sortedPlants = plants.stream().sorted((Plant s1,Plant s2) -> (int)s1.getCost() - (int)s2.getCost()).collect(Collectors.toList());
		return sortedPlants;
	}

	@Override
	public List<Plant> costHighToLow() {
		List<Plant> plants = plantRepo.findAll();
		List<Plant> sortedPlants = plants.stream().sorted((Plant s1,Plant s2) -> (int)s2.getCost() - (int)s1.getCost()).collect(Collectors.toList());
		return sortedPlants;
	}

	@Override
	public List<Plant> filterPlantByBloomTime(BloomTime type) {
		List<Plant> plants = plantRepo.findAll();
		List<Plant> filteredPlants = plants.stream().filter((p) -> p.getBloomTime().equals(type)).collect(Collectors.toList());
		return filteredPlants;
	}

	@Override
	public List<Plant> filterPlantByDifficulty(Difficulty difficultyLevel) {
		List<Plant> plants = plantRepo.findAll();
		List<Plant> filteredPlants = plants.stream().filter((p) -> p.getDifficultyLevel().equals(difficultyLevel)).collect(Collectors.toList());
		return filteredPlants;
	}
	
	public String getBloomingStatus(int id) {
		LocalDate now = LocalDate.now();
		int thisYear = now.getYear();
		LocalDate SummerStart = LocalDate.of(thisYear, 3, 1);
		LocalDate SummerEnd = LocalDate.of(thisYear, 5, 31);
		LocalDate MonsoonStart = LocalDate.of(thisYear, 6, 1);
		LocalDate MonsoonEnd = LocalDate.of(thisYear, 9, 30);
		LocalDate AutumnStart = LocalDate.of(thisYear, 10, 1);
		LocalDate AutumnEnd = LocalDate.of(thisYear, 11, 30);
		LocalDate WinterStart = LocalDate.of(thisYear, 12, 1);
		LocalDate WinterEnd = LocalDate.of(thisYear, 2, 28);
		if(BloomTime.WINTER==plantRepo.findById(id).get().getBloomTime()) {
				if(now.isAfter(WinterStart) && now.isBefore(WinterEnd)) {
					return "Bloom Time is going on";
				}
				else {
					Period difference = Period.between(now,WinterStart);
					return "This plant will bloom after "+difference.getMonths()+" months and "
							+difference.getDays()+" days";
				}
		}else if(BloomTime.SUMMER==plantRepo.findById(id).get().getBloomTime()) {
			if(now.isAfter(SummerStart) && now.isBefore(SummerEnd)) {
				return "Bloom Time is going on";
			}
			else {
				Period difference = Period.between(now,SummerStart);
				return "This plant will bloom after "+difference.getMonths()+" months and "
				+difference.getDays()+" days";
			}
		}else if(BloomTime.MONSOON==plantRepo.findById(id).get().getBloomTime()) {
			if(now.isAfter(MonsoonStart) && now.isBefore(MonsoonEnd)) {
				return "Bloom Time is going on";
			}
			else {
				Period difference = Period.between(now,MonsoonStart);
				return "This plant will bloom after "+difference.getMonths()+" months and "
				+difference.getDays()+" days";

			}
		}
		else if(BloomTime.AUTUMN==plantRepo.findById(id).get().getBloomTime()) {
			if(now.isAfter(AutumnStart) && now.isBefore(AutumnEnd)) {
				return "Bloom Time is going on";
			}
			else {
				Period difference = Period.between(now,AutumnStart);
				return "This plant will bloom after "+difference.getMonths()+" months and "
				+difference.getDays()+" days";
			}

		}
		return "something Went Wrong";
	}
}