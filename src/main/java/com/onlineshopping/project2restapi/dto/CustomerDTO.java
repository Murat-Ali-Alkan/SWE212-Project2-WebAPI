package com.onlineshopping.project2restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String address;
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
