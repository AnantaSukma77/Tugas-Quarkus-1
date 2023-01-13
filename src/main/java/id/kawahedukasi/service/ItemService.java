package id.kawahedukasi.service;

import id.kawahedukasi.model.Item;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ItemService {
    @Transactional //karena save data item
    public Response created(JsonObject request){
        String name = request.getString("name");
        Double count =request.getDouble("count");
        Long price = request.getLong("price");
        String type= request.getString("type");
        String description =request.getString("description");

        if (name == null || count == null || price == null || type == null || description == null)
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "BAD REQUEST"))
                    .build();
        }

        Item item = new Item();
        item.setName(name);
        item.setCount(count);
        item.setPrice(price);
        item.setType(type);
        item.setDescription(description);

        item.persist();

        return Response.ok().entity(Map.of("id", item.getId())).build();
    }

    public Response getAll(){
        List<Item> items = Item.listAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for(Item item : items){
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            map.put("count", item.getCount());
            map.put("price", item.getPrice());
            map.put("type", item.getType());
            map.put("description", item.getDescription());

            result.add(map);
        }

        return Response.ok().entity(result).build();
    }

    public Response getById(@PathParam("id") Integer id){
        Item item = Item.findById(id);
        if(item == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "ITEM_NOT_FOUND"))
                    .build();
        }

        return Response.ok().entity(item).build();
    }

    @Transactional
    public Response update(@PathParam("id") Integer id, JsonObject request){
        String name = request.getString("name");
        Double count =request.getDouble("count");
        Long price = request.getLong("price");
        String type= request.getString("type");
        String description =request.getString("description");

        if (name == null || count == null || price == null || type == null || description == null)
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "BAD REQUEST"))
                    .build();
        }

        Item item = Item.findById(id); //select * from peserta where id = :1
        if (item == null)
        { return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "ITEM NOT FOUND")).build();
        }

        item.setName(name);
        item.setCount(count);
        item.setPrice(price);
        item.setType(type);
        item.setDescription(description);

        //save
        item.persist();

        return Response.ok().entity(Map.of("id", item.getId())).build();
    }

    @Transactional
    public Response delete(@PathParam("id") Integer id){

        Item item = Item.findById(id); //select * from peserta where id = :1
        if (item == null)
        { return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "ITEM NOT FOUND")).build();
        }
        item.delete();
        return Response.ok().entity(Map.of("id", item.getId())).build();
    }
}
