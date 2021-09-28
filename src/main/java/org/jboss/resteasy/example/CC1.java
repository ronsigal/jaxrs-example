package org.jboss.resteasy.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//import org.jboss.test.resteasy.CC1_Server;

@Path("p")
public class CC1 {

//   private CC1_Server server;

   @Path("m1")
   @GET
   public String m1(CC2 cc2) {
      return "x";
   }

   String m2(String s) {
      return "x";
   }

   @Path("m3")
   @GET
   public String m3(CC4 cc4) {
      return "x";
   }

   @Path("m4")
   @GET
   public boolean m4(int i) {
      return true;
   }

   @Path("m5")
   @GET
   public String m5() {
      return "m5";
   }

   @Path("m6")
   @GET
   public CC4 m6(CC2 cc2) {
      CC5 cc5 = new CC5(cc2.j);
      System.out.println("cc2.s: " + cc2.s + ", cc5.k: " + cc5.k);
      return new CC4(cc2.s, cc5);
   }

   @Path("short")
   @GET
   public short getShort(short n) {
	   return short.class.cast(n + 1);
   }

   @Path("Short")
   @GET
   public Short getShortWrapper(Short n) {
	   return Short.valueOf((short) (n.shortValue() + 1));
   }

   @Path("int")
   @GET
   public int getInt(int n) {
	   return n + 1;
   }

   @Path("Integer")
   @GET
   public Integer getInteger(Integer n) {
	   return Integer.valueOf(n.intValue() + 1);
   }

   @Path("long")
   @GET
   public long getLong(long n) {
	   return n + 1;
   }

   @Path("Long")
   @GET
   public Long getLongWrapper(Long n) {
	   return Long.valueOf(n.longValue() + 1);
   }
}
