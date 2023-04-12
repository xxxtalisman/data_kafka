package com.emdata.common.util.sf;

import lombok.extern.slf4j.Slf4j;

import static com.emdata.common.util.sf.DefaultKeyGenerator.setWorkerId;


/**
 * Key generator interface.
 *
 * @author snow
 */
@Slf4j
public class SnowFlaskInstance {
    private static SnowFlaskInstance ourInstance = new SnowFlaskInstance();
    private KeyGenerator keyGenerator = null;

    private SnowFlaskInstance() {
        if (null == keyGenerator) {
            create();
        }
    }

    public static SnowFlaskInstance getInstance() {
        return ourInstance;
    }

    private synchronized void create() {
        if (null == keyGenerator) {
            setWorkerId(2);
            keyGenerator = new DefaultKeyGenerator();
        }
    }

    public KeyGenerator GetKeyGenerator() {
        return keyGenerator;
    }
}
