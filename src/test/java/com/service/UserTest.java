package com.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

import com.entity.Address;
import com.entity.Admin;
import com.entity.Customer;
import com.entity.Role;
import com.entity.User;
import com.exceptions.UserNotFoundException;
import com.repository.IUserRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserTest {

	@Mock
	IUserRepository userRepositoryMock;

	@InjectMocks
	UserServiceImpl userServiceMock;

	User c1, c2;
	User a1, a2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Customer(1, "pavan@gmail.com", "Pavan@123", Role.CUSTOMER, "Pavan Kumar", "9182183522",
				new Address("2-31", "Sai nagar", "Ongole", "Andhra Pradesh", 523279));

		c2 = new Customer(2, "rahul@gmail.com", "rahul@123", Role.CUSTOMER, "Rahul sai", "9666635326",
				new Address("3-21", "sarathnagar", "kondepi", "Andhra Pradesh", 523276));

		a1 = new Admin("Sravan@gmail", "saran@123", Role.ADMIN, "Sravan Kumar", "987654321");

		a2 = new Admin("ramesh@gmail", "ramesh@123", Role.ADMIN, "Suresh", "8927654262");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void customerRegister() {

		when(userRepositoryMock.save(c1)).thenReturn(c1);
		assertEquals("Pavan Kumar", ((Customer) userServiceMock.register(c1)).getName());

	}

	@Test
	public void adminRegister() {

		when(userRepositoryMock.save(a2)).thenReturn(a2);
		assertEquals("Suresh", ((Admin) userServiceMock.register(a2)).getName());

	}

	@Test
	public void updateCustomerTest() {
		c1 = new Customer(1, "pavankumarkantu@gmail.com", "Pavan@123", Role.CUSTOMER, "Pavan Kumar", "9182183522",
				new Address("2-31", "Sai nagar", "Ongole", "Andhra Pradesh", 523279));

		when(userRepositoryMock.findById(1)).thenReturn(Optional.of(c1));
		when(userRepositoryMock.save(c1)).thenReturn(c1);

		assertEquals("pavankumarkantu@gmail.com", userServiceMock.updateUser(1, c1).getEmail());

	}

	@Test
	public void validationTest() {

		when(userRepositoryMock.findByEmail("pavan@gmail.com")).thenReturn(Optional.of(c1));
		assertEquals("Pavan@123", userServiceMock.login(c1).getPassword());

		Exception exception = assertThrows(UserNotFoundException.class, () -> userServiceMock.login(c2));
		assertTrue(exception.getMessage().contains("Bad Credentials"));

	}

	@Test
	public void resetPasswordTest() {
		c2 = new Customer(2, "rahul@gmail.com", "oracle123", Role.CUSTOMER, "Rahul sai", "9666635326",
				new Address("3-21", "sarathnagar", "kondepi", "Andhra Pradesh", 523276));

		Map<Object, Object> map = new HashMap<Object, Object>();

		map.put("email", "rahul@gmail.com");
		map.put("password", "oracle123");

		when(userRepositoryMock.findById(2)).thenReturn(Optional.of(c2));
		when(userRepositoryMock.save(c2)).thenReturn(c2);

		assertEquals("oracle123", userServiceMock.resetPasswordById(2, map).getPassword());

	}

	@Test
	public void userByRole() {

		List<User> l = new ArrayList<>();
		l.add(a1);
		l.add(a2);

		when(userRepositoryMock.findAll()).thenReturn(l);
		assertEquals(2, userServiceMock.userByRole(Role.ADMIN).size());

	}

}