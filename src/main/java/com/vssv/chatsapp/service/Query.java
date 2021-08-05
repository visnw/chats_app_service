package com.vssv.chatsapp.service;

import lombok.Builder;
import lombok.Data;

public class Query {
    private String queryType;
    private String getColumn;
    public String tableName;
    public String condition;
    private String query;
    private String leftJoin;

    public static Query builder(){
        return new Query();
    }

    public Query setQueryType(String queryType){
        this.queryType = queryType;
        return this;
    }
    public Query setLeftJoin(String queryType){
        this.queryType = queryType;
        return this;
    }
    public Query setGetColumn(String getColumn){
        this.getColumn = getColumn;
        return this;
    }
    public Query setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }
    public Query setCondition(String condition){
        this.condition = condition;
        return this;
    }

    public String build(){
        this.query = queryType + " " + getColumn + " form " + tableName + " where " + condition;
        return this.query;
    }



}






