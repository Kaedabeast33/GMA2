package org.example.ai.registry.parse;

public enum MetaParseFile {
    MAIN_PARSE_FILE("main_parse_file", new MainParseFileMethod());

    final String methodName;
    final ParseFileMethod parseFileMethod;

    MetaParseFile(String methodName, ParseFileMethod parseFileMethod) {
        this.methodName = methodName;
        this.parseFileMethod = parseFileMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public ParseFileMethod getParseFileMethod() {
        return parseFileMethod;
    }
}
