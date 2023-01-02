package com.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Seed extends Product{
	
	
	@Enumerated(EnumType.STRING)
	private BloomTime bloomTime;
	private String watering;
	
	@Enumerated(EnumType.STRING)
	private Difficulty difficultyLevel;
	private String temperature;
	
	private String typeOfSeed;
	private String description;

	@NotNull
	@Positive(message = "A positive value of seeds per packet required")
	private Integer seedsPerPacket;
	

	public Seed(BloomTime bloomTime, String watering, Difficulty difficultyLevel, String temperature, String typeOfSeed,
			String description,
			@NotNull @Positive(message = "A positive value of seeds per packet required") Integer seedsPerPacket) {
		super();
		this.bloomTime = bloomTime;
		this.watering = watering;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfSeed = typeOfSeed;
		this.description = description;
		this.seedsPerPacket = seedsPerPacket;
	}


	public Seed(int id, @Positive(message = "The cost should be positive") int cost,
			@Positive(message = "The stock should be positive") int stock, Type type,
			@NotBlank(message = "Name is necessary") String name, BloomTime bloomTime, String watering,
			Difficulty difficultyLevel, String temperature, String typeOfSeed, String description,
			@NotNull @Positive(message = "A positive value of seeds per packet required") Integer seedsPerPacket) {
		super(id, cost, stock, type, name);
		this.bloomTime = bloomTime;
		this.watering = watering;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfSeed = typeOfSeed;
		this.description = description;
		this.seedsPerPacket = seedsPerPacket;
	}


	public Seed(int cost, int stock, Type type, BloomTime bloomTime, String watering, Difficulty difficultyLevel,
			String temperature, String typeOfSeed, String description,
			@NotNull @Positive(message = "A positive value of seeds per packet required") Integer seedsPerPacket) {
		super(cost, stock, type);
		this.bloomTime = bloomTime;
		this.watering = watering;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfSeed = typeOfSeed;
		this.description = description;
		this.seedsPerPacket = seedsPerPacket;
	}
	

	public Seed(int id, int cost, int stock, Type type, BloomTime bloomTime, String watering,
			Difficulty difficultyLevel, String temperature, String typeOfSeed, String description,
			@NotNull @Positive(message = "A positive value of seeds per packet required") Integer seedsPerPacket) {
		super(id, cost, stock, type);
		this.bloomTime = bloomTime;
		this.watering = watering;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfSeed = typeOfSeed;
		this.description = description;
		this.seedsPerPacket = seedsPerPacket;
	}

	public Seed() {
		super();
	}



	public BloomTime getBloomTime() {
		return bloomTime;
	}
	public void setBloomTime(BloomTime bloomTime) {
		this.bloomTime = bloomTime;
	}

	public String getWatering() {
		return watering;
	}
	public void setWatering(String watering) {
		this.watering = watering;
	}

	public Difficulty getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(Difficulty difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeedsPerPacket() {
		return seedsPerPacket;
	}
	public void setSeedsPerPacket(Integer seedsPerPacket) {
		this.seedsPerPacket = seedsPerPacket;
	}

	public String getTypeOfSeed() {
		return typeOfSeed;
	}

	public void setTypeOfSeed(String typeOfSeed) {
		this.typeOfSeed = typeOfSeed;
	}

	@Override
	public String toString() {
		return "Seed [bloomTime=" + bloomTime + ", watering=" + watering + ", difficultyLevel=" + difficultyLevel
				+ ", temperature=" + temperature + ", typeOfSeed=" + typeOfSeed + ", description=" + description
				+ ", seedsPerPacket=" + seedsPerPacket + "]";
	}



}