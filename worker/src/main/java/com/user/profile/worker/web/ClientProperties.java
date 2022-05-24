package com.user.profile.worker.web;

import org.springframework.web.util.UriComponentsBuilder;
import lombok.Data;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 01/05/22
 */
@Data
public class ClientProperties {

    private String host;
    private String scheme;
    private Integer port;
    private String path;

    public String getUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path(path)
                .build()
                .toString();
    }

}
