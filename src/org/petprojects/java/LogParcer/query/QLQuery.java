package org.petprojects.java.LogParcer.query;

import java.util.Set;

public interface QLQuery {
    Set<Object> execute(String query);
}
