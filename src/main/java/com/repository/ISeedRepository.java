package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Seed;

public interface ISeedRepository extends JpaRepository<Seed, Integer>{
	
	Optional<Seed> findByName(String name);
	List<Seed> findByTypeOfSeed(String typeOfSeeds);

}