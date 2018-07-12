package com.domo.sdk.tasks;

import com.domo.sdk.request.RequestException;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.tasks.model.Attachment;
import com.domo.sdk.tasks.model.Project;
import com.domo.sdk.tasks.model.ProjectList;
import com.domo.sdk.tasks.model.Task;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class TasksClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE = "v1/projects";

    public TasksClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    public List<Project> listProjects(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Project>>() {
        }.getType());
    }

    public Project getPersonalProject() {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment("me")
                .build();

        return transport.getJson(url, Project.class);
    }

    public Project getProject(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        try {
            return transport.getJson(url, Project.class);
        } catch (RequestException e) {
            if (e.getStatusCode() == 404) {
                return null;
            }

            throw e;
        }
    }

    public Project createProject(Project proj) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, proj, Project.class);
    }

    public Project updateProject(String id, Project proj) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        return transport.putJson(url, proj, Project.class);
    }

    public void deleteProject(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        transport.deleteJson(url);
    }

    public Collection<Long> getProjectMembers(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("members")
                .build();

        return transport.getJson(url, new TypeToken<List<Long>>(){}.getType());
    }

    public void setProjectMembers(String id, Collection<Long> userIds) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("members")
                .build();

        transport.putJson(url, userIds);
    }

    public List<ProjectList> getProjectLists(String projectId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .build();

        return transport.getJson(url, new TypeToken<List<ProjectList>>() {}.getType());
    }

    public List<ProjectList> getProjectLists(String projectId, int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<ProjectList>>() {}.getType());
    }

    public ProjectList getProjectList(String projectId, String listId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .build();

        return transport.getJson(url, new TypeToken<ProjectList>() {}.getType());
    }

    public ProjectList addProjectList(String projectId, ProjectList list) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .build();

        return transport.postJson(url, list, ProjectList.class);
    }

    public ProjectList updateProjectList(String projectId, String listId, ProjectList list) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .build();

        return transport.putJson(url, list, ProjectList.class);
    }

    public void deleteProjectList(String projectId, String listId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .build();

        transport.deleteJson(url);
    }

    public List<Task> getTasks(String projectId, int limit, int offset){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("tasks")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Task>>() {}.getType());
    }
    public List<Task> getTasks(String projectId, String listId, int limit, int offset){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Task>>() {}.getType());
    }

    public Task getTask(String projectId, String listId, String taskId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .build();

        return transport.getJson(url, new TypeToken<Task>() {}.getType());
    }

    public Task updateTask(String projectId, String listId, String taskId, Task task) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .build();

        return transport.putJson(url, task, Task.class);
    }

    public Task addTask(String projectId, String listId, Task task) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .build();

        return transport.postJson(url, task, Task.class);
    }

    public List<Attachment> getAttachments(String projectId, String listId, String taskId, int limit, int offset){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .addPathSegment("attachments")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Attachment>>() {}.getType());
    }

    public Attachment addAttachment(String projectId, String listId, String taskId, String filepath){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .addPathSegment("attachments")
                .build();

        return transport.uploadFile(url, filepath);
    }

    public InputStream downloadAttachment(String projectId, String listId, String taskId, String attachmentId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("lists")
                .addPathSegment(listId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .addPathSegment("attachments")
                .addPathSegment(attachmentId)
                .build();

        return transport.getFile(url);
    }

    public void deleteAttachment(String projectId, String taskId, String attachmentId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(projectId)
                .addPathSegment("tasks")
                .addPathSegment(taskId)
                .addPathSegment("attachments")
                .addPathSegment(attachmentId)
                .build();

        transport.deleteJson(url);
    }
}