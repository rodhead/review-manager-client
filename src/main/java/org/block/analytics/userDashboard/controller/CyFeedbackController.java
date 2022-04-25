package org.block.analytics.userDashboard.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cy/")
@CrossOrigin("http://localhost:4200")
public class CyFeedbackController {

    @GetMapping("test")
    public String test(){
        return "ping successful";
    }

    @GetMapping(value = "cy-feedback")
    public String getCyFeedback() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "POST");
        jsonObject.put("firstName", "http://www.tutorialspoint.com/");
        jsonObject.put("lastName", "HTTP/1.1");

        JSONArray arr = new JSONArray();
        arr.put(jsonObject);

        JSONObject js = new JSONObject();
        js.put("data",arr);
        return js.toString();
    }
}
