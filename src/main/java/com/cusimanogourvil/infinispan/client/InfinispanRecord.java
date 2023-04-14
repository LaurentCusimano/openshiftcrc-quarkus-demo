package com.cusimanogourvil.infinispan.client;

import java.util.Objects;

public class InfinispanRecord {

    private String name;
    private String content;

    public InfinispanRecord() {
    }

    public InfinispanRecord(String name, String content) {
        this.name = Objects.requireNonNull(name);
        this.content = Objects.requireNonNull(content);
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
