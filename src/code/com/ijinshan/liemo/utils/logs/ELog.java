package com.ijinshan.liemo.utils.logs;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ELog {
    private static final String LOGTAG = "ELog";
    private static final Logger LOGGER = Logger.getLogger("com.elog");
    private static final ELogHandler LOG_HANDLER = new ELogHandler();
    public static boolean isDebug = Log.isLoggable(LOGTAG, Log.VERBOSE);

    static {
        LogManager.getLogManager().addLogger(LOGGER);
        LOGGER.addHandler(LOG_HANDLER);
        LOGGER.setLevel(Level.FINE);
    }

    private ELog() {}

    public static void c(final String message) {
        if(isDebug) {
            ELog.c(message, null);
        }
    }

    public static void v(final String message) {
        if(isDebug) {
            ELog.v(message, null);
        }
    }

    public static void d(final String message) {
        if(isDebug) {
            ELog.d(message, null);
        }
    }

    public static void i(final String message) {
        if(isDebug) {
            ELog.i(message, null);
        }
    }

    public static void w(final String message) {
        if(isDebug) {
            ELog.w(message, null);
        }
    }

    public static void e(final String message) {
        if(isDebug) {
            ELog.e(message, null);
        }
    }

    public static void c(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.FINEST, message, throwable);
        }
    }

    public static void v(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.FINE, message, throwable);
        }
    }

    public static void d(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.CONFIG, message, throwable);
        }
    }

    public static void i(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.INFO, message, throwable);
        }
    }

    public static void w(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.WARNING, message, throwable);
        }
    }

    public static void e(final String message, final Throwable throwable) {
        if(isDebug) {
            LOGGER.log(Level.SEVERE, message, throwable);
        }
    }

    private static final class ELogHandler extends Handler {
        private static final Map<Level, Integer> LEVEL_TO_LOG = new HashMap<Level, Integer>(7);

        /*
         * Mapping between Level.* and Log.*:
         * Level.FINEST  => Log.v
         * Level.FINER   => Log.v
         * Level.FINE    => Log.v
         * Level.CONFIG  => Log.d
         * Level.INFO    => Log.i
         * Level.WARNING => Log.w
         * Level.SEVERE  => Log.e
         */
        static {
            LEVEL_TO_LOG.put(Level.FINEST, Log.VERBOSE);
            LEVEL_TO_LOG.put(Level.FINER, Log.VERBOSE);
            LEVEL_TO_LOG.put(Level.FINE, Log.VERBOSE);
            LEVEL_TO_LOG.put(Level.CONFIG, Log.DEBUG);
            LEVEL_TO_LOG.put(Level.INFO, Log.INFO);
            LEVEL_TO_LOG.put(Level.WARNING, Log.WARN);
            LEVEL_TO_LOG.put(Level.SEVERE, Log.ERROR);
        }

        @SuppressLint("LogTagMismatch")
        @Override
        public void publish(final LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                final int priority;
                if (LEVEL_TO_LOG.containsKey(logRecord.getLevel())) {
                    priority = LEVEL_TO_LOG.get(logRecord.getLevel());
                } else {
                    priority = Log.VERBOSE;
                }

                String message = logRecord.getMessage() + "\n";

                final Throwable error = logRecord.getThrown();
                if (error != null) {
                    message += Log.getStackTraceString(error);
                }

                Log.println(priority, LOGTAG, message);
            }
        }

        @Override public void close() {}

        @Override public void flush() {}
    }
}
