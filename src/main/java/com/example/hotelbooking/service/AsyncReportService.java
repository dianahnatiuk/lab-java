package com.example.hotelbooking.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncReportService {

    // 1. «Запустив і забув» (Fire-and-forget)
    @Async("hotelAsyncExecutor")
    public void sendEmailNotification(String email, String message) {
        try {
            Thread.sleep(1000); // Імітація затримки ~1 сек
            System.out.println("-> [Email] Лист надіслано на потоці: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 2. Повільний метод генерації PDF (повертає CompletableFuture)
    @Async("hotelAsyncExecutor")
    public CompletableFuture<String> generatePdfReport(Long hotelId) {
        try {
            Thread.sleep(1200); // Імітація важкої операції
            System.out.println("-> [PDF] Згенеровано на потоці: " + Thread.currentThread().getName());
            return CompletableFuture.completedFuture("PDF-звіт для готелю #" + hotelId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(new RuntimeException("Помилка генерації PDF"));
        }
    }

    // 3. Повільний виклик зовнішнього сервісу
    @Async("hotelAsyncExecutor")
    public CompletableFuture<String> fetchExternalServiceData() {
        try {
            Thread.sleep(800);
            System.out.println("-> [External] Дані отримано на потоці: " + Thread.currentThread().getName());
            return CompletableFuture.completedFuture("Дані від зовнішнього провайдера");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(new RuntimeException("Зовнішній сервіс недоступний"));
        }
    }
}