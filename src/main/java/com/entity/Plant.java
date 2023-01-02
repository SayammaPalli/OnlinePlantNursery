package com.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Plant extends Product{
	
	@ApiModelProperty(notes = "Provide in inches")							
	private float height;
	private String spread;
	
	@Enumerated(EnumType.STRING)
	private BloomTime bloomTime;

	private String medicinalOrCulinaryUse;
	@Enumerated(EnumType.STRING)
	private Difficulty difficultyLevel;
	
	private String temperature;
	private String typeOfPlant;
	private String description;
	
	public Plant() {
	}



	public Plant(float height, String spread, BloomTime bloomTime, String medicinalOrCulinaryUse,
			Difficulty difficultyLevel, String temperature, String typeOfPlant, String description) {
		super();
		this.height = height;
		this.spread = spread;
		this.bloomTime = bloomTime;
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfPlant = typeOfPlant;
		this.description = description;
	}



	public Plant(int cost, int stock, Type type, float height, String spread, BloomTime bloomTime,
			String medicinalOrCulinaryUse, Difficulty difficultyLevel, String temperature, String typeOfPlant,
			@NotBlank(message = "Name is necessary") String name, String description) {
		super(cost, stock, type,name);
		this.height = height;
		this.spread = spread;
		this.bloomTime = bloomTime;
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfPlant = typeOfPlant;
//		this.name = name;
		this.description = description;
	}
	
	
	public Plant(int id, int cost, int stock, Type type, float height, String spread, BloomTime bloomTime,
			String medicinalOrCulinaryUse, Difficulty difficultyLevel, String temperature, String typeOfPlant,
			@NotBlank(message = "Name is necessary") String name, String description) {
		super(id, cost, stock, type,name);
		this.height = height;
		this.spread = spread;
		this.bloomTime = bloomTime;
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfPlant = typeOfPlant;
		this.description = description;
	}
	
	public Plant(int cost, int stock, float height, String spread, BloomTime bloomTime, String medicinalOrCulinaryUse,
			Difficulty difficultyLevel, String temperature, String typeOfPlant,
			@NotBlank(message = "Name is necessary") String name, String description) {
		super(cost, stock,name);
		this.height = height;
		this.spread = spread;
		this.bloomTime = bloomTime;
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
		this.difficultyLevel = difficultyLevel;
		this.temperature = temperature;
		this.typeOfPlant = typeOfPlant;	
		this.description = description;
	}

	public Difficulty getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Difficulty difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}


	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public BloomTime getBloomTime() {
		return bloomTime;
	}

	public void setBloomTime(BloomTime bloomTime) {
		this.bloomTime = bloomTime;
	}

	public String getMedicinalOrCulinaryUse() {
		return medicinalOrCulinaryUse;
	}


	public void setMedicinalOrCulinaryUse(String medicinalOrCulinaryUse) {
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
	}


	public String getTemparature() {
		return temperature;
	}

	public void setTemparature(String temperature) {
		this.temperature = temperature;
	}

	public String getTypeOfPlant() {
		return typeOfPlant;
	}

	public void setTypeOfPlant(String typeOfPlant) {
		this.typeOfPlant = typeOfPlant;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Plant [height=" + height + ", spread=" + spread + ", bloomTime=" + bloomTime
				+ ", medicinalOrCulinaryUse=" + medicinalOrCulinaryUse + ", difficultyLevel=" + difficultyLevel
				+ ", temperature=" + temperature + ", typeOfPlant=" + typeOfPlant + ", description=" + description
				+ "]";
	}
	
	



}