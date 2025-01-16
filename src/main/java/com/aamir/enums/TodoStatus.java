package com.aamir.enums;
//notest get krenge to id milega jo 1 diya humne,or name milega 
//variable declare krke value assign bhi krenge
//id->1 means not started, id->2 means in progress,id->3 means completed
public enum TodoStatus {

	NOT_STARTED(1,"Not Started"),IN_PROGRESS(2,"In Progress"),COMPLETED(3,"Completed");

	private 	Integer id;
	
	private String name;
	
	
	//constructor create kr liya
	TodoStatus(Integer id, String name) {
	//value assign kr liya
		this.id=id;
		this.name=name;
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
