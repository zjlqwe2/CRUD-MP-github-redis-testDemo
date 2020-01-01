//package com.example.demo.Utils;
//
//public class RedisSerialize {
//
//    ObjectOutputStream oos = null;
//
//    ByteArrayOutputStream baos = null;
//
//   try {
//
//        baos = new ByteArrayOutputStream();
//
//        oos = new ObjectOutputStream(baos);
//
//        oos.writeObject(object);
//
//        return baos.toByteArray();
//
//    }catch (Exception e) {
//
//        throw new CacheException(e);
//
//    }
//
//}
//    public Object unserialize(byte[] bytes) {
//
//        if (bytes == null) {
//
//            return null;
//
//        }
//
//        ByteArrayInputStreambais = null;
//
//        try {
//
//            bais = new ByteArrayInputStream(bytes);
//
//            ObjectInputStream ois = new ObjectInputStream(bais);
//
//            return ois.readObject();
//
//        }catch (Exception e) {
//
//            throw new CacheException(e);
//
//
//}