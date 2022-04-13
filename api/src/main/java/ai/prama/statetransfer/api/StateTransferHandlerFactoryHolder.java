package ai.prama.statetransfer.api;

import ai.prama.statetransfer.handlers.AggregatedStateTransferHandler;

import java.util.List;

/**
 * Holds the list of wired transfer handlers.
 */
public interface StateTransferHandlerFactoryHolder {

    void addFactory(String factoryBeanName, StateTransferHandlerFactory factory);
    void removeFactory(String factoryBeanName);

    List<StateTransferHandlerFactory> getFactories();

    List<StateTransferHandler> createNewInstances();

    AggregatedStateTransferHandler createAggregatedHandler();

}
