package org.example.bank.db.contextObj.match;

import org.example.bank.db.contextObj.Rules;

public interface ContextInterface {
    String getName();
    String[] getTags();
    String[] getDescription();
    Rules getRules();
}
