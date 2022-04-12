package ai.prama.statetransfer.api;

public interface StateTransferHandler {

    /**
     * Capture the current state of the thread.
     */
    void captureState();

    /**
     * Set the previously captured state into the new thread.
     */
    void setState();

    /**
     * Clear the now unneeded state and put the thread back at a clean start.
     */
    void cleanUpState();

}
