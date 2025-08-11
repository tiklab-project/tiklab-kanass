package io.tiklab.kanass.sql;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class Pmc159Task implements DsmProcessTask {
    @Autowired
    private ProjectService projectService;
    @Override
    public void execute() {
        List<Project> allProject = projectService.findAllProject();
        for (Project project : allProject) {
            int color = new Random().nextInt(3) + 1;
            project.setColor(color);

            projectService.updateProject(project);
        }
    }
}
