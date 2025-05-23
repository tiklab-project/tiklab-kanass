package io.tiklab.kanass.project.mantis.model;

public class MantisProject {
    private String id;
    private String newID;
    private String projectName;
    private String projectKey;

    public MantisProject(String id, String projectName) {
        this.id = id;
        this.projectName = projectName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // 重写equals和hashCode以确保Set中唯一性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantisProject user = (MantisProject) o;
        return id.equals(user.getId()) && projectName.equals(user.getProjectName());
    }

    @Override
    public int hashCode() {
        return 31 * id.hashCode() + projectName.hashCode();
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }
}
