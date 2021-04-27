package org.onosproject.floodlightpof.sp.protocol;


public enum SPModType {
	
	ENTRY_ADD    (0),
    ENTRY_UPDATE     (1),
    ENTRY_DEL	 (2);
	
    protected int value;

    SPModType(int value) {
        this.value = value;
    }

    /**
     * @return the
     * value
     */
    public int getValue() {
        return value;
    }
	
}
