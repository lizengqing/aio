package com.http.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpUrlConnectionEample {
	public static void main(String[] args) throws Exception {
		doGetRequest();
		// doPostRequest();
		// doPostRequestBodyAsString();
	}

	public static void doGetRequest() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("fname", "JAVA PROGRAMMING");
		String postParams = getPostDataString(params);

		URL url = new URL("http://localhost:8080/Struts2DynamicImage/rest/helloGetParam/1?" + postParams);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setReadTimeout(10000);
		urlConnection.setConnectTimeout(15000);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);

		int responseCode = urlConnection.getResponseCode();
		String response = processResponse(responseCode, urlConnection.getInputStream());
		System.out.println("RESPONSE FROM SERVER :: " + response);
	}

	public static void doPostRequestBodyAsString() throws Exception {
		URL url = new URL("http://localhost:8080/Struts2DynamicImage/rest/helloPostAsBody/1");

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setReadTimeout(10000);
		urlConnection.setConnectTimeout(15000);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);

		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

		String bodyString = "{'name':'java'}";
		writer.write(bodyString);
		writer.flush();
		writer.close();
		os.close();

		int responseCode = urlConnection.getResponseCode();
		String response = processResponse(responseCode, urlConnection.getInputStream());
		System.out.println("RESPONSE FROM SERVER :: " + response);
	}

	public static void doPostRequest() throws Exception {
		// AS PARAM EXMAPLE
		URL url = new URL("http://localhost:8080/Struts2DynamicImage/rest/helloPostParam/1");

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setReadTimeout(10000);
		urlConnection.setConnectTimeout(15000);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);

		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

		Map<String, String> params = new HashMap<String, String>();
		params.put("fname", "JAVA PROGRAMMING");
		String postParams = getPostDataString(params);
		writer.write(postParams);
		writer.flush();
		writer.close();
		os.close();

		int responseCode = urlConnection.getResponseCode();
		String response = processResponse(responseCode, urlConnection.getInputStream());
		System.out.println("RESPONSE FROM SERVER :: " + response);
	}

	private static String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (first) {
				first = false;
			} else {
				result.append("&");
			}

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	private static String processResponse(int responseCode, InputStream inputStream) throws Exception {
		String response = "";
		if (responseCode == HttpsURLConnection.HTTP_OK) {
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				response += line;
			}
		} else {
			response = "";
		}

		return response;
	}
}
