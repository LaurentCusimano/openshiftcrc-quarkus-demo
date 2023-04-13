package org.acme.rest.json;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/record")
public class InfinispanRecordResource {

    private Set<InfinispanRecord> records = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public InfinispanRecordResource(){
        records.add(new InfinispanRecord("TestRec", "Hello"));
        records.add(new InfinispanRecord("TestRec2", "World"));

    }

    @GET
    public Set<InfinispanRecord> list(){
        return records;
    }

    @POST
    public Set<InfinispanRecord> add(InfinispanRecord record){
        records.add(record);
        return records;
    }

    @DELETE
    public Set<InfinispanRecord> delete(InfinispanRecord record){
        records.removeIf(existingRecord ->
                existingRecord.name.contentEquals(record.name));
        return records;
    }
}
