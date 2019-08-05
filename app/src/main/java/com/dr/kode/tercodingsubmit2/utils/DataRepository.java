package com.dr.kode.tercodingsubmit2.utils;

public class DataRepository {
    public static <T extends IMDBCall> T of(Class<T> klass) {
        try {
            return klass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
