package com.compass.challenge.common;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.domain.entitys.Post;

import java.util.ArrayList;
import java.util.Set;

public class PostConstants {
    static Set<Comments> commentsList;
    static Set<History> historyList;
    public static Post POST = new Post("1",1L,"Post01","testendo",commentsList,historyList);
    public static Post POST_INVALID = new Post("",1L,"","",commentsList,historyList);

}
