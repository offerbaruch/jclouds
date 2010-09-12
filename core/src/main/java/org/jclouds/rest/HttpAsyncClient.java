/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.rest;

import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseETagHeader;
import org.jclouds.http.options.HttpRequestOptions;
import org.jclouds.io.Payload;
import org.jclouds.rest.annotations.EndpointParam;
import org.jclouds.rest.annotations.ExceptionParser;
import org.jclouds.rest.annotations.ResponseParser;
import org.jclouds.rest.functions.ReturnFalseOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Simple rest client
 * 
 * @author Adrian Cole
 */
public interface HttpAsyncClient {
   /**
    * @see HttpClient#post
    */
   @PUT
   @Path("")
   @ResponseParser(ParseETagHeader.class)
   ListenableFuture<String> put(@EndpointParam URI location, Payload payload);

   @PUT
   @Path("")
   @ResponseParser(ParseETagHeader.class)
   ListenableFuture<String> put(@EndpointParam URI location, Payload payload, HttpRequestOptions options);

   /**
    * @see HttpClient#post
    */
   @POST
   @Path("")
   @ResponseParser(ParseETagHeader.class)
   ListenableFuture<String> post(@EndpointParam URI location, Payload payload);

   @POST
   @Path("")
   @ResponseParser(ParseETagHeader.class)
   ListenableFuture<String> post(@EndpointParam URI location, Payload payload, HttpRequestOptions options);

   /**
    * @see HttpClient#exists
    */
   @HEAD
   @Path("")
   @ExceptionParser(ReturnFalseOnNotFoundOr404.class)
   ListenableFuture<Boolean> exists(@EndpointParam URI location);

   /**
    * @see HttpClient#get
    */
   @GET
   @Path("")
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<InputStream> get(@EndpointParam URI location);

   /**
    * @see HttpClient#get
    */
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<InputStream> get(HttpRequest location);

   @GET
   @Path("")
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<InputStream> get(@EndpointParam URI location, HttpRequestOptions options);

   /**
    * @see HttpClient#delete
    */
   @DELETE
   @Path("")
   @ExceptionParser(ReturnFalseOnNotFoundOr404.class)
   ListenableFuture<Boolean> delete(@EndpointParam URI location);

}
