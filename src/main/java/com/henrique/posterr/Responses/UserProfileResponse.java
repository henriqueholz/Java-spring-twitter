package com.henrique.posterr.Responses;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserProfileResponse {
    private Map<String, Object> body = new LinkedHashMap<>();

    public UserProfileResponse()
    {
        body.put("username", null);
        body.put("joinedDate", null);
        body.put("followsNumber", null);
        body.put("followingNumber", null);
        body.put("userPostsNumber", null);
        body.put("timestamp", null);
    }

    public void setUsername(String username)
    {
        body.put("username", username);
    }

    public void setJoinedDate(Object joinedDate)
    {
        body.put("joinedDate", joinedDate);
    }

    public void setFollowsNumber(Object followsNumber)
    {
        body.put("followsNumber", followsNumber);
    }

    public void setFollowingNumber(Object followingNumber)
    {
        body.put("followingNumber", followingNumber);
    }

    public void setUserPostsNumber(Object userPostsNumber)
    {
        body.put("userPostsNumber", userPostsNumber);
    }


    public Map<String,Object> getBodyResponse()
    {
        body.put("timestamp", LocalDateTime.now());
        return body;
    }
}