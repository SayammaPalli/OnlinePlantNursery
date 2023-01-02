package com.service;
//import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.entity.BloomTime;
import com.entity.Difficulty;
import com.entity.Plant;
import com.entity.Type;
import com.exceptions.PlantIdNotFoundException;
import com.repository.IPlantRepository;





@TestMethodOrder(OrderAnnotation.class) 
@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
class TestPlantService {

	@Mock
	IPlantRepository plantRepoMock;
	
	@InjectMocks
	PlantServiceImpl plantServiceMock;
	
	static Plant plant1;
	static Plant plant2;
	static Plant plant3;
	static List<Plant> plants = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		plant1 = new Plant(11,5,5,Type.PLANT,5,"fast",BloomTime.WINTER,"for beauty", Difficulty.EASY,"warm","herb","Jasmine","flowering-plant");
		plant2 = new Plant(12,5,3,Type.PLANT,23,"fast",BloomTime.SUMMER,"for beauty", Difficulty.HARD,"warm","herb","Mango","flowering-plant");
		plant3 = new Plant(13,5,2,Type.PLANT,3,"fast",BloomTime.AUTUMN,"for beauty", Difficulty.HARD,"warm","shrub","Banana","flowering-plant");

		plants.add(plant1);
		plants.add(plant2);
		plants.add(plant3);
	}
	
	@BeforeEach
	void setUp() throws Exception {

	}
	@Order(1)
	//@Disabled
	@Test
	public void addNewPlantTest() {
		when(plantRepoMock.save(plant1)).thenReturn(plant1);
//		assertEquals("Jasmine",plantServiceMock.addNewPlant(plant1).getCommonName());	
	}
	@Order(2)
	//@Disabled
	@Test
	public void addPlantStockTest(){
		
//		when(plantRepoMock.findByCommonName("Mango")).thenReturn(Optional.of(plant2));
		assertEquals(5,plantServiceMock.addPlantStock("Mango", 2).getStock());
		
		//trying to add stock to a plant which does not exists
		//assertThrows(PlantIdNotFoundException.class,()-> plantServiceMock.addPlantStock("jaj", 2)); 
	}
	@Order(3)
	//@Disabled
	@Test
	public void decreasePlantStockTest(){
		
		when(plantRepoMock.findById(11)).thenReturn(Optional.of(plant1));
		assertEquals(3,plantServiceMock.decreaseStock(11, 2).getStock());
		
		//trying to add stock to a plant which does not exists
		//assertThrows(PlantIdNotFoundException.class,()-> plantServiceMock.decreaseStock(909, 2)); 
	}
	@Order(4)
	//@Disabled
	@Test
	public void updatePlantTest() {
		when(plantRepoMock.findById(11)).thenReturn(Optional.of(plant1));
		Plant plant1Update = new Plant(11,5,5,Type.PLANT,10,"fast",BloomTime.WINTER,"for beauty", Difficulty.EASY,"warm","herb","Jasmine","flowering-plant");
		//updating height 5 to 10
//		assertEquals("10", plantServiceMock.updatePlant(plant1Update, 11).getHeight().toString());
		
	}
	@Order(5)
	//@Disabled
	@Test
	public void deletePlantTest() {
		when(plantRepoMock.findById(11)).thenReturn(Optional.of(plant1));
		
		
//		assertEquals("Jasmine",plantServiceMock.deletePlant(11).getCommonName());
	}
	@Order(6)
	//@Disabled
	@Test
	public void getPlantwithIdTest() {
		
		when(plantRepoMock.findById(11)).thenReturn(Optional.of(plant1));
		
		assertEquals(plant1,plantServiceMock.getPlant(11));
		//Exception exception = assertThrows(PlantIdNotFoundException.class,()->plantServiceMock.getPlant(1));
		//assertTrue(exception.getMessage().contains("Plant Not Found"));
	}
	@Order(7)
	//@Disabled
	@Test
	public void getPlantwithCommonNameTest() {
		
//		when(plantRepoMock.findByCommonName("Jasmine")).thenReturn(Optional.of(plant1));
		
		assertEquals(plant1,plantServiceMock.getPlant("Jasmine"));
		//Exception exception = assertThrows(PlantIdNotFoundException.class,()->plantServiceMock.getPlant(1));
		//assertTrue(exception.getMessage().contains("Plant Not Found"));
	}
	
	@Order(8)
	//@Disabled
	@Test
	public void getAllPlants() {
		when(plantRepoMock.findAll()).thenReturn(plants);		
		assertEquals(3,plantServiceMock.getAllPlants().size());
	}
	
	@Order(9)
	//@Disabled
	@Test
	public void getAllPlantsByType() {
		List<Plant> list = new ArrayList<>();
		list.add(plant1);
		list.add(plant2);
		when(plantRepoMock.findByTypeOfPlant("herb")).thenReturn(list);		
		assertEquals(2,plantServiceMock.getAllPlants("herb").size());
	}
}