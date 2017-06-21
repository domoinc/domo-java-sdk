package com.domo.sdk.streams;

import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.streams.model.Execution;
import com.domo.sdk.streams.model.Stream;
import com.domo.sdk.streams.model.StreamRequest;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

public class StreamClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE = "v1/streams";
    private static final Logger logger = LoggerFactory.getLogger(StreamClient.class);



    /**
     Streams
     - Programmatically manage Domo Streams
     - A Domo Stream is a specialized upload pipeline pointing to a single Domo DataSet
     - Use Streams for massive, constantly changing, or rapidly growing data sources
     - Streams support uploading data sources in parts, in parallel
     - Use plain DataSets for data sources that only require occasional updates via replacement
     - Usage: Create a Stream, create an Execution, upload data via the Execution, then 'commit' the Execution
     - Docs: https://developer.domo.com/docs/data-apis/data
     */

    public StreamClient( UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     * Create a Stream and DataSet
     * A Stream is an upload pipeline attached to the newly created DataSet
     * Currently, Streams cannot be applied to existing DataSets
     * Deleting a Stream does not delete the associated DataSet
     * @param streamRequest the specifications for the new Stream
     * @return the created Stream metadata
     */
    public Stream create( StreamRequest streamRequest) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, streamRequest, Stream.class);
    }

    /**
     * Get a Stream
     * @param streamId the Stream id
     * @return the Stream, if it exists
     */
    public Stream get( long streamId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addQueryParameter("fields", "all")
                .build();

        return transport.getJson(url, Stream.class);
    }

    /**
     * List Streams
     * @param limit the result set limit
     * @param offset the result set offset for pagination purposes
     * @return a list of all Streams
     */
    public List<Stream> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("fields", "all")
                .addQueryParameter("limit", String.valueOf(limit))
                .addQueryParameter("offset", String.valueOf(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Stream>>(){}.getType());
    }

    /**
     * Update a Stream's metadata
     * @param streamId the Stream id
     * @param update the Stream update
     * @return
     */
    public Stream update( long streamId, StreamRequest update) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .build();

        return transport.patchJson(url, update, Stream.class);
    }

    /**
     * Delete a Stream
     * Deleting a Stream does not delete the associated DataSet
     * @param streamId the Stream id
     */
    public void delete( long streamId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .build();

        transport.deleteJson(url);
    }
    
    /**
     * Search for Streams by property
     * @param property the property by which to search for Streams
     * @return
     */
    public List<Stream> search( String property){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment("search")
                .addQueryParameter("q", property)
                .addQueryParameter("fields", "all")
                .build();

        return transport.getJson(url, new TypeToken<List<Stream>>(){}.getType());
    }

    /**
     * Create a Stream Execution (begin a multi-part upload process for a single Domo DataSet)
     * A Stream Execution is an upload pipeline that supports uploading data in chunks, or parts
     * Create a new Execution each time you would like to update your Domo DataSet
     * Be sure to 'commit' the Execution once all data parts have been uploaded
     * Do not create multiple Executions on a single Stream
     * @param streamId the Stream id
     * @return the created Execution metadata
     */
    public Execution createExecution( long streamId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .build();

        return transport.postJson(url, null, Execution.class);
    }

    /**
     * Get a Stream Execution
     * @param streamId the Stream id
     * @param executionId the Execution id
     * @return the Execution metadata, if the Execution exists
     */
    public Execution getExecution( long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .build();

        return transport.getJson(url, Execution.class);
    }

    /**
     * List Stream Executions
     * @param streamId the Stream id
     * @param limit the result set limit
     * @param offset the result set offset for pagination purposes
     * @return a list of Executions for the given Stream
     */
    public List<Execution> listExecutions( long streamId, long limit, long offset){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addQueryParameter("limit", Long.toString(limit))
                .addQueryParameter("offset", Long.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Execution>>(){}.getType());
    }


    /**
     Upload a data part - String
     * Data sources should be broken into parts and uploaded in parallel
     * Parts should be a minimum of 25mb
     * @param streamId the Stream id
     * @param executionId the Execution id
     * @param partNum the data part number, or index. Call 'uploadDataPart()' concurrently to upload all parts in parallel.
     * @param contents the CSV string data part contents
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
     Upload a data part - String
     * Data sources should be broken into parts and uploaded in parallel
     * Parts should be a minimum of 25mb
     * @param streamId the Stream id
     * @param executionId the Execution id
     * @param partNum the data part number, or index. Call 'uploadDataPart()' concurrently to upload all parts in parallel.
     * @param contents the CSV file data part contents
     */
    public void uploadDataPart(long streamId, long executionId, long partNum, File contents){
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
     * Commit an Execution (finalize a multi-part upload process)
     * Finalize a multi-part upload process by committing the execution
     * The execution/upload/commit process is NOT atomic; this is a RESTful approach to multi-part uploading
     * @param streamId the Stream id
     * @param executionId the Execution id
     * @return the Execution metadata
     */
    public Execution commitExecution( long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("commit")
                .build();

        return transport.putJson(url, "", Execution.class);
    }

    /**
     * Abort an Execution on a given Stream
     * @param streamId the Stream id
     * @param executionId the Execution id
     * @return the Execution metadata
     */
    public Execution abortExecution( long streamId, long executionId){
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("abort")
                .build();

        return transport.putJson(url, "", Execution.class);
    }

    /**
     * Abort the current Execution on a given Stream
     * @param streamId the Stream id
     * @param executionId the Execution id
     */
    public void abortCurrentExecution(long streamId, long executionId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(streamId))
                .addPathSegment("executions")
                .addPathSegment(Long.toString(executionId))
                .addPathSegment("abort")
                .build();

        transport.putJson(url, "", String.class);
    }


    /**
     * Asynchronously compress and upload csv data to a Domo DataSet via Stream Execution
     * @param streamId the target DataSet's Stream id
     * @param csvFiles a list of csv files, preferrably compressed
     */
    public void compressAndUploadPartsAsync( long streamId, List<File> csvFiles){

        //Compress the incoming csv files in a temp folder
        File tempFolder = new File(csvFiles.get(0).getParent() + "/temp/");
        if (!tempFolder.exists()){
            tempFolder.mkdir();
        }
        List<File> compressedCsvFiles = toGzipFilesUTF8(csvFiles, tempFolder.getPath() + "/");

        // Create an Execution on the given Stream
        Execution execution = createExecution(streamId);

        // Create the asynchronous executor service and task collection
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Object>> uploadTasks = Collections.synchronizedList(new ArrayList<>());

        // For each row group, create a runnable upload task
        long partNum = 1;
        for (File compressedCsvFile : compressedCsvFiles){
            long myPartNum = partNum;
            Runnable partUpload = () -> uploadDataPart(streamId, execution.getId(), myPartNum, compressedCsvFile); // "uploadDataPart()" accepts csv strings, csv files, and compressed csv files
            uploadTasks.add(Executors.callable(partUpload));
            partNum++;
        }

        // Asynchronously execute all uploading tasks
        try {
            long start = System.currentTimeMillis();
            executorService.invokeAll(uploadTasks);
            long duration = System.currentTimeMillis() - start;
            logger.info("\n\n******* Parallel upload time: " + duration + "ms");
        }
        catch (Exception e){
            logger.error("Error uploading all data parts", e);
        }

        // Commit the Stream Execution to finalize the multi-part upload
        commitExecution(streamId, execution.getId());

        // Delete the temp folder
        if (tempFolder.exists()) {
            try {
                Files.delete(tempFolder.toPath());
            }
            catch (Exception e){
                logger.error("Error deleting the compressed csv temp folder");
            }
        }
    }
}
