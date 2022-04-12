package ai.prama.statetransfer.handlers;

import ai.prama.statetransfer.api.StateTransferHandler;
import org.slf4j.MDC;

import java.util.Map;

public class MdcStateTransferHandler implements StateTransferHandler {
    private Map mdcState;

    @Override
    public void captureState() {
        mdcState = MDC.getCopyOfContextMap();
    }

    @Override
    public void setState() {
        MDC.setContextMap(mdcState);
    }

    @Override
    public void cleanUpState() {
        MDC.clear();
    }
}
