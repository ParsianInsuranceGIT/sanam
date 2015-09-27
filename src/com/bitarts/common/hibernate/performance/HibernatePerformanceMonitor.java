package com.bitarts.common.hibernate.performance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: navit
 * Date: 1/31/12
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernatePerformanceMonitor {private static final Log log = LogFactory.getLog(HibernatePerformanceMonitor.class);

    private ConcurrentMap<Long, HibernateStatisticsStopWatch> watches;
    private AtomicLong idGenerator = new AtomicLong(0);
    private static HibernatePerformanceMonitor singleton =
        new HibernatePerformanceMonitor();

    private HibernatePerformanceMonitor() {
        watches = new ConcurrentHashMap<Long, HibernateStatisticsStopWatch>();
    }

    public static HibernatePerformanceMonitor get() {
        return singleton;
    }

    public long start() {
        if (log.isDebugEnabled()) {
            HibernateStatisticsStopWatch watch = new HibernateStatisticsStopWatch();
            long id = idGenerator.incrementAndGet();
            watches.put(id, watch);
            watch.start();
            return id;
        }
        return 0;
    }

    public void stop(long id, String callingContext) {
        if (log.isDebugEnabled()) {
            HibernateStatisticsStopWatch watch = watches.remove(id);
            if (watch == null) {
                // could happen if debugging was enabled after start() was called
                return;
            }
            watch.stop();
            String caller = (callingContext == null ? "(unknown)" : callingContext);
            log.debug(watch.toString() + " for " + caller);
        }
    }
}
