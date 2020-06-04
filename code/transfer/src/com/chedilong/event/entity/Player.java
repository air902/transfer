package com.chedilong.event.entity;

/**
 * 选手实体类
 */
public class Player {
    private Integer id;
    private String account;
    private String password;
    private String portrait;
    private String name;
    private Integer age;
    private String introduction;
    private String lastTeam;
    private String joinDate;
    private String email;
    private String accountStatus;
    private String teamStatus;
    private String rank;
    private String reason;

    public Player(Integer id, String account, String password, String portrait, String name, Integer age, String introduction, String lastTeam, String joinDate, String email, String accountStatus, String teamStatus, String rank, String reason) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.portrait = portrait;
        this.name = name;
        this.age = age;
        this.introduction = introduction;
        this.lastTeam = lastTeam;
        this.joinDate = joinDate;
        this.email = email;
        this.accountStatus = accountStatus;
        this.teamStatus = teamStatus;
        this.rank = rank;
        this.reason = reason;
    }

    public Player(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLastTeam() {
        return lastTeam;
    }

    public void setLastTeam(String lastTeam) {
        this.lastTeam = lastTeam;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
