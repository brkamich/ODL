package org.elasticsearch.plugin.extractor;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.*;
import org.elasticsearch.common.inject.Inject;

import java.io.IOException;

import static org.elasticsearch.rest.RestRequest.Method.GET;

public class ExtractorRestHandler extends BaseRestHandler {
    private final String NAME = "_extract_metadata";
    @Inject
    public ExtractorRestHandler(Settings settings, RestController restController) {
        super();
        restController.registerHandler(RestRequest.Method.GET, "/"+NAME, this);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        return channel -> {
            XContentBuilder builder = channel.newBuilder();
            builder.startObject().field("message", "Hello from extractor!").endObject();
            channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
        };
    }
}