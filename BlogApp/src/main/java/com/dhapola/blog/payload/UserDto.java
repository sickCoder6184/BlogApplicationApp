package com.dhapola.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

////Used to transfer the data into service class as DTOs helps in decoupling 
//the data exchanged between layers from the internal representation of data 
//within those layers so that we dont directly expose our entity
//We will use UserDto manipulate the data.

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be of minimum 4 character	")
	private String name;
	
	@Email(message = "Email Adress is not valid")
	
	private String email;
	
	@NotEmpty
	@Size(min = 4,max=20,message = "Password must be of minimum 4 character and maximum of 10 char")
	private String password;
	
	@NotEmpty
	private String about;
}
