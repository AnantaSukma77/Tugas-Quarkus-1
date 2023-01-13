package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;
import id.kawahedukasi.model.dto.UploadItemRequest;
import id.kawahedukasi.service.ItemService;
import id.kawahedukasi.service.ReportService;
//import id.kawahedukasi.service.UploadService;
import io.vertx.core.json.JsonObject;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemService itemService;
    ReportService reportService;
//    UploadService uploadService;

    @POST
    public Response created(JsonObject request){
        return itemService.created(request);
    }

    //get all
    @GET
    public Response getAll(){
        return itemService.getAll();
    }

    @GET
    @Path("/report")
    @Produces("application/pdf")
    public Response create() throws JRException {
        return reportService.exportJasper();
    }

//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @POST
//    public Response upload(@MultipartForm UploadItemRequest request){
//        return uploadService.upload(request);
//    }

    //get by id
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id){
        return itemService.getById(id);
    }

    //update
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, JsonObject request){
        return itemService.update(id, request);
    }

    //delete by id
    @DELETE
    @Path ("/{id}")
    public Response delete(@PathParam("id") Integer id){
        return  itemService.delete(id);
    }
}
