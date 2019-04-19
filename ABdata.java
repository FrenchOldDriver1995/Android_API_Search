package com.example.listviewtest;

import java.util.ArrayList;

public class ABdata {
    private String name;
    private int stargazers_count;

    public ArrayList<owner> getowner;
    public ABdata(){

    }

    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return this.name;
    }

    public void setStargazers_count(int s){
        this.stargazers_count = s;
    }
    public int getStargazers_count(){
        return this.stargazers_count;
    }

    class owner{//内部类
        public String avatar_url;

        public void setAvatar_url(String a){
            this.avatar_url = a;
        }
        public String getAvatar_url(){
            return avatar_url;
        }
    }
}

