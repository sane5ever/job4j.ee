package ru.job4j.jobseeker.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.jobseeker.dao.JdbiConfigurator;
import ru.job4j.jobseeker.dao.TaskDao;
import ru.job4j.jobseeker.dao.UserDao;
import ru.job4j.jobseeker.dao.VacancyDao;

import javax.inject.Singleton;
import javax.sql.DataSource;

@Slf4j
public class DaoModule extends AbstractModule {
    private final DataSource dataSource;

    public DaoModule(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure() {
        bind(JdbiConfigurator.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    protected DataSource provideDataSource() {
        return dataSource;
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(JdbiConfigurator configurator) {
        return configurator.getDao(UserDao.class);
    }

    @Provides
    @Singleton
    public TaskDao provideTaskDao(JdbiConfigurator configurator) {
        return configurator.getDao(TaskDao.class);
    }

    @Provides
    @Singleton
    public VacancyDao provideVacancyDao(JdbiConfigurator configurator) {
        return configurator.getDao(VacancyDao.class);
    }
}
