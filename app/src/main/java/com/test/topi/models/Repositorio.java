package com.test.topi.models;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Iterator;

public class Repositorio {

    public Integer id;
    public String node_id;
    public String name;
    public String description;
    public String full_name;
    public Boolean private_;
    public Owner owner;
    public String html_url;
    public Boolean fork;
    public String url;
    public String forks_url;
    public String keys_url;
    public String collaborators_url;
    public String teams_url;
    public String hooks_url;
    public String issue_events_url;
    public String events_url;
    public String assignees_url;
    public String branches_url;
    public String tags_url;
    public String blobs_url;
    public String git_tags_url;
    public String git_refs_url;
    public String trees_url;
    public String statuses_url;
    public String languages_url;
    public String stargazers_url;
    public String contributors_url;
    public String subscribers_url;
    public String subscription_url;
    public String commits_url;
    public String git_commits_url;
    public String comments_url;
    public String issue_comment_url;
    public String contents_url;
    public String compare_url;
    public String merges_url;
    public String archive_url;
    public String downloads_url;
    public String issues_url;
    public String pulls_url;
    public String milestones_url;
    public String notifications_url;
    public String labels_url;
    public String releases_url;
    public String deployments_url;
    public String created_at;
    public String updated_at;
    public String pushed_at;
    public String git_url;
    public String ssh_url;
    public String clone_url;
    public String svn_url;
    public String homepage;
    public Integer size;
    public Integer stargazers_count;
    public Integer watchers_count;
    public String language;
    public Boolean has_issues;
    public Boolean has_projects;
    public Boolean has_downloads;
    public Boolean has_wiki;
    public Boolean has_pages;
    public Integer forks_count;
    public String mirror_url;
    public Boolean archived;
    public Boolean disabled;
    public Integer open_issues_count;
    public Object license;
    public Integer forks;
    public Integer open_issues;
    public Integer watchers;
    public String default_branch;
    public Float score;

    public Repositorio() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumber() {
        return this.forks;
    }


    public Repositorio fromJson(String repositorio) {
        Gson gson = new Gson();
        return gson.fromJson(repositorio, Repositorio.class);
    }


    public class Owner {
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
    }

}