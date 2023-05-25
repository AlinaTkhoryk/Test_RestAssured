package com.demo.test.helper;

import com.demo.test.data.User;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Helper {
    private Helper() {
    }

    public static User initFromJsonUser(String data)
    {
        Gson gson= new Gson();
        return gson.fromJson(data, User.class);
    }

    public static User initUserFromXml(String data) {
        XStream xStream = new XStream();
        xStream.addPermission(NoTypePermission.NONE); //forbid everything
        xStream.addPermission(NullPermission.NULL);   // allow "null"
        xStream.addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types
        xStream.processAnnotations(User.class);
        xStream.allowTypes(new Class[]{
                com.demo.test.data.User.class
        });

        return (User) xStream.fromXML(data);
    }

    public static String GetUserJson() throws IOException {
        List<String> lines= Files.readAllLines(Paths.get("./mappings/jsonUser"));
        String json = lines.get(0);
        return json;
    }

    public static String GetUserXml() throws IOException {
        List<String> lines= Files.readAllLines(Paths.get("./mappings/xmlUser"));
        String json = "";
        for (int i=0;i<lines.size();i++){
            json+= lines.get(i);
        }
        return json;
    }
}
