package com.carlossantana.TrelloApiTest.models;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class Manager extends User{
    public Manager(String id, String username, String fullName, String email, String senha) {
        super(id, username, fullName, email, senha);
    }

    private Double averageGrade;


    public Double getTaskPontuation(JSONObject jsonCard) {

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

        int peso = 0;

        for (Object user : jsonCard.getJSONArray("idMembers")) {
            if (user.equals(this.getId())){
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
                peso = 1;
                break;
            }

        }

        return matrizCalc[urgencyAxis][difficultyAxis] * peso;
    }

}
