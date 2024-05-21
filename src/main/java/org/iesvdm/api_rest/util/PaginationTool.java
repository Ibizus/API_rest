package org.iesvdm.api_rest.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PaginationTool {

    /**
     * Receives a whole Page entity and the name we want to give to content.
     * Then creates a HasMap with the Content, Current page,
     * Total items and Total pages.
     * @param page
     * @param contentName
     * @return Response HashMap<String, Object>
     */
    public static Map<String, Object> createPaginatedResponseMap(Page page, String contentName){

        Map<String, Object> response = new HashMap<>();
        response.put(contentName, page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }
}
