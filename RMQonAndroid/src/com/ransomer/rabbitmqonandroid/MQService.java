package com.ransomer.rabbitmqonandroid;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.rabbitmq.client.ConnectionFactory;

public class MQService extends Service{
	
	private boolean sRunning = false;
	
	// Binder given to clients
    //private final IBinder mBinder = new MQBinder();
    
	/*
	MQBinder provides getService() method for clients to retrieve the current
    instance of MQService
    */
	public class MQBinder extends Binder {
		MQService getService() {
		
		//Return this instance of MQService so clients can call public methods
	    return MQService.this;
	    }
	}
	
	@Override
     public void onCreate() {
           super.onCreate();
          
           
           Log.d("MQService", "Service created");
           Toast.makeText(this,"Service created ...", Toast.LENGTH_LONG).show();
           
                                
     }
	
	public void connectToRabbitMQ() throws IOException
    {
      
        	Log.d("MQService", "Attempting to connect to RabbitMQ broker...");
        	MyApplication.mFactory = new ConnectionFactory();
	      	MyApplication.mFactory.setHost("137.140.3.151");
	      	MyApplication.mFactory.setUsername("test");
	      	MyApplication.mFactory.setPassword("testrabbitmq");
	        MyApplication.mFactory.setPort(5672);
            
            MyApplication.mConnection = MyApplication.mFactory.newConnection();
        
    }
	public Runnable mRunnable = new Runnable(){  
		public void run() {

			try {
				connectToRabbitMQ();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}
	};
     
	// For time consuming and long tasks you can launch a new thread here...
     public int onStartCommand(Intent intent, int flags, int startId)
     {
    	 
    	 if (sRunning) {
            // sRunning = true;
        	 Log.d("MQService", "MQService Already Started, start id " + startId + ": " + intent);

         }
    	 
    	 else{
    	
	    	 Toast.makeText(this, " MQService Started", Toast.LENGTH_LONG).show();
	    	 Thread ConnectToRabbitMQ = new Thread(mRunnable);
	         ConnectToRabbitMQ.start();
	         sRunning = true;
            
    	 }	 
    	 return START_STICKY;
     }
  
     @Override
     public void onDestroy() {
           super.onDestroy();
           
           Log.d("MQService", "Service Destroyed");
       
	
     }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}  
  
}