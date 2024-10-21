package com.example.restaurantsystem.config;

import com.example.restaurantsystem.enums.OrderEvent;
import com.example.restaurantsystem.enums.OrderState;
import com.example.restaurantsystem.entity.Order;
import com.example.restaurantsystem.entity.OrderStatusForOrder;
import com.example.restaurantsystem.repository.OrderRepository;
import com.example.restaurantsystem.repository.OrderStatusForOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Configuration
@Slf4j
public class StatemachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Configuration
    public static class JpaPersisterConfigForDonor {
        @Bean
        public StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersisterForDonor(JpaStateMachineRepository jpaStateMachineRepository) {
            return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
        }
    }

    @Configuration
    @EnableStateMachineFactory()
    @RequiredArgsConstructor
    public static class MachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

        private final StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister;

        private final OrderRepository orderRepository;
        private final OrderStatusForOrderRepository orderStatusForOrderRepository;

        @Override
        public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
            config
                    .withConfiguration()
                    .listener(new StateMachineListenerAdapter<>())
                    .and()
                    .withPersistence()
                    .runtimePersister(stateMachineRuntimePersister);
        }

        @Override
        public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
            states
                    .withStates()
                    .initial(OrderState.CREATED)  // Vəziyyətlərin başlanğıcını ikinci koda uyğunlaşdırırıq
                    .states(EnumSet.allOf(OrderState.class))
                    .end(OrderState.DELIVERED)
                    .end(OrderState.CANCELLED);
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
            transitions
                    .withInternal()
                    .source(OrderState.CREATED)
                    .event(OrderEvent.CREATE)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository))

                    .and()
                    .withExternal()
                    .source(OrderState.CREATED).target(OrderState.PROCESSING)
                    .event(OrderEvent.PROCESS)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository))

                    .and()
                    .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.SHIPPED)
                    .event(OrderEvent.SHIP)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository))

                    .and()
                    .withExternal()
                    .source(OrderState.SHIPPED).target(OrderState.DELIVERED)
                    .event(OrderEvent.DELIVER)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository))

                    .and()
                    .withExternal()
                    .source(OrderState.CREATED).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository))

                    .and()
                    .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL)
                    .action(createOrderState(orderRepository, orderStatusForOrderRepository));
        }

        @Bean
        public Action<OrderState, OrderEvent> createOrderState(OrderRepository orderRepository, OrderStatusForOrderRepository orderStatusForOrderRepository) {
            return ctx -> {
                Long orderId = ctx.getExtendedState().get("ORDER_ID", Long.class);
                Order order = orderRepository.getReferenceById(orderId);
                OrderStatusForOrder orderStatusForOrder = OrderStatusForOrder.builder()
                        .order(order)
                        .state(ctx.getTarget().getId())
                        .regDate(LocalDateTime.now())
                        .build();
                orderStatusForOrderRepository.save(orderStatusForOrder);
            };
        }

        @Configuration
        public static class ServiceConfigForDonor {
            @Bean
            public StateMachineService<OrderState, OrderEvent> stateMachineForDonorService(
                    StateMachineFactory<OrderState, OrderEvent> stateMachineFactory,
                    StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister) {
                return new DefaultStateMachineService<>(stateMachineFactory, stateMachineRuntimePersister);
            }
        }

    }
}
