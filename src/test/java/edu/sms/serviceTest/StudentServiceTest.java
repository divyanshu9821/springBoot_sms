package edu.sms.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.sms.dto.StudentDTO;
import edu.sms.entity.Address;
import edu.sms.entity.Student;
import edu.sms.repository.StudentRepository;
import edu.sms.service.StudentService;
import edu.sms.util.StudentConverter;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	StudentService stdService;
	
	@MockBean
	StudentRepository stdRepository;
	
	@Autowired
	StudentConverter stdConverter;
	
	@Test
	void testSaveStudent()
	{
	 	Address add = Address.builder().location("Solan").state("HimachalPradesh").
	 			 pincode("173211").build();	
		Student std = Student.builder().name("Aniket").email("aniket@gmail.com").gender("Male").
						contact("9865478962").dateOfBirth(LocalDate.of(2002, 12, 31)).
						dateOfJoining(LocalDate.now()).address(add).build();
		
		Mockito.when(stdRepository.save(std)).thenReturn(std);
		
		//assertNotNull(stdService.saveStudent(std));
		
		StudentDTO stDto = stdConverter.convertStdEntityToDto(std);
		
		assertTrue(stdService.saveStudent(std).getName().equals("Aniket"));
		//assertEquals(stDto.getName(), stdService.saveStudent(std).getName());
	}
	
	@Test
	void testGetStudentById()
	{
		Address add = Address.builder().location("Solan").state("HimachalPradesh").
	 			 pincode("173211").build();	
		Student std = Student.builder().name("Aniket").email("aniket@gmail.com").gender("Male").
						contact("9865478962").dateOfBirth(LocalDate.of(2002, 12, 31)).
						dateOfJoining(LocalDate.now()).address(add).build();
		
		//Optional<Student> opStd = Optional.of(std);
		Mockito.when(stdRepository.findById(std.getId())).
		thenReturn(Optional.of(std));
		
		//assertNotNull(stdService.getStudentById(std.getId()));
		assertThat(stdService.getStudentById(std.getId()).getEmail())
		.isEqualTo("aniket@gmail.com");
		
	}
	
	
	@Test
	void testGetStdUsingEmail()
	{
		Address add = Address.builder().location("Hapur").state("Uttar Pradesh").
	 			 pincode("245101").build();	
		Student std = Student.builder().name("Divyanshu").email("divyanshu@gmail.com").gender("Male").
						contact("9865478962").dateOfBirth(LocalDate.of(2002, 12, 31)).
						dateOfJoining(LocalDate.now()).address(add).build();
		
		Mockito.when(stdRepository.findByEmail(std.getEmail()))
		.thenReturn(Optional.of(std));
		
		assertEquals(std.getId(), stdService.getStudentByEmail(std.getEmail()).getId());
		
	}
	
	
	
	@Test
	@DisplayName("Negative Test Case")
	void testNegativeGetStudentById()
	{
		Address add = Address.builder().location("Solan").state("HimachalPradesh").
	 			 pincode("173211").build();	
		Student std = Student.builder().name("Aniket").email("aniket@gmail.com").gender("Male").
						contact("9865478962").dateOfBirth(LocalDate.of(2002, 12, 31)).
						dateOfJoining(LocalDate.now()).address(add).build();
		
		//Optional<Student> opStd = Optional.of(std);
		Mockito.when(stdRepository.findById(std.getId())).
		thenReturn(Optional.of(std));
		
		//assertNotNull(stdService.getStudentById(std.getId()));
		assertThat(stdService.getStudentById(std.getId()).getEmail())
		.isEqualTo("aniket123@gmail.com");
		
	}
	
	
	
	
	
	
	

}
