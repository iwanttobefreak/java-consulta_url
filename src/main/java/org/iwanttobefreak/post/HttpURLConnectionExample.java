package org.iwanttobefreak.post;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.net.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.*;
import org.apache.http.conn.scheme.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.*;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static String main() throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("\nTesting 2 - Send Http POST request");
		return http.sendPost();

	}

	// HTTP POST request
	private String sendPost() throws Exception {

		String url = "https://itecbanpre.wizink.net/scms-ESPA/services/rsAuthSearchService/authsearchservice/searchByQuery/";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Content-Type", "application/xml");

                //Petición JSON si tiene comillas, las trampeamos con \
		String urlParameters = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><httpBeanMessage>    <credential>        <password>DLKTT3HqFx7ew1zqPahENY1hy54CUxFm</password>        <username>WEB</username>    </credential>    <query>select * from Statement where statement:account_number in ('0004255499000006005','4255499000006005')</query></httpBeanMessage>";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
//		System.out.println(response.toString());
              return response.toString();

	}
}
