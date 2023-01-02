package com.service;

import java.lang.reflect.Field;
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
import com.entity.Seed;
import com.entity.Type;
import com.exceptions.OutOfStockException;
import com.exceptions.SeedIdNotFoundException;
import com.repository.ISeedRepository;

@Service
@Transactional
public class SeedServiceImpl implements ISeedService{
	
	@Autowired
	private ISeedRepository seedRepo;

	@Override
	public Seed addSeed(Seed seed) {
		return seedRepo.save(seed);
	}
	
	@Override
	public Seed addStock(String name, int stock) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findByName(name);
		if(seedOptional.isPresent()) {
			Seed seedNew = seedOptional.get();
			int newStock = seedNew.getStock() + stock;
			seedNew.setStock(newStock);
			seedRepo.save(seedNew);
			return seedNew;
		}
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Invalid Common name ... cannot update stock"));
	}

	@Override
	public Seed updateSeed(Seed seed) throws SeedIdNotFoundException{
		Optional<Seed> seedOptional = seedRepo.findById(seed.getId());
		if(seedOptional.isPresent()) {
			return seedRepo.save(seed);
		}
		return seedOptional.orElseThrow(()->new SeedIdNotFoundException("Invalid seed id...Cannot update"));	
	}

	@Override
	public Seed deleteSeed(Seed seed) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findById(seed.getId());		
		if(seedOptional.isPresent()) {
			seedRepo.delete(seed);
		}
		return seedOptional.orElseThrow(()->new SeedIdNotFoundException("Invalid seed id...Cannot delete"));	
	}
	
	@Override
	public Seed deleteSeedById(int id) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findById(id);		
		if(seedOptional.isPresent()) {
			seedRepo.delete(seedOptional.get());
		}
		return seedOptional.orElseThrow(()->new SeedIdNotFoundException("Invalid seed id...Cannot delete"));	
	}
	
	public Seed buySeeds(String commonName, int stock) throws SeedIdNotFoundException, OutOfStockException {
		Optional<Seed> seedOptional = seedRepo.findByName(commonName);
		if(seedOptional.isPresent()) {
			Seed seedNew = seedOptional.get();
			int newStock = seedNew.getStock() - stock;
			if(newStock<0) {
				throw new OutOfStockException("Not enough stock");
			}
			else {
				seedNew.setStock(newStock);
				seedRepo.save(seedNew);
				return seedNew;
			}
		}
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Invalid Common name ... Please select other seed"));
	}
	
	@Override
	public Seed buySeeds(int id, int stock) {
		Optional<Seed> seedOptional = seedRepo.findById(id);
		if(seedOptional.isPresent()) {
			Seed seedNew = seedOptional.get();
			int newStock = seedNew.getStock() - stock;
			if(newStock<0) {
				throw new OutOfStockException("Not enough stock");
			}
			else {
				seedNew.setStock(newStock);
				seedRepo.save(seedNew);
				return seedNew;
			}
		}
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Invalid ID ... please select other seed"));
	}
	
	@Override
	public Seed getSeed(int id) throws SeedIdNotFoundException{
		Optional<Seed> seedOptional = seedRepo.findById(id);
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found...Invalid ID"));
	}

	@Override
	public Seed getSeed(String commonName) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findByName(commonName);
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found...Invalid Name"));
	}

	@Override
	public List<Seed> getSeeds() {
		return seedRepo.findAll();
	}

	@Override
	public List<Seed> getSeeds(String type) {
		return seedRepo.findByTypeOfSeed(type);
	}
	
	//SORT BY
	
	@Override
	public List<Seed> costLowToHigh() {
		List<Seed> seeds = seedRepo.findAll();
		List<Seed> sortedSeeds = seeds.stream().sorted((Seed s1,Seed s2) -> (int)s1.getCost() - (int)s2.getCost()).collect(Collectors.toList());
		return sortedSeeds;
	}
	
	@Override
	public List<Seed> costHighToLow() {
		List<Seed> seeds = seedRepo.findAll();
		List<Seed> sortedSeeds = seeds.stream().sorted((Seed s1,Seed s2) -> (int)s2.getCost() - (int)s1.getCost()).collect(Collectors.toList());
		return sortedSeeds;
	}
	
	//Filter
	
	@Override
	public List<Seed> filterSeedByType(Type type) {
		List<Seed> seeds = seedRepo.findAll();
		List<Seed> filteredSeeds = seeds.stream().filter((p) -> p.getType().equals(type)).collect(Collectors.toList());
		return filteredSeeds;
			
	}
	
	@Override
	public List<Seed> filterSeedByDifficulty(Difficulty difficultyLevel) {
		List<Seed> seeds = seedRepo.findAll();
		List<Seed> filteredSeeds = seeds.stream().filter((p) -> p.getDifficultyLevel().equals(difficultyLevel)).collect(Collectors.toList());
		return filteredSeeds;
			
	}

	@Override
	public Seed updateSeed(int id, Map<Object, Object> fields) {
		Optional<Seed> seedOptional = seedRepo.findById(id);
		if(seedOptional.isPresent()) {
		
			Seed seed = seedRepo.findById(id).get();
			fields.forEach((k,v)->{
				Field field = ReflectionUtils.findRequiredField(Seed.class, (String)k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, seed, v);	
			});
			return seedRepo.save(seed);
		}
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Plant Not Found"));

}
	
	@Override
	public List<Seed> filterSeedByBloomTime(BloomTime type) {
		List<Seed> seeds = seedRepo.findAll();
		List<Seed> filteredSeeds = seeds.stream().filter((p) -> p.getBloomTime() == type).collect(Collectors.toList());
		return filteredSeeds;
	}
}