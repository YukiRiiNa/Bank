package me.dablakbandit.bank.config.path;

import java.util.Map;

public interface BankExtendedPath {

    void setExtendedValues(Map<String, Object> extendedValues);

    <T> T getExtendValue(String key, Class<T> clazz);
}
