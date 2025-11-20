package com.inventory.app.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map<String, Object> Ok(Object data){
        Map<String, Object> m = new HashMap<>();
        m.put("status", true);
        m.put("message", "success");
        m.put("data", data);
        return m;
    };

    public static Map<String, Object> error(String message, Object data) {
        Map<String, Object> m = new HashMap<>();
        m.put("status", false);
        m.put("message", message);
        m.put("data", data);
        return m;
    }
}
