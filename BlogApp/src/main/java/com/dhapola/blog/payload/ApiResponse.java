package com.dhapola.blog.payload;


import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String message;
	private boolean success;
    private LocalTime time;// Add timestamp field

}
