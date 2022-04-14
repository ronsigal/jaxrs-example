package org.jboss.resteasy.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

//import org.jboss.test.resteasy.CC1_Server;

@Path("p")
public class CC1 {

//   private CC1_Server server;

   @Path("m1")
   @POST
   public String m1(CC2 cc2) {
      return "x";
   }

   String m2(String s) {
      return "x";
   }

   @Path("m3")
   @POST
   public String m3(CC4 cc4) {
      return "x";
   }

   @Path("m4")
   @POST
   public boolean m4(int i) {
      return true;
   }

   @Path("m5")
   @GET
   public String m5() {
      return "m5";
   }

   @Path("m6")
   @POST
   public CC4 m6(CC2 cc2) {
      CC5 cc5 = new CC5(cc2.j);
      System.out.println("cc2.s: " + cc2.s + ", cc5.k: " + cc5.k);
      return new CC4(cc2.s, cc5);
   }

   @Path("m7")
   @POST
   public CC6 m7(int i) {
      CC7 cc7 = new CC7("m7", i + 1);
      CC6 cc6 = new CC6(i + 2, cc7);
      return cc6;
   }

   @Path("boolean")
   @POST
   public boolean getBoolean(boolean b) {
      return !b;
   }

   @Path("Boolean")
   @POST
   public Boolean getBooleanWrapper(Boolean b) {
      return Boolean.valueOf(!b);
   }

   @Path("byte")
   @POST
   public byte getByte(byte b) {
      int i = b + 1;
      return (byte) i;
   }

   @Path("Byte")
   @POST
   public Byte getByteWrapper(Byte b) {
      return Byte.valueOf((byte) (b.byteValue() + 1));
   }
   
   @Path("short")
   @POST
   public short getShort(short n) {
      int i = n + 1;
      return (short) i;
   }

   @Path("Short")
   @POST
   public Short getShortWrapper(Short n) {
      return Short.valueOf((short) (n.shortValue() + 1));
   }

   @Path("int")
   @POST
   public int getInt(int n) {
      return n + 1;
   }

   @Path("Integer")
   @POST
   public Integer getInteger(Integer n) {
      return Integer.valueOf(n.intValue() + 1);
   }

   @Path("long")
   @POST
   public long getLong(long n) {
      return n + 1;
   }

   @Path("Long")
   @POST
   public Long getLongWrapper(Long n) {
      return Long.valueOf(n.longValue() + 1);
   }

   @Path("float")
   @POST
   public float getFloat(float f) {
      return Float.valueOf((float) (f + 1.0f));
   }

   @Path("Float")
   @POST
   public Float getFloatWrapper(Float f) {
      return Float.valueOf((float) (f.floatValue() + 1.0f));
   }

   @Path("double")
   @POST
   public double getDouble(double d) {
      return Double.valueOf((double) (d + 1.0d));
   }

   @Path("Double")
   @POST
   public Double getDoubleWrapper(Double d) {
      return Double.valueOf((double) (d.floatValue() + 1.0d));
   }

   @Path("char")
   @POST
   public char getChar(char c) {
      return Character.toUpperCase(c);
   }

   @Path("Character")
   @POST
   public Character getCharacter(Character c) {
      return Character.toUpperCase(c);
   }

   @Path("string")
   @POST
   public String getString(String s) {
      return s.toUpperCase();
   }

   @Path("response")
   @GET
   public Response getResponse() {
      CC7 cc7 = new CC7("cc7", 11);
      return Response.ok(cc7).build();
   }
/*
   @Path("response/async")
   @GET
   public void getResponseAsync(@Suspended final AsyncResponse response) {
      Thread t = new Thread() {
         @Override
         public void run()
         {
            try
            {
               CC7 cc7 = new CC7("cc7", 11);
               response.resume(Response.ok(cc7).build());
            }
            catch (Exception e)
            {
               response.resume(e);
            }
         }
      };
      t.start();
   }
*/ 
   @Path("async/cs")
   @GET
   public CompletionStage<String> getResponseCompletionStage() {
      final CompletableFuture<String> response = new CompletableFuture<>();
      Thread t = new Thread() {
         @Override
         public void run() {
            try {
               response.complete("cs");
            } catch (Exception e) {
               response.completeExceptionally(e);
            }
         }
      };
      t.start();
      return response;
   }

   @Path("cc7")
   @GET
   public CC7 getCC7() {
      CC7 cc7 = new CC7("cc7", 11);
      return cc7;
   }
   
   @Path("produces")
   @Produces("abc/xyz")
   @GET
   public String produces() {
      return "produces";
   }

   @Path("consumes")
   @Consumes("xyz/abc")
   @GET
   public String consumes() {
	   return "consumes";
   }

   @Path("path/{p1}/param/{p2}")
   @GET
   public String pathParams(@PathParam("p1") String p1, @PathParam("p2") String p2) {
      return "x" + p1 + "y" + p2 + "z";
   }

   @Path("query")
   @GET
   public String queryParams(@QueryParam("q1") String q1, @QueryParam("q2") String q2) {
      return "x" + q1 + "y" + q2 + "z";
   }

   @Path("matrix/more")
   @GET
   public String matrixParams(@MatrixParam("m1") String m1, @MatrixParam("m2") String m2, @MatrixParam("m3") String m3) {
      return "w" + m1 + "x" + m2 + "y" + m3 + "z";
   }

   @Path("cookieParams")
   @GET
   public String cookieParams(@CookieParam("c1") Cookie c1, @CookieParam("c2") Cookie c2) {
      return "x" + cookieStringify(c1) + "y" + cookieStringify(c2) + "z";
   }
   
   private String cookieStringify(Cookie c) {
      StringBuilder sb = new StringBuilder();
      sb.append(c.getName())
        .append("=")
        .append(c.getValue())
        .append(";")
        .append(c.getDomain()).append(",")
        .append(c.getPath()).append(",")
        .append(c.getVersion());
      return sb.toString();
   }

   @Path("headerParams")
   @GET
   public String headerParams(@HeaderParam("h1") String h1, @HeaderParam("h2") String h2) {
      return "x" + h1 + "y" + h2 + "z";
   }
   
   @GET
   @Path("suspend")
   public void suspend(@Suspended final AsyncResponse response) //throws Exception
   {
      Thread t = new Thread()
      {
         @Override
         public void run()
         {
            try
            {
               Response jaxrs = Response.ok("suspend").build();
               response.resume(jaxrs);
            }
            catch (Exception e)
            {
               response.resume(e);
            }
         }
      };
      t.start();
   }
   
   @GET
   @Path("context")
   public String context(@Context HttpServletRequest request) {
      String contextPath = request.getServletContext().getContextPath();
      return contextPath;
   }
   
//   @GET
//   @Path("sse")
//   @Produces(MediaType.SERVER_SENT_EVENTS)
//   public void sse(@Context SseEventSink eventSink, @Context Sse sse) {
//      ExecutorService executor = Executors.newFixedThreadPool(3);
//      executor.execute(() -> {
//         try (SseEventSink sink = eventSink) {
//            System.out.println("sending event1");
//            eventSink.send(sse.newEvent("event1"));
//            System.out.println("sent event1");
//            System.out.println("sending event2");
//            eventSink.send(sse.newEvent("event2"));
//            System.out.println("sent event2");
//            System.out.println("sending event3");
//            eventSink.send(sse.newEvent("event3"));
//            System.out.println("send event3");
//         }
//      });
//   }
}
