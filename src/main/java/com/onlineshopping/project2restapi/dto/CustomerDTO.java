package com.onlineshopping.project2restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "Name cannot be blank or null")
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank or null")
    @Size(min = 3, max = 32, message = "Address must be between 3 and 32 characters")
    private String address;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+90-[0-9]{3}-[0-9]{3}-[0-9]{4}$", message = "Please enter a valid phone number in the format : +90-555-555-5555 ")
    private String phone;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomerDTO customerDTO = (CustomerDTO) obj;
        return Objects.equals(id,customerDTO.getId()) && Objects.equals(name, customerDTO.getName())
                && Objects.equals(address, customerDTO.getAddress()) && Objects.equals(phone, customerDTO.getPhone());
    }
}
