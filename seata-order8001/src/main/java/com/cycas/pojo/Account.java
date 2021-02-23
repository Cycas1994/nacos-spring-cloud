package com.cycas.pojo;

public class Account {

    /**
     * id
     */
    private Integer id;

    /**
     * user_id
     */
    private String userId;

    /**
     * money
     */
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
