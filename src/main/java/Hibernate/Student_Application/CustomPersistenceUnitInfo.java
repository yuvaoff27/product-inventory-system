package Hibernate.Student_Application;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map; // Added this missing import
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    private final Map<String, String> props;

    public CustomPersistenceUnitInfo(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public String getPersistenceUnitName() { return "dynamic-unit"; }

    @Override
    public String getPersistenceProviderClassName() { return "org.hibernate.jpa.HibernatePersistenceProvider"; }

    @Override
    public PersistenceUnitTransactionType getTransactionType() { return PersistenceUnitTransactionType.RESOURCE_LOCAL; }

    @Override
    public List<String> getManagedClassNames() {
        List<String> classes = new ArrayList<>();
        classes.add("Hibernate.Student_Application.Product");
        return classes;
    }

    @Override
    public Properties getProperties() {
        Properties p = new Properties();
        p.putAll(props);
        return p;
    }

    @Override public DataSource getJtaDataSource() { return null; }
    @Override public DataSource getNonJtaDataSource() { return null; }
    @Override public List<String> getMappingFileNames() { return Collections.emptyList(); }
    @Override public List<URL> getJarFileUrls() { return Collections.emptyList(); }
    @Override public URL getPersistenceUnitRootUrl() { return null; }
    @Override public boolean excludeUnlistedClasses() { return true; }
    @Override public SharedCacheMode getSharedCacheMode() { return SharedCacheMode.UNSPECIFIED; }
    @Override public ValidationMode getValidationMode() { return ValidationMode.AUTO; }
    @Override public String getPersistenceXMLSchemaVersion() { return "3.0"; }
    @Override public ClassLoader getClassLoader() { return Thread.currentThread().getContextClassLoader(); }
    @Override public void addTransformer(ClassTransformer transformer) {}
    @Override public ClassLoader getNewTempClassLoader() { return null; }
}
