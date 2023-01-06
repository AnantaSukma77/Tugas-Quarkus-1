package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;
import io.vertx.core.json.JsonObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/item")
public class ItemController {
    @POST
    @Transactional
    public Response created(JsonObject request){
        Item item = new Item();
        item.name = request.getString("name");
        item.count = request.getInteger("count");
        item.price = request.getInteger("price");
        item.type = request.getString("type");
        item.description = request.getString("description");

        item.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }
    //get semua data yang ada di database
    @GET
    public Response list(){
        return Response.ok().entity(Item.listAll()).build();
    }

    //get by id
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id){
        Item item = Item.findById(id);
        if(item == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "ITEM_NOT_FOUND"))
                    .build();
        }

        return Response.ok().entity(item).build();
    }

    //update
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, JsonObject request){
        Item item = Item.findById(id); //select * from peserta where id = :1
        item.name = request.getString("name");
        item.count = request.getInteger("count");
        item.price = request.getInteger("price");
        item.type = request.getString("type");
        item.description = request.getString("description");

        //save
        item.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }

    //delete by id
    @DELETE
    @Path ("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id, JsonObject request){
        Item.deleteById(id);
        return Response.ok().entity(new HashMap<>()).build();
    }
}
