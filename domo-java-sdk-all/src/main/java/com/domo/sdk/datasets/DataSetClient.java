package com.domo.sdk.datasets;

import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.DataSetAndPDP;
import com.domo.sdk.datasets.model.DataSetListResult;
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

    /**
     DataSets
     - Programmatically manage Domo DataSets
     - Use DataSets for fairly static data sources that only require occasional updates via data replacement
     - Use Streams if your data source is massive, constantly changing, or rapidly growing
     - Docs: https://developer.domo.com/docs/data-apis/data
     */

    public DataSetClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     * Create a DataSet
     * @param dataSet the DataSet specifications
     * @return the created DataSet
     */
    public DataSet create(CreateDataSetRequest dataSet) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, dataSet, DataSet.class);
    }

    /**
     * Get a DataSet
     * @param id the DataSet id
     * @return the DataSet, if it exists
     */
    public DataSetAndPDP get(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        return transport.getJson(url, DataSetAndPDP.class);
    }

    /**
     * List DataSets
     * @param sort the DataSet field property by which to sort the list
     * @param limit the result set limit. The max is 50; use offset pagination to retrieve more DataSets
     * @param offset the result set offset for pagination purposes
     * @return a list of DataSets
     */
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

    /**
     * Update a DataSet
     * @param update the DataSet update
     * @return the updated DataSet
     */
    public DataSetAndPDP update(DataSet update) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(update.getId())
                .build();

        return transport.putJson(url, update, DataSetAndPDP.class);
    }

    /**
     * Delete a DataSet
     * @param id the DataSet id
     */
    public void delete(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .build();

        transport.deleteJson(url);
    }

    /**
     * Import data from a CSV string
     * @param id the DataSet id
     * @param contents the data contents in string CSV format
     */
    public void importData(String id, String contents) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("data")
                .build();

        transport.putCsv(url, contents);
    }

    /**
     * Import data from a CSV file
     * @param id the DataSet id
     * @param contents the data contents in CSV format, from a file
     */
    public void importData(String id, File contents) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("data")
                .build();

        transport.putCsv(url, contents);
    }

    /**
     * Export CSV data as an InputStream
     * @param id the DataSet id
     * @param includeHeader whether or not the CSV header row should be included
     * @return a Java InputStream for data retrieval
     */
    public InputStream exportData(String id, boolean includeHeader) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(id)
                .addPathSegment("data")
                .addQueryParameter("includeHeader", Boolean.toString(includeHeader))
                .build();

        return transport.getCsv(url);
    }

    /**
     * Export data to a CSV file
     * @param id the DataSet id
     * @param includeHeader whether or not the CSV header row should be included
     * @param file the file object to which the CSV data will be written
     */
    public void exportDataToFile( String id, boolean includeHeader, File file) {
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
