package com.petermassmann.Conditions;

import com.petermassmann.SQLManager;

public interface SQLConditionSet {

    String getConditionSetString(SQLManager manager);

    void addCondition(SQLCondition condition);

    /**
     * Check whether this condition set has any conditions in it.
     * @return
     */
    boolean isEmpty();
}
