package ai.prama.statetransfer.config;

import ai.prama.statetransfer.api.StateTransferHandler;
import ai.prama.statetransfer.api.StateTransferHandlerFactory;
import ai.prama.statetransfer.api.StateTransferHandlerFactoryHolder;
import ai.prama.statetransfer.handlers.AggregatedStateTransferHandler;
import ai.prama.statetransfer.handlers.MdcStateTransferHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "feature.statetransfer", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StateTransferConfig {

    @Bean
    @ConditionalOnMissingBean(name = "mdcStateTransferHandlerFactory")
    public StateTransferHandlerFactory mdcStateTransferHandlerFactory() {
        return MdcStateTransferHandler::new;
    }

    @Bean
    @ConditionalOnMissingBean(name = "defaultStateTransferHandlerFactoryHolder")
    public StateTransferHandlerFactoryHolder stateTransferHandlerFactoryHolder(
            Map<String, StateTransferHandlerFactory> transferHandlerFactories) {
        return new StateTransferHandlerFactoryHolder() {
            @Override
            public void addFactory(String factoryBeanName, StateTransferHandlerFactory factory) {
                transferHandlerFactories.put(factoryBeanName, factory);
            }

            @Override
            public void removeFactory(String factoryBeanName) {
                transferHandlerFactories.remove(factoryBeanName);
            }

            @Override
            public List<StateTransferHandlerFactory> getFactories() {
                return new ArrayList<>(transferHandlerFactories.values());
            }

            @Override
            public List<StateTransferHandler> createNewInstances() {
                List<StateTransferHandler> transferHandlers = new LinkedList<>();
                for (StateTransferHandlerFactory transferHandlerFactory : transferHandlerFactories.values()) {
                    transferHandlers.add(transferHandlerFactory.createInstance());
                }

                return transferHandlers;
            }

            @Override
            public AggregatedStateTransferHandler createAggregatedHandler() {
                return new AggregatedStateTransferHandler(
                    createNewInstances()
                );
            }
        };
    }

}
