package ai.prama.statetransfer.handlers;

import ai.prama.statetransfer.api.StateTransferHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AggregatedStateTransferHandler implements StateTransferHandler {
    private final List<StateTransferHandler> transferHandlers;

    public AggregatedStateTransferHandler(final List<StateTransferHandler> transferHandlers) {
        this.transferHandlers = transferHandlers;

        log.info("State Transfer Handlers List: {}", transferHandlers);
    }

    @Override
    public void captureState() {
        for (StateTransferHandler transferHandler : transferHandlers) {
            transferHandler.captureState();
        }
    }

    @Override
    public void setState() {
        for (StateTransferHandler transferHandler : transferHandlers) {
            transferHandler.setState();
        }
    }

    @Override
    public void cleanUpState() {
        for (StateTransferHandler transferHandler : transferHandlers) {
            transferHandler.cleanUpState();
        }
    }
}
