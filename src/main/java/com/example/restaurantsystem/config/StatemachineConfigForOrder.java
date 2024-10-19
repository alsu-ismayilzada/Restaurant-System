package com.example.restaurantsystem.config;

import com.example.restaurantsystem.enums.OrderEvent;
import com.example.restaurantsystem.enums.OrderState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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


import java.util.EnumSet;

@Configuration
@Slf4j
public class StatemachineConfigForOrder extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Configuration
    public static class JpaPersisterConfigForDonor {
        @Bean
        @Primary
        public StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersisterForDonor(JpaStateMachineRepository jpaStateMachineRepository) {
            return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
        }
    }
    @Configuration
    @EnableStateMachineFactory()
    @RequiredArgsConstructor
    public static class MachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

        @Qualifier("stateMachineRuntimePersisterForDonor")
        private final StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister;


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
                    .initial(OrderState.NEW)
                    .states(EnumSet.allOf(OrderState.class))
                    .end(OrderState.DELIVERED)
                    .end(OrderState.CANCELLED);
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
            transitions
                    .withExternal()
                    .source(OrderState.NEW).target(OrderState.CREATED)
                    .event(OrderEvent.CREATE)

                    .and()
                    .withExternal()
                    .source(OrderState.CREATED).target(OrderState.PROCESSING)
                    .event(OrderEvent.PROCESS)

                    .and()
                    .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.SHIPPED)
                    .event(OrderEvent.SHIP)

                    .and()
                    .withExternal()
                    .source(OrderState.SHIPPED).target(OrderState.DELIVERED)
                    .event(OrderEvent.DELIVER)

                    .and()
                    .withExternal()
                    .source(OrderState.NEW).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL)

                    .and()
                    .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL);
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

//        @Bean
//        @Qualifier("jpaStateMachineRuntimePersister")
//        public StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister(JpaStateMachineRepository jpaStateMachineRepository) {
//            return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
//        }

//    @Bean
//    public StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister() {
//        return new JpaStateMachineRuntimePersister();
//    }

//        @Bean
//        public StateMachineService<OrderState, OrderEvent> stateMachineService(
//                StateMachineFactory<OrderState, OrderEvent> stateMachineFactory,
//                @Qualifier("jpaStateMachineRuntimePersister") StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister) {
//            return new DefaultStateMachineService<>(stateMachineFactory, stateMachineRuntimePersister);
//        }

//    @Bean
//    public StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister() {
//        return new JpaStateMachineRuntimePersister();
//    }
    }


}

