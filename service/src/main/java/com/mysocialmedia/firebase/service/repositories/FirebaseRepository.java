package com.mysocialmedia.firebase.service.repositories;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.mysocialmedia.firebase.service.models.dtos.FirebaseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Repository
public class FirebaseRepository {

    @Value("${bucket.name}")
    private String bucketname;

    @Value("${folder.name}")
    private String foldername;

    private Storage getStorage() {
        return StorageClient.getInstance().bucket().getStorage();
    }

    public FirebaseDto save(MultipartFile file) throws IOException {
        String name = UUID.randomUUID() + file.getOriginalFilename();
        String filename = foldername + "/" + name;
        Storage storage = getStorage();
        BlobId blobId = BlobId.of(bucketname, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        String urlImage =
                "https://firebasestorage.googleapis.com/v0/b/"
                        + bucketname +
                        "/o/"
                        + filename.replace("/", "%2F") + "?alt=media";
        return FirebaseDto.builder().filename(name).urlImage(urlImage).build();
    }

    public void deleteImage(String name){
        String filename = foldername + "/" + name;
        Storage storage = getStorage();
        BlobId blobId = BlobId.of(bucketname, filename);
        Blob blob = storage.get(blobId);

        if(blob!= null){
            blob.delete();
        }
    }
}


