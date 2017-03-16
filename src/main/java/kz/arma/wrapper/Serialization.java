/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.arma.wrapper;

import com.google.gson.Gson;
import kz.arma.gson.*;

/**
 *
 * @author a.amanzhol
 */
public class Serialization {

    public static GsonUserDetail wrapToGsonUserDetailByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonUserDetail.class);
    }

    public static GsonEmailDetail wrapToGsonEmailDetailByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonEmailDetail.class);
    }

    public static GsonEmail wrapToGsonEmailByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonEmail.class);
    }
    public static GsonGames wrapToGsonGamesByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonGames.class);
    }

    public static GsonMsgTemplate wrapToGsonMsgTemplateByJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GsonMsgTemplate.class);
    }
}
