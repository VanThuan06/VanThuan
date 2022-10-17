package com.example;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
    List<Mobile> lstMobile = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response _getLstMobile(){
        return Response.ok(lstMobile).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMobile(Mobile mobile){
        lstMobile.add(mobile);
        return Response.ok(mobile).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id ,Mobile mobileUpdate){
        lstMobile = lstMobile.stream().map(mobile -> {
            if (mobile.getId()==id){
                return mobileUpdate;
            }else{
                return mobile;
            }
        }).collect(Collectors.toList());
        return Response.ok(lstMobile).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobile(@PathParam("id") int idMobileDelete){
        Optional<Mobile> mobileDelete =  lstMobile.stream()
                .filter(mobile -> mobile.getId()==idMobileDelete)
                .findFirst();
        if (mobileDelete.isPresent()){
            Mobile mobile = mobileDelete.get();
            lstMobile.remove(mobile);
            return Response.ok(lstMobile).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileId(@PathParam("id") int id){
        Optional<Mobile> getmobile =  lstMobile.stream()
                .filter(mobile -> mobile.getId()==id)
                .findFirst();
        if(getmobile.isPresent()){
            return Response.ok(getmobile.get()).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
