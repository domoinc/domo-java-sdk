package com.domo.sdk.streams;

import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.streams.model.StreamDataSet;
import com.domo.sdk.streams.model.StreamDataSetRequest;
import com.domo.sdk.streams.model.StreamExecution;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class StreamDataSetClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE ="v1/streams";

    public StreamDataSetClient( UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     * CREATE A STREAM DATASET
     * - Use Stream DataSets to import massive or rapidly changing datasources via multi-part uploads
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Create%20a%20Stream
     * @param streamRequest
     * @return the newly created Stream's metadata
     */
    public StreamDataSet createStreamDataset( StreamDataSetRequest streamRequest) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, streamRequest, StreamDataSet.class);
    }


    /**
     * GET A STREAM DATASET
     * - Get the metadata for a given Stream DataSet
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Retrieve%20a%20Stream
     */
    public StreamDataSet getStreamDataset(long streamId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addQueryParameter("fields", "all")
                .build();

        return transport.getJson(url, StreamDataSet.class);
    }


    /**
     * LIST ALL STREAM DATASETS
     * - Get a list of metadata for each of your Stream DataSets
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20List%20Streams
     */
    public List<StreamDataSet> listStreamDatasets() {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("fields", "all")
                .build();

        return transport.getJson(url, new TypeToken<List<StreamDataSet>>(){}.getType());
    }


    /**
     * UPDATE A STREAM DATASET
     * - Update the metadata for a given Stream DataSet
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Partially%20Update%20a%20Stream
     */
    public StreamDataSet updateStreamDataset( long streamId, StreamDataSetRequest update) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .build();

        return transport.patchJson(url, update, StreamDataSet.class);
    }


    /**
     * DELETE A STREAM DATASET
     * - Delete a stream dataset
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Delete%20a%20Stream
     */
    public void deleteStreamDataset(long streamId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .build();

        transport.deleteJson(url);
    }


    /**
     * SEARCH FOR STREAM DATASETS
     * - Search for a Stream DataSet by property
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Search%20Streams
     */
    public List<StreamDataSet> searchStreamDatasets( String property){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment("search")
                .addQueryParameter("q", property)
                .addQueryParameter("fields", "all")
                .build();

        return transport.getJson(url, new TypeToken<List<StreamDataSet>>(){}.getType());
    }


    /**
     * CREATE A STREAM EXECUTION
     * - Begin a multi-part upload by creating a Stream Execution
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Create%20a%20Stream%20Execution
     */
    public StreamExecution createStreamExecution(long streamId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .build();

        return transport.postJson(url, null, StreamExecution.class);
    }


    /**
     * GET A STREAM EXECUTION
     * - Get the metadata for a given stream execution
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Get%20a%20Stream%20Execution
     */
    public StreamExecution getStreamExecution(long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .build();

        return transport.getJson(url, StreamExecution.class);
    }


    /**
     * LIST ALL STREAM EXECUTIONS
     * - Get a list of metadata for each of your stream executions
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20List%20Stream%20Executions
     */
    public List<StreamExecution> listStreamExecutions(long streamId, long limit, long offset){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addQueryParameter("limit", Long.toString(limit))
                .addQueryParameter("offset", Long.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<StreamExecution>>(){}.getType());
    }


    /**
     * UPLOAD A DATA PART
     * - After creating a Stream Execution, upload your datasource in parts
     * - Each part should not exceed ??????? rows
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Upload%20a%20Part
     */
    public void uploadDataPart(long streamId, long executionId, long partNum, String contents){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("part")
                .addPathSegment(Long.toString(partNum))
                .build();

        //TODO modify putCsv to return http response
        transport.putCsv(url, contents);
    }


    /**
     * COMMIT A STREAM EXECUTION (this is not atomic)
     * - Commit an Execution to end the multi-part upload process
     * - The upload process is NOT atomic; it is a RESTful approach to uploading massive datasources
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Commit%20a%20Stream%20Execution
     */
    public StreamExecution commitStreamExecution(long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("commit")
                .build();

        return transport.putJson(url, "", StreamExecution.class);
    }


    /**
     * ABORT AN EXECUTION
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Abort%20a%20Stream%20Execution
     */
    public StreamExecution abortStreamExecution(long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("abort")
                .build();

        return transport.putJson(url, "", StreamExecution.class);
    }


    /**
     * ABORT THE CURRENT EXECUTION
     * - https://developer.domo.com/docs/domo-apis/stream-apis#API%20-%20Abort%20the%20Current%20Stream%20Execution
     */
    public void abortCurrentStreamExecution(long streamId, long executionId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("abort")
                .build();

        transport.putJson(url, "", String.class);
    }
}
