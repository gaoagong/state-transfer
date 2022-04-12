package ai.prama.statetransfer.api;

import java.util.List;

/**
 * Holds the list of wired transfer handlers.
 */
public interface StateTransferHandlerFactoryHolder {

    void addStateTransferHandlerFactory(String factoryBeanName, StateTransferHandlerFactory factory);
    void removeStateTransferHandlerFactory(String factoryBeanName);

    List<StateTransferHandlerFactory> getStateTransferHandlerFactories();

    List<StateTransferHandler> createNewInstances();

}
