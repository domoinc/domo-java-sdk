package com.domo.sdk.pages;

import com.domo.sdk.DomoClient;
import com.domo.sdk.pages.model.Page;
import com.domo.sdk.pages.model.PageCollection;
import com.domo.sdk.pages.model.PageSummary;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * <p>Threadsafe client for performing User related requests. See <a href="https://developer.domo.com/docs/domo-apis/users">
 *     User API</a> docs for complete details.
 * </p>
 * <p><b>Use {@link DomoClient#userClient()} to get a reference to an instance of this client.</b></p>
 *
 * Usage:
 * <pre>
 * {@code Client client = Client.create("<your app id>","<your app secret>");
 *  client.userClient().list(10,0);
 * }
 * </pre>
 */
public class PageClient {
    private static final String PAGES_URL = "v1/pages";
    private final UrlBuilder urlBuilder;
    private final Transport transport;

    /**
     * Avoid using this constructor directly.
     *
     * @param urlBuilder Config of base URL for requests
     * @param transport Helper for Request and Response processing
     */
    public PageClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20List%20Pages">List Pages</a> Api Docs
     *
     * @return List of Page Summaries
     */
    public List<PageSummary> list() {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL).build();

        return transport.getJson(url, new TypeToken<List<PageSummary>>(){}.getType());
    }

    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Create%20a%20Page">Create Page</a> Api Docs
     *
     * @param newPage page to create
     * @return Page that was created, including page ID
     */
    public Page create(Page newPage) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL).build();

        return transport.postJson(url, newPage, Page.class);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Get%20a%20Page">Get Page</a> Api Docs
     *
     * @param pageId id of page to fetch
     * @return Page that was found
     */
    public Page get(long pageId) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .build();

        return transport.getJson(url, Page.class);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Update%20a%20Page">Update Page</a> Api Docs
     *
     * @param page the page to update
     */
    public void update(Page page) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(page.getId()))
                .build();

        transport.putJson(url, page);
    }

    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Delete%20a%20Page">Delete Page</a> API Docs
     *
     * @param pageId id of page to delete
     */
    public void delete(long pageId) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .build();

        transport.deleteJson(url);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Get%20Collections">Get Collections</a> Api Docs
     *
     * @param pageId id of page to get collections from
     * @return List of Collections
     */
    public List<PageCollection> getCollections(long pageId) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .addPathSegment("collections")
                .build();

        return transport.getJson(url, new TypeToken<List<PageCollection>>(){}.getType());
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Create%20a%20Collection">Create Collection</a> Api Docs
     *
     * @param pageId id of page to create a collection on
     * @return PageCollection that was created, including collection ID
     */
    public PageCollection createCollection(long pageId, PageCollection newCollection) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .addPathSegment("collections")
                .build();

        return transport.postJson(url, newCollection, PageCollection.class);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Update%20a%20Collection">Update Collection</a> Api Docs
     *
     * @param pageId id of page to update a collection of
     * @param collection the collection to update
     */
    public void updateCollection(long pageId, PageCollection collection) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .addPathSegment("collections")
                .addPathSegment(Long.toString(collection.getId()))
                .build();

        transport.putJson(url, collection);
    }

    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/pages#API%20-%20Delete%20a%20Collection">Delete Collection</a> API Docs
     *
     * @param pageId id of page to delete a collection from
     * @param collectionId id of collection to delete
     */
    public void deleteCollection(long pageId, long collectionId) {
        HttpUrl url = urlBuilder.fromPathSegments(PAGES_URL)
                .addPathSegment(Long.toString(pageId))
                .addPathSegment("collections")
                .addPathSegment(Long.toString(collectionId))
                .build();

        transport.deleteJson(url);
    }
}
