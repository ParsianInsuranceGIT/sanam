package com.bitarts.common.hibernate.performance;

import org.hibernate.stat.Statistics;

/**
 * Created by IntelliJ IDEA.
 * User: navit
 * Date: 1/31/12
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateStatisticsStopWatch {

    private org.hibernate.stat.Statistics stats;

    // some interesting metrics
    long queryExecutions;
    long transactions;
    long entityLoads;
    long connects;
    long time;

    public HibernateStatisticsStopWatch() {
//        this.stats = // get statistics service from the MBeanServer
    }

    public void start() {
        queryExecutions = -stats.getQueryExecutionCount();
        transactions = -stats.getTransactionCount();
        entityLoads = -stats.getEntityLoadCount();
        connects = -stats.getConnectCount();
        time = -System.currentTimeMillis();
    }

    public void stop() {
        queryExecutions += stats.getQueryExecutionCount();
        transactions += stats.getTransactionCount();
        entityLoads += stats.getEntityLoadCount();
        connects += stats.getConnectCount();
        time += System.currentTimeMillis();
    }

    // getter methods for various delta metrics

    public String toString() {
        return "Stats"
            + "[ queries=" + queryExecutions
            + ", xactions=" + transactions
            + ", loads=" + entityLoads
            + ", connects=" + connects
            + ", time=" + time + " ]";
    }
}
