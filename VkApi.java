package com.company;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VkApi {

    private static final String ACCESS_TOKEN = "2ad22de6fcbe8da4ffec4f1c9bf4479e0c7e71638492f8376157739178cfc9e828d27a01094f7d5e468fe";
    private static final int APP_ID = 8001584;
    private final VkApiClient vk;
    private final UserActor actor;
    public VkApi(){
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, ACCESS_TOKEN);
    }

    public List<JsonObject> find_users() throws ClientException, ApiException {
        var memberIds = getMemberIds();
        var members = new ArrayList<JsonObject>(memberIds.size());
        var maxCount = 0;
        while (maxCount<memberIds.size()){
            var temp = new JsonParser().parse(vk.users().get(actor).userIds(memberIds.subList(maxCount,maxCount+(memberIds.size()-maxCount)%1000))
                    .fields(new Fields[]{Fields.BDATE,Fields.CITY,Fields.PHOTO_MAX,Fields.SEX}).execute().toString()).getAsJsonArray();
            temp.forEach(a->members.add(a.getAsJsonObject()));
            maxCount+=1000;
        }

        return members;
    }

    private List<String> getMemberIds() throws ApiException, ClientException {
        var request = vk.groups().getMembers(actor).groupId("iot_second_urfu");
        var temp = new JsonParser().parse(request.execute().toString()).getAsJsonObject();
        var maxElements = temp.get("count").getAsInt();
        var memberIds = temp.get("items").getAsJsonArray();
        var members = new HashSet<String>(maxElements);
        memberIds.forEach(a->members.add(a.getAsString()));
        while (maxElements>0){
            temp = new JsonParser().parse(request.offset(1000).execute().toString()).getAsJsonObject();
            memberIds = temp.get("items").getAsJsonArray();
            memberIds.forEach(a->members.add(a.getAsString()));
            maxElements-=1000;
        }

        return new ArrayList<>(members);
    }
}
