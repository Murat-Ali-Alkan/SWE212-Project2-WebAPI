package com.onlineshopping.project2restapi.updateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {


    @NotBlank(message = "Name cannot be blank or null")
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank or null")
    @Size(min = 3, max = 32, message = "Address must be between 3 and 32 characters")
    private String address;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+90-[0-9]{3}-[0-9]{3}-[0-9]{4}$", message = "Please enter a valid phone number in the format : +90-555-555-5555 ")
    private String phone;

}
