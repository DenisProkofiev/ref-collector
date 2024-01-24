package ru.hellforge.refcollector.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static java.lang.Integer.parseInt;
import static ru.hellforge.refcollector.util.BaseOperationService.isArgumentExist;

@Configuration
public class ApplicationStartConfiguration {
//    @Bean
//    public void setApplicationServerPort(ApplicationArguments arguments, ConfigurableWebServerFactory webServerFactory) {
//        int port = isArgumentExist(arguments.getSourceArgs(), 0) ? parseInt(arguments.getSourceArgs()[1])
//                : 8090;
//
//        webServerFactory.setPort(port);
//    }

    @Bean
    public DataSource setDataBaseSource(ApplicationArguments arguments) {
        String dataSource = isArgumentExist(arguments.getSourceArgs(), 1) ? arguments.getSourceArgs()[1]
                : "jdbc:h2:file:C:/Users/Public/refcollector/rcdb.mv.db";

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.username("den");
        dataSourceBuilder.password("");
        dataSourceBuilder.url(dataSource);
        return dataSourceBuilder.build();
    }
}