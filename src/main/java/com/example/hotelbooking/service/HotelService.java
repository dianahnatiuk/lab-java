package com.example.hotelbooking.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class HotelService {

    private final AsyncReportService asyncReportService;

    // Якщо ви використовуєте конструктор для ін'єкцій:
    public HotelService(AsyncReportService asyncReportService) {
        this.asyncReportService = asyncReportService;
    }

    // Метод для демонстрації паралельної обробки (вимоги лабораторної)
    public String processAsyncHotelOperations(Long hotelId, String email) {
        // 1. Fire-and-forget виклик
        asyncReportService.sendEmailNotification(email, "Розпочато обробку даних готелю!");

        // 2. Паралельний запуск двох повільних завдань із захистом від винятків (exceptionally)
        CompletableFuture<String> pdfFuture = asyncReportService.generatePdfReport(hotelId)
                .exceptionally(ex -> "Звіт тимчасово недоступний (fallback)");

        CompletableFuture<String> externalFuture = asyncReportService.fetchExternalServiceData()
                .exceptionally(ex -> "Зовнішні дані відсутні (fallback)");

        // 3. Об'єднання результатів за допомогою thenCombine
        CompletableFuture<String> combinedResult = pdfFuture.thenCombine(externalFuture, (pdf, extData) -> {
            return "Успішно об'єднано -> [" + pdf + "] та [" + extData + "]";
        });

        try {
            // 4. Очікування результату з обмеженням часу (таймаут 3 секунди)
            return combinedResult.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            return "Помилка або перевищено ліміт часу очікування асинхронних завдань.";
        }
    }
}