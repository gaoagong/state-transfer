package ai.prama.statetransfer.api;

@FunctionalInterface
public interface StateTransferHandlerFactory<H extends StateTransferHandler> {

    H createInstance();

}
