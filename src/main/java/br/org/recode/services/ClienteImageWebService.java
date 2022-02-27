package br.org.recode.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpMethod;

import java.io.IOException;

@Service
public class ClienteImageWebService {

    private static final String baseUrl = "https://api.imgur.com";
    private static final HttpHeaders headers = new HttpHeaders();
    private static LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    private static HttpEntity<LinkedMultiValueMap<String, Object>> httpRequestEntity;
    private static ResponseImageWebService responseImageWebService;

    public static String[] uploadImageAndGetData(MultipartFile image, String clientId) throws IOException {

        String[] imageData = new String[2];

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", clientId);
        body.add("image", new MultipartInputStreamFileResource(image.getInputStream(), image.getOriginalFilename()));

        httpRequestEntity = new HttpEntity<>(body, headers);
        responseImageWebService = new RestTemplate().postForObject(baseUrl+"/3/image",httpRequestEntity, ResponseImageWebService.class);

        System.out.println(responseImageWebService);

        imageData[0] = responseImageWebService.data.link;
        imageData[1] = responseImageWebService.data.deletehash;

        return imageData;

    }


    public static String deleteImage(String deleteHash, String clientId) {
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", clientId);

        try {
            httpRequestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = new RestTemplate()
                    .exchange(
                            baseUrl+"/3/image/"+deleteHash,
                            HttpMethod.DELETE,
                            httpRequestEntity,
                            String.class
                    );

            System.out.println(response);
            return (response.toString());

        } catch (RestClientException e) {
            System.out.println("Error. " + e.getMessage());
            return "Error";
        }



    }

}
