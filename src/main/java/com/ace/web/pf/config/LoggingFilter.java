package com.ace.web.pf.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class LoggingFilter extends CommonsRequestLoggingFilter {

@Override
protected void beforeRequest(final HttpServletRequest request, final String message) {
    // DO something
    myAsyncMethodRequest(request, message);
}

@Override
protected void afterRequest(final HttpServletRequest request, final String message) {
    // Do something
   myAsyncMethodResponse(request, message);
}

// -----------------------------------------
// Async Methods
// -----------------------------------------

   @Async
   protected void myAsyncMethodRequest(HttpServletRequest request, String message) {

    // Do your thing
    // You can use message that has a raw message from the properties
   // defined in the logFilter() method. 
   // Also you can extract it from the HttpServletRequest using: 
   // IOUtils.toString(request.getReader());

   }

   @Async
   protected void myAsyncMethodResponse(HttpServletRequest request, String message) {

    // Do your thing
   }

}
