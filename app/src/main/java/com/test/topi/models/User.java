package com.test.topi.models;

import com.google.gson.Gson;

public class User {
    public String login;
    public Integer id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public Boolean site_admin;
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public String hireable;
    public String bio;
    public String twitter_username;
    public Integer  public_repos;
    public Integer public_gists;
    public Integer followers;
    public Integer following;
    public String created_at;
    public String updated_at;


    public User fromJson(String user) {
        Gson gson = new Gson();
        return gson.fromJson(user, User.class);
    }

}
