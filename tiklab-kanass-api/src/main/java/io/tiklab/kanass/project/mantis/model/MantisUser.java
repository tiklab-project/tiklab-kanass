package io.tiklab.kanass.project.mantis.model;

public class MantisUser {
    private String id;
    private String newID;
    private String userName;

    public MantisUser(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewID() {
        return newID;
    }

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // 重写equals和hashCode以确保Set中唯一性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantisUser user = (MantisUser) o;
        return id.equals(user.getId()) && userName.equals(user.getUserName());
    }

    @Override
    public int hashCode() {
        return 31 * id.hashCode() + userName.hashCode();
    }
}
