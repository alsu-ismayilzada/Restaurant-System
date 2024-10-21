package com.example.restaurantsystem.config;

import com.example.restaurantsystem.enums.OrderEvent;
import com.example.restaurantsystem.enums.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatemachineEngine {

//    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
//    private final StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister;
    private final StateMachineService<OrderState, OrderEvent> stateMachineService;


    public void sendMessage(Long orderId, OrderEvent event) {
        StateMachine<OrderState, OrderEvent> stateMachine = getStateMachineServiceForOrder(String.valueOf(orderId));
        stateMachine.getExtendedState().getVariables().put("ORDER_ID", orderId);
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .copyHeaders(Map.of("orderId", orderId))
                .build();
        stateMachine.sendEvent(Mono.just(message)).blockLast();
    }

    private synchronized StateMachine<OrderState, OrderEvent> getStateMachineServiceForOrder(String machineId) {
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineService.acquireStateMachine(machineId);
        stateMachine.startReactively().block();
        return stateMachine;
    }
}
