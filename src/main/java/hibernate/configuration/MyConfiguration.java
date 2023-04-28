package hibernate.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;

/**
 * Configuration file, replaces hibernate.cfg.xml and web.xml.
 * Set up Session factory bean, EntityManager bean and data source for DB connection.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "hibernate")
@EnableTransactionManagement
public class MyConfiguration implements WebMvcConfigurer {

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        Properties hibernateProperties = new Properties();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("hibernate");

        hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.show_sql","true");
        hibernateProperties.put("hibernate.hbm2ddl.auto","update");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/mybd");
            dataSource.setUser("postgres");
            dataSource.setPassword("admin");
        return dataSource;
    }



    @Bean
    public HibernateTransactionManager transactionManager() throws PropertyVetoException {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                mapper.registerModule(new Hibernate5Module());
            }
        }
    }

}
