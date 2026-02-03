package com.cloudshop.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureBlobService {

    private final BlobContainerClient containerClient;
    private final String sas;

    public AzureBlobService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName,
            @Value("${azure.storage.sas}") String sas) {

        BlobServiceClient blobServiceClient =
                new BlobServiceClientBuilder()
                        .connectionString(connectionString)
                        .buildClient();

        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
        this.sas = sas;
    }

    public List<String> listImageUrls() {
        List<String> urls = new ArrayList<>();

        for (BlobItem blobItem : containerClient.listBlobs()) {
            // Build full Azure blob URL
            String blobUrl = containerClient
                    .getBlobClient(blobItem.getName())
                    .getBlobUrl();
            String decodedUrl = URLDecoder.decode(blobUrl, StandardCharsets.UTF_8);

            urls.add(decodedUrl +"?"+sas);
        }
        return urls;
    }
}



