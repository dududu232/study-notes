package com.test.test01.domain;

import java.util.List;
import java.util.Map;

public class Student {
    private String id;
    private String name;
    private String sex;
    private List<Score> scores;
    private Map<String,String> scoreMap;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Map<String, String> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, String> scoreMap) {
        this.scoreMap = scoreMap;
    }
}
