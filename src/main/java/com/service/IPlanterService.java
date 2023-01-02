package com.service;

import java.util.List;
import java.util.Map;
import com.entity.Planter;

public interface IPlanterService {
	
	//BASIC CRUD
	Planter addPlanter(Planter planter);
	Planter updatePlanter(Planter planter);
	
//	Planter deletePlanter(int planterId);
	Planter deletePlanter(Planter planter);
	Planter getPlanter(int planterId);
	List<Planter> getPlanters();
	Planter deletePlanterById(int id);
	
	Planter partialUpdatePlanter(Map<Object, Object> fields, int id);
	List<Planter> getPlanters(double minCost, double maxCost);
	Planter removePlanterStock(Planter planter, int quantity);
	Planter addPlanterStock(int id, int quantity);
	List<Planter> deletePlanters();

	//SORTBY
	List<Planter> costLowToHigh();
	List<Planter> costHighToLow();

	//FILTER
	List<Planter> filterPlantersByColor(String color);
	List<Planter> filterPlantersByHeight(float height);
	List<Planter> filterPlantersByShape(String planterShape);
	List<Planter> filterPlantersByCapacity(int capacity);
	List<Planter> filterPlantersByDrainageHoles(int drainageHoles);
		
}
