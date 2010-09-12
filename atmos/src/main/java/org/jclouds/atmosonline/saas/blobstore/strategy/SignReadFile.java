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

package org.jclouds.atmosonline.saas.blobstore.strategy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.atmosonline.saas.AtmosStorageAsyncClient;
import org.jclouds.blobstore.strategy.SignRequestForBlobStrategy;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpRequestFilter;
import org.jclouds.http.options.GetOptions;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.jclouds.rest.internal.RestAnnotationProcessor;

import com.google.common.collect.ImmutableMultimap;

/**
 * 
 * @author Adrian Cole
 */
@Singleton
public class SignReadFile implements SignRequestForBlobStrategy {
   private final RestAnnotationProcessor<AtmosStorageAsyncClient> processor;
   private final Method method;

   @Inject
   public SignReadFile(RestAnnotationProcessor<AtmosStorageAsyncClient> processor) throws SecurityException,
            NoSuchMethodException {
      this.processor = checkNotNull(processor, "processor");
      this.method = AtmosStorageAsyncClient.class.getMethod("readFile", String.class, GetOptions[].class);
   }

   @Override
   public HttpRequest apply(String container, String name) {
      GeneratedHttpRequest<AtmosStorageAsyncClient> returnVal = processor.createRequest(method, checkNotNull(container,
               "container")
               + "/" + checkNotNull(name, "name"));
      for (HttpRequestFilter filter : returnVal.getFilters())
         filter.filter(returnVal);
      return new HttpRequest(returnVal.getMethod(), returnVal.getEndpoint(), ImmutableMultimap.copyOf(returnVal
               .getHeaders()));
   }

}