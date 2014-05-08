package com.ransomer.rabbitmqonandroid;


import java.util.UUID;

/* An instance of SDNEvent will represent a single log entry.  An example is listed below:
 * "2013-07-12 10:27:45.513 INFO  [n.f.c.m.FloodlightModuleLoader] Loading default modules"
 */
public class SDNEvent {
	 
	//private UUID mId; // an ID number will uniquely identify an instance of Crime
	private String TimeStamp; //ex. - 2013-07-12 10:27:45.513
	private String LogType; //ex. - DEBUG, INFO
	private String ClassName; //ex. - [n.f.c.m.FloodlightModuleLoader]
	private String Comment;//ex. - "Found Module net.floodlightcontroller.storage.memory.MemoryStorageSource"
		 
	//constructor for event
	SDNEvent(String Timestamp, String LogType, String ClassName, String Comment) {
		this.TimeStamp = TimeStamp;
		this.LogType = LogType;
		this.ClassName = ClassName;
		this.Comment = Comment;
		//Generate unique identifier
		//mId = UUID.randomUUID();
	}

	/*
	public UUID getmId() {
		return mId;
	}
 	*/
	
	@Override
    public String toString() {
        return null;
    }
}

