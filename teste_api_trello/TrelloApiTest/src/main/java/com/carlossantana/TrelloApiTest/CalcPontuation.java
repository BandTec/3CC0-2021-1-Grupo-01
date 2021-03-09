package com.carlossantana.TrelloApiTest;

import com.carlossantana.TrelloApiTest.controllers.AppConfig;
import com.carlossantana.TrelloApiTest.models.Task;
import com.carlossantana.TrelloApiTest.models.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.time.LocalDateTime;
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

    public Double createTasks(JSONArray jsonArray){
        JSONArray arrayTasks = new JSONArray();

        jsonArray.forEach(json-> {
            JSONObject jsonCard = (JSONObject) json;

//            JSONObject task = new JSONObject();

            Task task = new Task(
                    (String) jsonCard.get("id"),
                    calculatePontuation(jsonCard),
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
        return 0.0;
    }

    public Double calculatePontuation(JSONObject jsonCard) {
        Integer urgencyAxis = 0;
        Integer difficultyAxis = 0;
        Double totalPoints = 0.0;

        Double[][] matrizCalc= {
                {10.0,12.5,15.0,17.5},
                {12.5, 15.0, 17.5, 20.0},
                {15.0,17.5,20.0,22.5},
                {17.5,20.0,22.5,25.0}
        };

//        System.out.println(jsonCard);
        //Verificar labels
        if (!jsonCard.getJSONArray("labels").isEmpty()){

            for (Object label : jsonCard.getJSONArray("labels")) {
                JSONObject labelJson = (JSONObject) label;

                //TODO: Implementar verificação para que não seja possível adicionar mais de uma label a um mesmo card
                if (labelJson.get("name").equals("Alta Urgência")
                        && labelJson.get("color").equals("red")) {
                    urgencyAxis = 3;
                } else if (labelJson.get("name").equals("Expert")
                        && labelJson.get("color").equals("red")){
                    difficultyAxis = 3;
                } else if (labelJson.get("name").equals("Urgente")
                        && labelJson.get("color").equals("orange")) {
                    urgencyAxis = 2;
                } else if (labelJson.get("name").equals("Difícil")
                        && labelJson.get("color").equals("orange")){
                    difficultyAxis = 2;
                } else if (labelJson.get("name").equals("Média Urgência")
                        && labelJson.get("color").equals("yellow")) {
                    urgencyAxis = 1;
                } else if (labelJson.get("name").equals("Médio")
                        && labelJson.get("color").equals("yellow")){
                    difficultyAxis = 1;
                } else if (labelJson.get("name").equals("Pouca Urgência")
                        && labelJson.get("color").equals("green")) {
                    urgencyAxis = 0;
                } else if (labelJson.get("name").equals("Fácil")
                        && labelJson.get("color").equals("green")){
                    difficultyAxis = 0;
                }
            }
        }

        //Prazo Cumprido ou não
        Double timeBonusValue = jsonCard.get("dueComplete").equals(true) ? 1.5 : 0.5;

        //TODO: Implementar verificação da qtd de vezes em q uma tarefa foi refeita

        System.out.println(matrizCalc[urgencyAxis][difficultyAxis] * timeBonusValue);
        return matrizCalc[urgencyAxis][difficultyAxis] * timeBonusValue;
    }

    public JSONArray getCardsFromDoneList(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/list/" + AppConfig.idListDone + "/cards")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

//        System.out.println(response.getBody().getArray());
        return response.getBody().getArray();
    }

    public static void main(String[] args) {
        CalcPontuation calc = new CalcPontuation();
//        calc.verifyParameters(calc.getLastAction());

        calc.getCardsFromDoneList();
        calc.createTasks(calc.getCardsFromDoneList());


    }
}
