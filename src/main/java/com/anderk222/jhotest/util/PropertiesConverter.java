package com.anderk222.jhotest.util;

public interface PropertiesConverter {
    
    public final String JWT_RANDOM_KEY = "#{new com.anderk222.jhotest.util.KeyGenerator().jwtRandomKey(256,'${jwt.secret}')}";

}
