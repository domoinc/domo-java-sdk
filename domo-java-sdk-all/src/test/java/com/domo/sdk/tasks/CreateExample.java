package com.domo.sdk.tasks;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.tasks.model.Project;
import com.domo.sdk.tasks.model.ProjectList;
import com.domo.sdk.tasks.model.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

public class CreateExample extends ExampleBase {

    @Test
    public void tasksClient_smokeTest() throws IOException {
        TasksClient tasksClient = new TasksClient(client.getUrlBuilder(), client.getTransport());

        //Create a Project
        Project project = new Project();
        project.setName("Test Project");
        Project projectResponse = tasksClient.createProject(project);

        //Create a List
        ProjectList list = new ProjectList();
        list.setName("Test List");
        list.setType(ProjectList.ListType.TODO);
        ProjectList listResponse = tasksClient.addProjectList(projectResponse.getId(), list);

        //Create a Task
        Task task = new Task();
        task.setTaskName("Test Task");
        Task taskResponse = tasksClient.addTask(projectResponse.getId(), listResponse.getId(), task);

        //Create attachment
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Domo.png");
        File file = File.createTempFile("domo", ".tmp");
        file.deleteOnExit();
        OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
        tasksClient.addAttachment(projectResponse.getId(), listResponse.getId(), taskResponse.getId(), file.getPath());
    }

}
