package com.demo.test.helper;

import com.demo.test.data.User;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class Helper {
    private Helper() {
    }

    public static User initFromJsonUser(String data){
        //TODO:
        return null;
    }

    public static User initUserFromXml(String data){
        XStream xStream = new XStream();
        xStream.addPermission(NoTypePermission.NONE); //forbid everything
        xStream.addPermission(NullPermission.NULL);   // allow "null"
        xStream.addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types
        xStream.processAnnotations(User.class);
        xStream.allowTypes(new Class[] {
                com.demo.test.data.User.class
        });

        return (User)xStream.fromXML(data);
    }
}
