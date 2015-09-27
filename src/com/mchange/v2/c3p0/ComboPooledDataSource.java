package com.mchange.v2.c3p0;

import com.bitarts.parsian.security.SecurityUtil;
import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import javax.naming.Referenceable;
import javax.sql.DataSource;

public final class ComboPooledDataSource
        extends AbstractPoolBackedDataSource
        implements PooledDataSource, Serializable, Referenceable
{
    static final MLogger logger = MLog.getLogger(ComboPooledDataSource.class);
    static final Set TO_STRING_IGNORE_PROPS = new HashSet(Arrays.asList(new String[] { "connection", "lastCheckinFailureDefaultUser", "lastCheckoutFailureDefaultUser", "lastConnectionTestFailureDefaultUser", "lastIdleTestFailureDefaultUser", "logWriter", "loginTimeout", "numBusyConnections", "numBusyConnectionsAllUsers", "numBusyConnectionsDefaultUser", "numConnections", "numConnectionsAllUsers", "numConnectionsDefaultUser", "numFailedCheckinsDefaultUser", "numFailedCheckoutsDefaultUser", "numFailedIdleTestsDefaultUser", "numIdleConnections", "numIdleConnectionsAllUsers", "numIdleConnectionsDefaultUser", "numUnclosedOrphanedConnections", "numUnclosedOrphanedConnectionsAllUsers", "numUnclosedOrphanedConnectionsDefaultUser", "numUserPools", "effectivePropertyCycleDefaultUser", "startTimeMillisDefaultUser", "statementCacheNumCheckedOutDefaultUser", "statementCacheNumCheckedOutStatementsAllUsers", "statementCacheNumConnectionsWithCachedStatementsAllUsers", "statementCacheNumConnectionsWithCachedStatementsDefaultUser", "statementCacheNumStatementsAllUsers", "statementCacheNumStatementsDefaultUser", "threadPoolSize", "threadPoolNumActiveThreads", "threadPoolNumIdleThreads", "threadPoolNumTasksPending", "threadPoolStackTraces", "threadPoolStatus", "overrideDefaultUser", "overrideDefaultPassword", "password", "reference", "upTimeMillisDefaultUser", "user", "userOverridesAsString", "allUsers", "connectionPoolDataSource" }));
    transient DriverManagerDataSource dmds;
    transient WrapperConnectionPoolDataSource wcpds;
    private static final long serialVersionUID = 1L;
    private static final short VERSION = 1;

    public ComboPooledDataSource()
    {
        this(true);
    }

    public ComboPooledDataSource(boolean autoregister)
    {
        super(autoregister);



        this.dmds = new DriverManagerDataSource();
        this.wcpds = new WrapperConnectionPoolDataSource();

        this.wcpds.setNestedDataSource(this.dmds);
        try
        {
            setConnectionPoolDataSource(this.wcpds);
        }
        catch (PropertyVetoException e)
        {
            logger.log(MLevel.WARNING, "Hunh??? This can't happen. We haven't set up any listeners to veto the property change yet!", e);
            throw new RuntimeException("Hunh??? This can't happen. We haven't set up any listeners to veto the property change yet! " + e);
        }
        setUpPropertyEvents();
    }

    private void setUpPropertyEvents()
    {
        VetoableChangeListener wcpdsConsistencyEnforcer = new VetoableChangeListener()
        {
            public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException
            {
                String propName = evt.getPropertyName();
                Object val = evt.getNewValue();
                if ("connectionPoolDataSource".equals(propName)) {
                    if ((val instanceof WrapperConnectionPoolDataSource))
                    {
                        DataSource nested = ((WrapperConnectionPoolDataSource)val).getNestedDataSource();
                        if (!(nested instanceof DriverManagerDataSource)) {
                            throw new PropertyVetoException("ComboPooledDataSource requires that its unpooled DataSource  be set at all times, and that it be a com.mchange.v2.c3p0.DriverManagerDataSource. Bad: " + nested, evt);
                        }
                    }
                    else
                    {
                        throw new PropertyVetoException("ComboPooledDataSource requires that its ConnectionPoolDataSource  be set at all times, and that it be a com.mchange.v2.c3p0.WrapperConnectionPoolDataSource. Bad: " + val, evt);
                    }
                }
            }
        };
        addVetoableChangeListener(wcpdsConsistencyEnforcer);

        PropertyChangeListener wcpdsStateUpdater = new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent evt)
            {
                ComboPooledDataSource.this.updateLocalVarsFromCpdsProp();
            }
        };
        addPropertyChangeListener(wcpdsStateUpdater);
    }

    private void updateLocalVarsFromCpdsProp()
    {
        this.wcpds = ((WrapperConnectionPoolDataSource)getConnectionPoolDataSource());
        this.dmds = ((DriverManagerDataSource)this.wcpds.getNestedDataSource());
    }

    public ComboPooledDataSource(String configName)
    {
        this();
        initializeNamedConfig(configName);
    }

    public String getDescription()
    {
        return this.dmds.getDescription();
    }

    public void setDescription(String description)
    {
        this.dmds.setDescription(description);
    }

    public String getDriverClass()
    {
        return this.dmds.getDriverClass();
    }

    public void setDriverClass(String driverClass)
            throws PropertyVetoException
    {
        this.dmds.setDriverClass(driverClass);
    }

    public String getJdbcUrl()
    {
        return this.dmds.getJdbcUrl();
    }

    public void setJdbcUrl(String jdbcUrl)
    {
        this.dmds.setJdbcUrl(jdbcUrl);
        resetPoolManager(false);
    }

    public Properties getProperties()
    {
        return this.dmds.getProperties();
    }

    public void setProperties(Properties properties)
    {
        this.dmds.setProperties(properties);
        resetPoolManager(false);
    }

    public String getUser()
    {
        return this.dmds.getUser();
    }

    public void setUser(String user)
    {
        this.dmds.setUser(user);
        resetPoolManager(false);
    }

    public String getPassword()
    {
        return this.dmds.getPassword();
    }

    public void setPassword(String password)
    {
        this.dmds.setPassword(SecurityUtil.decrypt(password));
        resetPoolManager(false);
    }

    public int getCheckoutTimeout()
    {
        return this.wcpds.getCheckoutTimeout();
    }

    public void setCheckoutTimeout(int checkoutTimeout)
    {
        this.wcpds.setCheckoutTimeout(checkoutTimeout);
        resetPoolManager(false);
    }

    public int getAcquireIncrement()
    {
        return this.wcpds.getAcquireIncrement();
    }

    public void setAcquireIncrement(int acquireIncrement)
    {
        this.wcpds.setAcquireIncrement(acquireIncrement);
        resetPoolManager(false);
    }

    public int getAcquireRetryAttempts()
    {
        return this.wcpds.getAcquireRetryAttempts();
    }

    public void setAcquireRetryAttempts(int acquireRetryAttempts)
    {
        this.wcpds.setAcquireRetryAttempts(acquireRetryAttempts);
        resetPoolManager(false);
    }

    public int getAcquireRetryDelay()
    {
        return this.wcpds.getAcquireRetryDelay();
    }

    public void setAcquireRetryDelay(int acquireRetryDelay)
    {
        this.wcpds.setAcquireRetryDelay(acquireRetryDelay);
        resetPoolManager(false);
    }

    public boolean isAutoCommitOnClose()
    {
        return this.wcpds.isAutoCommitOnClose();
    }

    public void setAutoCommitOnClose(boolean autoCommitOnClose)
    {
        this.wcpds.setAutoCommitOnClose(autoCommitOnClose);
        resetPoolManager(false);
    }

    public String getConnectionTesterClassName()
    {
        return this.wcpds.getConnectionTesterClassName();
    }

    public void setConnectionTesterClassName(String connectionTesterClassName)
            throws PropertyVetoException
    {
        this.wcpds.setConnectionTesterClassName(connectionTesterClassName);
        resetPoolManager(false);
    }

    public String getAutomaticTestTable()
    {
        return this.wcpds.getAutomaticTestTable();
    }

    public void setAutomaticTestTable(String automaticTestTable)
    {
        this.wcpds.setAutomaticTestTable(automaticTestTable);
        resetPoolManager(false);
    }

    public boolean isForceIgnoreUnresolvedTransactions()
    {
        return this.wcpds.isForceIgnoreUnresolvedTransactions();
    }

    public void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions)
    {
        this.wcpds.setForceIgnoreUnresolvedTransactions(forceIgnoreUnresolvedTransactions);
        resetPoolManager(false);
    }

    public int getIdleConnectionTestPeriod()
    {
        return this.wcpds.getIdleConnectionTestPeriod();
    }

    public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod)
    {
        this.wcpds.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        resetPoolManager(false);
    }

    public int getInitialPoolSize()
    {
        return this.wcpds.getInitialPoolSize();
    }

    public void setInitialPoolSize(int initialPoolSize)
    {
        this.wcpds.setInitialPoolSize(initialPoolSize);
        resetPoolManager(false);
    }

    public int getMaxIdleTime()
    {
        return this.wcpds.getMaxIdleTime();
    }

    public void setMaxIdleTime(int maxIdleTime)
    {
        this.wcpds.setMaxIdleTime(maxIdleTime);
        resetPoolManager(false);
    }

    public int getMaxPoolSize()
    {
        return this.wcpds.getMaxPoolSize();
    }

    public void setMaxPoolSize(int maxPoolSize)
    {
        this.wcpds.setMaxPoolSize(maxPoolSize);
        resetPoolManager(false);
    }

    public int getMaxStatements()
    {
        return this.wcpds.getMaxStatements();
    }

    public void setMaxStatements(int maxStatements)
    {
        this.wcpds.setMaxStatements(maxStatements);
        resetPoolManager(false);
    }

    public int getMaxStatementsPerConnection()
    {
        return this.wcpds.getMaxStatementsPerConnection();
    }

    public void setMaxStatementsPerConnection(int maxStatementsPerConnection)
    {
        this.wcpds.setMaxStatementsPerConnection(maxStatementsPerConnection);
        resetPoolManager(false);
    }

    public int getMinPoolSize()
    {
        return this.wcpds.getMinPoolSize();
    }

    public void setMinPoolSize(int minPoolSize)
    {
        this.wcpds.setMinPoolSize(minPoolSize);
        resetPoolManager(false);
    }

    public String getOverrideDefaultUser()
    {
        return this.wcpds.getOverrideDefaultUser();
    }

    public void setOverrideDefaultUser(String overrideDefaultUser)
    {
        this.wcpds.setOverrideDefaultUser(overrideDefaultUser);
        resetPoolManager(false);
    }

    public String getOverrideDefaultPassword()
    {
        return this.wcpds.getOverrideDefaultPassword();
    }

    public void setOverrideDefaultPassword(String overrideDefaultPassword)
    {
        this.wcpds.setOverrideDefaultPassword(overrideDefaultPassword);
        resetPoolManager(false);
    }

    public int getPropertyCycle()
    {
        return this.wcpds.getPropertyCycle();
    }

    public void setPropertyCycle(int propertyCycle)
    {
        this.wcpds.setPropertyCycle(propertyCycle);
        resetPoolManager(false);
    }

    public boolean isBreakAfterAcquireFailure()
    {
        return this.wcpds.isBreakAfterAcquireFailure();
    }

    public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure)
    {
        this.wcpds.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
        resetPoolManager(false);
    }

    public boolean isTestConnectionOnCheckout()
    {
        return this.wcpds.isTestConnectionOnCheckout();
    }

    public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout)
    {
        this.wcpds.setTestConnectionOnCheckout(testConnectionOnCheckout);
        resetPoolManager(false);
    }

    public boolean isTestConnectionOnCheckin()
    {
        return this.wcpds.isTestConnectionOnCheckin();
    }

    public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin)
    {
        this.wcpds.setTestConnectionOnCheckin(testConnectionOnCheckin);
        resetPoolManager(false);
    }

    public boolean isUsesTraditionalReflectiveProxies()
    {
        return this.wcpds.isUsesTraditionalReflectiveProxies();
    }

    public void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies)
    {
        this.wcpds.setUsesTraditionalReflectiveProxies(usesTraditionalReflectiveProxies);
        resetPoolManager(false);
    }

    public String getPreferredTestQuery()
    {
        return this.wcpds.getPreferredTestQuery();
    }

    public void setPreferredTestQuery(String preferredTestQuery)
    {
        this.wcpds.setPreferredTestQuery(preferredTestQuery);
        resetPoolManager(false);
    }

    public String getUserOverridesAsString()
    {
        return this.wcpds.getUserOverridesAsString();
    }

    public void setUserOverridesAsString(String userOverridesAsString)
            throws PropertyVetoException
    {
        this.wcpds.setUserOverridesAsString(userOverridesAsString);
        resetPoolManager(false);
    }

    public int getMaxAdministrativeTaskTime()
    {
        return this.wcpds.getMaxAdministrativeTaskTime();
    }

    public void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime)
    {
        this.wcpds.setMaxAdministrativeTaskTime(maxAdministrativeTaskTime);
        resetPoolManager(false);
    }

    public int getMaxIdleTimeExcessConnections()
    {
        return this.wcpds.getMaxIdleTimeExcessConnections();
    }

    public void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections)
    {
        this.wcpds.setMaxIdleTimeExcessConnections(maxIdleTimeExcessConnections);
        resetPoolManager(false);
    }

    public int getMaxConnectionAge()
    {
        return this.wcpds.getMaxConnectionAge();
    }

    public void setMaxConnectionAge(int maxConnectionAge)
    {
        this.wcpds.setMaxConnectionAge(maxConnectionAge);
        resetPoolManager(false);
    }

    public String getConnectionCustomizerClassName()
    {
        return this.wcpds.getConnectionCustomizerClassName();
    }

    public void setConnectionCustomizerClassName(String connectionCustomizerClassName)
    {
        this.wcpds.setConnectionCustomizerClassName(connectionCustomizerClassName);
        resetPoolManager(false);
    }

    public int getUnreturnedConnectionTimeout()
    {
        return this.wcpds.getUnreturnedConnectionTimeout();
    }

    public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout)
    {
        this.wcpds.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);
        resetPoolManager(false);
    }

    public boolean isDebugUnreturnedConnectionStackTraces()
    {
        return this.wcpds.isDebugUnreturnedConnectionStackTraces();
    }

    public void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces)
    {
        this.wcpds.setDebugUnreturnedConnectionStackTraces(debugUnreturnedConnectionStackTraces);
        resetPoolManager(false);
    }

    public String getFactoryClassLocation()
    {
        return super.getFactoryClassLocation();
    }

    public void setFactoryClassLocation(String factoryClassLocation)
    {
        this.dmds.setFactoryClassLocation(factoryClassLocation);
        this.wcpds.setFactoryClassLocation(factoryClassLocation);
        super.setFactoryClassLocation(factoryClassLocation);
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(512);
        sb.append(getClass().getName());
        sb.append(" [ ");
        try
        {
            BeansUtils.appendPropNamesAndValues(sb, this, TO_STRING_IGNORE_PROPS);
        }
        catch (Exception e)
        {
            sb.append(e.toString());
        }
        sb.append(" ]");





        return sb.toString();
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException
    {
        oos.writeShort(1);
    }

    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException
    {
        short version = ois.readShort();
        switch (version)
        {
            case 1:
                updateLocalVarsFromCpdsProp();
                setUpPropertyEvents();
                break;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
