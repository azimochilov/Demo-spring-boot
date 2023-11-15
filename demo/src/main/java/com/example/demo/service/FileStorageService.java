package com.example.demo.service;

import com.example.demo.domain.FileStorage;
import com.example.demo.domain.FileStorageStatus;
import com.example.demo.repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    @Value("${upload.folder}")
    private String uploadFolder;
    private final Hashids hashids;


    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(),6);
    }

    public void save(MultipartFile multipartFile){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtansion(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepository.save(fileStorage);

        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d/", this.uploadFolder,now.getYear()+1900,1+ now.getMonth(),now.getDate()));
        if(!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("Files saved !!");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",now.getYear()+1900,1+ now.getMonth(),now.getDate(),
                fileStorage.getHashId(), fileStorage.getExtansion()));
        fileStorageRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s",fileStorage.getHashId(),fileStorage.getExtansion()));

        try {
            multipartFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public FileStorage getById(String hashId){
        return  fileStorageRepository.findByHashId(hashId);
    }

    public void delete(String hashId){
        FileStorage fileStorage = getById(hashId);
        File file = new File(String.format("%s/%s",
                this.uploadFolder,fileStorage.getUploadPath()));
        if(file.delete()){
            fileStorageRepository.delete(fileStorage);
        }
    }

    private String getExt(String fileName){
        String ext = null;
        if(fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if(dot > 0 && dot <= fileName.length()-2){
                ext = fileName.substring(dot+1);
            }
        }
        return ext;
    }
}
