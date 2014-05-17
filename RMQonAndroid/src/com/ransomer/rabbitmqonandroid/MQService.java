package com.ransomer.rabbitmqonandroid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.rabbitmq.client.ConnectionFactory;
import com.ransomer.rabbitmqonandroid.MessageConsumer.OnReceiveMessageHandler;

public class MQService extends Service{
	private MessageConsumer mConsumer;
    private TextView mOutput;
	
	
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
		
		//The output TextView we'll use to display messages
        //mOutput =  (TextView) findViewById(R.id.output);
 
        //Create the consumer
        mConsumer = new MessageConsumer("137.140.3.151",
                "sdn_events",
                "topic");
 
        //Connect to broker
        mConsumer.connectToRabbitMQ();
 
        //register for messages
        mConsumer.setOnReceiveMessageHandler(new OnReceiveMessageHandler(){
 
            public void onReceiveMessage(byte[] message) {
                String text = "";
                try {
                    text = new String(message, "UTF8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
 
                mOutput.append("\n"+text);
            }
        });


		Log.d("MQService", "Service created");
		Toast.makeText(this,"Service created ...", Toast.LENGTH_LONG).show();


	}
	
	
	public Runnable mRunnable = new Runnable(){  
		public void run() {

			mConsumer.connectToRabbitMQ();
				

		}
	};
     
	// For time consuming and long tasks you can launch a new thread here...
     public int onStartCommand(Intent intent, int flags, int startId)
     {
    	 
    	 Thread connect = new Thread(mRunnable);
         connect.start();	 
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