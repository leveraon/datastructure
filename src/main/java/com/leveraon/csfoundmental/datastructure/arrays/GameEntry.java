package com.leveraon.csfoundmental.datastructure.arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameEntry {
	private String name; // name of the person earning this score
	private int score; // the score value

}