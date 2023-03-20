/**
 * SecurityProperties.java — Configuration properties to enable or disable 
 * security, to provide CORs configuration as application properties, to 
 * configure OpenID OAuth 2.0 security server URLs.
 */
package com.truteq.authenticate.util;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "rest.security")
public class SecurityProperties {

  private boolean enabled;
  private String apiMatcher;
  private Cors cors;
  private String issuerUri;

  public CorsConfiguration getCorsConfiguration() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(cors.getAllowedOrigins());
    corsConfiguration.setAllowedMethods(cors.getAllowedMethods());
    corsConfiguration.setAllowedHeaders(cors.getAllowedHeaders());
    corsConfiguration.setExposedHeaders(cors.getExposedHeaders());
    corsConfiguration.setAllowCredentials(cors.getAllowCredentials());
    corsConfiguration.setMaxAge(cors.getMaxAge());

    return corsConfiguration;
  }

  @Getter
  @Setter
  public static class Cors {

    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;
  }

}
