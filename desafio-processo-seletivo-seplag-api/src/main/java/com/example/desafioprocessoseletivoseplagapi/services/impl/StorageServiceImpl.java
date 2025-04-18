package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.StorageException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.services.StorageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService, LayerDefinition {

    private final AmazonS3 s3Client;

    public StorageServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void upload(FotoDTO dto, String bucket, String key) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("filename", sanitize(dto.getFilename()));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(dto.getContentType());
            objectMetadata.setContentLength(dto.getContent().length);
            objectMetadata.setUserMetadata(map);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, new ByteArrayInputStream(dto.getContent()), objectMetadata);
            s3Client.putObject(putObjectRequest);
        } catch (AmazonServiceException e) {
            throw new StorageException("Erro ao salvar arquivo: " + e.getMessage(), this);
        }

    }

    @Override
    public FotoDTO download(String bucket, String key) throws IOException {
        try {
            ObjectMetadata objectMetadata = s3Client.getObject(new GetObjectRequest(bucket, key)).getObjectMetadata();
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
            byte[] content = s3Client.getObject(getObjectRequest).getObjectContent().readAllBytes();
            FotoDTO dto = new FotoDTO();
            dto.setContent(content);
            dto.setContentType(objectMetadata.getContentType());
            dto.setFilename(objectMetadata.getUserMetadata().get("filename"));
            return dto;
        } catch (AmazonServiceException e) {
            throw new StorageException("Erro ao recuperar arquivo: " + e.getMessage(), this);
        }

    }

    @Override
    public void delete(String bucket, String key) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, key);
        s3Client.deleteObject(deleteObjectRequest);
    }

    private String sanitize(String fileName) {
        try {
           return URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "arquivo";
        }
    }


    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
