package com.example.hotelbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "hotelAsyncExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);          // Базова кількість потоків
        executor.setMaxPoolSize(4);           // Максимальна кількість потоків
        executor.setQueueCapacity(10);        // Місткість черги завдань
        executor.setThreadNamePrefix("HotelAsync-"); // Префікс для перевірки в логах

        // Стратегія при переповненні черги (CallerRunsPolicy виконує завдання в потоці викликаючого коду)
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }
}