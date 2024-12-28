package com.aamir.dto;
// user ko response kya dikhna hai
//mujhe sab chij response me nhi dikhana hai
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	private Integer id;

	private String name;

	private String description;

}
