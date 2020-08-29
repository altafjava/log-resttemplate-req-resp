package com.altafjava.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import com.altafjava.constant.AppConstant;
import lombok.extern.log4j.Log4j2;

/**
 * @author altaf
 *
 */
@Log4j2
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		response = traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		log.info("=========================== Request Begin ===========================");
		log.info("URI          : " + request.getURI());
		log.info("Method       : " + request.getMethod());
		log.info("Headers      : " + request.getHeaders());
		log.info("Body : " + new String(body, AppConstant.UTF8));
		log.info("============================ Request End ============================");
	}

	private ClientHttpResponse traceResponse(ClientHttpResponse response) throws IOException {
		ClientHttpResponse newCopiedResponse = new BufferingClientHttpResponseWrapper(response);
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(newCopiedResponse.getBody(), "UTF-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			line = bufferedReader.readLine();
		}
		log.info("=========================== Response Begin ===========================");
		log.info("Status code   : {}", response.getStatusCode());
		log.info("Status text   : {}", response.getStatusText());
		log.info("Headers       : {}", response.getHeaders());
		log.info("Response Body : {}", inputStringBuilder.toString());
		log.info("============================ Response End ============================");
		return newCopiedResponse;
	}

}