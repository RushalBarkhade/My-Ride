package com.example.myride.verification;

import com.example.myride.pojo.POJOForLicenceInfo;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LicenceVerification {

    private Request request;
    private OkHttpClient client;

    public LicenceVerification(String drivingLicence,String DOB){
        String content = "DLnumber="+drivingLicence+"&DOB="+DOB;
        client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);
        request = new Request.Builder()
                .url("https://driving-licence-info.p.rapidapi.com/DLinfo")
                .post(body)
                .addHeader("x-rapidapi-host", "driving-licence-info.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "d20be7b7b6msh6dd324d837ad3f7p1fa634jsn102655b8d94d")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

    }

    public POJOForLicenceInfo getDetails(){
        final POJOForLicenceInfo info=new POJOForLicenceInfo();
        try {
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }
                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();

                    String pattern = body.string().replaceAll("<.*?>","");
                    JSONParser parser = new JSONParser();
                    try {
                        JSONObject json = (JSONObject) parser.parse(pattern);
                        JSONObject userPerson  = (JSONObject) json.get("DetailsOfDL");
                        assert userPerson != null;
                        String userName = (String) userPerson.get("Holder_Name");
                        String licenceNumber = (String) userPerson.get("DLno");
                        String address = (String) userPerson.get("Last_Transaction");
                        String issueDate = (String) userPerson.get("Issue_Date");
                        String currentStatus = (String) userPerson.get("Current_Status");
                        info.setAddress(address);
                        info.setCurrentStatus(currentStatus);
                        info.setIssueData(issueDate);
                        info.setLicenceNo(licenceNumber);
                        info.setUserName(userName);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
