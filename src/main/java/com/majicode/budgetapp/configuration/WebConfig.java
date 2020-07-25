package com.majicode.budgetapp.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * This class will redirect all traffic from http://localhost:8081 to https://localhost:8443
 * Need to change the port defined in the application.yml to 8443 to work
 * 
 * @author MikeyJudd
 *
 */

//@Configuration
public class WebConfig {

	@Bean
	public ServletWebServerFactory servletContainer() {
		final TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

			@Override
			protected void postProcessContext(Context context) {
				final SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");

				final SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(redirectConnector());
		return tomcat;
	}

	private Connector redirectConnector() {
		final Connector connector = new Connector("org.apache.coyote.http2.Http2Protocol");
		connector.setScheme("http");
		connector.setPort(8081);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
}
