package com.cusimanogourvil.infinispan.client;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/record")
public class InfinispanRecordResource {

    @Inject
    @Remote("mycache")
    RemoteCache<String, String> cache;

    private Set<String> records = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public InfinispanRecordResource(){

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String getValue(@PathParam("id") String path){
        if(records.contains(path)){
            return cache.get(path);
        }
        else {
            throw new NotFoundException(path + " Not Found");
        }
    }

    @GET
    public Set<InfinispanRecord> list(){
        Set<InfinispanRecord> currentLocalCache = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for(String record: records){
            currentLocalCache.add(new InfinispanRecord(record, cache.get(record)));
        }
        return currentLocalCache;
    }

    @POST
    public String add(InfinispanRecord record){
        records.add(record.getName());
        return cache.put(record.getName(), record.getContent());
    }

    @DELETE
    public Set<String> delete(InfinispanRecord record){
        records.remove(record.getName());
        return records;
    }
}
