package klapertart.lab.curl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

/**
 * @author kurakuraninja
 * @since 14/03/2023
 */

@SpringBootTest
public class EncodeUrlTest {

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

    @Test
    public void givenRequestParam_whenUTF8Scheme_thenEncode(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("query", "source:DESKTOP-S9I46UA");
        requestParams.put("limit", "2");
        requestParams.put("range", "3600");
        requestParams.put("decorate", "true");

        String encodedURL = requestParams.keySet().stream()
                .map(key -> {
                    try {
                        return key + "=" + encodeValue(requestParams.get(key));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(joining("&", "http://localhost:9000/api/search/universal/relative?", ""));


        System.out.println(encodedURL);

    }
}
