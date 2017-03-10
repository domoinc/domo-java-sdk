package com.domo.sdk.datasets;

import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.DataSetAndPDP;
import com.domo.sdk.datasets.model.DataSetListResult;
import com.domo.sdk.groups.model.Group;
import com.domo.sdk.request.RequestException;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class DataSetClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE = "v1/datasets";

    public DataSetClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }


    public DataSet create(CreateDataSetRequest dataSet) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, dataSet, DataSet.class);
    }


    public DataSetAndPDP get(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        return transport.getJson(url, DataSetAndPDP.class);
    }


    public List<DataSetListResult> list(String sort, int limit, int offset) {
        HttpUrl.Builder builder = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset));

        if (sort != null) {
            builder.addQueryParameter("sort", sort);
        }
        HttpUrl url = builder.build();

        return transport.getJson(url, new TypeToken<List<DataSetListResult>>() {
        }.getType());
    }

    public List<DataSetListResult> list(int limit, int offset) {
        return list(null, limit, offset);
    }


    public DataSetAndPDP update(DataSet request) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(request.getId())
                .build();

        return transport.putJson(url, request, DataSetAndPDP.class);
    }

    public void delete(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        transport.deleteJson(url);
    }


    public void importData(String id, InputStream contents) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("data")
                .build();

        transport.putCsv(url, contents);
    }


    public InputStream exportData(String id, boolean includeHeader) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("data")
                .addQueryParameter("includeHeader", Boolean.toString(includeHeader))
                .build();

        return transport.getCsv(url);
    }

    public void exportData(String id, boolean includeHeader, File file) {
        BufferedInputStream input = new BufferedInputStream(exportData(id, includeHeader));
        try {
            OutputStream output = new FileOutputStream(file);

            byte[] data = new byte[1024];

            int count = 0;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            throw new RequestException("Error exporting data and writing to file:"+file, e);
        }

    }


}
