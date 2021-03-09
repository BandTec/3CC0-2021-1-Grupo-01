package com.carlossantana.TrelloApiTest;

import com.carlossantana.TrelloApiTest.controllers.AppConfig;
import com.carlossantana.TrelloApiTest.models.Developer;
import com.carlossantana.TrelloApiTest.models.Manager;
import com.carlossantana.TrelloApiTest.models.Task;
import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalcPontuation {

    User dev = new Developer("60288b1a7a611413aebb1ee4", "vicentin123", "Victor Vicente",
            "123", "321");
    User manager = new Manager("60288b1a7a611413aebb1ee4", "calinSantana", "Carlos Santana",
            "345", "453");

    String boardId = AppConfig.idBoardMonitored;


    public JSONArray getAllCardsBoard(){


        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/"+ boardId +"/cards")
                .queryString("key",  dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray().getJSONObject(0).get("id"));
//        return response.getBody().getArray().getJSONObject(0).get("id");
        return response.getBody().getArray();
    }

    public void getUserCards(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/" + dev.getId() + "/cards")
                .queryString("key",  dev.getKey())
                .queryString("token", dev.getToken())
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
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .queryString("limit", 1)
                .asJson();

        return response.getBody().getArray();
    }

    public JSONArray getCardInfo(String idCard){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/cards/" + idCard)
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray());
        return response.getBody().getArray();
    }

    public void createTasks(JSONArray jsonArray){
//        JSONArray arrayTasks = new JSONArray();

        jsonArray.forEach(json-> {
            JSONObject jsonCard = (JSONObject) json;

//            JSONObject task = new JSONObject();

            Task task = new Task(
                    (String) jsonCard.get("id"),
                    dev.getTaskPontuation(jsonCard),
                    false,
                    null
            );
//            task.put("id", jsonCard.get("id");
//            task.put("points",
//            task.put("status", false);
//            task.put("approver", "id manager");

//            System.out.println(task.toString());
        });



            //verificar tipo do action
//            if (json.getJSONObject("data").getJSONObject("board").get("id").equals(boardId)
//                    && json.get("type").equals("updateCard")) {

//                System.out.println(json.getJSONObject("data").getJSONObject("card").get("id"));
//                JSONArray completeCard = getCardInfo((String) json.getJSONObject("data").getJSONObject("card").get("id"));
//                System.out.println(completeCard);


//                    JSONObject pObject = (JSONObject) p;
//                    System.out.println(pObject.getJSONArray("labels").getJSONObject(2).get("name"));

//                    pObject.getJSONArray("labels").forEach(label -> {
//                        JSONObject labelObject = (JSONObject) label;
//                        System.out.println(labelObject.get("name"));



//                        System.out.println(labelObject.get("color"));
//                    });
//                }
//            }
//        }
    }

    public JSONArray getCardsFromDoneList(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/list/" + AppConfig.idListDone + "/cards")
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray());
        return response.getBody().getArray();
    }


    public static void main(String[] args) {
        CalcPontuation calc = new CalcPontuation();
//        calc.verifyParameters(calc.getLastAction());

        User dev = new Developer("60288b1a7a611413aebb1ee4", "vicentin123", "Victor Vicente",
                "123", "321");

        calc.getCardsFromDoneList();
        calc.createTasks(calc.getCardsFromDoneList());
//        dev.getTaskPontuation(calc.getCardsFromDoneList());

    }
}
