package kz.arma.wrapper;

import kz.arma.entity.*;
import kz.arma.gson.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by a.amanzhol.
 */
public class Wrapper {

    public static GsonUsers wrapToGsonUsers(Users user) {
        if (user != null) {
            GsonUsers gson = new GsonUsers();
            gson.setuName(user.getuName());
            gson.setUserDetail(wrapToGsonUserDetail(user.getUserDetail()));
            return gson;
        }
        return null;
    }

    public static List<GsonUserDetail> wrapToGsonUserDetailList(List<UserDetail> list) {
        List<GsonUserDetail> result = new ArrayList<>();
        for (UserDetail detail : list) {
            result.add(wrapToGsonUserDetail(detail));
        }
        return result;
    }

    private static GsonUserDetail wrapToGsonUserDetail(UserDetail user) {
        if (user != null) {
            GsonUserDetail gson = new GsonUserDetail();
            gson.setuName(user.getuName().getuName());
            gson.setFirstname(user.getFirstname());
            gson.setLastname(user.getLastname());
            gson.setMiddlename(user.getMiddlename());
            gson.setEmail(user.getEmail());
            gson.setLocked(user.getLocked());
            return gson;
        }
        return null;
    }

    public static UserDetail wrapToGsonUserDetail(GsonUserDetail gson) {
        if (gson != null) {
            UserDetail userDetail = new UserDetail();
            userDetail.setuName(new Users(gson.getuName()));
            userDetail.setFirstname(gson.getFirstname());
            userDetail.setLastname(gson.getLastname());
            userDetail.setMiddlename(gson.getMiddlename());
            userDetail.setEmail(gson.getEmail());
            userDetail.setLocked(gson.getLocked() == null ? 0 : gson.getLocked());
            return userDetail;
        }
        return null;
    }


    public static List<GsonGroupmembers> wrapToGsonGroupmembersList(List<Groupmembers> list) {
        List<GsonGroupmembers> result = new ArrayList<>();
        for (Groupmembers groupmembers : list) {
            result.add(wrapToGsonGroupmembers(groupmembers));
        }
        return result;
    }


    private static GsonGroupmembers wrapToGsonGroupmembers(Groupmembers g) {
        if (g != null) {
            GsonGroupmembers gson = new GsonGroupmembers();
            gson.setgMember(g.getGroupmembersPK().getGMember());
            gson.setgName(g.getGroupmembersPK().getGName());
            return gson;
        }
        return null;
    }


    public static List<GsonGroups> wrapToGsonGroupsList(List<Groups> list) {
        List<GsonGroups> result = new ArrayList<>();
        for (Groups group : list) {
            result.add(wrapToGsonGroup(group));
        }
        return result;
    }

    private static GsonGroups wrapToGsonGroup(Groups group) {
        if (group != null) {
            GsonGroups gson = new GsonGroups();
            gson.setGDescription(group.getGDescription());
            gson.setGName(group.getGName());
            return gson;
        }
        return null;
    }


    public static List<Groupmembers> wrapToGroupmembersList(List<GsonGroupmembers> gsonList, String uName) {
        List<Groupmembers> result = new ArrayList<>();
        for (GsonGroupmembers gson : gsonList) {
            result.add(wrapToGroupmembers(gson, uName));
        }
        return result;
    }


    private static Groupmembers wrapToGroupmembers(GsonGroupmembers g, String uName) {
        if (g != null) {
            Groupmembers gson = new Groupmembers();
            gson.setGroupmembersPK(new GroupmembersPK(g.getgName(), uName));
            return gson;
        }
        return null;
    }

    public static GsonMsgTemplate wrapToGsonMsgTemplate(MsgTemplate obj) {
        GsonMsgTemplate gson = new GsonMsgTemplate();
        if (obj != null) {
            gson.setId(obj.getId());
            gson.setCode(obj.getCode());
            gson.setTemplate(obj.getTemplate());
            gson.setTitle(obj.getTitle());
        }
        return gson;
    }

    public static MsgTemplate wrapToMsgTemplate(GsonMsgTemplate obj) {
        MsgTemplate template = new MsgTemplate();
        template.setId(obj.getId());
        template.setTitle(obj.getTitle());
        template.setTemplate(obj.getTemplate());
        template.setCode(obj.getCode());
        return template;
    }


    public static GsonEmailDetail wrapToGsonEmailDetail(EmailDetail obj) {
        GsonEmailDetail gson = new GsonEmailDetail();
        if (obj != null) {
            gson.setId(obj.getId());
            gson.setHost(obj.getHost());
            gson.setPort(obj.getPort());
            gson.setPassword("****");
            gson.setUsername(obj.getUsername());
            gson.setType(obj.getType());
        }
        return gson;
    }
}
