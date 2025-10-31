package org.example.Annotations;

public @interface KdbDependency {
    String[] referenceColumns()default{};
}
