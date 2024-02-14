package com.f1info.client;

import com.f1info.domain.api.f1Api.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseConverter {

    private final ObjectMapper objectMapper;

    public <T> ResponseDto<T> convertResponse(ResponseEntity<ResponseDto> response, Class<T> responseType) {
        ResponseDto<T> convertedResponse = new ResponseDto<>();
        List<LinkedHashMap<String, Object>> linkedHashMapList = response.getBody().getResponse();

        if (linkedHashMapList != null) {
            List<T> convertedList = linkedHashMapList.stream()
                    .map(linkedHashMap -> objectMapper.convertValue(linkedHashMap, responseType))
                    .collect(Collectors.toList());

            convertedResponse.setResponse(convertedList);
        }
        return convertedResponse;
    }
}
