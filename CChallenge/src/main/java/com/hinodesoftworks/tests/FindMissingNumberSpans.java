package com.hinodesoftworks.tests;

import java.util.ArrayList;

import com.hinodesoftworks.core.Testable;

public class FindMissingNumberSpans implements Testable {

	//
	
	ArrayList<Integer> sortedNumbers;
	
	public void populateList(){
		sortedNumbers = new ArrayList<>();
		//TODO: automate this
		sortedNumbers.add(1);
		sortedNumbers.add(2);
		sortedNumbers.add(3);
		sortedNumbers.add(6);
		sortedNumbers.add(40);
		sortedNumbers.add(66);
	}
	
	@Override
	public String runTest() {
		populateList();
		
		ArrayList<Integer> missing = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 100; i++){
			if (!sortedNumbers.contains(i)) missing.add(i);	
		}
		
		int first, second;
		
		for (int index = 0; index < missing.size(); index++){
			first = missing.get(index);
			second = first;
			
			while (index < missing.size() - 1 && missing.get(index + 1) - missing.get(index) == 1){
				second = missing.get(index);
				index++;
			}
			sb.append(first == second ? first : first + "-" + second);
			sb.append(", ");
			
			
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		
		return sb.toString();
	}

}
