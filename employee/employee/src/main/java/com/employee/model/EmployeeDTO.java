package com.employee.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Schema(description = "Employee Data Transfer Object")
public class EmployeeDTO {
	
	private long id;
	
	 @Schema(
		        description = "First name of the employee",
		        example = "John",
		        required = true
		    )
		    @NotBlank(message = "First name is required")
	private String firstName;
	
	 @Schema(
		        description = "Last name of the employee",
		        example = "Doe",
		        required = true
		    )
		    @NotBlank(message = "Last name is required")
	private String lastName;
	
	 @Schema(
		        description = "Email address of the employee",
		        example = "john.doe@example.com",
		        required = true
		    )
		    @NotBlank(message = "Email is required")
		    @Email(message = "Email should be valid")
	private String emailId;

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDTO(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	

	public EmployeeDTO(long id, String firstName, String lastName, String emailId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
