package br.org.recode.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ClienteUploadImageWebService {

    private static final String baseUrl = "https://api.imgur.com";
    private static final HttpHeaders headers = new HttpHeaders();
    private static LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    private static HttpEntity<LinkedMultiValueMap<String, Object>> httpRequestEntity;
    private static ResponseUploadImageWebService responseUploadImageWebService;

    public static String UploadClienteImageAndGetLink(MultipartFile image, String clientId) throws IOException {

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", clientId);
        body.add("image", new MultipartInputStreamFileResource(image.getInputStream(), image.getOriginalFilename()));

        httpRequestEntity = new HttpEntity<>(body, headers);
        responseUploadImageWebService = new RestTemplate().postForObject(baseUrl+"/3/image",httpRequestEntity, ResponseUploadImageWebService.class);

        return responseUploadImageWebService.data.link;

    }

}
