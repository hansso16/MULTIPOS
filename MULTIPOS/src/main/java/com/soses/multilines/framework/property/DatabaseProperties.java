package com.soses.multilines.framework.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/properties/${spring_profiles_active}/database.properties")
public class DatabaseProperties {

	@Value("${db.driverClassName}")
    protected String driverClassName;

    /** The host. */
    @Value("${db.host}")
    protected String host;

    /** The port. */
    @Value("${db.port}")
    protected String port;

    /** The db name. */
    @Value("${db.dbName}")
    protected String dbName;

    /** The username. */
    @Value("${db.username}")
    protected String username;

    /** The password. */
    @Value("${db.password}")
    protected String password;

    /** The max pool size. */
    @Value("${db.maxPoolSize}")
    protected int maxPoolSize;

    /** The minimum idle. */
    @Value("${db.minimumIdle}")
    protected int minimumIdle;

    /** The idle timeout. */
    @Value("${db.idleTimeout}")
    protected int idleTimeout;

    /** The connection test query. */
    @Value("${db.connectionTestQuery}")
    protected String connectionTestQuery;

    /** The pool name. */
    @Value("${db.poolName}")
    protected String poolName;

    /** The cache prep stmts. */
    @Value("${db.cachePrepStmts}")
    protected boolean cachePrepStmts;

    /** The prep stmt cache size. */
    @Value("${db.prepStmtCacheSize}")
    protected int prepStmtCacheSize;

    /** The prep stmt cache sql limit. */
    @Value("${db.prepStmtCacheSqlLimit}")
    protected int prepStmtCacheSqlLimit;

    /** The use server prep stmts. */
    @Value("${db.useServerPrepStmts}")
    protected boolean useServerPrepStmts;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getMinimumIdle() {
		return minimumIdle;
	}

	public void setMinimumIdle(int minimumIdle) {
		this.minimumIdle = minimumIdle;
	}

	public int getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	public String getConnectionTestQuery() {
		return connectionTestQuery;
	}

	public void setConnectionTestQuery(String connectionTestQuery) {
		this.connectionTestQuery = connectionTestQuery;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public boolean isCachePrepStmts() {
		return cachePrepStmts;
	}

	public void setCachePrepStmts(boolean cachePrepStmts) {
		this.cachePrepStmts = cachePrepStmts;
	}

	public int getPrepStmtCacheSize() {
		return prepStmtCacheSize;
	}

	public void setPrepStmtCacheSize(int prepStmtCacheSize) {
		this.prepStmtCacheSize = prepStmtCacheSize;
	}

	public int getPrepStmtCacheSqlLimit() {
		return prepStmtCacheSqlLimit;
	}

	public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
		this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
	}

	public boolean isUseServerPrepStmts() {
		return useServerPrepStmts;
	}

	public void setUseServerPrepStmts(boolean useServerPrepStmts) {
		this.useServerPrepStmts = useServerPrepStmts;
	}
    
    
}
