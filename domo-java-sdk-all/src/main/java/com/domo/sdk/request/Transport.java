package com.domo.sdk.request;

import com.domo.sdk.tasks.model.Attachment;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class Transport {
    private final OkHttpClient httpClient;
    private final Gson gson;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType CSV
            = MediaType.parse("text/csv; charset=utf-8");


    public Transport(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    public <T> T getJson(HttpUrl url, Type type) {
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .build();

        try(Response response = httpClient.newCall(request).execute()) {
            if(response.code() > 399) {
                throw new RequestException(response.code(), "Error making request url:"+url.toString()+" reponseBody:"+response.body().source().readUtf8());
            }
            return gson.fromJson(response.body().charStream(), type);
        } catch (IOException e) {
            throw new RequestException("Error making request url:"+url.toString(), e);
        }
    }

    public void postJson(HttpUrl url, Object body) {
        postJson(url, body, null);
    }

    public <T> T postJson(HttpUrl url, Object body, Class<T> clazz) {
        return methodJson("POST", url, body, clazz);
    }

    public void putJson(HttpUrl url, Object body) {
        putJson(url, body, null);
    }

    public <T> T putJson(HttpUrl url, Object body, Class<T> clazz) { return methodJson("PUT", url, body, clazz); }

    public <T> T patchJson(HttpUrl url, Object body) {
        return methodJson("PATCH", url, body, null);
    }

    public <T> T patchJson(HttpUrl url, Object body, Class<T> clazz) {
        return methodJson("PATCH", url, body, clazz);
    }

    public <T> T methodJson(String method, HttpUrl url, Object body, Class<T> clazz) {
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(body));

        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .method(method, requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if(response.isSuccessful()) {
                if (clazz != null) {
                    return gson.fromJson(response.body().charStream(), clazz);
                } else {
                    return null;
                }
            } else {
                ErrorResponse err =  gson.fromJson(response.body().charStream(), ErrorResponse.class);
                throw new RequestException(err);
            }
        } catch (IOException e) {
            throw new RequestException("Error making request, url:"+url.toString(), e);
        }
    }

    public void deleteJson(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
        } catch (IOException e) {
            throw new RequestException("Error deleting, url:"+url.toString(), e);
        }
    }

    public InputStream getCsv(HttpUrl url) {
        Request request = new Request.Builder()
                .header("Accept", "text/csv")
                .url(url)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            if(!response.isSuccessful()) {
                throw new RequestException("Error making request url:"+url.toString()+" responseBody:"+response.body().source().readUtf8());
            }
            return response.body().byteStream();
        } catch (IOException e) {
            throw new RequestException("Error making request url:"+url.toString(), e);
        }
    }

    public InputStream postQuery(HttpUrl url, String query) {
        RequestBody requestBody = RequestBody.create(JSON, query);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            return new ByteArrayInputStream(response.body().bytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putCsv(HttpUrl url, File contents) {
        RequestBody requestBody = RequestBody.create(CSV, contents);
        putCsvInternal(url, requestBody);
    }

    public void putCsv(HttpUrl url, String contents) {
        RequestBody requestBody = RequestBody.create(CSV, contents);
        putCsvInternal(url, requestBody);
    }

    private void putCsvInternal(HttpUrl url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .header("Content-Type", "text/csv")
                .url(url)
                .put(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RequestException("Error uploading csv. url:"+url.toString()+" responseBody:"+response.body().source().readUtf8());
            }
        } catch (IOException e) {
            throw new RequestException("Error uploading csv. url:"+url.toString(), e);
        }

    }

    public InputStream getFile(HttpUrl url){
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return new ByteArrayInputStream(response.body().bytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Attachment uploadFile(HttpUrl url, String filePath){
        try {
            File file = new File(filePath);
            MediaType fileContentType = MediaType.parse(new MimetypesFileTypeMap().getContentType(file));
            MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(fileContentType, file)).build();

            Request request = new Request.Builder().url(url).post(multipartBody).build();
            Response response = httpClient.newCall(request).execute();
            return gson.fromJson(response.body().charStream(), Attachment.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
