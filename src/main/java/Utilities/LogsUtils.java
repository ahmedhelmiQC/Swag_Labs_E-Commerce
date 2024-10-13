package Utilities;

import java.util.logging.LogManager;

public class LogsUtils {
    public static String LOGS_PATH = "test-outputs/logs";
    public static void Strace(String message){
        LogManager.getLogManager(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
    }
}
