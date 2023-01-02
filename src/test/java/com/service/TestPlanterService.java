package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.entity.Planter;
import com.repository.IPlanterRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestPlanterService {
	
	@Mock
	IPlanterRepository dao;
	
	@InjectMocks
	PlanterServiceImpl service;
	
	List<Planter> planterList;
	Planter planter1;
	Planter planter2;
	Planter planter3;
	Planter planter4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		planterList = new ArrayList<>();
		planter1 = new Planter(1,30.0f,"Rectangle",19,450);
		planter2 = new Planter(2,28.0f,"Square",17,550);
		planter3 = new Planter(3,33.0f,"Rectangle",19,450);
		planter4 = new Planter(1,28.0f,"Circle",10,700);
		planterList.add(planter1);
		planterList.add(planter2);
		planterList.add(planter3);
		planterList.add(planter4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void viewAllPlantersTest() {
		when(dao.findAll()).thenReturn(planterList);
		assertEquals(4, service.getPlanters().size());
	}
	
	@Test
	public void addPlanterTest() {
		when(dao.save(planter1)).thenReturn(planter1);
		assertEquals(planter1, service.addPlanter(planter1));
	}
	
	@Test
	public void deletePlanter() {
		when(dao.findById(planter1.getId())).thenReturn(Optional.of(planter1));
		assertEquals(planter1.getStock()-1, service.deletePlanter(planter1).getStock());
	}
	
	@Test
	public void viewPlanterTest() {
		when(dao.findById(planter1.getId())).thenReturn(Optional.of(planter1));
		assertEquals(planter1, service.getPlanter(planter1.getId()));
	}
	
	@Test
	public void updatePlanterTest() {
		when(dao.findById(planter1.getId())).thenReturn(Optional.of(planter4));
		assertEquals(planter4, service.updatePlanter(planter1));;
	}
	
	
	@Test
	public void costHighToLowTest() {
		List<Planter> sortedList = new ArrayList<>();
		sortedList.add(planter1);
		sortedList.add(planter3);
		sortedList.add(planter2);
		sortedList.add(planter4);
		
		when(dao.findAll()).thenReturn(sortedList);
		assertEquals(sortedList.get(0), service.costHighToLow().get(0));
	}
	
	@Test
	public void viewPlantersByHeightTest() {
		List<Planter> newList = new ArrayList<>();
		newList.add(planter2);
		newList.add(planter4);
		
		when(dao.findAll()).thenReturn(newList);
		assertEquals(newList, service.filterPlantersByHeight(28.0f));
	}
	
	

}
