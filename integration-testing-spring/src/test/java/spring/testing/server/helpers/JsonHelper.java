package spring.testing.server.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	public static String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
