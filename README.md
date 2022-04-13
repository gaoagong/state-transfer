# State Transfer Utility

The purpose of this library is to provide a way of transferring state between threads in a customizable/extendable way.

# Usage

Include the following in your maven `dependencies` section.

```xml
<dependency>
    <groupId>ai.prama.utils</groupId>
    <artifactId>state-transfer-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

Whenever you go off-thread, you can use the utility like so:

```java
@Service
public class OffThreadService {

    private final StateTransferHandlerFactoryHolder holder;

    public OffThreadService(StateTransferHandlerFactoryHolder holder) {
        this.holder = holder;
    }

    public void goOffThread() {
        final AggregatedStateTransferHandler handler = holder.createAggregatedHandler();
        handler.captureState();

        final Thread thread = new Thread(() -> {
            try {
                handler.setState();

                // do something that required the state to be transferred
            } finally {
                handler.cleanUpState();
            }

        });
        thread.start();
    }
}
```