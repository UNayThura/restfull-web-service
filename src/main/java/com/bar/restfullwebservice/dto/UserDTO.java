package com.bar.restfullwebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Htay Hlaing Aung on 9/22/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    private Long id;
    @Size(min = 4, message = "Name must be at least 4 characters.")
    private String name;
    @NotNull(message = "Date cannot be null.")
    @Past(message = "Date of birth Must be a past date.")
    private Date dateOfBirth;
}
