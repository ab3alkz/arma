/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.arma.rest;


import kz.arma.gson.GsonGames;
import kz.arma.session.AdminSession;
import kz.arma.wrapper.Serialization;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;

import java.io.InputStream;
import java.math.BigDecimal;

import static kz.arma.util.Util.getGsonResult;
import static kz.arma.util.Util.objectToJson;

/*
* REST Web Service
*
* @author a.amanzhol
*/
@Stateless
@Path("admin")
public class AdminResource {

    @Context
    private HttpServletRequest request;


    @EJB
    AdminSession adminSession;

    @Context
    SecurityContext sc;

    @Resource
    private javax.transaction.UserTransaction utx;

    private String getUserName() {
        return sc.getUserPrincipal().getName();
    }

    @GET
    @Produces("application/json")
    @Path("getGroups")
    public String getGroups() {
        return objectToJson(adminSession.getGroups());
    }

    @GET
    @Produces("application/json")
    @Path("getLastGameName")
    public String getLastGameName() {
        return objectToJson(adminSession.getLastGsonGame());
    }

    @GET
    @Produces("application/json")
    @Path("getArchiveGameList")
    public String getArchiveGameList() {
        return objectToJson(adminSession.getArchiveGameList());
    }


    @GET
    @Produces("application/json")
    @Path("getSlidesList")
    public String getSlidesList(@QueryParam("gameId") String gameId) {
        return objectToJson(adminSession.getSlidesList(gameId));
    }

    @POST
    @Produces("application/json")
    @Path("lockUser")
    @Consumes("application/x-www-form-urlencoded")
    public String lockUser(MultivaluedMap<String, String> formParams) {
        if (!sc.isUserInRole("admin_role"))
            return objectToJson(getGsonResult(false, "У вас не достаточно прав"));
        String uName = formParams.getFirst("uName");
        Integer lock = Integer.parseInt(formParams.getFirst("lock"));
        return objectToJson(adminSession.lockUser(uName, lock));
    }

    @POST
    @Produces("application/json")
    @Path("createUser")
    @Consumes("application/x-www-form-urlencoded")
    public String createUser(MultivaluedMap<String, String> formParams) {
        return objectToJson(adminSession.createUser(formParams.getFirst("uName"), formParams.getFirst("newPass"), formParams.getFirst("confirmPass"), formParams.getFirst("uDesc")));
    }


    @POST
    @Produces("application/json")
    @Path("addGame")
    public String addGame(String json) {
        GsonGames game = Serialization.wrapToGsonGamesByJsonString(json);
        return objectToJson(adminSession.addGame(game ));
    }

    @POST
    @Produces("application/json")
    @Path("resetPassword")
    @Consumes("application/x-www-form-urlencoded")
    public String resetPassword(MultivaluedMap<String, String> formParams) {
        return objectToJson(adminSession.resetPassword(formParams.getFirst("uName"), formParams.getFirst("oldPass"), formParams.getFirst("newPass"), formParams.getFirst("confirmPass")));
    }


    @GET
    @Produces("application/json")
    @Path("createSlide")
    public String createSlide(@QueryParam("slideType") String slideType,@QueryParam("game") String game) {
        return objectToJson(adminSession.createSlide(slideType, game));
    }

    @POST
    @Produces("application/json")
    @Path("uploadImg")
    public String uploadImg(InputStream inputStream) {
       // inputStream
        return objectToJson(null);
    }

    @POST
    @Produces("application/json")
    @Path("uploadVideo")
    public String uploadVideo(InputStream inputStream) {
       // inputStream
        return objectToJson(null);
    }
    @POST
    @Produces("application/json")
    @Path("uploadSound")
    public String uploadSound(InputStream inputStream) {
       // inputStream
        return objectToJson(null);
    }

}