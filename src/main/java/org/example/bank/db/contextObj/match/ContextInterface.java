package org.example.bank.db.contextObj.match;

import org.example.bank.db.contextObj.Rules;

import java.util.Map;

public interface ContextInterface {
    String getName();
    String[] getTags();
    String[] getDescription();
    Rules getRules();
}
