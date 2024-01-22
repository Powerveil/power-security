package com.power.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Powerveil
 * @Date 2024/1/22 15:51
 */
public class MockServer {

    private static String fileFolder = "mock/response/";

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();

        String path = "/order/2";
        String filePath = "02.txt";


        extracted(path, filePath);

    }

    private static void extracted(String path, String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileFolder + filePath);

        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), StandardCharsets.UTF_8), "\n");

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
