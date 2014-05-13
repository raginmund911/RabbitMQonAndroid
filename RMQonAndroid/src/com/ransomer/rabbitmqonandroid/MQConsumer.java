package com.ransomer.rabbitmqonandroid;
import android.os.Handler;

import com.rabbitmq.client.QueueingConsumer;
import com.ransomer.rabbitmqonandroid.MessageConsumer.OnReceiveMessageHandler;

import java.io.IOException;


/**
 *Consumes messages from a RabbitMQ broker
 *
 */
public class MQConsumer extends MQService{

	public MQConsumer(String server, String exchange, String exchangeType) {
		super();
	}
	
	//last message to post back
    private byte[] mLastMessage;
    
	// An interface to be implemented by an object that is interested in messages(listener)
    public interface OnReceiveMessageHandler{
        public void onReceiveMessage(byte[] message);
    };
    
    //
    /**
     *A reference to the listener, we can only have one at a time.  Since only one queue
     *will be consumed at a time, this is exceptable for now
     */
    private OnReceiveMessageHandler mOnReceiveMessageHandler;
 
    /**
     *
     * Set the callback for received messages
     * @param onReceiveMessageHandler The callback
     */
    public void setOnReceiveMessageHandler(OnReceiveMessageHandler onReceiveMessageHandler){
        mOnReceiveMessageHandler = onReceiveMessageHandler;
    };
    
    //Create two message handlers to post messages to the main thread
    private Handler mMessageHandler = new Handler();
    private Handler mConsumeHandler = new Handler();
 
    // Create runnable for posting back to main thread
    final Runnable mReturnMessage = new Runnable() {
        public void run() {
            mOnReceiveMessageHandler.onReceiveMessage(MyApplication.mLastMessage);
        }
    };
    
    final Runnable mConsumeRunner = new Runnable() {
        public void run() {
            Consume();
        }
    };
 
	
}
