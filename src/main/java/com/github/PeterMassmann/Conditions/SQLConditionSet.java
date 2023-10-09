package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;

public interface SQLConditionSet {

    String getConditionSetString(SQLManager manager);

    void addCondition(SQLCondition condition);

    /**
     * Check whether this condition set has any conditions in it.
     * @return
     */
    boolean isEmpty();
}
