package com.demo.test;

import com.demo.test.data.User;
import com.demo.test.helper.Helper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRestTask {


    @Test()
    public void testGet(){
        RequestSpecification spec = RestAssured.given();

        //String apiURL ="http://localhost:8080/api/mytest?param1=val1";
        String apiURL ="http://localhost:8080/api/users";
        //spec.queryParam("param1", "val1");
        ValidatableResponse resp = spec.get(apiURL).then();
        System.out.println(resp.extract().statusCode());
        System.out.println(resp.extract().contentType());
        System.out.println(resp.extract().body().asString());
        //System.out.println("Response: "+obj.getString("users[0].userName"));

    }

    @Test()
    public void testPost() throws IOException {
       
        String apiURL ="http://localhost:8080/api/users?userId=23";
        RequestSpecification spec = RestAssured.given();

        String resp = spec
                        .log().all()
                        .get(apiURL)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .and()
                        .contentType(ContentType.JSON.toString())
                        .extract()
                        .body()
                        .asString();


        User userFromServer = Helper.initFromJsonUser(resp);
        System.out.println("response:"+resp);
 //my test that parsing works

        String usersJson = Helper.GetUserJson(); // this is like we did request to server
        User users = Helper.initFromJsonUser(usersJson);

        System.out.println("response:"+usersJson);
    }

    @Test()
    public void testGetWithParams() throws IOException {

        RequestSpecification spec = RestAssured.given();
        String apiURL ="http://localhost:8080/api/users?userId=25";


        String resp = spec
                .log().all()
                .get(apiURL)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML.toString())
                .extract()
                .body()
                .asString();


        User userFromServer = Helper.initUserFromXml(resp);
        System.out.println("response:"+resp);


        //my test that parsing works


        String xml = Helper.GetUserXml();
        User user= Helper.initUserFromXml(xml);

        System.out.println("response:"+xml);

    }

}
