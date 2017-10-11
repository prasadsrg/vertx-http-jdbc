package io.vertx.example;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject; 
import io.vertx.ext.jdbc.JDBCClient; 
import io.vertx.ext.sql.ResultSet; 
import io.vertx.ext.sql.SQLConnection; 
 
import javax.sql.DataSource; 
 

/**
 * @author 
 */
public class HelloWorldEmbedded {

  public static void main(String[] args) {
    // Create an HTTP server which simply returns "Hello World!" to each request.
    Vertx.vertx()
    	.createHttpServer()
    	.requestHandler( req -> { 
    		// req.response().end(getData()); 
    		 JDBCClient client = getConnection();
    		 client.getConnection(res -> { 
    			if (res.failed()) {
    		        return;
    		      }
    		      if (res.succeeded()) { 
    		 
    		        SQLConnection connection = res.result(); 
    		 
    		        connection.query("SELECT * FROM profile", res2 -> { 
    		          if (res2.succeeded()) { 
    		 
    		            ResultSet rs = res2.result(); 
    		            req.response().end(rs.getRows().toString());
    		           
    		          } 
    		        }); 
    		      } else { 
    		        // Failed to get connection - deal with it 
    		      } 
    		    }); 
    		
    	})
    	.listen(8008);
  }
  
//  public static String getData(){
//	  
//		 return "abcd";
//  }
  
  public static JDBCClient getConnection() {
	    JsonObject config = new JsonObject() 
	    	      .put("url", "jdbc:mysql://localhost:3306/test") 
	    	      .put("user", "root") 
	    	      .put("password", "Test!234") 
	    	      .put("driver_class", "com.mysql.jdbc.Driver") 
	    	      .put("max_pool_size", 5); 
	    System.out.println("------------start------");
	    	    JDBCClient client = JDBCClient.createShared(Vertx.vertx(), config); 
	    	    System.out.println("--------client----------");
  			  System.out.println(client);  
	    	    return client;
  }

}
