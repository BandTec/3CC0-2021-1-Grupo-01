package com.carlossantana.TrelloApiTest;

import com.carlossantana.TrelloApiTest.controllers.AppConfig;
import com.carlossantana.TrelloApiTest.models.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalcPontuation {

    User user = new User("60288b1a7a611413aebb1ee4", "vicentin123", "Victor Vicente",
            "123", "321");

    String boardId = AppConfig.idBoardMonitored;

    public JSONArray getAllCardsBoard(){


        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/"+ boardId +"/cards")
                .queryString("key",  user.getKey())
                .queryString("token", user.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray().getJSONObject(0).get("id"));
//        return response.getBody().getArray().getJSONObject(0).get("id");
        return response.getBody().getArray();
    }

    public void getUserCards(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/" + user.getId() + "/cards")
                .queryString("key",  user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        List<JSONObject> cardList = new ArrayList<>();


        response.getBody().getArray().forEach(json ->{
            JSONObject jsonObject = (JSONObject) json;
            if (boardId.equals(jsonObject.get("idBoard"))
                    && !(jsonObject.get("idList").equals(AppConfig.idListDone))) {
                cardList.add(jsonObject);
            }
        });

        cardList.forEach(list ->{
            System.out.println(list);
        });

    }

    public JSONArray getLastAction(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/me/actions")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .queryString("limit", 1)
                .asJson();

        return response.getBody().getArray();
    }

    public JSONArray getCardInfo(String idCard){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/cards/" + idCard)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray());
        return response.getBody().getArray();

    }



    public Double verifyParameters(JSONArray jsonArray){

        jsonArray.forEach(cardJson -> {
            JSONObject json = (JSONObject) cardJson;

            //verificar tipo do action
            if (json.getJSONObject("data").getJSONObject("board").get("id").equals(boardId)
                    && json.get("type").equals("updateCard")){

//                System.out.println(json.getJSONObject("data").getJSONObject("card").get("id"));
                JSONArray completeCard = getCardInfo((String) json.getJSONObject("data").getJSONObject("card").get("id"));
                System.out.println(completeCard);

                completeCard.forEach(p -> {
                    JSONObject label = (JSONObject) p;
                });

            }

        });


        return 0.0;
    }

    public static void main(String[] args) {
        CalcPontuation calc = new CalcPontuation();
        calc.verifyParameters(calc.getLastAction());
    }
}
