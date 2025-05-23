package io.tiklab.kanass.project.mantis.model;

public class MantisVersion {
//    private String id;
    private String newID;
    private String versionName;
    private String projectId;
    private String newProjectId;

    public MantisVersion(String versionName, String projectId) {
        this.versionName = versionName;
        this.projectId = projectId;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getNewID() {
        return newID;
    }

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(String newProjectId) {
        this.newProjectId = newProjectId;
    }

    // 重写equals和hashCode以确保Set中唯一性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantisVersion version = (MantisVersion) o;
        return versionName.equals(version.getVersionName());
    }

    @Override
    public int hashCode() {
        return versionName.hashCode();
    }
}
