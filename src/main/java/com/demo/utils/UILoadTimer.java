package com.demo.utils;

import io.qameta.allure.Allure;

public class UILoadTimer {
    public static void timerLog(String stepName, Runnable action) {
        long start = System.currentTimeMillis();
        try {
            action.run();
        } catch (Exception e) {
            Allure.addAttachment(stepName + " - FAILED", e.getMessage());
        }
        long duration = System.currentTimeMillis() - start;
        String message = stepName + " took " + duration + " ms";
        System.out.println("time " + message);
        Allure.addAttachment(stepName, duration + " ms");
    }
}
