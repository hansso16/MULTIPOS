package com.soses.multilines.framework.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * The Class HibernateProperties.
 *
 * @author hso
 * @since Dec 21, 2021
 */
@Configuration
@PropertySource("classpath:/properties/${spring_profiles_active}/hibernate.properties")
public class HibernateProperties {

	/** The dialect. */
    @Value("${hb.dialect}")
    protected String dialect;

    /** The show sql. */
    @Value("${hb.showSql}")
    protected boolean showSql;

    /** The hbm 2 ddl auto. */
    @Value("${hb.hbm2ddl.auto}")
    protected String hbm2DdlAuto;

    /** The storage engine. */
    @Value("${hb.dialect.storage_engine}")
    protected String storageEngine;
    
    /** The enable lazy load no trans. */
    @Value("${hb.enable_lazy_load_no_trans}")
    protected String enableLazyLoadNoTrans;

    /** The packages to scan. */
    @Value("${hb.entitymanager.packagesToScan}")
    protected String packagesToScan;

    /** Entity manager factory persistence name. */
    @Value("${hb.entitymanager.persistenceUnitName}")
    protected String persistenceUnitName;
    
    /** The generate statistics. */
    @Value("${hb.generate_statistics}")
    protected boolean generateStatistics;
    
    /** The batch size. */
    @Value("${hb.jdbc.batch_size}")
    protected String batchSize;
    
    /** The order inserts. */
    @Value("${hb.order_inserts}")
    protected boolean orderInserts;
    

	/**
	 * Checks if is generate statistics.
	 *
	 * @return true, if is generate statistics
	 */
	public boolean isGenerateStatistics() {
		return generateStatistics;
	}

	/**
	 * Gets the batch size.
	 *
	 * @return the batch size
	 */
	public String getBatchSize() {
		return batchSize;
	}

	/**
	 * Checks if is order inserts.
	 *
	 * @return true, if is order inserts
	 */
	public boolean isOrderInserts() {
		return orderInserts;
	}

	/**
	 * Gets the dialect.
	 *
	 * @return the dialect
	 */
	public String getDialect() {
		return dialect;
	}

	/**
	 * Checks if is show sql.
	 *
	 * @return true, if is show sql
	 */
	public boolean isShowSql() {
		return showSql;
	}

	/**
	 * Gets the hbm 2 ddl auto.
	 *
	 * @return the hbm 2 ddl auto
	 */
	public String getHbm2DdlAuto() {
		return hbm2DdlAuto;
	}

	/**
	 * Gets the storage engine.
	 *
	 * @return the storage engine
	 */
	public String getStorageEngine() {
		return storageEngine;
	}

	/**
	 * Gets the enable lazy load no trans.
	 *
	 * @return the enable lazy load no trans
	 */
	public String getEnableLazyLoadNoTrans() {
		return enableLazyLoadNoTrans;
	}

	/**
	 * Gets the packages to scan.
	 *
	 * @return the packages to scan
	 */
	public String getPackagesToScan() {
		return packagesToScan;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "HibernateProperties [dialect=" + dialect + ", showSql=" + showSql + ", hbm2DdlAuto=" + hbm2DdlAuto
				+ ", storageEngine=" + storageEngine + ", enableLazyLoadNoTrans=" + enableLazyLoadNoTrans
				+ ", packagesToScan=" + packagesToScan + ", persistenceUnitName=" + persistenceUnitName
				+ ", generateStatistics=" + generateStatistics + ", batchSize=" + batchSize + ", orderInserts="
				+ orderInserts + "]";
	}

	/**
	 * Gets the persistence unit name.
	 *
	 * @return the persistence unit name
	 */
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

    
}
