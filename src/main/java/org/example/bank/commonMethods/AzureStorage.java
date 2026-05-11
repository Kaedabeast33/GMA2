package org.example.bank.commonMethods;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.example.bank.AppConfig.getAzureConnectionString;

public class AzureStorage {
    public void uploadReport(File file, String blobName, String container) throws IOException {



        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(getAzureConnectionString())
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);






        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try (FileInputStream fis = new FileInputStream(file)) {

            blobClient.upload(fis, file.length(), true);

            String contentType = Files.probeContentType(file.toPath());
            if (contentType != null) {
                blobClient.setHttpHeaders(
                        new BlobHttpHeaders().setContentType(contentType)
                );
            }
        }

        System.out.println("Uploaded: " + blobName);
    }
}
