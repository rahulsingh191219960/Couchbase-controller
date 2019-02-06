package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.couchbase.client.java.*;
import com.couchbase.client.java.document.*;
import com.couchbase.client.java.document.json.*;
//import com.couchbase.client.java.query.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class controller {
	@RequestMapping("/add") 
	public String say()
	{
		Cluster cluster = CouchbaseCluster.create("127.0.0.1"); //always
        cluster.authenticate("Administrator", "123456");
        Bucket bucket = cluster.openBucket("beer_name");
        JsonObject arthur = JsonObject.create()
                .put("country", "Afg")
                .put("name", "XXX");
        bucket.upsert(JsonDocument.create("3", arthur));
        
        return("bucket-added");
	}
	
	@RequestMapping("/get")
	public String get()
	{
		Cluster cluster = CouchbaseCluster.create("127.0.0.1"); //always
        cluster.authenticate("Administrator", "123456");
        Bucket bucket = cluster.openBucket("beer_name");
        String s = bucket.get("2").content().toString();
        return s;
//        return "hello get";v
	}

}
