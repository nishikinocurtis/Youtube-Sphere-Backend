package com.privoce.youtube_sphere_backend.controller;

import cn.authing.core.types.User;
import com.alibaba.fastjson.JSONObject;
import com.privoce.youtube_sphere_backend.entity.Reaction;
import com.privoce.youtube_sphere_backend.entity.SphereUser;
import com.privoce.youtube_sphere_backend.entity.VideoInfo;
import com.privoce.youtube_sphere_backend.service.AuthingService;
import com.privoce.youtube_sphere_backend.service.GraphDBService;
import com.privoce.youtube_sphere_backend.service.NeoService;
import com.privoce.youtube_sphere_backend.service.YoutubeService;

import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class SphereUserController {
    @Autowired
    AuthingService authingService;
    @Autowired
    YoutubeService youtubeService;
    @Autowired
    NeoService neoService;


    @GetMapping("/connect")
    public List<User> getFriends(String userId) throws IOException {
        return neoService.getFriends(userId);
    }

    @GetMapping("/connect/liked")
    public List<VideoInfo> getFriendsLiked(String userId) throws IOException {
        return neoService.getFriendsReaction(userId);
    }

    @PostMapping("/connect/liked")
    public Record createReaction(@RequestBody  Reaction reaction){
        return neoService.createReaction(reaction);
    }

    @PostMapping("")
    public Record createUser(@RequestBody Map<String,String> map){
        return neoService.createUser(map.get("userId"));
    }

}
