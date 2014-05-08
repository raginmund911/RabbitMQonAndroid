package com.ransomer.rabbitmqonandroid;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ransomer.rabbitmqonandroid.MyApplication;

import java.io.IOException;

/**
 * Base class for objects that connect to a RabbitMQ Broker
 */
public abstract class IConnectToRabbitMQ {
      public String mServer;
      public String mExchange;
 
      protected Channel mModel = null;
      protected Connection  mConnection;
 
      protected boolean Running ;
 
      protected String MyExchangeType ;
 
      /**
       *
       * @param server The server address
       * @param exchange The named exchange
       * @param exchangeType The exchange type name
       */
      public IConnectToRabbitMQ(String server, String exchange, String exchangeType)
      {
          mServer = server;
          mExchange = exchange;
          MyExchangeType = exchangeType;
      }
      
      //close channel and connection
      public void Dispose()
      {
          Running = false;
 
            try {///if there is an open connection, close it
                if (mConnection!=null)
                    mConnection.close();
                //if there is an open channel, close it
                if (mModel != null)
                    mModel.abort();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
      }
 
      /**
       * Connect to the broker and create the exchange
       * @return success
       */
      public boolean connectToRabbitMQ()
      {
    	  ///Check if a reference to the channel exists and if it's open
          if(mModel!= null && mModel.isOpen() )//already declared
              return true;
          try
          {
        	MyApplication.mFactory = new ConnectionFactory();
      	    MyApplication.mFactory.setHost("137.140.3.151");
      	    MyApplication.mFactory.setUsername("guest");
      	    MyApplication.mFactory.setPassword("guest");
              //factory.setPort(5672);
              System.out.println(""+MyApplication.mFactory.getHost()+MyApplication.mFactory.getPort()+MyApplication.mFactory.getRequestedHeartbeat()+MyApplication.mFactory.getUsername());
              MyApplication.mConnection = MyApplication.mFactory.newConnection();
              mModel = mConnection.createChannel();
              mModel.exchangeDeclare(mExchange, MyExchangeType, true);
              
 
              return true;
          }
          catch (Exception e)
          {
              e.printStackTrace();
              return false;
          }
      }
}